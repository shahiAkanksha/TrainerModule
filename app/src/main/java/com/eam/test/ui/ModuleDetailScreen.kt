package com.eam.test.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eam.test.data.TrainingModule

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuleDetailScreen(
    module: TrainingModule,
    onStatusChange: (Boolean) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(module.title) }) }
    ) { padding ->

        Column(
            modifier = Modifier.padding(padding).padding(16.dp)
        ) {

            Text(module.title, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))
            Text(module.description)

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = { onStatusChange(!module.isCompleted) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (module.isCompleted) "Mark as Pending"
                    else "Mark as Completed"
                )
            }
        }
    }
}
