package br.unifor.ead.conversordeunidades.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InputField(input: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = input,
        onValueChange = onValueChange,
        label = { Text("Valor a ser convertido") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun PreviewInputField() {
    InputField(input = "10", onValueChange = {})
}
