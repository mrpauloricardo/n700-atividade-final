package br.unifor.ead.conversordeunidades.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UnitSelectionDropdown(
    label: String,
    units: List<String>,
    selectedUnit: String,
    onUnitSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(text = label, style = MaterialTheme.typography.labelLarge)
        OutlinedButton(onClick = { expanded = true }) {
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

@Preview
@Composable
fun PreviewUnitSelectionDropdown() {
    UnitSelectionDropdown(
        label = "Unidade de Entrada",
        units = listOf("Metros", "Centímetros", "Milhas"),
        selectedUnit = "Metros",
        onUnitSelected = {}
    )
}
