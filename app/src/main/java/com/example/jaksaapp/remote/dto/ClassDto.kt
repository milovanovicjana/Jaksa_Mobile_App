package com.example.jaksaapp.remote.dto

enum class ClassStatus {
    APPROVED,
    REJECTED,
    PENDING
}
data class ClassDto(
    val id: Long?,
    val studentId: Long,
    val studentFirstName: String,
    val studentLastName: String,
    var date: String,
    var timeStart: String,
    var duration: String,
    var classStatus: ClassStatus,
    var description: String,
    var requestedByStudent: Boolean
)
