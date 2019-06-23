package dioxo.migi.Objets.Objs.Seguridad;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public interface Encriptar_Interface {

    String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
    byte[] getSalt() throws NoSuchAlgorithmException;
    byte[] fromHex(String hex) throws NoSuchAlgorithmException;
    String toHex(byte[] array) throws NoSuchAlgorithmException;
    boolean validatePassword(String originalPassword, String storedPassword)  throws NoSuchAlgorithmException, InvalidKeySpecException;

}
