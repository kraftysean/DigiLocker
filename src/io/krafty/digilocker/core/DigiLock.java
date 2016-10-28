package io.krafty.digilocker.core;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class DigiLock {

    static String message = "message to be encryted";
    static String secretKey = "16bytepassphrase";  // 16 bytes
    static byte[] secretKeyBytes = secretKey.getBytes();
    static byte[] iv = new byte[16];    // Initialization Vector

    public static void main(String[] args) {
        try {
            byte[] encryptedText = encrypt(message, secretKeyBytes);
            String encryptedTextBase64 = Base64.getEncoder().encodeToString(encryptedText);
            byte[] decodedEncryptedText = Base64.getDecoder().decode(encryptedTextBase64);
            String decryptedText = decrypt(decodedEncryptedText, secretKeyBytes);
/*
            System.err.println(printEncryptedBytes(encryptedText));
            System.err.println(encryptedTextBase64);
            System.err.println(printEncryptedBytes(decodedEncryptedText));
            System.err.println(decryptedText);
*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static byte[] encrypt(String plainText, byte[] encryptionKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(encryptionKey, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal(plainText.getBytes());
    }

    public static String decrypt(byte[] cipherText, byte[] encryptionKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(encryptionKey, "AES");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        return new String(cipher.doFinal(cipherText));
    }

    private static String printEncryptedBytes(byte[] encryptedText) {
        StringBuilder sb = new StringBuilder();
        for(byte b : encryptedText)
            sb.append(new Integer(b) + " ");
        return sb.toString().trim();
    }
}
