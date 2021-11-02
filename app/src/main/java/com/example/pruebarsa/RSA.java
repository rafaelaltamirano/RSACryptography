package com.example.pruebarsa;

import android.util.Base64;
import android.util.Log;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;

public class RSA {

    private KeyPairGenerator kpg;
    private KeyPair kp;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private String descryptedString;
    private byte[] encrytedByte;
    private byte[] descryptedByte;
    private Cipher cipher;

    private final static String CRYPTO_METHOD = "RSA";
    private final static int CRYPTO_BITS = 2048;
    private final static String OPCION_RSA = "RSA/ECB/PKCS1PADDING";


    /**
     * Esta función genera una clave publica y privada aleatoria, se utiliza para pruebas ya que estas claves deben ser proporcionadas por API
     */
    public void generateKayPair() throws Exception {
        kpg = KeyPairGenerator.getInstance(CRYPTO_METHOD);
        kpg.initialize(CRYPTO_BITS);
        kp = kpg.generateKeyPair();
        publicKey = kp.getPublic();
        Log.d("TAG1", "public key -> " + publicKey);
        privateKey = kp.getPrivate();
        Log.d("TAG1", "private key -> " + privateKey);
    }
    /**
     * Esta función códifica un mensaje.
     * @param mensajeAEncriptar mensaje a encriptar
     * @param Key PublicKey en formato KeySpec X509
     * @return mensaje encriptado en base64
     */
    public String encrypt(String mensajeAEncriptar, KeySpec Key) throws Exception {

        cipher = Cipher.getInstance(OPCION_RSA);
       KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(Key);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        encrytedByte = cipher.doFinal(mensajeAEncriptar.getBytes());
        return Base64.encodeToString(encrytedByte, Base64.DEFAULT);
    }
    /**
     * Esta función decódifica un mensaje.
     * @param  mensajeEncriptado a desencriptar
     * @param Key PrivateKey en formato KeySpec PKCS8
     * @return mensaje encriptado
     */
    public String descrypt(String mensajeEncriptado, KeySpec Key) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(Key);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        descryptedByte = cipher.doFinal(Base64.decode(mensajeEncriptado, Base64.DEFAULT));
        descryptedString = new String(descryptedByte);
        return descryptedString;

    }
}
