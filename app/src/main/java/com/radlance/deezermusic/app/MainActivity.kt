package com.radlance.deezermusic.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.radlance.deezermusic.app.navigation.BottomNavigationBar
import com.radlance.deezermusic.app.navigation.NavGraph
import com.radlance.deezermusic.app.navigation.rememberNavigationState
import com.radlance.deezermusic.presentation.ui.theme.DeezerMusicTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeezerMusicTheme {
                val bottomNavigationState = rememberNavigationState(rememberNavController())
                Scaffold(
                    bottomBar = { BottomNavigationBar(bottomNavigationState) },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavGraph(
                        bottomNavigationState = bottomNavigationState,
                        modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
                    )
                }
            }
        }
    }
}