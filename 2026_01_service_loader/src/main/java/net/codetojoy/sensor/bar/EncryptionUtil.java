package net.codetojoy.sensor.bar;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Security;
import java.util.Base64;

/**
 * Utility to encrypt sensor data files for Version Bar.
 * Run this once to generate the encrypted data file.
 */
public class EncryptionUtil {

    // Must match BarSensorDataProvider
    private static final String KEY = "SensorDataKey123";
    private static final String IV = "InitVector123456";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: EncryptionUtil <input-file> <output-file>");
            System.out.println("Example: EncryptionUtil sensor_data.txt sensor_data_encrypted.txt");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputFile = args[1];

        String plainText = Files.readString(Path.of(inputFile));
        String encrypted = encrypt(plainText);
        Files.writeString(Path.of(outputFile), encrypted);

        System.out.println("Encrypted " + inputFile + " -> " + outputFile);
    }

    public static String encrypt(String plainText) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }
}
