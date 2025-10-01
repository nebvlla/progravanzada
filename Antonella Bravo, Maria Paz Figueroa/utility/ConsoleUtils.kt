package utility

class ConsoleUtils {
    fun leerLineaNoVacia(prompt: String): String {
        while (true) {
            print("$" + prompt + ": ")
            val s = readLine()?.trim() ?: ""
            if (s.isNotEmpty()) return s
            println("Debe ingresar un valor no vacío.")
        }
    }

    fun leerDouble(prompt: String): Double {
        while (true) {
            print("$" + prompt + ": ")
            val t = readLine()?.trim() ?: ""
            t.toDoubleOrNull()?.let { return it }
            println("Ingrese un número válido (usa punto).")
        }
    }

    fun leerInt(prompt: String): Int {
        while (true) {
            print("$" + prompt + ": ")
            val t = readLine()?.trim() ?: ""
            t.toIntOrNull()?.let { return it }
            println("Ingrese un entero válido.")
        }
    }

    fun pause() {
        println("Presiona ENTER para continuar...")
        readLine()
    }

    fun formatearMonto(v: Double): String =
        "$" + "%,d".format(v.toInt()).replace(",", ".")
}
