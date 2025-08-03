package com.example.jaksaapp.viewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jaksaapp.remote.dto.ClassDto
import com.example.jaksaapp.remote.dto.ClassRequest
import com.example.jaksaapp.remote.dto.ClassesByMonthRequest
import com.example.jaksaapp.repository.ClassRepository
import dateFormatter
import getFirstDayOfMonth
import getMonth
import getYear
import kotlinx.coroutines.launch
import timeParser
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.Date

class ClassViewModel(private val repository: ClassRepository = ClassRepository()) : ViewModel() {
    private var token: String? = null
    var classesForMonth by mutableStateOf<List<ClassDto>>(emptyList())
        private set

    var allClasses by mutableStateOf<List<ClassDto>>(emptyList())
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var requestClassResult by mutableStateOf<String?>(null)
        private set

    fun setToken(token: String?) {
        this.token = token
    }

    fun getClassesForMonth(request: ClassesByMonthRequest) {
        viewModelScope.launch {
            try {
                val response = repository.getClassesByMonth("Bearer $token", request)
                if (response.isSuccessful) {
                    classesForMonth = response.body() ?: emptyList()
                    errorMessage = null
                } else {
                    errorMessage = "Greska: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                errorMessage = e.localizedMessage
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkClassTimeOverlap(
        selectedDate: Date,
        startTime: LocalTime,
        durationOption: String,
        classes: List<ClassDto>
    ): Boolean {
        val localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        val durationInMinutes = (durationOption.removeSuffix("h").toFloat() * 60).toLong()
        val newClassStart = LocalDateTime.of(localDate, startTime)
        val newClassEnd = newClassStart.plusMinutes(durationInMinutes)

        for (existingClass in classes) {
            val existingDate = LocalDate.parse(existingClass.date, dateFormatter)

            if (existingDate == localDate) {
                val existingStartTime = LocalTime.parse(existingClass.timeStart, timeParser)
                val existingDurationMinutes = (existingClass.duration.removeSuffix("h").toFloat() * 60).toLong()
                val existingStart = LocalDateTime.of(existingDate, existingStartTime)
                val existingEnd = existingStart.plusMinutes(existingDurationMinutes)

                val overlaps = newClassStart.isBefore(existingEnd) && existingStart.isBefore(newClassEnd)
                if (overlaps) {
                    return true
                }
            }
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun validateAddClassRequestFields(
        selectedDate: Date?,
        startTime: LocalTime?,
        durationOption: String,
        description: String,
        studentId: Long
    ): String {
        if (selectedDate == null || startTime == null || durationOption.isEmpty() || description.isEmpty() || studentId.toInt() == 0) {
            return "Polja ne smeju biti prazna!"
        }

        if (checkClassTimeOverlap(selectedDate, startTime, durationOption, classesForMonth)) {
            return "Već postoji čas koji se preklapa sa unetim vremenom."
        }
        return ""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createClassRequest(request: ClassRequest) {
        viewModelScope.launch {
            try {
                requestClassResult = null
                val response = repository.createClassRequest("Bearer $token", request)
                if (response.isSuccessful) {
                    requestClassResult = "Uspesno poslat zahtev za cas!"
                } else {
                    requestClassResult = "Greska: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                requestClassResult = e.localizedMessage
            }
        }
    }

    fun getClassesForUser(studentId: Long) {
        viewModelScope.launch {
            try {
                val response = repository.getAllClassesForStudent("Bearer $token", studentId)
                if (response.isSuccessful) {
                    allClasses = response.body() ?: emptyList()
                    errorMessage = null
                } else {
                    errorMessage = "Greška: $response"
                }
            } catch (e: Exception) {
                errorMessage = e.localizedMessage
            }
        }
    }

    fun getAllClasses() {
        viewModelScope.launch {
            try {
                val response = repository.getAllClasses("Bearer $token")
                if (response.isSuccessful) {
                    allClasses = response.body() ?: emptyList()
                    errorMessage = null
                } else {
                    errorMessage = "Greška: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                errorMessage = e.localizedMessage
            }
        }
    }

    fun acceptRequest(classId: Long, studentId: Long, isTeacher: Boolean) {
        viewModelScope.launch {
            try {
                val response = repository.acceptRequest("Bearer $token", classId)
                if (response.isSuccessful) {
                    errorMessage = null
                    if (isTeacher) {
                        getAllClasses()
                    } else {
                        getClassesForUser(studentId)
                    }
                } else {
                    errorMessage = "Greška: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                errorMessage = e.localizedMessage
            }
        }
    }

    fun rejectRequest(classId: Long, studentId: Long, isTeacher: Boolean) {
        viewModelScope.launch {
            try {
                val response = repository.rejectRequest("Bearer $token", classId)

                if (response.isSuccessful) {
                    errorMessage = null
                    if (isTeacher) {
                        getAllClasses()
                    } else {
                        getClassesForUser(studentId)
                    }
                } else {
                    errorMessage = "Greška: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                errorMessage = e.localizedMessage
            }
        }
    }

    fun deleteClass(classId: Long, studentId: Long, isTeacher: Boolean) {
        viewModelScope.launch {
            try {
                val response = repository.deleteClass("Bearer $token", classId)
                if (response.isSuccessful) {
                    errorMessage = null
                    val currentDate=getFirstDayOfMonth(Date())
                    getClassesForMonth(ClassesByMonthRequest(getYear(currentDate),getMonth(currentDate)))
                    if (isTeacher) {
                        getAllClasses()
                    } else {
                        getClassesForUser(studentId)
                    }
                } else {
                    errorMessage = "Greška: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                errorMessage = e.localizedMessage
            }
        }
    }
}
