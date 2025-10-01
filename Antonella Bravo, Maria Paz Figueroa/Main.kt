import kotlin.system.exitProcess

fun leerLinea(msg: String): String {
    print("$msg: ")
    return readLine()?.trim().orEmpty()
}

fun main() {
    val repo = Repositorio()
    repo.seedEjemplo() // Carga AFPs + 3 empleados de ejemplo

    while (true) {
        println("\n=== MENÚ ===")
        println("1) Listar empleados")
        println("2) Agregar empleado")
        println("3) Generar liquidación por RUT")
        println("4) Listar liquidaciones")
        println("5) Filtrar empleados por AFP (y ordenar por sueldo líquido desc)")
        println("6) Eliminar empleado")
        println("7) Salir")
        when (leerLinea("Opción")) {
            "1" -> {
                if (repo.getEmpleados().isEmpty()) println("No hay empleados.")
                else repo.getEmpleados().forEach { println(it) }
            }
            "2" -> {
                val rut = leerLinea("RUT")
                val nombres = leerLinea("Nombres")
                val apellidos = leerLinea("Apellidos")
                val calle = leerLinea("Calle")
                val numero = leerLinea("Número").toIntOrNull() ?: 0
                val comuna = leerLinea("Comuna")
                val ciudad = leerLinea("Ciudad")
                val sueldo = leerLinea("Sueldo base").toIntOrNull() ?: 0

                if (repo.getAfps().isEmpty()) {
                    println("Primero debe existir al menos una AFP.")
                } else {
                    println("AFPs disponibles:")
                    repo.getAfps().forEachIndexed { i, a -> println("${i+1}) ${a.getNombre()} (${(a.getTasa()*100)}%)") }
                    val idx = (leerLinea("Seleccione AFP por número").toIntOrNull() ?: 0) - 1
                    if (idx in repo.getAfps().indices) {
                        val afp = repo.getAfps()[idx]
                        try {
                            val dir = Direccion(calle, numero, comuna, ciudad)
                            val emp = Empleado(rut, nombres, apellidos, dir, afp, sueldo)
                            repo.agregarEmpleado(emp)
                            println("Empleado agregado.")
                        } catch (e: IllegalArgumentException) {
                            println("Error: ${e.message}")
                        }
                    } else {
                        println("Selección inválida.")
                    }
                }
            }
            "3" -> {
                val rut = leerLinea("RUT del empleado")
                val liq = repo.generarLiquidacion(rut)
                if (liq == null) println("No se encontró empleado.")
                else println(liq)
            }
            "4" -> {
                if (repo.getLiquidaciones().isEmpty()) println("No hay liquidaciones generadas.")
                else {
                    repo.getLiquidaciones().forEach { println(it) }
                    println("Total descuentos de la nómina: ${repo.totalDescuentosNomina()}")
                }
            }
            "5" -> {
                val nombreAfp = leerLinea("Nombre de la AFP")
                val lista = repo.filtrarEmpleadosPorAFP(nombreAfp)
                if (lista.isEmpty()) {
                    println("No hay empleados con AFP $nombreAfp.")
                } else {
                    val ordenados = lista
                        .map { it to LiquidacionSueldo(it).getSueldoLiquido() }
                        .sortedByDescending { it.second }
                    println("Empleados con AFP $nombreAfp (ordenados por sueldo líquido desc):")
                    ordenados.forEach { (emp, liq) ->
                        println("${emp.getRut()} - ${emp.getNombres()} ${emp.getApellidos()} | Líquido aprox: $liq")
                    }
                }
            }
            "6" -> {
                val rut = leerLinea("RUT del empleado a eliminar")
                if (repo.eliminarEmpleadoPorRut(rut)) println("Empleado eliminado.")
                else println("No se encontró empleado.")
            }
            "7" -> {
                println("Saliendo...")
                exitProcess(0)
            }
            else -> println("Opción inválida.")
        }
    }
}
