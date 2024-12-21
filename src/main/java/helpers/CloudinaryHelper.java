package helpers;

import com.cloudinary.Cloudinary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CloudinaryHelper {

    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("/src/main/resources/cloudinary.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (cloudinary == null) {
            Map<String, String> configMap = new HashMap<>();
            configMap.put("cloud_name", properties.getProperty("cloudinary.cloud_name"));
            configMap.put("api_key", properties.getProperty("cloudinary.api_key"));
            configMap.put("api_secret", properties.getProperty("cloudinary.api_secret"));
            cloudinary = new Cloudinary(configMap);
        }
        return cloudinary;
    }
}
