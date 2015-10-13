/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author MiriamMarie
 */
public abstract class Algorithm {
    protected Node startNode;
    protected char[][] maze;
    
    public void init(char[][] maze){
        // look for start point
        this.maze = maze;
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[x].length; y++) {
                if (maze[x][y] == '.') {
                    startNode = new Node(x, y, '.');
                    break;
                }
            }
            if (startNode != null) {
                break;
            }
        }
    }
    
    public abstract void solve();
    protected void populateChildren(Node node) {
        List<Node> children = new LinkedList<>();
        
        // add only walkable children
        Node[] possibleChildren =
            {new Node(node.x, node.y - 1, maze[node.x][node.y - 1]),
            new Node(node.x + 1, node.y, maze[node.x + 1][node.y]),
            new Node(node.x, node.y + 1, maze[node.x][node.y + 1]),
            new Node(node.x - 1, node.y, maze[node.x - 1][node.y])};
        
        for (Node child : possibleChildren) {
            if (child.c != '%') {
                child.parent = node;
                children.add(child);
            }
        }
        
        node.children = children;
    }
    
}
