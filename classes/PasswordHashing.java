package classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing
{
    public static String hashPassword(String password) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Convert the password to a byte array and hash it
            byte[] hashedBytes = digest.digest(password.getBytes());

            // Convert the hashed byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                // Convert each byte to a 2-digit hexadecimal value
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: SHA-256 algorithm not found.", e);
        }
    }
}
