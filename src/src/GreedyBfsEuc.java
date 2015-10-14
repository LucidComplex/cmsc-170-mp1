/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author MiriamMarie
 */

public class GreedyBfsEuc extends Algorithm{
    public GreedyBfsEuc(char[][] maze, String mazeName){
        this.init(maze, mazeName);
    }
    @Override
    public void solve() {
        
    }

    @Override
    public double heuristic(Node node) {
        double xSquaredDiff = Math.pow((node.x)-(endNode.x),2);
        double ySquaredDiff = Math.pow((node.y)-(endNode.x),2);
        return Math.sqrt(xSquaredDiff + ySquaredDiff);
    }
}
