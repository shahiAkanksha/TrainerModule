package com.eam.test.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AppNavHost(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "list") {

        composable("list") {
            ModuleListScreen(
                modules = viewModel.modules.collectAsState().value,
                onItemClick = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }

        composable(
            "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { entry ->
            val id = entry.arguments!!.getInt("id")
            val module = viewModel.modules.value.first { it.id == id }

            ModuleDetailScreen(
                module = module,
                onStatusChange = { viewModel.toggleStatus(module.id, it) }
            )
        }
    }
}
