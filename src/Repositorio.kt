class Repositorio {
    private val afps: MutableList<AFP> = mutableListOf()
    private val empleados: MutableList<Empleado> = mutableListOf()
    private val liquidaciones: MutableList<LiquidacionSueldo> = mutableListOf()

    fun getAfps(): MutableList<AFP> = afps
    fun getEmpleados(): MutableList<Empleado> = empleados
    fun getLiquidaciones(): MutableList<LiquidacionSueldo> = liquidaciones

    fun agregarAFP(afp: AFP) { afps.add(afp) }

    fun agregarEmpleado(e: Empleado) {
        require(empleados.none { it.getRut().equals(e.getRut(), ignoreCase = true) }) { "Ya existe un empleado con ese RUT." }
        empleados.add(e)
    }

    fun eliminarEmpleadoPorRut(rut: String): Boolean {
        val idx = empleados.indexOfFirst { it.getRut().equals(rut, ignoreCase = true) }
        if (idx >= 0) {
            empleados.removeAt(idx)
            return true
        }
        return false
    }

    fun buscarEmpleadoPorRut(rut: String): Empleado? =
        empleados.find { it.getRut().equals(rut, ignoreCase = true) }

    fun filtrarEmpleadosPorAFP(nombreAfp: String): List<Empleado> =
        empleados.filter { it.getAfp().getNombre().equals(nombreAfp, ignoreCase = true) }

    fun generarLiquidacion(rut: String): LiquidacionSueldo? {
        val emp = buscarEmpleadoPorRut(rut) ?: return null
        val liq = LiquidacionSueldo(emp)
        liquidaciones.add(liq)
        return liq
    }

    fun totalDescuentosNomina(): Int = liquidaciones.sumOf { it.getTotalDescuentos() }

    fun seedEjemplo() {
        if (afps.isEmpty()) {
            agregarAFP(AFP("Modelo", 0.10))
            agregarAFP(AFP("Capital", 0.114))
            agregarAFP(AFP("Provida", 0.112))
        }
        if (empleados.isEmpty()) {
            val dir1 = Direccion("Av. Siempre Viva", 742, "Talca", "Maule")
            val dir2 = Direccion("Los Olivos", 120, "Curicó", "Maule")
            val dir3 = Direccion("Las Amapolas", 55, "Linares", "Maule")
            agregarEmpleado(Empleado("12.345.678-9", "Ana", "Pérez", dir1, afps[0], 900000))
            agregarEmpleado(Empleado("9.876.543-2", "Bruno", "Gómez", dir2, afps[1], 1200000))
            agregarEmpleado(Empleado("22.222.222-2", "Carla", "Rojas", dir3, afps[2], 700000))
        }
    }
}
