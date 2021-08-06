package helloworld.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import helloworld.components.layouts.PageLayout
import kobweb.core.Page
import kobweb.silk.components.text.Text
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.P

class HomePage : Page {
    @Composable
    override fun render() {
        PageLayout("Welcome to Kobweb!") {
            Text("Please enter your name")
            var name by remember { mutableStateOf("") }
            Input(
                InputType.Text,
                attrs = {
                    onInput { e -> name = e.value }
                }
            )
            P()
            Text("Hello ${name.takeIf { it.isNotBlank() } ?: "World"}!")
        }
    }
}