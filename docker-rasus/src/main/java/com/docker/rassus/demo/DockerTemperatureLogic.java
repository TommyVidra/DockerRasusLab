package com.docker.rassus.demo;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class DockerTemperatureLogic {

    private DockerTemperatureH2Data data;
    private ResourceLoader resourceLoader;

    public DockerTemperatureLogic(DockerTemperatureH2Data data, ResourceLoader resourceLoader) {
        this.data = data;
        this.resourceLoader = resourceLoader;
    }

    private static final int TEMPERATURE_INDEX = 0;
    private static final String TEMPERATURE_UNIT = "C";
    private static final String NAME_VALUE = "Temperature";

    private static final String NAME_KEY = "name";
    private static final String UNIT_KEY = "unit";
    private static final String VALUE_KEY = "value";

    public static int decipherSecondsToLine(long seconds){
        return (int) (seconds % 100) + 1;
    }

    private BufferedReader returnBufferedReader() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:readings.txt");
        return new BufferedReader(new InputStreamReader( resource.getInputStream()));
    }

    public void readAndSaveAllReadings(){

        try (BufferedReader br = returnBufferedReader()){
            String st;

            long lineIndex = 0;
            while ((st = br.readLine()) != null){
                if(lineIndex != 0){
                    data.saveEntry(
                            lineIndex,
                            TEMPERATURE_UNIT,
                            Integer.parseInt(st.split(",")[TEMPERATURE_INDEX])
                    );
                }

                ++lineIndex;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public JSONObject readLineFromFile(long seconds) throws JSONException, IOException {

        int line = decipherSecondsToLine(seconds);
        String temperatureValue = "";

        try (BufferedReader br = returnBufferedReader()){

            String st;
            int lineIndex = 1;
            while ((st = br.readLine()) != null){
                if(lineIndex == line) {
                    temperatureValue = st;
                    break;
                }
                ++lineIndex;
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }

        temperatureValue = temperatureValue.split(",")[TEMPERATURE_INDEX];

        JSONObject Json = new JSONObject();

        Json.put(NAME_KEY, NAME_VALUE);
        Json.put(VALUE_KEY, temperatureValue);
        Json.put(UNIT_KEY, TEMPERATURE_UNIT);

        return Json;
    }
}
