package com.example.pruebarsa;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import android.util.Base64;

import javax.crypto.Cipher;

public class RSAData {


    public void encrypt() {
        String publicKey = new String();
        String secretMessage = new String();
        publicKey = "-----BEGIN PUBLICString publicKey = new String(); KEY----\n" + publicKey + "\n-----END PUBLIC KEY-----";
        secretMessage = "Baeldung secret message";

    }

    public static PublicKey getKey(String key){
        try{
            byte[] byteKey = Base64.decode(key.getBytes(), Base64.DEFAULT);
            X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");

            return kf.generatePublic(X509publicKey);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

//    public static byte[] encrypt(String data, String publicKey) {
//        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
//        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
//        return cipher.doFinal(data.getBytes());
//    }
}



