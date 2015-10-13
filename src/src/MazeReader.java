/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MiriamMarie
 */
public class MazeReader {
    public MazeReader() {
        
    }
    
    public char[][] read(String mazeName) throws UnsupportedEncodingException {
        InputStream is = getClass().getResourceAsStream("/mazes/" + mazeName + ".lay.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
        
        // load maze into list
        String strLine;
        List<String> lines = new ArrayList<>();
        try {
            while((strLine = br.readLine()) != null){
                lines.add(strLine);
            }
        } catch (IOException ex) {
            Logger.getLogger(MazeReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // convert list to array
        char[][] maze = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < maze.length; i++) {
            maze[i] = lines.get(i).toCharArray();
        }

        return maze;
    }
}
