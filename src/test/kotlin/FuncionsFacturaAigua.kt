import org.example.descompteBonoSocial
import org.example.descomptePerFamilia
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FuncionsFacturaAigua {

    @Test
    fun testConsumAiguaValorNormal(){
        val consumNormal = 100
        val preuConsumNormal = 0.15
        val resultat = 100*0.15
     //   assertEquals(Pair(consumNormal * preuConsumNormal, consumNormal), resultat)


    }

    @Test
    fun testDescomptePerFamiliaOpcioUsuariIstInt(){
        val opcioUsuari= readln().toInt()
        assertTrue(opcioUsuari is Int)
    }

    @Test
    fun testNoTeDescomptePerFamilia(){

        assertEquals(0.0, descomptePerFamilia(50.0))
    }

    @Test
    fun testBonoSocialEsBoolea(){
        val teBonoSocial = true
//        assertTrue(descompteBonoSocial(teBonoSocial) is Pair<Double, Double>)
    }

    @Test
    fun testBonoSocialDescompte(){
        val preuResultant= descompteBonoSocial(100.0)
        assertEquals(Pair(0.0, 0.0), preuResultant)

    }
}