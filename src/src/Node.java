/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.List;

/**
 *
 * @author MiriamMarie
 */
public class Node {
    public int x;
    public int y;
    public char c;
    public Node parent;
    public List<Node> children;
    
    public Node(int x, int y, char c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Node (" + String.valueOf(x)
                + ", " + String.valueOf(y) + ")");
        return sb.toString();
    }
    
    public boolean equals(Object o) {
        return o.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.x;
        hash = 59 * hash + this.y;
        return hash;
    }
}
