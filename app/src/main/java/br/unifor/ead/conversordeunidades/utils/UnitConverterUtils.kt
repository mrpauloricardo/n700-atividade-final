package br.unifor.ead.conversordeunidades.utils

fun convertUnits(value: Double, inputUnit: String, outputUnit: String): String {
    val valueInMeters = when (inputUnit) {
        "Centímetros" -> value / 100
        "Metros" -> value
        "Quilômetros" -> value * 1000
        "Milhas" -> value * 1609.344
        else -> 0.0
    }

    val result = when (outputUnit) {
        "Centímetros" -> valueInMeters * 100
        "Metros" -> valueInMeters
        "Quilômetros" -> valueInMeters / 1000
        "Milhas" -> valueInMeters / 1609.344
        else -> 0.0
    }

    return "%.2f".format(result) + " $outputUnit"
}
