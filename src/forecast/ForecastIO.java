/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forecast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Joshua
 */
public class ForecastIO {
    private File fp;
    final String file_name = ".favorites";
    private ArrayList<String> favorites;
    
    ForecastIO() {
        favorites = new ArrayList();
        try {
            fp = new File(file_name);
            if (fp.createNewFile())
                System.out.println("Favorites file not found, creating...");
            else {
                populate_favorites_list();
            }
        } catch (IOException e) {
        }
    }
    
    private void populate_favorites_list() {
        try {
            Scanner stream = new Scanner(fp);
            while (stream.hasNextLine())
                favorites.add(stream.nextLine());
        } catch (FileNotFoundException e) {
        }
    }
    
    public ArrayList<String> get_favorites() {
        return favorites;
    }
    
    public int append_favorite(String loc) {
        loc = loc.toLowerCase();
        if (favorites.contains(loc)) {
            System.out.println("City already in favorites, skipping");
            return 0;
        }
        else 
            favorites.add(loc);
        
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file_name, true));
            bw.write(loc);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    
                }
            }
        }
        return 1;
    }
    
    public int remove_favorite(String loc) {
        loc = loc.toLowerCase();
        if (favorites.contains(loc)) {
            favorites.remove(loc);
            System.out.println("removed!");
        }
        else  {
            System.out.println("City not found in favorites file, skipping");
            return 0;
        }
        /* theres no non-third party library to easily seek files, so we're just going to rewrite everything */
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file_name));
            for (String s : favorites) {
                bw.write(s);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    
                }
            }
        }
        return 1;
    }
    
    
}
