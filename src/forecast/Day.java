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
public class Day {
    private final double temperature;
    private final String description;
    private final String weekday;

    public double getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    public String getWeekday() {
        return weekday;
    }
    
    public Day(double temp, String desc, String wd) {
        temperature =  temp;
        description = desc;
        weekday = wd;
    }
}
