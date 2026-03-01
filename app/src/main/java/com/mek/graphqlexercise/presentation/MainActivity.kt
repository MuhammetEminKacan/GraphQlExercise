package com.mek.graphqlexercise.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.mek.graphqlexercise.presentation.launch_list.LaunchListScreen
import com.mek.graphqlexercise.presentation.navigation.LaunchList
import com.mek.graphqlexercise.presentation.navigation.Navigator
import com.mek.graphqlexercise.presentation.navigation.rememberNavigationState
import com.mek.graphqlexercise.presentation.navigation.toEntries
import com.mek.graphqlexercise.presentation.ui.theme.GraphQlExerciseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GraphQlExerciseTheme {
                //YENİ: Navigation state
                val navigationState = rememberNavigationState(
                    startRoute = LaunchList,
                    topLevelRoutes = setOf(LaunchList)
                )

                // YENİ: Navigator (NavController yerine)
                val navigator = remember {
                    Navigator(navigationState)
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavDisplay(
                        modifier = Modifier.padding(innerPadding),
                        entries = navigationState.toEntries { key ->
                            when (key) {

                                is LaunchList -> NavEntry(key = key) {
                                    LaunchListScreen()
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
