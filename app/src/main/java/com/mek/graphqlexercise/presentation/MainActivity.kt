package com.mek.graphqlexercise.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.mek.graphqlexercise.presentation.launch_detail.LaunchDetailScreen
import com.mek.graphqlexercise.presentation.launch_list.LaunchListScreen
import com.mek.graphqlexercise.presentation.company_detail.CompanyScreen
import com.mek.graphqlexercise.presentation.navigation.*
import com.mek.graphqlexercise.presentation.ui.theme.GraphQlExerciseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GraphQlExerciseTheme {

                // Top-level bottom navigation itemler
                val bottomRoutes = setOf(LaunchList, CompanyNavKey)

                val navigationState = rememberNavigationState(
                    startRoute = LaunchList,
                    topLevelRoutes = bottomRoutes
                )

                val navigator = remember { Navigator(navigationState) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = navigationState.topLevelRoute == LaunchList,
                                onClick = { navigator.navigate(LaunchList) },
                                label = { Text("Launches") },
                                icon = { Icon(Icons.Default.List, contentDescription = null) }
                            )
                            NavigationBarItem(
                                selected = navigationState.topLevelRoute == CompanyNavKey,
                                onClick = { navigator.navigate(CompanyNavKey) },
                                label = { Text("Company") },
                                icon = { Icon(Icons.Default.Info, contentDescription = null) }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavDisplay(
                        modifier = Modifier.padding(innerPadding),
                        entries = navigationState.toEntries { key ->
                            when (key) {
                                is LaunchList -> NavEntry(key = key) {
                                    LaunchListScreen(navigator = navigator)
                                }
                                is LaunchDetail -> NavEntry(key = key) {
                                    LaunchDetailScreen(launchId = key.launchId)
                                }
                                is CompanyNavKey -> NavEntry(key = key) {
                                    CompanyScreen()
                                }
                                else -> error("Unknown route: $key")
                            }
                        },
                        onBack = { navigator.goBack() }
                    )
                }
            }
        }
    }
}