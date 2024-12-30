package com.basma.foodorderingapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.basma.foodorderingapp.ui.theme.FoodOrderingAppTheme
import com.basma.presentation.component.AppNavigation
import com.basma.presentation.component.ForcePortraitOrientation
import com.basma.presentation.viewmodel.TablesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val tablesViewModel: TablesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        enableEdgeToEdge()
        setContent {
            FoodOrderingAppTheme {
                // Lock orientation to portrait mode
                ForcePortraitOrientation()
                AppNavigation(tablesViewModel)
            }
        }
    }
}

