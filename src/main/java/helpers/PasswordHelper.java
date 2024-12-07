package helpers;

import jakarta.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHelper {

    private PasswordHelper() {}

    public static String encrypt(String password){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean passwordIsCorrect(String inputPassword, String hashPassword) {
        String encryptedPwd = encrypt(inputPassword);
        return encryptedPwd.equals(hashPassword);
    }
}
