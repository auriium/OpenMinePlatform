package xyz.auriium.openmineplatform.api.interfaceable;

public interface Colorer {

    /**
     * Inserts color into string based on certain platforms which require such a thing
     * @param string input
     * @return a colored string
     */
    String color(String string);

}
