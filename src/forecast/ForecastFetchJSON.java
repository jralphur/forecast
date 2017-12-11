/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forecast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Joshua
 */
public class ForecastFetchJSON {
    public ForecastFetchJSON() {
 
    }
    /* http://api.openweathermap.org/data/2.5/forecast?q={city name},{country code}&APPID=$api_key/ */
    static final String base_url = "http://api.openweathermap.org/data/2.5/forecast?q=";
    final String api_key = "b1cdbca4690d279bf8bc246bd9372046";
    
    public ArrayList<Day> fetchJSON(String loc) {
        ArrayList<Day> ret = new ArrayList();
       
        // Connect to the URL using java's native library
        try {
           URL url = new URL(base_url + loc + "&APPID=" + api_key);
           HttpURLConnection request = (HttpURLConnection) url.openConnection();
           request.connect();

           // Convert to a JSON object to print data
           JsonParser jp = new JsonParser(); //from gson
           JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
           JsonObject rootobj = root.getAsJsonObject();
           
           /* openweathermap returns weather every 3 hours for 5 days per query,
            * so we need to parse through the list array:
            *   for every json object in the json array:
            *       grab the date
            *       grab the average of the weather of the date
            *       
           */
           Integer cnt = rootobj.get("cnt").getAsInt();
           JsonArray list = rootobj.get("list").getAsJsonArray();
            
           /* first date of the list */
           double kelvin = list.get(0).getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsDouble();
           int entries = 1;
           
           /* weather description */
           String description = list.get(0).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString();
           /* previous entry in index */
           String prev = list.get(0).getAsJsonObject().get("dt_txt").getAsString().split(" ")[0];
           SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
           Date d = date_format.parse(prev);
           SimpleDateFormat format2 = new SimpleDateFormat("EE");
           String weekday = format2.format(d);
           for (int i = 0; i < cnt; ++i) {
               JsonObject subscript = list.get(i).getAsJsonObject();
               String date = subscript.get("dt_txt").getAsString().split(" ")[0];
               JsonObject main = subscript.get("main").getAsJsonObject();
            
               if (date.compareToIgnoreCase(prev) != 0) {
                   /* different dates */
                   kelvin = kelvin / entries;
                   ret.add(new Day(toFahrenheit(kelvin), description, weekday));
                   /* reset for new day */
                   entries = 1;
                   kelvin = main.get("temp").getAsDouble();
                   prev = date;
                   description = list.get(i).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString();
                   d = date_format.parse(prev);
                   weekday = format2.format(d);
               } else {
                   kelvin += main.get("temp").getAsDouble();
                   ++entries;
               }
           }
           kelvin = kelvin / entries;
           ret.add(new Day(toFahrenheit(kelvin), description, weekday));
           request.disconnect();
        } catch (MalformedURLException e) {
           e.printStackTrace();
        } catch (IOException e) {
           System.out.println("City doesn't exist. Sorry");
        } catch (ParseException e) {
           e.printStackTrace();
        }
        return ret;
    }
    
    private double toFahrenheit(double k) {
        return (9/5)*(k - 273) + 32;
    }
}
