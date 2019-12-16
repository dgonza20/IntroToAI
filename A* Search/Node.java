/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiassignment2;

/**
 *
 * @author degme
 */
public class Node {
        private int row, col, f, g, h;
        private Node parent;
        private boolean walk;
        // Node constructor
        public Node(int row, int col){
            this.row = row;
            this.col = col;
            parent = null;
            this.walk=true;   
        }
        //mutator methods to set values
        public void setF(){
            f = g + h;
        }
        public void setG(int value){
            g = value;
        }
        public void setH(int value){
            h = value;
        }
        public void setParent(Node n){
            parent = n;
        }
        //accessor methods to get values
        public int getF(){
            return f;
        }
        public int getG(){
            return g;
        }
        public int getH(){
            return h;
        }
        public Node getParent(){
            return parent;
        }
        public int getRow(){
            return row;
        }
        public int getCol(){
            return col;
        }
        public boolean equals(Object in){
        //typecast to Node
        Node n = (Node) in;
            return row == n.getRow() && col == n.getCol();
        }    
        public void setWalkable(boolean walk) {
            this.walk = walk;
        }
        public boolean getWalkable(){
            return walk;
        }
}
