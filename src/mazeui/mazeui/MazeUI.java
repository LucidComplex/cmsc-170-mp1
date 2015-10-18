/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeui;

import java.util.ArrayList;

/**
 *
 * @author Lesterrific17
 */
public interface MazeUI {
    
    public void markFrontier(int x, int y);
    
    public void markCurrent(int x, int y);
    
    public void setStart(int x, int y);
    
    public void setGoal(int x, int y);
    
    public void markWall(int x, int y);
    
    public void parse2DArray(char[][] maze);
    
    public void markPath(int x, int y);
    
    public void addState(MazeState ms);
    
    public void setPath(ArrayList<Coordinate> p);
}
