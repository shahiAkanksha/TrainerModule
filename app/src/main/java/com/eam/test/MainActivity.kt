package com.eam.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eam.test.data.DataStoreManager
import com.eam.test.data.ModuleRepository
import com.eam.test.ui.AppNavHost
import com.eam.test.ui.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repo = ModuleRepository(DataStoreManager(this))
        val vm = MainViewModel(repo)

        setContent {

            AppNavHost(vm)

        }
    }
}
