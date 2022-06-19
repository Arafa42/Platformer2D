package Helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ConfigFileReader {

    private HashMap<String, Integer> configFileData;
    private static ConfigFileReader configFileReaderInstance;
    private ConfigFileReader() {this.configFileData = new HashMap<>();}

    public static ConfigFileReader getConfigFileReaderInstance() {
        if(configFileReaderInstance == null){return configFileReaderInstance = new ConfigFileReader();}
        return configFileReaderInstance;
    }

    public HashMap<String, Integer> procesConfigFile(final String configFile) throws FileNotFoundException {
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
