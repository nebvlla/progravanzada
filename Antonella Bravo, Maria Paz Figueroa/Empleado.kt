class Empleado(
    rut: String,
    nombres: String,
    apellidos: String,
    direccion: Direccion,
    afp: AFP,
    sueldoBase: Int
) {
    private var rut: String = rut
    private var nombres: String = nombres
    private var apellidos: String = apellidos
    private var direccion: Direccion = direccion
    private var afp: AFP = afp
    private var sueldoBase: Int = sueldoBase

    fun getRut(): String = rut
    fun setRut(v: String) { rut = v.trim() }

    fun getNombres(): String = nombres
    fun setNombres(v: String) { nombres = v.trim() }

    fun getApellidos(): String = apellidos
    fun setApellidos(v: String) { apellidos = v.trim() }

    fun getDireccion(): Direccion = direccion
    fun setDireccion(v: Direccion) { direccion = v }

    fun getAfp(): AFP = afp
    fun setAfp(v: AFP) { afp = v }

    fun getSueldoBase(): Int = sueldoBase
    fun setSueldoBase(v: Int) { require(v >= 0) { "Sueldo base no puede ser negativo" }; sueldoBase = v }

    override fun toString(): String =
        "Empleado(rut=${getRut()}, nombre=${getNombres()} ${getApellidos()}, afp=${getAfp().getNombre()}, sueldoBase=${getSueldoBase()}, direccion=${getDireccion()})"
}
