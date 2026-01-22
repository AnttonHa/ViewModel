package com.example.viikko1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.viikko1.Domain.Task
import com.example.viikko1.viewmodel.TaskViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = viewModel()
) {
    var newTaskTitle by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Task list", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        Row {
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Add task") }
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                if (newTaskTitle.isNotBlank()) {
                    viewModel.addTask(
                        Task(
                            id = viewModel.tasks.size + 1,
                            title = newTaskTitle,
                            description = "",
                            priority = 1,
                            dueDate = "2026-01-30",
                            done = false
                        )
                    )
                    newTaskTitle = ""
                }
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,
                    contentColor = Color.White
                )
            )
            { Text("Add") }
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { viewModel.sortByDueDate() }) {
                Text("Sort by date")
            }
            Button(onClick = { viewModel.filterByDone(true) }) {
                Text("Done")
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(viewModel.tasks) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = task.done,
                                onCheckedChange = {
                                    viewModel.toggleDone(task.id)
                                }
                            )
                            Text(
                                text = task.title,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }

                        Text(
                            text = "Duedate: ${task.dueDate}",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 40.dp)
                        )
                    }

                    Button(
                        onClick = { viewModel.removeTask(task.id) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        )
                    ) {
                        Text("Remove")
                    }
                }
            }
        }
    }
}

