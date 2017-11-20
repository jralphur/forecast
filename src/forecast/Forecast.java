/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forecast;

import org.json.JSONObject;
//1. At least 3 classes / objects NOT including your "main" class.
//



//2. You'll need to read data from a file
//
//3. You'll need to write data to a file
//
//4. You'll need to accept input from the user. 

/*
 2 & 3 can be ForecastIO
 Menu Class
*/
/**
 *
 * @author Joshua
 */
public class Forecast {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ForecastCore engine = new ForecastCore();
       ForecastMenu menu = new ForecastMenu();
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
           menu.getResponse();
           engine.execCommand(menu.getResponse()); /* does nothing on quit */

       } while (/* menu.response() != menu.quit */ true);
    }
    
}
