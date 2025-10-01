package data

data class LiquidacionSueldo(
    val periodo: String,         // "YYYY-MM"
    val empleado: Empleado,
    val imponible: Double,
    val noImponible: Double,
    val descAfp: Double,
    val descSalud: Double,
    val descCesantia: Double,
    val totalDescuentos: Double,
    val sueldoLiquido: Double
) {
    fun resumen(): String = buildString {
        appendLine("Liquidación $periodo - ${empleado.nombre} (${empleado.rut})")
        appendLine("Imponible: $imponible")
        appendLine("No imponible: $noImponible")
        appendLine("Descuentos -> AFP: $descAfp  Salud: $descSalud  Cesantía: $descCesantia")
        appendLine("Total descuentos: $totalDescuentos")
        appendLine("Sueldo líquido: $sueldoLiquido")
    }

    companion object {
        fun calcular(
            periodo: String,
            empleado: Empleado,
            tasaSalud: Double = 0.07,
            tasaCesantia: Double = 0.006
        ): LiquidacionSueldo {
            val imponible = empleado.sueldoImponible()
            val noImp = empleado.bonosNoImponibles
            val dAfp = imponible * empleado.afp.tasa
            val dSalud = imponible * tasaSalud
            val dCes = imponible * tasaCesantia
            val totDesc = dAfp + dSalud + dCes
            val liquido = imponible - totDesc + noImp
            return LiquidacionSueldo(
                periodo = periodo,
                empleado = empleado,
                imponible = imponible,
                noImponible = noImp,
                descAfp = dAfp,
                descSalud = dSalud,
                descCesantia = dCes,
                totalDescuentos = totDesc,
                sueldoLiquido = liquido
            )
        }
    }
}
