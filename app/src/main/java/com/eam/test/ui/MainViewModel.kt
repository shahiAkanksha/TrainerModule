package com.eam.test.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eam.test.data.ModuleRepository
import com.eam.test.data.TrainingModule
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val repo: ModuleRepository) : ViewModel() {

    private val _filter = MutableStateFlow(FilterType.ALL)
    val filter: StateFlow<FilterType> = _filter

    val modules: StateFlow<List<TrainingModule>> =
        combine(repo.getModules(), filter) { list, filter ->
            when (filter) {
                FilterType.ALL -> list
                FilterType.COMPLETED -> list.filter { it.isCompleted }
                FilterType.PENDING -> list.filter { !it.isCompleted }
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun setFilter(type: FilterType) {
        _filter.value = type
    }

    fun toggleStatus(id: Int, completed: Boolean) {
        viewModelScope.launch {
            repo.updateStatus(id, completed)
        }
    }
}

