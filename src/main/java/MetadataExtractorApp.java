import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;

public class MetadataExtractorApp {
    public static void main(String[] args) {
        String filePath ="C:\\Users\\KIIT\\Desktop\\20260516_134044.jpg"; 
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Error: File not found at " + file.getAbsolutePath());
            return;
        }

        try {
            System.out.println("Extracting metadata from: " + file.getName() + "...\n");
            Metadata metadata = ImageMetadataReader.readMetadata(file);

            // Gps Directory target karein
            GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
            
            System.out.println("==================================================");
            System.out.println("            GEOLOCATION DASHBOARD                 ");
            System.out.println("==================================================");
            
            if (gpsDirectory != null) {
                GeoLocation geoLocation = gpsDirectory.getGeoLocation();
                
                if (geoLocation != null && !geoLocation.isZero()) {
                    double latitude = geoLocation.getLatitude();
                    double longitude = geoLocation.getLongitude();
                    
                    System.out.println("Parsed Latitude  : " + latitude);
                    System.out.println("Parsed Longitude : " + longitude);
                    
                    // 1. FREE REVERSE GEOCODING CALL (Fetching real location name)
                    System.out.println("\nSearching map database for named location...");
                    String realAddress = fetchLocationName(latitude, longitude);
                    System.out.println("📍 RESOLVED LOCATION : " + realAddress);
                    
                    // 2. Backup Clickable Link
                    System.out.println("\n🔗 GOOGLE MAPS LINK:");
                    System.out.println("https://www.google.com/maps?q=" + latitude + "," + longitude);
                } else {
                    System.out.println("Location Data: GPS coordinates are zero.");
                }
            } else {
                System.out.println("Location Data: No GPS Metadata found.");
            }
            System.out.println("==================================================");

        } catch (ImageProcessingException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Yeh function internet se real address lekar aayega
    private static String fetchLocationName(double lat, double lon) {
        try {
            // Rounding coordinates to 4 decimal places for better API matching
            String formattedLat = String.format(java.util.Locale.US, "%.4f", lat);
            String formattedLon = String.format(java.util.Locale.US, "%.4f", lon);
            
            // Added &accept-language=en to get clean readable address structures
            String urlStr = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" 
                            + formattedLat + "&lon=" + formattedLon + "&accept-language=en";
            
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            // Strict User-Agent setup to prevent server-side blockings
            conn.setRequestProperty("User-Agent", "Java-Metadata-Extractor-Agent");

            if (conn.getResponseCode() == 200) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }

                String json = response.toString();
                // Extracting the complete address string safely
                if (json.contains("\"display_name\":\"")) {
                    int start = json.indexOf("\"display_name\":\"") + 16;
                    int end = json.indexOf("\"", start);
                    return json.substring(start, end);
                }
            } else {
                return "Server Response Error: Code " + conn.getResponseCode();
            }
        } catch (java.net.MalformedURLException e) {
            return "Network Error (Malformed URL): " + e.getMessage();
        } catch (IOException e) {
            return "Network Error (I/O): " + e.getMessage();
        } catch (SecurityException e) {
            return "Network Error (Security): " + e.getMessage();
        }
        return "Named address not found in global database limits.";
    }
}