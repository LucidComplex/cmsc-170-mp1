/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeui;

import java.util.ArrayList;
import java.util.Collection;
import src.Node;

/**
 *
 * @author Lesterrific17
 */
public class MazeState {
    
    public Node c;
    public ArrayList<Coordinate> f;
    
    public MazeState(Node current, ArrayList<Coordinate> frontier){
        c = current;
        f = frontier;
    }
     
}
