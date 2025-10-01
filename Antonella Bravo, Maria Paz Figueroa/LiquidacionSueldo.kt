class LiquidacionSueldo(empleado: Empleado) {
    private val empleado: Empleado = empleado
    private val salud: Double = 0.07
    private val seguroCesantia: Double = 0.006

    fun getEmpleado(): Empleado = empleado

    fun getDescuentoAFP(): Int = (empleado.getSueldoBase() * empleado.getAfp().getTasa()).toInt()
    fun getDescuentoSalud(): Int = (empleado.getSueldoBase() * salud).toInt()
    fun getDescuentoCesantia(): Int = (empleado.getSueldoBase() * seguroCesantia).toInt()

    fun getTotalDescuentos(): Int = getDescuentoAFP() + getDescuentoSalud() + getDescuentoCesantia()

    fun getSueldoLiquido(): Int = empleado.getSueldoBase() - getTotalDescuentos()

    override fun toString(): String {
        return buildString {
            appendLine("Liquidación de ${empleado.getNombres()} ${empleado.getApellidos()} (${empleado.getRut()})")
            appendLine("Sueldo base        : ${empleado.getSueldoBase()}")
            appendLine("AFP ${empleado.getAfp().getNombre()} : -${getDescuentoAFP()}")
            appendLine("Salud 7%           : -${getDescuentoSalud()}")
            appendLine("Seguro Cesantía 0.6%: -${getDescuentoCesantia()}")
            appendLine("TOTAL DESCUENTOS   : -${getTotalDescuentos()}")
            appendLine("SUELDO LÍQUIDO     : ${getSueldoLiquido()}")
        }
    }
}
