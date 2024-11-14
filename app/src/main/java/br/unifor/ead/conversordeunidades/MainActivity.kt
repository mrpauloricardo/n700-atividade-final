package br.unifor.ead.conversordeunidades

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.unifor.ead.conversordeunidades.ui.theme.ConversorDeUnidadesTheme
import br.unifor.ead.conversordeunidades.utils.convertUnits
import androidx.compose.ui.platform.LocalConfiguration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConversorDeUnidadesTheme(darkTheme = true) { // Ativa o tema escuro
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverterScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UnitConverterScreen(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var selectedInputUnit by remember { mutableStateOf("Metros") }
    var selectedOutputUnit by remember { mutableStateOf("Centímetros") }
    var result by remember { mutableStateOf("") }

    val units = listOf("Centímetros", "Metros", "Quilômetros", "Milhas")

    // Obtendo a largura da tela para adaptar o layout
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        InputField(input = input, onValueChange = { input = it })

        // Ajuste dinâmico de largura do dropdown com base na tela
        UnitSelectionDropdown(
            label = "Unidade de Entrada",
            units = units,
            selectedUnit = selectedInputUnit,
            onUnitSelected = { selectedInputUnit = it },
            modifier = Modifier.fillMaxWidth()
        )

        UnitSelectionDropdown(
            label = "Unidade de Saída",
            units = units,
            selectedUnit = selectedOutputUnit,
            onUnitSelected = { selectedOutputUnit = it },
            modifier = Modifier.fillMaxWidth()
        )

        ConvertButton(input, selectedInputUnit, selectedOutputUnit) { conversionResult ->
            result = conversionResult
        }

        ResultDisplay(result)
    }
}

@Composable
fun InputField(input: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = input,
        onValueChange = onValueChange,
        label = { Text("Valor a ser convertido") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun UnitSelectionDropdown(
    label: String,
    units: List<String>,
    selectedUnit: String,
    onUnitSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(text = label, style = MaterialTheme.typography.labelLarge)
        OutlinedButton(onClick = { expanded = true }, modifier = modifier) {
            Text(selectedUnit)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            units.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        onUnitSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun ConvertButton(input: String, selectedInputUnit: String, selectedOutputUnit: String, onConversion: (String) -> Unit) {
    Button(
        onClick = {
            val value = input.toDoubleOrNull()
            if (value != null) {
                onConversion(convertUnits(value, selectedInputUnit, selectedOutputUnit))
            } else {
                onConversion("Insira um valor válido")
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Converter")
    }
}

@Composable
fun ResultDisplay(result: String) {
    Text(text = "Resultado: $result", style = MaterialTheme.typography.bodyLarge)
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    ConversorDeUnidadesTheme(darkTheme = true) {
        UnitConverterScreen()
    }
}
