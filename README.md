# 📷 Media Metadata Extractor (Java)

A lightweight, high-performance Java command-line utility that extracts structured metadata parameters (such as resolution, hardware camera profiles, color spacing, and geotagged tracking vectors) from raw media file streams including images and videos.

This project utilizes the **drewnoakes/metadata-extractor** processing core to parse binary headers without loading full image matrices into memory.

---

# ✨ Features

- **Unified Media Pipeline:** Automatically handles various formats (`.jpg`, `.png`, `.webp`, `.mp4`, `.mov`, `.avi`).
- **Live Reverse Geocoding Engine:** Automatically extracts raw GPS coordinates, queries OpenStreetMap APIs, and resolves them into human-readable locations (e.g., *Mangan, Sikkim, India*).
- **Dynamic Map Routing:** Generates a clickable Google Maps navigation link for any geotagged asset.
- **Hardware Architecture Decoding:** Extracts hardware-specific camera information (Apple profiles, Samsung MakerNotes, etc.).
- **Exposure Data Parsing:** Captures shutter speed, aperture (*f-number*), ISO, focal length, and other camera parameters.

---

# 🏗 Core Architecture Design

The extraction layer treats incoming media files as sequential byte streams, scanning metadata marker segments without decoding the full media content.

```text
                      [ Raw Media File ]
                              │
                              ▼
                 [ Unified Processing Engine ]
                              │
        ┌─────────────────────┼─────────────────────┐
        ▼                     ▼                     ▼
 [ JPEG / PNG Segments ] [ EXIF / XMP Profiles ] [ MP4 Atom Boxes ]
        │                     │                     │
        └─────────────────────┼─────────────────────┘
                              ▼
                [ Extraction & Parsing Layer ]
                              │
        ┌─────────────────────┴─────────────────────┐
        ▼                                           ▼
 [ Console Metadata Output ]           [ Reverse Geocoding API ]
                                                  │
                                                  ▼
                                   [ Named Address Resolution ]
```

---

# ⚙️ Setup & Installation

## Prerequisites

- **JDK:** Java 11 or higher *(Tested on JDK 24)*
- **Build Tool:** Apache Maven 3.6+
- **Network:** Active internet connection *(required for reverse geocoding)*

---

## Dependency Management

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.drewnoakes</groupId>
    <artifactId>metadata-extractor</artifactId>
    <version>2.19.0</version>
</dependency>
```

---

# 🚀 How to Test & Run Locally

## 1️⃣ Clone the Repository

```bash
git clone https://github.com/technomatrixsudo14/MetadataExtractorProject.git
cd MetadataExtractorProject
```

---

## 2️⃣ Add a Test File

Copy any supported media file (for example, a photo captured with your phone that contains GPS metadata) into the project root directory.

Example:

```
MetadataExtractorProject/
│
├── sample.jpg
├── pom.xml
└── src/
```

---

## 3️⃣ Update the Source Code

Open:

```
src/main/java/MetadataExtractorApp.java
```

Modify the file path:

```java
String filePath = "sample.jpg";
```

---

## 4️⃣ Compile & Run

Execute the following command:

```bash
mvn compile exec:java -Dexec.mainClass="MetadataExtractorApp"
```

---

# 🤝 How to Contribute

Contributions are welcome!

If you'd like to improve this project (for example by adding video duration extraction, a GUI, REST API, or database logging), follow these steps:

## 1. Fork the repository

Click the **Fork** button in the upper-right corner of GitHub.

---

## 2. Create a feature branch

```bash
git checkout -b feature/AmazingFeature
```

---

## 3. Commit your changes

```bash
git commit -m "Add some AmazingFeature"
```

---

## 4. Push to GitHub

```bash
git push origin feature/AmazingFeature
```

---

## 5. Open a Pull Request

Create a Pull Request describing your changes.

---

# 📦 Supported Media Formats

| Images | Videos |
|---------|---------|
| JPG | MP4 |
| JPEG | MOV |
| PNG | AVI |
| WEBP | |

---

# 📄 License

This project is open-source and available under the **MIT License**.

---

# 🙏 Acknowledgements

- **drewnoakes/metadata-extractor** for the excellent metadata parsing library.
- **OpenStreetMap / Nominatim** for reverse geocoding services.
- Java & Apache Maven community.

---

⭐ If you found this project useful, consider giving it a star on GitHub!
