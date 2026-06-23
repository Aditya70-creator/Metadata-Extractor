import java.io.File;
import java.io.IOException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class MetadataExtractorApp {
    public static void main(String[] args) {
        // We will update this filename in the next step!
        String filePath = "sample.jpg"; 
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Error: File not found at " + file.getAbsolutePath());
            return;
        }

        try {
            System.out.println("Extracting metadata from: " + file.getName() + "...\n");

            // Reads the file and parses all metadata segments automatically
            Metadata metadata = ImageMetadataReader.readMetadata(file);

            // Loop through the metadata segments (Directories)
            for (Directory directory : metadata.getDirectories()) {
                System.out.println("--- Segment: " + directory.getName() + " ---");
                
                // Loop through individual key-value pairs (Tags) inside each directory
                for (Tag tag : directory.getTags()) {
                    System.out.println(tag.getTagName() + " : " + tag.getDescription());
                }
                System.out.println();
            }

        } catch (ImageProcessingException e) {
            System.err.println("Error processing media file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("File read error: " + e.getMessage());
        }
    }
}