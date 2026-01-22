package com.example.viikko1.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.viikko1.Domain.Task
import com.example.viikko1.Domain.MockTasks

class TaskViewModel : ViewModel() {

    var tasks by mutableStateOf<List<Task>>(emptyList())
        private set

    init {
        tasks = MockTasks
    }

    fun addTask(task: Task) {
        tasks = tasks + task
    }

    fun toggleDone(id: Int) {
        tasks = tasks.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }
    }

    fun removeTask(id: Int) {
        tasks = tasks.filterNot { it.id == id }
    }

    fun filterByDone(done: Boolean) {
        tasks = tasks.filter { it.done == done }
    }

    fun sortByDueDate() {
        tasks = tasks.sortedBy { it.dueDate }
    }
}