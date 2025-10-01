package data

data class Empleado(
    val rut: String,
    var nombre: String,
    var sueldoBase: Double,
    var afp: AFP,
    var direccion: Direccion,
    var bonosImponibles: Double = 0.0,
    var bonosNoImponibles: Double = 0.0
) {
    fun sueldoImponible(): Double = sueldoBase + bonosImponibles
}
