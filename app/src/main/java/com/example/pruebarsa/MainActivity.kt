package com.example.pruebarsa

import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.security.spec.KeySpec
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec


private const val PUBLICKEY =
    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7KmWot7bz1kcvIpBN6XMgDPzcOBONLv9EvkWpFlf6tLAmgXUKzU57to8/Yd3MteLi71YTHYKuqvzECpr5hz+FADwbPlYOFYP6efyEiPZ/QbQu9Y0LJdCcrj6vsk2+3SU/eaDric+N+GuWkG9c2zbAF1rv1NFyQv4AFprhIOwyZ+TBelU4G+CNYfMJQ1ftxpTQzDgV9MBcRXDUXheWFstNGseNMSjWw7KPJbcUEWJ8ez33ilAwf1abe0bRZap8SJXBIQ+KKI4//yfk/QYAmBNzsgnzBYuHkAfMfO4oqdEYfvxEtAFJt0ntmMncFXKDBOkfL9KjlbozgpSZn4CJwn9jwIDAQAB"
private const val PRIVATEKEY =
    "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDsqZai3tvPWRy8ikE3pcyAM/Nw4E40u/0S+RakWV/q0sCaBdQrNTnu2jz9h3cy14uLvVhMdgq6q/MQKmvmHP4UAPBs+Vg4Vg/p5/ISI9n9BtC71jQsl0JyuPq+yTb7dJT95oOuJz434a5aQb1zbNsAXWu/U0XJC/gAWmuEg7DJn5MF6VTgb4I1h8wlDV+3GlNDMOBX0wFxFcNReF5YWy00ax40xKNbDso8ltxQRYnx7PfeKUDB/Vpt7RtFlqnxIlcEhD4oojj//J+T9BgCYE3OyCfMFi4eQB8x87iip0Rh+/ES0AUm3Se2YydwVcoME6R8v0qOVujOClJmfgInCf2PAgMBAAECggEBAJm3AP9irvS7VZ3kUr3ZM72lgJ41Ira/z32uShWPc2xrXOvtk0RJOwq3t1uYzEQ6mgm2fw6SsR9UJfi/BPweoqzNe1vcjH9y7r0niSRdIu5kqdkHJTY9PMyU91BKEys6KrfLLIZGOPRE4mD/SBUbhBd1llFbvfz6iCd6k1Er0/zwJ+YGC2KOpW1v8KdhPbVjP2c800N8/U96M3j1sa6Thu73BQrgdOjtxUTv6aLxXVemn65eqhLOUTYLSla+fOeWoecUPS4JxbPdjfwO6KHtAphMheNis+t7Wop22k02gEfufWslYx+zDAIRf76mbFMIuraCMLTvSmLE8Y0oYcakA+ECgYEA99kL4AAMEV+l6UggiCWUgiF0L3RRFbiRuAuZZDVFn1HwXtatxEt4EbjztAeniSE3aTUOjX1ferjQmGwQjPWwA3IJ4j9EX4ykXjA6ZwFiFlrZ5GQ7XStasBeO0HM2oMLC5U4VxTnHDon8KUg/OxavwLvc6U0sSnxRtdqb0kdYfv8CgYEA9HJblKnExzoOYKgrqg5twxFp39/kO9AkVKxlwpVqf+YpDXqG2qyhJ2i0kIg0J3rAKHU2h9Z3kHPA8eeobnXH+DVfpipWLt1AJusEncWeVN2e8uuTr6wGaKzYjXHoj/QTkHbxaLBWX0phGaPQaq9hXYMvzzoIqUOxw/3wvEJ1EXECgYAbLBeKMlcgXH0jCz5NztjuP16uiTEOkZKgO91QSCre4avZqe2ZFLEQOFe3iBNNDHA3v5Jd3r3mBXcRDt/tpE3swsbkoA0N8KxgiycL/fHUA5fZ20dK2qLxpVOJ9OerTk7xttLaMZBqPqR/niUYhE82cSIyDbzqWaDXsCTfM+U23wKBgFTldqr9/yqWHjIxleCc3EAYRyYKkzC2zDdgHINk8zTyUaWtvTIrLFkMg1GDDGH9vskOVF7+/E199i/NnfCnFUk9+Dc0kvP5wXMCwnqtDJ06zx5c9NDZNOybwyX0vqloQnc06AjM3WKA6ZJHE3ZJ7M3WZv9Yj3OB4DZKdpZbml7xAoGAXmJnAMyrnl7uVFF2Gxyg/suW7S668A0EDcQ/KKgSzEbrl2rmZ+16DYW8NWgqrpiSe0KtuBUcxH1q1ZnCT8ivxXFBleF8S6YUAKWbd1QGBciV+fosQzt3w0gSVG9FgmhFlWz/ci5BiVWuHft66GTHG/3pMZCJYxSVdl7jLm8AHAU="

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rsa = RSA()

        try {
//            rsa.generateKayPair() //Solo se utiliza para pruebas con claves autogeneradas
            val mensajeEncriptado: String =
                rsa.encrypt("yolanda@yopmail.com", getPublicKey(PUBLICKEY))
            Log.d("TAG1", "mensaje encriptado -> $mensajeEncriptado")
            val mensajeDesencirptado: String =
                rsa.descrypt(mensajeEncriptado, getPrivateKey(PRIVATEKEY))
            Log.d("TAG1", "mensaje desencritpatado -> $mensajeDesencirptado")
        } catch (e: Exception) {
            Log.d("TAG1", e.toString())
        }
    }
}
/**
 * Esta función parsea la public key de formato string a formato KeySpec X509 que es la utilizada para la clave publica
 * @param publicKey public key en formato string ya desencriptada en aes
 * @return KeySpec
 */
fun getPublicKey(publicKey: String): KeySpec {
    val publicKeyFull = publicKey
    val publicBytes = Base64.decode(publicKeyFull, Base64.DEFAULT)
    val keySpec = X509EncodedKeySpec(publicBytes)
    return keySpec
}
/**
 * Esta función parsea la private key de formato string a formato KeySpec PKCS8 que es la utilizada para la clave privada
 * @param privateKey public key en formato string ya desencriptada en aes
 * @return KeySpec
 */
fun getPrivateKey(privateKey: String): PKCS8EncodedKeySpec {
    val publicKeyFull = privateKey
    val privateBytes = Base64.decode(publicKeyFull, Base64.DEFAULT)
    val keySpec = PKCS8EncodedKeySpec(privateBytes)
    return keySpec
}



