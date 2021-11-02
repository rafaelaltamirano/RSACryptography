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
    private final static String PUBLICKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvRJ7mH/OUUk8yG8X4bLVz3yxVAQBwLqQxccSxwQylkjgG26/6epdVnyEJtz4rXmrPldACkKw2SpKncI2AlXmjntMn7TbmMuUnqCdMTv7o9v9RupK+rY/zSIPd3nQC6Kl+7ZSq470UoaXK5vIiV40crJK4nH0l4cODbM71ESY6M84+QlzOOC9+Oa8L6kT72AlZCtBGuFHgHOxE9Uk4qaxCmddfKQF2582ubSzhG9UBySphS7ArwhvlZCuL+VWY9leSsyXKQdGG1jjweblwiONPd3pLylEjAx1uKy9O6b/5mXyFEGv0hcwnLnY2vW9RKY/BN3p5KQH7x3sl7RyFvtb1QIDAQAB";
    private final static String PRIVATEKEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC9EnuYf85RSTzIbxfhstXPfLFUBAHAupDFxxLHBDKWSOAbbr/p6l1WfIQm3Piteas+V0AKQrDZKkqdwjYCVeaOe0yftNuYy5SeoJ0xO/uj2/1G6kr6tj/NIg93edALoqX7tlKrjvRShpcrm8iJXjRyskricfSXhw4NszvURJjozzj5CXM44L345rwvqRPvYCVkK0Ea4UeAc7ET1STiprEKZ118pAXbnza5tLOEb1QHJKmFLsCvCG+VkK4v5VZj2V5KzJcpB0YbWOPB5uXCI4093ekvKUSMDHW4rL07pv/mZfIUQa/SFzCcudja9b1Epj8E3enkpAfvHeyXtHIW+1vVAgMBAAECggEALEpAGaEWu4PkcS9U8NHrtAcJoxytLHCHqUzQ4T3ie8HmfYab/jegVbolWtU4i6qMqrXVkpG8y6JUiFAORs3IjGflsyOyrUBDOEV3jSj38ZJ+6jXICqLWnz7zUJGAYrlfGG/FPdrm8M4Ga3i6qTMgJh4N3K3FFhnU+PCbe0X2a9J3BrjilsrYW8iSBr6yryWHQdztIQb/uKvZ+IXzxeviPCroGUTvRcNVC4NH36s5uR4GPWpRgGCKROedl2RUcaHoo/oOTrC0PkqgkBlhjsWxaJD/lHVXqMN6DgRyA5rF+2KAoHGQ4UMP6xeSXvEtgSZhwQH81MSgXw7tunNvmjVSoQKBgQD8D7uWZdQjHphFZ6wFOWAbD3Id+6gaPRT6honMjKfk89+CoQBz/2jye9CADSpXMHD5K88QDH2U23zZ7xJsFgpqpKlUDCihf56sZ+iE9Rsh1TNhtjbJHLUWWjLgIFqjB0QXchDgI8YTTg4mRUWN5oBBp/4jBWLdPmMWw2aPaX/iKwKBgQDABsmjd3G9Btj4/mefWwQRraITR/nMSOnO5EY0QKgVMSAhOZqfbBw0yUq7OIS7Ic/Y+sgplhoVu8e/dXxoAN";
    private String message = "yolanda@yopmail.com";
    private final static String publicKeyFull = "-----BEGIN PUBLIC KEY----$PUBLICKEY-----END PUBLIC KEY-----";

    /**
     * Esta función genera una clave publica y privada aleatoria, se utiliza para pruebas ya que estas claves deben ser proporcionadas por backend
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
     * @return mensaje encriptado en base64
     */
    public String encrypt(String mensajeAEncriptar, KeySpec Key) throws Exception {

        cipher = Cipher.getInstance(OPCION_RSA);
       // KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      //  PublicKey publicKey = keyFactory.generatePublic(Key);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        encrytedByte = cipher.doFinal(mensajeAEncriptar.getBytes());
        return Base64.encodeToString(encrytedByte, Base64.DEFAULT);
    }
    /**
     * Esta función decódifica un mensaje.
     * @param  mensajeEncriptado a desencriptar
     * @return mensaje encriptado
     */
    public String descrypt(String mensajeEncriptado, KeySpec Key) throws Exception {
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        PrivateKey privateKey = keyFactory.generatePrivate(Key);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        descryptedByte = cipher.doFinal(Base64.decode(mensajeEncriptado, Base64.DEFAULT));
        descryptedString = new String(descryptedByte);
        return descryptedString;

    }
}
