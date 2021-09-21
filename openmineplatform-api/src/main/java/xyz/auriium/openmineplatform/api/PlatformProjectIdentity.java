/*
 * OpenMinePlatform
 * Copyright © 2021 Auriium
 *
 * OpenMinePlatform is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * OpenMinePlatform is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenMinePlatform. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU Affero General Public License.
 */

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
        this.uuid = UUID.randomUUID();
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
