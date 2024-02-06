package com.example.composedweather.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComposedWeatherTheme {
                CentralNavigation(
                    backPress = {
                        finish()
                    },
                    openWhatsApp = {
                        openWhatsApp(it)
                    }
                )
            }
        }
    }

    private fun openWhatsApp(phoneNumber: String) {
        try {
            val uri = Uri.parse("whatsapp://send?phone=+$phoneNumber")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.whatsapp")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposedWeatherTheme {
        CentralNavigation(
            backPress = {},
            openWhatsApp = {}
        )
    }
}