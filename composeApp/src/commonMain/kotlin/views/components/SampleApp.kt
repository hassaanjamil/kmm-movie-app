package views.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.Greeting
import data.Movie

@Composable
fun SampleApp() {

        val names = arrayListOf("Hassan", "Nisar", "Hannan")
        var showContent by remember { mutableStateOf(false) }
        var randomName by remember { mutableStateOf("{name}") }
        Column(Modifier.fillMaxWidth().verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent
                if(showContent)
                    randomName = names.random()
            }) {
                Movie("M1", "http://", "4.5")
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                KMMLogoMsg(modifier = Modifier.fillMaxWidth(),
                    name = randomName,
                    greeting = greeting)
            }
        }
}
