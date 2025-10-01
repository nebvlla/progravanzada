class Direccion(calle: String, numero: Int, comuna: String, ciudad: String) {
    private var calle: String = calle
    private var numero: Int = numero
    private var comuna: String = comuna
    private var ciudad: String = ciudad

    fun getCalle(): String = calle
    fun setCalle(v: String) { calle = v.trim() }

    fun getNumero(): Int = numero
    fun setNumero(v: Int) { require(v > 0) { "Número debe ser positivo" }; numero = v }

    fun getComuna(): String = comuna
    fun setComuna(v: String) { comuna = v.trim() }

    fun getCiudad(): String = ciudad
    fun setCiudad(v: String) { ciudad = v.trim() }

    override fun toString(): String = "${getCalle()} ${getNumero()}, ${getComuna()}, ${getCiudad()}"
}
