package com.example.pruebarsa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.security.PublicKey
import java.util.*
import java.security.*
import javax.crypto.*
import android.util.Base64
import java.security.spec.X509EncodedKeySpec

private const val PUBLICKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvRJ7mH/OUUk8yG8X4bLVz3yxVAQBwLqQxccSxwQylkjgG26/6epdVnyEJtz4rXmrPldACkKw2SpKncI2AlXmjntMn7TbmMuUnqCdMTv7o9v9RupK+rY/zSIPd3nQC6Kl+7ZSq470UoaXK5vIiV40crJK4nH0l4cODbM71ESY6M84+QlzOOC9+Oa8L6kT72AlZCtBGuFHgHOxE9Uk4qaxCmddfKQF2582ubSzhG9UBySphS7ArwhvlZCuL+VWY9leSsyXKQdGG1jjweblwiONPd3pLylEjAx1uKy9O6b/5mXyFEGv0hcwnLnY2vW9RKY/BN3p5KQH7x3sl7RyFvtb1QIDAQAB"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        encode(PUBLICKEY,"yopmail@yopmail.com")
    }
}

fun encode(publicKey: String, textDecrypt: String): String {
    val publicKeyFull = "-----BEGIN PUBLIC KEY----\n" + publicKey + "\n-----END PUBLIC KEY-----";
    val publicBytes = Base64.decode(publicKeyFull, Base64.DEFAULT)
    val keySpec = X509EncodedKeySpec(publicBytes)
    val keyFactory: KeyFactory = KeyFactory.getInstance("RSA")
    val pubKey: PublicKey = keyFactory.generatePublic(keySpec)
    val cipher: Cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING") //or try with "RSA"
    cipher.init(Cipher.ENCRYPT_MODE, pubKey)
    val encrypted = cipher.doFinal(textDecrypt.toByteArray())
    val encoded = Base64.encodeToString(encrypted, Base64.DEFAULT)
    return encoded
}



