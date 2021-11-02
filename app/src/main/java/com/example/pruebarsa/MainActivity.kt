package com.example.pruebarsa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import java.lang.Exception
import java.security.KeyFactory
import java.security.spec.KeySpec
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec


private const val PUBLICKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvRJ7mH/OUUk8yG8X4bLVz3yxVAQBwLqQxccSxwQylkjgG26/6epdVnyEJtz4rXmrPldACkKw2SpKncI2AlXmjntMn7TbmMuUnqCdMTv7o9v9RupK+rY/zSIPd3nQC6Kl+7ZSq470UoaXK5vIiV40crJK4nH0l4cODbM71ESY6M84+QlzOOC9+Oa8L6kT72AlZCtBGuFHgHOxE9Uk4qaxCmddfKQF2582ubSzhG9UBySphS7ArwhvlZCuL+VWY9leSsyXKQdGG1jjweblwiONPd3pLylEjAx1uKy9O6b/5mXyFEGv0hcwnLnY2vW9RKY/BN3p5KQH7x3sl7RyFvtb1QIDAQAB"
private const val PRIVATEKEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCmct6jfeIFbBL0Qxq9t5joEqc4eOPKdUTKOrTpfiyad1kWmX5z/IeHFyGs9GCxibgrsQfCTswK/fbKgYu1EDY6c6vL6H03uo3CjuodhHJLLK/M1gYPMzzwEX1Em/9JnwUEw26dcECDS6wNtH6SBTeSpsRvdwZO4OiKTuNiSiy5YMeuQdOD3eu6AZ8kECB6DIXppYBfVsmnytmm4U9uTfD7rGTmqY25a8NFunmxcPuRb4A8XCre3E88RlN4w2tju0ckJxB30BDipeeRuA3zNhg58tC7TI5RvR8cGKtiEh6/RN01G11kP2feJ9UNiJ4mTWNWqYbKUObiaEonXtVGm5hlAgMBAAECggEAYdBlV6yVU1xkcxiwMUcgW1kC3ePk8UgiybjgMcrNCWUTfH1tW1nOhyunxYzC+fIVwc9B4QUPUXJXQkAIyrmhNI5fKfARDdvajLgRipEX3WeAVB595xbUO7v7YoNN7YS+nUL/0RNzRfUFQfSM4OCh/NN14o8OrPHslcKwS0WNrM3pSEFvSKAPUeIb+45UBB7zh/oU6/QEecER3zwgA+PSWvJzsy20vLE4Ejehig9pkSQJC1P1SxvDBCFHlPZ6B5x1FBQw9iTVbhgdOA5NMdL2bTq9rSZmbxtirSRvQwktHzxLdwOYYDIIQ8RhqSvs2PDBcm/gJ8DPgilb5T72M0ZlrQKBgQDQ9poFq5dH0AnCTYQ3FfWr4uACKl0RWpzHuM9BFj0y3xA//sRsZ5YQ50y263XStssVa9vIgCyHVCnG4VMMYL/tfrKSAe/+g7TbqqgcMrNbuCzjhOy+MWRaqQHH5b+PMCOfKnR+7mM9oA/Jn9hpO/AoJqBmB8tQ2dlF0E06aCVAIwKBgQDL6mNZPasqMvvbw3KEFFZmevPAOcuGvf9nN4p/+5rw4/pWBGHAK2h6zzgFReesOUsE4qQ1QWmUeNtgUEZO7NEwjOKz5qURd6N8aQ5oPhLPiv+LWrvCLxrCCjDQ+Gav6hskbaIsbPoN0iP//DEUpr054+JfFDVVdPy1BdeSoACJ1wKBgHm2ud4FpXdJwPVxK0reupsk6e+49z6tddaLWgUCTCE+dnugDI9MAsfTwgZlvdo/aKkQixMNPWKR7keOZU43aHOQMbkD8eNgmc69Z3ANRBVvgI4BldfucxKvkH773okrtAP4uaRZfGqfubJqLaaWJ/trDFCBx7ZPPdQBUYpssQ3bAoGAdj6le5NCFQdZetc8KZDthSGZBQe+mJTGbKENZCePck6Q1J5X3KqPPF+7GXuu2vSiJuQR2nvi8GCgvZnvG0dCCsg3kaCWaamZmMQGwbX1DHqKstFbfT/5YTmtkw/n4+x57sbQOri6y5bmn0MrZtFFezNFCmNahgJ/o60Ok4zKoyMCgYAmKAFAdizh+O6tBh6qq4if6D0T5pROTdfYCduRzK852ZWJev9q06O27G8DWyYSn8G/DiEfKUVqjxmsnveggfRjSYhCCnE2hI/x7BXzF3b85qemYBMaoxMgNJb1cc4uX6tvq2CK/z4HksEsXK8yKSoqM+nJ/7SPAd2mduUlD6RDyA=="
private const val publicKeyFull = "-----BEGIN PUBLIC KEY----\n$PUBLICKEY\n-----END PUBLIC KEY-----";


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rsa = RSA()

        try {
            rsa.generateKayPair()
            val mensajeEncriptado: String = rsa.encrypt("yopmail@yopmail.com",  getPublicKey(PUBLICKEY))
            Log.d("TAG1", "mensaje encriptado -> $mensajeEncriptado")
            val mensajeDesencirptado: String = rsa.descrypt(mensajeEncriptado,getPrivateKey(PRIVATEKEY))
            Log.d("TAG1", "mensaje desencritpatado -> $mensajeDesencirptado")
        } catch (e: Exception) {
            Log.d("TAG1", e.toString())
        }
    }
}

fun getPublicKey(publicKey: String): KeySpec {
    val publicKeyFull = publicKey
    val publicBytes = Base64.decode(publicKeyFull, Base64.DEFAULT)
    val keySpec = X509EncodedKeySpec(publicBytes)
   return keySpec
}

fun getPrivateKey(privateKey: String): PKCS8EncodedKeySpec  {
    val publicKeyFull = privateKey
    val privateBytes = Base64.decode(publicKeyFull, Base64.DEFAULT)
    val keySpec = PKCS8EncodedKeySpec(privateBytes)

    return keySpec
}



