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

package xyz.auriium.openmineplatform.api.interfaceable.user;

public class PlatformTitle {

    private final String title;
    private final String subtitle;

    private final int fadein;
    private final int stay;
    private final int fadeout;

    PlatformTitle(String title, String subtitle, int fadein, int stay, int fadeout) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadein = fadein;
        this.stay = stay;
        this.fadeout = fadeout;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getFadein() {
        return fadein;
    }

    public int getStay() {
        return stay;
    }

    public int getFadeout() {
        return fadeout;
    }

    public static PlatformTitle of(String title, String subtitle) {
        return new PlatformTitle(title, subtitle, 0, 20, 10);
    }

    public static PlatformTitle of(String title, String subtitle, int stay, int fadeout) {
        return new PlatformTitle(title, subtitle, 0, stay, fadeout);
    }

    public static PlatformTitle of(String title, String subtitle, int fadein, int stay, int fadeout) {
        return new PlatformTitle(title, subtitle, fadein, stay, fadeout);
    }
}
