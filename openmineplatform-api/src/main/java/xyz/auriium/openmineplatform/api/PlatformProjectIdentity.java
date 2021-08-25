package xyz.auriium.openmineplatform.api;

import java.util.UUID;

public class PlatformProjectIdentity {

    private final String projectName;
    private final String authorName;
    private final String versionString;
    private final UUID uuid;

    public PlatformProjectIdentity(String projectName, String authorName, String versionString) {
        this.projectName = projectName;
        this.authorName = authorName;
        this.versionString = versionString;
        this.uuid = UUID.fromString(projectName);
    }

    public String getProjectName() {
        return projectName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getVersionString() {
        return versionString;
    }

    public UUID getUuid() {
        return uuid;
    }
}
