package com.eam.test.data

data class TrainingModule(
    val id: Int,
    val title: String,
    val description: String,
    var isCompleted: Boolean = false
)