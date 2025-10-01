import data.AFP
import data.Direccion
import data.Empleado
import singleton.Repositorio
import utility.ConsoleUtils

private val utils = ConsoleUtils()

fun main() {
    seed()
    while (true) {
        println("===== MENÚ =====")
        println("1. Listar empleados")
        println("2. Agregar empleado")
        println("3. Eliminar empleado")
        println("4. Buscar empleado por RUT")
        println("5. Generar liquidación")
        println("6. Listar liquidaciones")
        println("0. Salir")
        when (utils.leerLineaNoVacia("Opción")) {
            "1" -> {
                Repositorio.empleados.forEach { println(it) }
                utils.pause()
            }
            "2" -> agregarEmpleadoFlow()
            "3" -> eliminarEmpleadoFlow()
            "4" -> buscarEmpleadoFlow()
            "5" -> generarLiquidacionFlow()
            "6" -> {
                Repositorio.liquidaciones.forEach { println(it.resumen()) }
                utils.pause()
            }
            "0" -> return
            else -> println("Opción inválida.")
        }
    }
}

private fun seed() {
    Repositorio.agregarAFP(AFP("Habitat", 0.105))
    Repositorio.agregarAFP(AFP("Provida", 0.112))
    Repositorio.agregarAFP(AFP("Modelo", 0.099))

    Repositorio.agregarEmpleado(
        Empleado(
            "11.111.111-1","Juan Pérez",800000.0,
            Repositorio.buscarAFPPorNombre("Habitat")!!,
            Direccion("Av. Siempre Viva",123,"Talca","Maule"),
            bonosImponibles = 50000.0,
            bonosNoImponibles = 20000.0
        )
    )
    Repositorio.agregarEmpleado(
        Empleado(
            "22.222.222-2","María Gómez",950000.0,
            Repositorio.buscarAFPPorNombre("Provida")!!,
            Direccion("Los Alerces",456,"Curicó","Maule")
        )
    )
    Repositorio.agregarEmpleado(
        Empleado(
            "33.333.333-3","Pedro Soto",700000.0,
            Repositorio.buscarAFPPorNombre("Modelo")!!,
            Direccion("San Martín",789,"Linares","Maule"),
            bonosNoImponibles = 15000.0
        )
    )
}

private fun agregarEmpleadoFlow() {
    val rut = utils.leerLineaNoVacia("RUT")
    val nombre = utils.leerLineaNoVacia("Nombre completo")
    val sueldoBase = utils.leerDouble("Sueldo base")
    val calle = utils.leerLineaNoVacia("Calle")
    val numero = utils.leerInt("Número")
    val ciudad = utils.leerLineaNoVacia("Ciudad")
    val region = utils.leerLineaNoVacia("Región")
    println("AFP disponibles:"); Repositorio.afps.forEach { println("- ${it.nombre} (tasa=${it.tasa})") }
    val afp = Repositorio.buscarAFPPorNombre(utils.leerLineaNoVacia("Nombre AFP"))
        ?: return println("AFP no encontrada.").also { utils.pause() }
    val bonosImp = utils.leerDouble("Bonos imponibles (0 si no aplica)")
    val bonosNoImp = utils.leerDouble("Bonos no imponibles (0 si no aplica)")

    Repositorio.agregarEmpleado(
        Empleado(rut,nombre,sueldoBase,afp,Direccion(calle,numero,ciudad,region),bonosImp,bonosNoImp)
    )
    println("Empleado agregado."); utils.pause()
}

private fun eliminarEmpleadoFlow() {
    val rut = utils.leerLineaNoVacia("RUT a eliminar")
    println(if (Repositorio.eliminarEmpleadoPorRut(rut)) "Empleado eliminado." else "No encontrado.")
    utils.pause()
}

private fun buscarEmpleadoFlow() {
    val rut = utils.leerLineaNoVacia("RUT a buscar")
    println(Repositorio.buscarEmpleadoPorRut(rut) ?: "No encontrado.")
    utils.pause()
}

private fun generarLiquidacionFlow() {
    val periodo = utils.leerLineaNoVacia("Periodo (YYYY-MM)")
    val rut = utils.leerLineaNoVacia("RUT del empleado")
    val emp = Repositorio.buscarEmpleadoPorRut(rut)
    if (emp == null) {
        println("Empleado no encontrado.")
    } else {
        val liq = Repositorio.generarLiquidacion(periodo, emp)
        println(liq.resumen())
    }
    utils.pause()
}
