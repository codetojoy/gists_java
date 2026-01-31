package net.codetojoy.sensor.bar;

import net.codetojoy.sensor.common.SensorDataProvider;
import net.codetojoy.sensor.common.SensorReading;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Version Bar implementation of SensorDataProvider.
 * Reads sensor data from an AES-encrypted file, decrypted using Bouncy Castle.
 */
public class BarSensorDataProvider implements SensorDataProvider {

    private static final String DATA_FILE = "/data/sensor_data_encrypted.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // Demo key and IV (16 bytes each for AES-128)
    private static final String KEY = "SensorDataKey123";
    private static final String IV = "InitVector123456";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Override
    public String getName() {
        return "BarSensorDataProvider (encrypted)";
    }

    @Override
    public List<SensorReading> getReadings() {
        List<SensorReading> readings = new ArrayList<>();

        try (InputStream is = getClass().getResourceAsStream(DATA_FILE);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            StringBuilder encryptedContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                encryptedContent.append(line);
            }

            String decrypted = decrypt(encryptedContent.toString());
            String[] lines = decrypted.split("\n");

            for (String dataLine : lines) {
                dataLine = dataLine.trim();
                if (dataLine.isEmpty() || dataLine.startsWith("#")) {
                    continue;
                }

                String[] parts = dataLine.split(",");
                if (parts.length == 2) {
                    LocalDateTime timestamp = LocalDateTime.parse(parts[0].trim(), FORMATTER);
                    double wattHours = Double.parseDouble(parts[1].trim());
                    readings.add(new SensorReading(timestamp, wattHours));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read encrypted sensor data from " + DATA_FILE, e);
        }

        return readings;
    }

    private String decrypt(String encryptedBase64) throws Exception {
        byte[] encrypted = Base64.getDecoder().decode(encryptedBase64);

        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        byte[] decrypted = cipher.doFinal(encrypted);
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}
