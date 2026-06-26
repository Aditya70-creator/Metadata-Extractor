# Media Metadata Extractor (Java)

A lightweight, high-performance Java command-line utility that extracts structured metadata parameters (such as resolution, hardware camera profiles, color spacing, and geotagged tracking vectors) from raw media file streams including images and videos. 

This project utilizes the `drewnoakes/metadata-extractor` processing core to parse binary headers without loading full image matrices into memory.

## Features
- **Unified Media Pipeline:** Automatically handles various formats (`.jpg`, `.png`, `.webp`, `.mp4`, `.mov`, `.avi`).
- **Hardware Architecture Decoding:** Extracts device profile signatures (e.g., Apple, Samsung Makernotes).
- **Exposure Data Parsing:** Captures shutter speeds, aperture coefficients ($f$-numbers), ISO scales, and focal layouts.
- **Geospatial Location Vectors:** Decodes embedded GPS coordinate frames (Latitude, Longitude, Altitude).

## Core Architecture Design
The extraction layer treats incoming file nodes as sequential byte arrays, searching for magic metadata marker segments:

```text
[ Raw Media File ] ──> [ Unified Processing Engine ]
                              │
         ┌────────────────────┼────────────────────┐
         ▼                    ▼                    ▼
   [ JPEG/PNG Segments ]   [ EXIF/XMP Profiles ]   [ MP4 Atom Boxes ]
         │                    │                    │
         └────────────────────┼────────────────────┘
                              ▼
                 [ Key-Value Tag Matrix Dump ]