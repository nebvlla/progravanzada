package singleton

import data.AFP
import data.Empleado
import data.LiquidacionSueldo

object Repositorio {
    val afps: List<AFP> get() = _afps
    val empleados: MutableList<Empleado> get() = _empleados
    val liquidaciones: MutableList<LiquidacionSueldo> get() = _liquidaciones

    private val _afps = mutableListOf<AFP>()
    private val _empleados = mutableListOf<Empleado>()
    private val _liquidaciones = mutableListOf<LiquidacionSueldo>()

    fun agregarAFP(afp: AFP) = _afps.add(afp)
    fun buscarAFPPorNombre(nombre: String): AFP? =
        _afps.find { it.nombre.equals(nombre.trim(), ignoreCase = true) }

    fun agregarEmpleado(empleado: Empleado) = _empleados.add(empleado)
    fun eliminarEmpleadoPorRut(rut: String): Boolean {
        val removed = _empleados.removeIf { it.rut.equals(rut.trim(), ignoreCase = true) }
        if (removed) _liquidaciones.removeIf { it.empleado.rut.equals(rut.trim(), ignoreCase = true) }
        return removed
    }
    fun buscarEmpleadoPorRut(rut: String): Empleado? =
        _empleados.find { it.rut.equals(rut.trim(), ignoreCase = true) }

    // Firma pedida: generarLiquidacion(periodo, empleado)
    fun generarLiquidacion(periodo: String, empleado: Empleado): LiquidacionSueldo {
        val liq = LiquidacionSueldo.calcular(periodo, empleado)
        _liquidaciones.add(liq)
        return liq
    }
}
