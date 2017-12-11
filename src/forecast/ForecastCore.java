/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forecast;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Joshua
 */
public class ForecastCore {
    private ForecastFetchJSON json;
    private ForecastIO io;
    
    ForecastCore() {
        json = new ForecastFetchJSON();
        io = new ForecastIO();
        
        query_forecast_favorites();
    }
    
    public void execCommand(int command) {
        switch (command) {
            case 1:
                query_forecast();
                break;
            case 2:
                add_favorite();
                break;
            case 3:
                delete_favorite();
                break;
            case 4:
                query_favorites();
                break;
            case 5:
                break;
            default:
                System.out.println("Not to be reached: " + command);
                break;
        }
    }
   
    private String getForecastFromInput() {
        StringBuilder query = new StringBuilder();
        Scanner scan = new Scanner(System.in);
        System.out.print("City name? ");
        query.append(scan.nextLine()).append(",");
        System.out.print("Country code? ");
        query.append(scan.nextLine());
        return query.toString();
    }
    private String query_forecast() {
        ArrayList<Day> list = null;
        String loc;
        do {
         loc = getForecastFromInput();
         list = json.fetchJSON(loc);
         if (list.isEmpty())
             System.out.println("Could not find location " + loc);
         else
             break;
        } while (true);
        pretty_print(list, loc);
        return loc;
    }
    
    private String query_forecast(String loc) {
        ArrayList<Day> list = json.fetchJSON(loc);
        if (list.isEmpty())
            System.out.println("Could not find location " + loc + " entry in favorites is incorrect");
        
        pretty_print(list, loc);
        return loc;
    }
    
    private void query_forecast_favorites() {
        ArrayList<String> favorites = io.get_favorites();
        
        for (String loc : favorites) {
            query_forecast(loc);
        }
    }
    private void add_favorite() {
        String loc = query_forecast();
        System.out.println("Adding to favorites file.");
        io.append_favorite(loc);
    }
    
    private void delete_favorite() {
        query_favorites();
        StringBuilder sb = new StringBuilder();
        Scanner scan = new Scanner(System.in);
  
        System.out.print("City name? ");
        sb.append(scan.nextLine());
        sb.append(",");
        System.out.print("County? ");
        sb.append(scan.nextLine());
        io.remove_favorite(sb.toString());
    }
    
    private void query_favorites() {
        ArrayList<String> favorites = io.get_favorites();
        System.out.println("Favorites: ");
        if (!favorites.isEmpty()) {
            for (String s : favorites) {
                String[] temp = s.split(",");
                System.out.println(temp[0] + ", " + temp[1]);
            }
                
        }
    }
    
    private void pretty_print(ArrayList<Day> list, String loc) {
        String[] temp = loc.split(",");
        System.out.println(list.size() + " day forecast for " + temp[0] + ", " + temp[1]);
        for (Day desc : list) 
            System.out.print(desc.getWeekday() + " \t");
        System.out.print("\n");
        for (Day temperature : list)
            System.out.print((int) temperature.getTemperature() +"°F"+ " \t");
        System.out.print("\n");
        System.out.println("\t       " + list.get(1).getDescription());
    }
    
}
