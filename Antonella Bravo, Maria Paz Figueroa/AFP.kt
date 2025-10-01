class AFP(nombre: String, tasa: Double) {
    private var nombre: String = nombre
    private var tasa: Double = tasa // 0.10 = 10%

    fun getNombre(): String = nombre
    fun setNombre(nuevo: String) { nombre = nuevo.trim() } /*ocupamos  trim para evitar los espacios*/

    fun getTasa(): Double = tasa
    fun setTasa(nueva: Double) {
        require(nueva in 0.0..1.0) { "La tasa debe estar entre 0.0 y 1.0" }
        tasa = nueva
    }

    override fun toString(): String = "AFP(nombre=${getNombre()}, tasa=${"%.2f".format(getTasa()*100)}%)"
}
