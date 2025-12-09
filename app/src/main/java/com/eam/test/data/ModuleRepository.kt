package com.eam.test.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ModuleRepository(private val dataStore: DataStoreManager) {

    private val modules = listOf(
        TrainingModule(1, "Kotlin Basics", "Learn variables, loops, functions."),
        TrainingModule(2, "Jetpack Compose", "Learn Composables and UI state."),
        TrainingModule(3, "Android Architecture", "MVVM, Repository, ViewModel."),
    )

    fun getModules(): Flow<List<TrainingModule>> =
        dataStore.completedIdsFlow.map { completed ->
            modules.map { it.copy(isCompleted = completed.contains(it.id.toString())) }
        }

    suspend fun updateStatus(id: Int, isCompleted: Boolean) {
        val current = dataStore.completedIdsFlow.map { it }.first()
        val updated = if (isCompleted)
            current + id.toString()
        else
            current - id.toString()

        dataStore.saveCompletedIds(updated)
    }
}
