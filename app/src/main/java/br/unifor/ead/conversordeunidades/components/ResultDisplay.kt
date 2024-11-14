package br.unifor.ead.conversordeunidades.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ResultDisplay(result: String) {
    Text(text = "Resultado: $result", style = MaterialTheme.typography.bodyLarge)
}

@Preview
@Composable
fun PreviewResultDisplay() {
    ResultDisplay(result = "1000")
}
