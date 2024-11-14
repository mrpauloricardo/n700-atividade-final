package br.unifor.ead.conversordeunidades.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.unifor.ead.conversordeunidades.utils.convertUnits

@Composable
fun ConvertButton(input: String, selectedInputUnit: String, selectedOutputUnit: String, onConversion: (String) -> Unit) {
    Button(
        onClick = {
            val value = input.toDoubleOrNull()
            if (value != null) {
                onConversion( convertUnits(value, selectedInputUnit, selectedOutputUnit))
            } else {
                onConversion("Insira um valor válido")
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Converter")
    }
}

@Preview
@Composable
fun PreviewConvertButton() {
    ConvertButton(input = "10", selectedInputUnit = "Metros", selectedOutputUnit = "Centímetros") {}
}
