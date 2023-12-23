package com.example.student_app

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class StudentRepo(val fileName:String) {
    fun loadStudents(): List<Student> {
        val fileContent = File(fileName).readText()
        println("Students loaded")
        return Json.decodeFromString(fileContent)
    }

    fun saveStudents(students: List<Student>) {
        val jsonString = Json.encodeToString(students)
        File(fileName).writeText(jsonString)
        println("Students saved")

    }
}