package com.docker.rassus.demo;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class HumidityLogic {

    private HumidityH2Data data;
    private ResourceLoader resourceLoader;

    public HumidityLogic(HumidityH2Data data, ResourceLoader resourceLoader) {
        this.data = data;
        this.resourceLoader = resourceLoader;
    }

    private static final int HUMIDITY_INDEX = 2;
    private static final String HUMIDITY_UNIT = "%";
    private static final String NAME_VALUE = "Humidity";

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
                            HUMIDITY_UNIT,
                            Integer.parseInt(st.split(",")[HUMIDITY_INDEX])
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
        String humidityValue = "";

        try (BufferedReader br = returnBufferedReader()){

            String st;
            int lineIndex = 1;
            while ((st = br.readLine()) != null){
                if(lineIndex == line) {
                    humidityValue = st;
                    break;
                }
                ++lineIndex;
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }

        humidityValue = humidityValue.split(",")[HUMIDITY_INDEX];

        JSONObject Json = new JSONObject();

        Json.put(NAME_KEY, NAME_VALUE);
        Json.put(VALUE_KEY, humidityValue);
        Json.put(UNIT_KEY, HUMIDITY_UNIT);

        return Json;
    }
}
