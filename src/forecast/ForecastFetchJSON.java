/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forecast;

/**
 *
 * @author Joshua
 */
public class ForecastFetchJSON {
    ForecastFetchJSON() {
        api_key = new String();
    }
    /* http://api.openweathermap.org/data/2.5/forecast&id=$ID&APPID=$api_key/ */
    static final String base_url = "http://api.openweathermap.org/data/2.5/forecast";
    final String api_key;
}
