package org.example

fun benvinguda() {
    println("$BLUE_BOLD BENVINGUT AL CALCUL DE LA FACTURA DE L'AIGUA $RESET")
}

fun consum(): Pair<Double, Int> {
    val preuConsumNormal: Double = 0.15
    val preuConsumMaxim: Double = 0.30

    /* Pedimos el consum del usuari y comproben que el consum es un nombre */
    val consumUsuari =
        readInt(
            "Si us plau indiqui el consum de litres d'aigua de la seva vivenda",
            "Ingressi un número vàlid"
        )

    if (consumUsuari in 50..200)
        return Pair((consumUsuari * preuConsumNormal), consumUsuari)

    if (consumUsuari > 200)
        return Pair((consumUsuari * preuConsumMaxim), consumUsuari)

    return Pair(0.0, consumUsuari)
}

fun descomptePerFamilia(consum: Double): Double {
    println("Indiqui si té descompte per familia nombrosa o monomarental")
    println("1- Familia Nombrosa")
    println("2- Familia Monomarental")
    println("3- No")

    var opcioUsuari = readln().toInt()

    when (opcioUsuari) {
        1 -> {
            return (0.50 * consum)
        }

        2 -> {
            println("Indiqui els fills que conviuen a la vivenda")
            var fills = readln().toInt()

            if (((fills + 1) * 0.10) > 0.50) {
                return  (0.50 * consum)
            } else {
                return ((fills + 1) * 0.10) * consum
            }
        }

        else -> return 0.0
    }

}

fun descompteBonoSocial(preuPerConsum: Double): Pair<Double, Double> {
    val teBonoSocial = readBoolean("Té un bono social a aplicar? s - n", "Error")

    if (teBonoSocial) return Pair((preuPerConsum * 0.80), 3.0)

    return Pair(0.0, 0.0)
}

fun mostrarFactura(
    consum: Int,
    preuConsum: Double,
    preuFixe: Double,
    descompteConsum: Double,
    descompteFixe: Double,
    totalFactura: Double
) {
    println(" -- Factura Aigua -- ")
    println(" | Consum aigua: ${consum}L               |")
    println(" | Preu consum aigua: ${preuConsum}€      |")
    println(" | Descompte consum: -${descompteConsum}€ |")
    println(" | -----------------------------------    |")
    println(" | Total preu consum: ${preuConsum - descompteConsum}€ |")
    println(" | -----------------------------------    |")
    println(" | Preu fixe manteniment: ${preuFixe}€ |")
    println(" | Descompte preu fixe: -${descompteFixe}€ |")
    println(" | -----------------------------------     |")
    println(" | Total preu fixe: ${preuFixe - descompteFixe}€ |")
    println(" | -----------------------------------     |")
    println(" | Total a pagar: ${totalFactura}€         |")
}

fun main() {
    val preuFixeManteniment: Double = 6.0
    val totalFactura: Double

    benvinguda()
    var (preuPerConsum, consum) = consum()

    /* Calcula descompte per familia nombrosa*/
    var descomptePerFamilia = descomptePerFamilia(preuPerConsum)

    /* Calcula descompte per Bono Social */
    var (descomptePerBonoSocialPerConsum, descomptPreuFixe) = descompteBonoSocial(preuPerConsum)

    /* Comprobem si el descompte per familia es mayor que el descompte per bono social */
    if (descomptePerFamilia > (descomptePerBonoSocialPerConsum + descomptPreuFixe)) {
        totalFactura = (preuPerConsum - descomptePerFamilia) + preuFixeManteniment
        mostrarFactura(consum, preuPerConsum, preuFixeManteniment, descomptePerFamilia, 0.0, totalFactura)
    } else {
        totalFactura = (preuPerConsum - descomptePerBonoSocialPerConsum) + (preuFixeManteniment - descomptPreuFixe)
        mostrarFactura(
            consum,
            preuPerConsum,
            preuFixeManteniment,
            descomptePerBonoSocialPerConsum,
            descomptPreuFixe,
            totalFactura
        )
    }
}

