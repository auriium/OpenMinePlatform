package xyz.auriium.openmineplatform.api;

import java.io.File;
import java.nio.file.Path;

public class PlatformLocation {

    private final Path path;

    public PlatformLocation(Path path) {
        this.path = path;
    }

    public Path getFolderPath() {
        return path;
    }

    public File getFolderFile() {
        return path.toFile();
    }
}
