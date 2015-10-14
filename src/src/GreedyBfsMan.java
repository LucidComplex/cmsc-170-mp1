/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author parfait
 */
public class GreedyBfsMan extends Algorithm{
    
    public GreedyBfsMan(char[][] maze, String mazeName){
        this.init(maze, mazeName);
    }

    @Override
    public void solve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double heuristic(Node node) {
        double h = Math.abs(node.x - endNode.x) + Math.abs(node.y - endNode.y);
        
        return h;
    }
    
}
