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
public class ForecastMenu {
    public class ForecastMenuItem {
        private final String description;
        ForecastMenuItem(String desc) {
            description = desc;
        }

        private String getDescription() {
            return description;
        }
    }
    private final ArrayList<ForecastMenuItem> menu_items;
    
    ForecastMenu() {
        menu_items = new ArrayList();
        menu_items.add(new ForecastMenuItem("Check weather for city"));
        menu_items.add(new ForecastMenuItem("Check weather and save it favorites"));
        menu_items.add(new ForecastMenuItem("Delete cities from favorites"));
        menu_items.add(new ForecastMenuItem("Query favorites file"));
        menu_items.add(new ForecastMenuItem("Quit"));
    }
    
    public void printMenu() {
        int i = 1;
        menu_items.forEach((item) -> {
            System.out.println(i + item.getDescription());
        });
    }
    public int getResponse() {
        Scanner user_input = new Scanner(System.in);
        int ret;
        do {
            ret = user_input.nextInt();
        } while (ret < 0 || ret > menu_items.size());
        return ret;
    }
}
