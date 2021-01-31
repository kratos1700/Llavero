/*
 * Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)
*/
package util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
//import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Kratos1700
 */
public class Codi {

//Metode per generar SHA 
    public static String codificarSHA(String contrasenya) {

        String SH256 = org.apache.commons.codec.digest.DigestUtils.sha256Hex(contrasenya);

        return SH256;
    }

//Metode per crear la clau per a codificar pasant un string i codificant a SH1
    private SecretKeySpec crearClau(String clau) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        //byte[] SH1= org.apache.commons.codec.digest.DigestUtils.sha1(clau );
        byte[] clauEncriptacio = clau.getBytes("UTF-8");

        MessageDigest sha = MessageDigest.getInstance("SHA-1");

        clauEncriptacio = sha.digest(clauEncriptacio);
        clauEncriptacio = Arrays.copyOf(clauEncriptacio, 16);

        SecretKeySpec secretKey = new SecretKeySpec(clauEncriptacio, "AES");

        return secretKey;
    }
//Metode per Encriptar AES

    public String encriptar(String cadena, String clau) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        // pasem la clau generada
        SecretKeySpec secretKey = this.crearClau(clau);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] dadesEncriptar = cadena.getBytes("UTF-8");
        byte[] bytesEncriptats = cipher.doFinal(dadesEncriptar);
        String encriptat = Base64.getEncoder().encodeToString(bytesEncriptats);

        return encriptat;

    }
// metode per desencriptar AES

    public String desenCriptar(String codificat, String clau) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        // pasem la clau generada
        SecretKeySpec secretKey = this.crearClau(clau);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] bytesEncriptats = Base64.getDecoder().decode(codificat);
        byte[] dadesDesencriptades = cipher.doFinal(bytesEncriptats);
        String dades = new String(dadesDesencriptades);

        return dades;

    }
}
