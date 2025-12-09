package com.eam.test.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eam.test.data.ModuleRepository
import com.eam.test.data.TrainingModule
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val repo: ModuleRepository) : ViewModel() {

    val modules: StateFlow<List<TrainingModule>> =
        repo.getModules().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun toggleStatus(id: Int, completed: Boolean) {
        viewModelScope.launch {
            repo.updateStatus(id, completed)
        }
    }
}
