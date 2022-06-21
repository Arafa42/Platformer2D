package Helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * ConfigFileReader class.
 * @author Arafa Yoncalik
 */
public class ConfigFileReader {

    private final HashMap<String, Integer> configFileData;
    private static ConfigFileReader configFileReaderInstance;
    private ConfigFileReader() {this.configFileData = new HashMap<>();}

    /**
     * Creates a singleton instance of the class.
     * @return
     */
    public static ConfigFileReader getConfigFileReaderInstance() {
        if(configFileReaderInstance == null){return configFileReaderInstance = new ConfigFileReader();}
        return configFileReaderInstance;
    }

    /**
     * Processes the file and returns a hashmap with key and value pair of the config file values.
     * @param configFile
     * @return returns hashmap with string and integer key value pair.
     * @throws FileNotFoundException
     */
    public HashMap<String, Integer> processConfigFile(final String configFile) throws FileNotFoundException {
            File file = new File("src/" + configFile);
            if (file.exists()) {
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine().replace(" ", "");
                    String key = data.substring(0, data.indexOf(":"));
                    Integer value = Integer.parseInt(data.substring(data.indexOf(":") + 1));
                    configFileData.put(key, value);
                }
            }

        return configFileData;
    }
}
