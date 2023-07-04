import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.sql.Date;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        ArrayList<Result> results = new ArrayList<>();

        try {

            Object obj = parser.parse(new FileReader("results.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray resultsArray = (JSONArray) jsonObject.get("results");


            for (Object resultObj : resultsArray) {
                JSONObject resultJson = (JSONObject) resultObj;
                JSONArray resultDetails = (JSONArray) resultJson.get(resultJson.keySet().iterator().next());

                for (Object detailObj : resultDetails) {
                    JSONObject detailJson = (JSONObject) detailObj;
                    String test = detailJson.keySet().iterator().next().toString();
                    JSONObject testDetails = (JSONObject) detailJson.get(test);

                    String login = resultJson.keySet().iterator().next().toString();
                    String dateString = testDetails.get("date").toString();
                    Date date = Date.valueOf(dateString);
                    double markDouble = Double.parseDouble(testDetails.get("mark").toString());
                    int mark = (int) markDouble;

                    Result result = new Result(login, test, date, mark);
                    results.add(result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (Result result : results) {
            System.out.println(result.login + ";" + result.test + ";" + result.date + ";" + result.mark);
        }
    }
}
