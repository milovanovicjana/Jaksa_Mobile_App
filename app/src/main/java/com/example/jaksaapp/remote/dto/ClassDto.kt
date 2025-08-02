package com.example.jaksaapp.remote.dto

enum class ClassStatus {
    APPROVED,
    REJECTED,
    PENDING
}
data class ClassDto(
    var date: String,
    var timeStart: String,
    var duration: String,
    var description: String,
    var classStatus: ClassStatus,
    val studentId: Long,
    val studentFirstName: String,
    val studentLastName: String
)
