package org.example

/**
 * Funció que dóna la benvinguda a l'usuari amb colors
 * @author Silvia Serra
 * @since 14/01/2024
 */
fun benvinguda() {
    println("$CYAN_BOLD BENVINGUT AL CALCUL DE LA FACTURA DE L'AIGUA $RESET")
}

/**
 * Funció que calcula el consum d'aigua per litres
 * @author Silvia Serra
 * @since 14/01/2024
 * @param preuConsumNormal preu del litre per un consum menor de 200 litres al mes
 * @param preuConsumMaxim preu del litre per un consum major de 200 litres al mes
 * @return preu del consum total de l'usuari per litre
 */
fun consum(): Pair<Double, Int> {
    val preuConsumNormal = 0.15
    val preuConsumMaxim = 0.30

    /* Pedimos el consum del usuari y comproben que el consum es un nombre */
    val consumUsuari =
        readInt(
            "$BLUE_BOLD Si us plau indiqui el consum de litres d'aigua de la seva vivenda $RESET",
            "$RED_BOLD Ingressi un número vàlid $RESET"
        )

    if (consumUsuari in 50..200)
        return Pair((consumUsuari * preuConsumNormal), consumUsuari)

    if (consumUsuari > 200)
        return Pair((consumUsuari * preuConsumMaxim), consumUsuari)

    return Pair(0.0, consumUsuari)
}

/**
 * Funció que calcula eldescompte segons familia monomarental, nombrosa.
 * @author Silvia Serra
 * @since 14/01/2024
 * @param opcioUsuari demana l'opcio a l'usuari del menu donat.
 * @return preu del consum total de l'usuari per litre amb el descompte aplicat.
 */
fun descomptePerFamilia(consum: Double): Double {
    println("Indiqui si té descompte per familia nombrosa o monomarental")
    println("1- Familia Nombrosa")
    println("2- Familia Monomarental")
    println("3- No")

    val opcioUsuari = readln().toInt()

    when (opcioUsuari) {
        1 -> {
            return (0.50 * consum)
        }

        2 -> {
            println(" $BLUE_BOLD Indiqui els fills que conviuen a la vivenda $RESET")
            val fills = readln().toInt()

            if (((fills + 1) * 0.10) > 0.50) {
                return  (0.50 * consum)
            } else {
                return ((fills + 1) * 0.10) * consum
            }
        }

        else -> return 0.0
    }

}

/**
 * Funció que calcula el consum d'aigua per litres
 * @author Silvia Serra
 * @since 14/01/2024
 * @param teBonoSocial booleà per dir si té bono social o no.
 * @return preu del consum total de l'usuari per litre amb el descompte de bono social.
 */
fun descompteBonoSocial(preuPerConsum: Double): Pair<Double, Double> {
    val teBonoSocial = readBoolean("$BLUE_BOLD Té un bono social a aplicar? Si:true - No:false $RESET", "$RED_BOLD Error $RESET")

    if (teBonoSocial) return Pair((preuPerConsum * 0.80), 3.0) //Els 6euros de la quota fixa amb bono social, resten 3euros

    return Pair(0.0, 0.0)
}

/**
 * Funció que mostra la factura de l'aigua
 * @author Silvia Serra
 * @since 14/01/2024
 * @param consum : litres consumits.
 * @param preuConsum : preu total dels litres consumits.
 * @param preuFixe : quota de manteniment de 6euros.
 * @param descompteConsum : descompte per tipus de familia: monomarental, nombrosa.
 * @param descompteFixe : descompte per bono social.
 * @return preu desglossat de tota la factura
 */
fun mostrarFactura(
    consum: Int,
    preuConsum: Double,
    preuFixe: Double,
    descompteConsum: Double,
    descompteFixe: Double,
    totalFactura: Double
) {
    println("$BLUE_BOLD -- Factura Aigua -- $RESET")
    println("$BLUE | Consum aigua: ${consum}L               |")
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
    println(" | Total a pagar: ${totalFactura}€         |$RESET")
}

fun main() {
    val preuFixeManteniment = 6.0
    val totalFactura: Double

    benvinguda()
    val (preuPerConsum, consum) = consum()

    /* Calcula descompte per familia nombrosa*/
    val descomptePerFamilia = descomptePerFamilia(preuPerConsum)

    /* Calcula descompte per Bono Social */
    val (descomptePerBonoSocialPerConsum, descomptPreuFixe) = descompteBonoSocial(preuPerConsum)

    /* Comprobem si el descompte per familia es maJor que el descompte per bono social */
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

