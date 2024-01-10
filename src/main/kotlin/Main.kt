package org.example

fun benvinguda(){
    println ("$BLUE_BOLD BENVINGUT AL CALCUL DE LA FACTURA DE L'AIGUA $RESET")
}

fun consum(): Double {
    var consumUsuari: Int

    do {
        println("Si us plau indiqui el consum de litres d'aigua de la seva vivenda ")
        consumUsuari = readLine()?.toIntOrNull() ?: 0
        val preuConsumMinim: Double = 0.10
        val preuConsumNormal: Double = 0.15
        val preuConsumMaxim: Double = 0.30
        var preuTotal = 0.0

        if (consumUsuari <= 50) {
            preuTotal = consumUsuari.toDouble() * preuConsumMinim
        } else if (consumUsuari >= 51 && consumUsuari <= 200) {
            preuTotal = consumUsuari.toDouble() * preuConsumNormal
        } else if (consumUsuari > 201 && consumUsuari < 500) {
            preuTotal = consumUsuari.toDouble() * preuConsumMaxim
        } else {
            println("Ha superat el límit, contacti amb nosaltres al telèfon 93-9999999 per pagar l'import amb un suplement")
        }

        println("El seu consum de: $consumUsuari que té un cost de : $preuTotal")
        return preuTotal

    } while (consumUsuari < 0 || consumUsuari > 500)
    println("Ingressi un número vàlid")
    return 0.0
}

fun mantenimentPreu (preuTotal:Double ):Double{
    val manteniment: Double= 6.0
    var preuTotal = 0
    var preuAmbManteniment= manteniment+preuTotal

    return preuAmbManteniment
}

fun descomptePerFamilia(preuTotal: Double, ):Double{
    do {
        println("Indiqui si té descompte per familia nombrosa o monoparental")
        println("1- Familia Nombrosa")
        println("2- Familia Monoparental")
        println("3- No té descomptes")

        var opcioUsuari = readln().toInt()

        println("Indiqui els progenitors que conviuen a la vivenda")
        var progenitors= readln().toInt()
        println("Indiqui els fills que conviuen a la vivenda")
        var fills= readln().toInt()
        var habitantsTotalsVivenda=0

        var preuAmbDescompte= 0.0

        if(opcioUsuari==1 && progenitors==2 && fills>=3){
            preuAmbDescompte= (preuTotal*10)/5
            println("La familia nombrosa té un descompte del 50%. El seu import és de $preuAmbDescompte")

        }else if (opcioUsuari==2 && progenitors==1 && fills>=1){
            preuAmbDescompte= (preuTotal*10)/habitantsTotalsVivenda
            println("La familia monoparental té un descompte del 10% per habitant. El seu import és de $preuAmbDescompte")
                //TODO aplicar un maxim del 50%
        }else{
            println("No hi ha descomptes a aplicar, el seu preu és de: $preuTotal")
        }

        return preuAmbDescompte

    }while (opcioUsuari<0 || opcioUsuari>4)
}

fun preuAmbBonoSocial(bonoSocial:Boolean, preuTotal: Double):Double{
    println("Té un bono social a aplicar?")
    println("s- si, n- no")
    val teBonoSocial = readln().lowercase()

        if (teBonoSocial== "s") return (preuTotal*80)/10

        return preuTotal

}
fun main() {
    benvinguda()
    var preuTotalFactura = consum()
    println("El preu del consum de la teva vivenda és: $preuTotalFactura")

    var preuTotalDescompteTipusFamilia= descomptePerFamilia()

    println("El preu de la factura amb el descompte de familia és de $preuTotalDescompteTipusFamilia")


    println("Preu total amb manteniment: ${mantenimentPreu(preuTotalFactura)}")
}

