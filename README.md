# 📷 Media Metadata Extractor (Java)

A lightweight Java utility that extracts structured metadata from images and videos without loading entire media files into memory. It supports hardware information, camera parameters, GPS extraction, reverse geocoding, and Google Maps routing using the **drewnoakes/metadata-extractor** library.

---

# ✨ Features

* Supports **JPG, JPEG, PNG, WEBP, MP4, MOV, AVI**
* Extracts camera hardware & EXIF metadata
* Reads GPS coordinates from images and MP4 files
* Converts GPS coordinates into human-readable locations
* Generates clickable Google Maps links
* High-performance binary header parsing

---

# 🏗 Architecture

```text
                [ Raw Media File ]
                        │
                        ▼
          [ Unified Processing Engine ]
                        │
      ┌─────────────────┼─────────────────┐
      ▼                 ▼                 ▼
 [ Image Tags ]   [ EXIF / XMP ]   [ MP4 Atom Boxes ]
      │                 │                 │
      └─────────────────┼─────────────────┘
                        ▼
            [ Metadata Extraction ]
                        │
          ┌─────────────┴─────────────┐
          ▼                           ▼
 [ Console Output ]        [ Reverse Geocoding ]
                                        │
                                        ▼
                             [ Location + Maps ]
```

---

# ⚙️ Setup

### Requirements

* Java 11+
* Maven 3.6+
* Internet connection (for reverse geocoding)

### Dependency

```xml
<dependency>
    <groupId>com.drewnoakes</groupId>
    <artifactId>metadata-extractor</artifactId>
    <version>2.19.0</version>
</dependency>
```

---

# 🚀 Run

Clone the repository:

```bash
git clone https://github.com/Aditya70-creator/MetadataExtractorProject.git
cd MetadataExtractorProject
```

Place a media file inside the project and update:

```java
String filePath = "sample.jpg";
```

Run:

```bash
mvn compile exec:java -Dexec.mainClass="MetadataExtractorApp"
```

---

# 🏛 Core Module

## `extractMediaProperties(String filePath)`

Main extraction API.

**Flow**

* Detect media type
* Extract Image EXIF or MP4 location tags
* Parse metadata
* Send GPS coordinates to reverse geocoder
* Display formatted metadata and location

---

## `fetchLocationName(double lat, double lon)`

Internal network layer that:

* Connects to OpenStreetMap (Nominatim)
* Resolves latitude & longitude
* Returns a readable location name

---

# 🔌 Integration

Add the dependency and call:

```java
MetadataExtractorApp.extractMediaProperties(
    "C:\\Users\\Desktop\\sample.mp4"
);
```

Example:

```java
public class UploadController {

    public void handleFileUpload(String path) {
        MetadataExtract.extractMediaProperties(path);
    }

}
```

---

# 🤝 Contributing

```bash
git checkout -b feature/MyFeature
git commit -m "Add new feature"
git push origin feature/MyFeature
```

Then open a Pull Request.

---

# 📦 Supported Formats

| Images | Videos |
| ------ | ------ |
| JPG    | MP4    |
| JPEG   | MOV    |
| PNG    | AVI    |
| WEBP   |        |

---

# 📄 License

MIT License

---

# 🙏 Acknowledgements

* drewnoakes/metadata-extractor
* OpenStreetMap / Nominatim
* Java & Apache Maven Community

⭐ Star the repository if you found it useful.
