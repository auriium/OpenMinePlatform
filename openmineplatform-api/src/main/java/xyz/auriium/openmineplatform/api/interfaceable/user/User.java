package xyz.auriium.openmineplatform.api.interfaceable.user;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import xyz.auriium.openmineplatform.api.binding.location.PlatformLocation;
import xyz.auriium.openmineplatform.api.binding.location.UnboundPlatformLocation;
import xyz.auriium.openmineplatform.api.interfaceable.Interfaceable;

import java.util.Optional;

/**
 * Represents a meta-interfaceable that is confirmed to exist at the moment and therefore can offer some tentative
 * optional state (this state is not guaranteed and so all stateful functions will return an optional)
 */
public interface User extends Interfaceable {

    /**
     * Returns the user's name if they are present on the server
     * @return
     */
    Optional<String> getName();


    void teleport(PlatformLocation platformLocation);

    void sendActionbarComponent(Component component);
    void sendActionbar(String string);

    void sendSystem(String string);

    void sendTitleComponent(Title title);
    void sendTitle(PlatformTitle title);

    void runCommandAsUser(String command);
    void runCommandAsPlatform(String command);

    @Deprecated
    void sendClickable(String message, String command);

}
