package com.example.viikko1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.viikko1.ui.theme.Viikko1Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.viikko1.Domain.MockTasks
import com.example.viikko1.Domain.Task
import com.example.viikko1.Domain.addTask
import com.example.viikko1.Domain.filterByDone
import com.example.viikko1.Domain.sortByDueDate
import com.example.viikko1.Domain.toggleDone

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Viikko1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {

    var allTasks by remember { mutableStateOf(MockTasks) }
    var visibleTasks by remember { mutableStateOf(MockTasks) }


    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Task list")
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(onClick = {
                visibleTasks = sortByDueDate(allTasks)
            }) {
                Text("Sort by date")
            }

            Button(onClick = {
                visibleTasks = filterByDone(allTasks, true)
            }) {
                Text("Done")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            allTasks = toggleDone(allTasks, id = 1)
            visibleTasks = allTasks
        }) {
            Text("Toggle task 1")
        }
        Button(onClick = {
            val newTask = Task(
                id = allTasks.size + 1,
                title = "Added task",
                description = "From button",
                priority = 1,
                dueDate = "2026-02-01",
                done = false
            )
            visibleTasks = addTask(allTasks, newTask)
        }) {
            Text("Add task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        visibleTasks.forEach { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column {
                    Text(text = task.title)
                    Text(text = "Deadline: ${task.dueDate}")
                    Text(text = if (task.done) "Done" else "Not done")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Viikko1Theme {
        HomeScreen()
    }
}