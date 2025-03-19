import javax.swing.JOptionPane

fun cifrarRailFence(texto: String, rieles: Int): String {
    if (rieles <= 1) return texto

    val matriz = Array(rieles) { StringBuilder() }
    var riel = 0
    var direccion = 1

    for (caracter in texto) {
        matriz[riel].append(caracter)
        riel += direccion
        if (riel == 0 || riel == rieles - 1) direccion *= -1
    }

    return matriz.joinToString("")
}

fun descifrarRailFence(textoCifrado: String, rieles: Int): String {
    if (rieles <= 1) return textoCifrado

    val patron = mutableListOf<Int>()
    var riel = 0
    var direccion = 1

    for (i in textoCifrado.indices) {
        patron.add(riel)
        riel += direccion
        if (riel == 0 || riel == rieles - 1) direccion *= -1
    }

    val matriz = Array(rieles) { StringBuilder() }
    val indicesOrdenados = patron.sorted().withIndex()

    for ((i, rielIndex) in indicesOrdenados) {
        matriz[rielIndex].append(textoCifrado[i])
    }

    val textoDescifrado = StringBuilder()
    for (i in patron.indices) {
        textoDescifrado.append(matriz[patron[i]][0])
        matriz[patron[i]].deleteCharAt(0)
    }

    return textoDescifrado.toString()
}

fun main() {
    val opciones = arrayOf("Cifrar", "Descifrar", "Salir")

    while (true) {
        val eleccion = JOptionPane.showOptionDialog(
            null, "Seleccione una opción:", "Cifrado Rail Fence",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
            null, opciones, opciones[0]
        )

        if (eleccion == 2 || eleccion == JOptionPane.CLOSED_OPTION) break

        val texto = JOptionPane.showInputDialog("Ingrese el texto:") ?: continue
        if (texto.isEmpty()) continue

        val rieles = JOptionPane.showInputDialog("Ingrese el número de rieles:")?.toIntOrNull() ?: continue

        val resultado = if (eleccion == 0) cifrarRailFence(texto, rieles)
        else descifrarRailFence(texto, rieles)

        JOptionPane.showMessageDialog(null, "Resultado: $resultado")
    }
}