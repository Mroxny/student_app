package com.example.student_app

import java.io.File

class StudentRepo(val fileName:String) {
    fun loadStudents(): List<Char> {
        val fileContent = File(fileName).readText()
        return "".toList()
    }

    fun saveStudents(students: List<Student>) {
        return 
    }
}