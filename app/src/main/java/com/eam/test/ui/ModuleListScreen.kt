package com.eam.test.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eam.test.data.TrainingModule

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuleListScreen(
    modules: List<TrainingModule>,
    onItemClick: (Int) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Training Modules") }) }
    ) { padding ->

        LazyColumn(
            modifier = Modifier.padding(padding).padding(12.dp)
        ) {
            items(modules.size) { index ->
                val module = modules[index]

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { onItemClick(module.id) },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(module.title, style = MaterialTheme.typography.titleMedium)
                        Text(module.description, style = MaterialTheme.typography.bodyMedium)
                        Text(
                            if (module.isCompleted) "Completed" else "Pending",
                            color = if (module.isCompleted) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}
