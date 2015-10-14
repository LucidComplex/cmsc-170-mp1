/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author MiriamMarie
 */
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        MazeReader mr = new MazeReader();
        char[][] maze = mr.read("tinyMaze");
        Algorithm algo = new GreedyBfsEuc(maze, "tinyMaze");
        algo.solve();
    }
}
