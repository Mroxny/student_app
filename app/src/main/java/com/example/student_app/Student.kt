package com.example.student_app

import kotlinx.serialization.Serializable

@Serializable
class Student(var name:String, var age:Int, var gpa:Float) {
    fun canPass(): Boolean {
        return this.gpa >= 3
    }
}