/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forecast;

/**
 *
 * @author Joshua Reno
 */
public class Forecast {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       System.out.println("Favorites: ");
       ForecastCore engine = new ForecastCore();
       ForecastMenu menu = new ForecastMenu();
       int response;
       /* the plan */
       /* init relevant classes */
       /* connect to api repository */
       /* check to see if favorites file exists, if it doesn't, create it */
       /* load favorites file */
       /* load json data from favorites */
       /* show favorite forecast information */
       /* main menu */
       /* 1. check weather for city */
       /* 2. check and save city in favorites */
       /* 3. delete cities from favorites */
       /* 4. queue your favorites */
       /* 5. quit */
       do {
           menu.printMenu();
           response = menu.getMenuResponse();
           engine.execCommand(response);
       } while (response != 5); /* quit */
       
       System.out.println("Exiting...");
    }
}
