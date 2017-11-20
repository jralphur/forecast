/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forecast;

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
    }
    
    public void execCommand(int command) {
        switch (command) {
            case 1:
                query_weather();
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
            default:
                System.out.println("Not to be reached: " + command);
                break;
        }
    }
   
    private void query_weather() {
        Scanner input = new Scanner(System.in);
        String s;
        do {
            System.out.println("What city?");
            s = input.nextLine();
        } while (true);
    }
    
    private void add_favorite() {
        query_weather();
        
        
    }
    
    private void delete_favorite() {
        
    }
    
    private void query_favorites() {
        
    }
}
