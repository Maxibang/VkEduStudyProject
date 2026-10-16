package com.example.vkeducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vkeducation.ui.theme.VkEducationTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonLoader = JSONLoader(this)
        val allApps = jsonLoader.loadAppDetails()

        setContent {
            var currentMainApp by remember { mutableStateOf(allApps.first()) }

            val recommendations = allApps.filter { it.title != currentMainApp.title }

            VkEducationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppDetailsScreen(
                        mainApp = currentMainApp,
                        recommendations = recommendations,
                        modifier = Modifier.padding(innerPadding),
                        onAppClick = { clickedApp ->
                            currentMainApp = clickedApp
                        }
                    )
                }
            }
        }
    }
}





@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VkEducationTheme {
        Greeting("Android")
    }
}