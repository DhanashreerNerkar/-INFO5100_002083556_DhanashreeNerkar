import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

public class Main {

    // Generate AES-256 key
    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);  // AES-256
        return keyGenerator.generateKey();
    }

    // AES encryption and decryption methods
    public static String encryptAES(String message, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        byte[] iv = new byte[12];  // GCM recommends 12 bytes IV
        new SecureRandom().nextBytes(iv);
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);  // 128-bit authentication tag length
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] cipherText = cipher.doFinal(message.getBytes());
        byte[] encryptedMessage = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, encryptedMessage, 0, iv.length);
        System.arraycopy(cipherText, 0, encryptedMessage, iv.length, cipherText.length);
        return Base64.getEncoder().encodeToString(encryptedMessage);
    }

    public static String decryptAES(String encryptedMessage, SecretKey key) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);
        byte[] iv = new byte[12];  // First 12 bytes are the IV
        System.arraycopy(encryptedBytes, 0, iv, 0, iv.length);
        byte[] cipherText = new byte[encryptedBytes.length - iv.length];
        System.arraycopy(encryptedBytes, iv.length, cipherText, 0, cipherText.length);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText);
    }

    // RSA encryption and decryption methods
    public static String encryptRSA(String message, RSAPublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherText = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decryptRSA(String encryptedMessage, RSAPrivateKey privateKey) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] plainText = cipher.doFinal(encryptedBytes);
        return new String(plainText);
    }

    // RSA signing and verification methods
    public static String signMessage(String message, RSAPrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        byte[] signedMessage = signature.sign();
        return Base64.getEncoder().encodeToString(signedMessage);
    }

    public static boolean verifySignature(String message, String signedMessage, RSAPublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes());
        byte[] signedMessageBytes = Base64.getDecoder().decode(signedMessage);
        return signature.verify(signedMessageBytes);
    }

    public static void main(String[] args) throws Exception {
        // Generate AES Key (256-bit)
        SecretKey aesKey = generateAESKey();

        // Generate RSA Keys for Alice and Bob
        KeyPairGenerator rsaKeyPairGen = KeyPairGenerator.getInstance("RSA");
        rsaKeyPairGen.initialize(2048);
        KeyPair aliceKeyPair = rsaKeyPairGen.generateKeyPair();
        KeyPair bobKeyPair = rsaKeyPairGen.generateKeyPair();

        RSAPublicKey alicePublicKey = (RSAPublicKey) aliceKeyPair.getPublic();
        RSAPrivateKey alicePrivateKey = (RSAPrivateKey) aliceKeyPair.getPrivate();
        RSAPublicKey bobPublicKey = (RSAPublicKey) bobKeyPair.getPublic();
        RSAPrivateKey bobPrivateKey = (RSAPrivateKey) bobKeyPair.getPrivate();

        // 1. Symmetric Encryption (AES-256)
        String message = "Hello Bob, this is Alice!";
        System.out.println("Original message: " + message);

        // Alice encrypts the message with AES
        String encryptedAESMessage = encryptAES(message, aesKey);
        System.out.println("Encrypted message (AES): " + encryptedAESMessage);

        // Bob decrypts the message with AES
        String decryptedAESMessage = decryptAES(encryptedAESMessage, aesKey);
        System.out.println("Decrypted message (AES): " + decryptedAESMessage);

        // 2. Asymmetric Encryption (RSA-2048)
        // Alice encrypts the message with Bob's RSA public key
        String encryptedRSAMessage = encryptRSA(message, bobPublicKey);
        System.out.println("Encrypted message (RSA): " + encryptedRSAMessage);

        // Bob decrypts the message with his private key
        String decryptedRSAMessage = decryptRSA(encryptedRSAMessage, bobPrivateKey);
        System.out.println("Decrypted message (RSA): " + decryptedRSAMessage);

        // 3. Digital Signature (RSA-2048)
        // Alice signs the message
        String signedMessage = signMessage(message, alicePrivateKey);
        System.out.println("Signed message: " + signedMessage);

        // Bob verifies Alice's signature
        boolean isSignatureValid = verifySignature(message, signedMessage, alicePublicKey);
        System.out.println("Is the signature valid? " + isSignatureValid);
    }
}