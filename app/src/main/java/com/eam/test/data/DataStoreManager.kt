package com.eam.test.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "modules")

class DataStoreManager(private val context: Context) {

    companion object {
        val COMPLETED_IDS = stringSetPreferencesKey("completed_ids")
    }

    val completedIdsFlow: Flow<Set<String>> = context.dataStore.data.map {
        it[COMPLETED_IDS] ?: emptySet()
    }

    suspend fun saveCompletedIds(ids: Set<String>) {
        context.dataStore.edit {
            it[COMPLETED_IDS] = ids
        }
    }
}
