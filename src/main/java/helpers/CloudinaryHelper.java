package helpers;

import com.cloudinary.Cloudinary;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CloudinaryHelper {

    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Properties properties = new Properties();
            try (InputStream inputStream = CloudinaryHelper.class
                    .getClassLoader()
                    .getResourceAsStream("cloudinary.properties")) {
                if (inputStream == null) {
                    throw new RuntimeException("cloudinary.properties file not found in resources folder");
                }
                properties.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException("Error loading cloudinary.properties", e);
            }

            Map<String, String> configMap = new HashMap<>();
            configMap.put("cloud_name", properties.getProperty("cloudinary.cloud_name"));
            configMap.put("api_key", properties.getProperty("cloudinary.api_key"));
            configMap.put("api_secret", properties.getProperty("cloudinary.api_secret"));
            cloudinary = new Cloudinary(configMap);
        }
        return cloudinary;
    }
}
