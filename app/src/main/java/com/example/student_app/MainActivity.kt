package com.example.student_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.student_app.ui.theme.Student_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sRepo = StudentRepo("data.json")
        val studentList = sRepo.loadStudents()

        setContent {
            Student_appTheme {
                MainActivityScreen(
                    studentList = studentList,
                    onDeleteStudent = { println("Del student") },
                )
            }
        }
    }
}


@Composable
fun MainActivityScreen(
    studentList: List<Student>,
    onDeleteStudent: () -> Unit,
) {
    var isColumn = true
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Studentci: ${studentList.size}", modifier = Modifier.padding(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { isColumn=false },
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("Row")
                }
            }

            Button(
                onClick = { isColumn=true },
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("Column")
                }
            }

            Button(
                onClick = { onDeleteStudent() },
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("Delete")
                }
            }
        }
        if (isColumn){
            LazyColumn{
                items(studentList) { student ->
                    StudentListItem(student = student)
                }
            }
        }
        else{
            LazyRow{
                items(studentList) { student ->
                    StudentListItem(student = student)
                }
            }
        }

    }
}

@Composable
fun StudentListItem(student: Student) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Imię: ${student.name}")
            Text("Wiek: ${student.age}")
            Text("Średnia: ${student.gpa}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Student_appTheme {
        MainActivityScreen(
            studentList = listOf(
                Student("John Doe", 20, 4.5F),
                Student("Jane Smith", 22, 3.8F),
                Student("Bob Johnson", 21, 4.0F)
            ),
            onDeleteStudent = {},
        )
    }
}