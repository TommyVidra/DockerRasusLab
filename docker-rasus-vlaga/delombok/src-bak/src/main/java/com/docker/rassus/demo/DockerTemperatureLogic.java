package com.docker.rassus.demo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class DockerTemperatureLogic {

    private static int TEMPERATURE_INDEX = 0;
    private static String TEMPERATURE_UNIT = "C";
    private static String NAME_VALUE = "Temperature";

    private static String NAME_KEY = "name";
    private static String UNIT_KEY = "unit";
    private static String VALUE_KEY = "value";

    public static int DecipherSecondsToLine(long seconds){
        return (int) (seconds % 100) + 1;
    }

    public static void ReadAndSaveAllReadings(){
        try{
            File file = new File("G:\\FER\\7.Semestar\\Raspodjeljeni_labos\\2021\\dz3\\docker-rasus\\src\\main\\java\\com\\docker\\rassus\\demo\\readings.txt");
            BufferedReader br  = new BufferedReader(new FileReader(file));

            String st;
            long line_index = 1;
            while ((st = br.readLine()) != null){
                DockerTemperatureH2Data.SaveEntrys(
                        line_index,
                        TEMPERATURE_UNIT,
                        Integer.parseInt(st.split(",")[TEMPERATURE_INDEX])
                );
                ++line_index;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    public static JSONObject ReadLineFromFile(long seconds) throws JSONException, IOException {

        int line = DecipherSecondsToLine(seconds);
        String TemperatureValue = "";

        try{
            File file = new File("G:\\FER\\7.Semestar\\Raspodjeljeni_labos\\2021\\dz3\\docker-rasus\\src\\main\\java\\com\\docker\\rassus\\demo\\readings.txt");
            BufferedReader br  = new BufferedReader(new FileReader(file));

            String st;
            int line_index = 1;
            while ((st = br.readLine()) != null){
                if(line_index == line) {
                    TemperatureValue = st;
                    break;
                }
                ++line_index;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

        TemperatureValue = TemperatureValue.split(",")[TEMPERATURE_INDEX];

        JSONObject Json = new JSONObject();

        Json.put(NAME_KEY, NAME_VALUE);
        Json.put(VALUE_KEY, TemperatureValue);
        Json.put(UNIT_KEY, TEMPERATURE_UNIT);

        return Json;
    }
}
