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
import java.util.*;

/**
 *
 * @author degme
 */
public class Astar {
    
    private int vhCost = 10;
    private int dCost = 14;
    private PriorityQueue<Node> openList;
    private List<Node> closedList;
    private Node goalNode;
    private Node startNode;
    private Node[][] map;
    
    // Added new functions
    private boolean isBlocked;
    
    // Initialize structures as well as variables for path finding
    public Astar(Node start, Node end){
        startNode = start;
        goalNode = end;
        
        // OpenList = maintains all avaliable moves, Nodes that we discovered but not vistited 
        openList = new PriorityQueue<Node>((Node node0, Node node1) -> Integer.compare(node0.getF(), node1.getF()));
        // ClosedList= maintains all of the nodes that we have vistited
        closedList = new ArrayList<>();
        
        //Intialize Node map
        map = createMap(); 
        
    }
    
    // Done
    public List<Node> AstarAlg(){
        
        // Add the start node to open list
        openList.add(startNode);
        
        while(!openList.isEmpty()){
            Node current = openList.poll();
            closedList.add(current);
            if((current.getRow()==goalNode.getRow()) && current.getCol() ==goalNode.getCol()){
                // goal node has been reached
                return path(current);
            }
            else{
                findNeighbors(current);
            }
        }
        
        // If this is reached, no path exists
        return new ArrayList<Node>();
    }
    
    
    // Produces the path to the end goal
    public List<Node> path(Node current){

        List<Node> path = new ArrayList();
        Node parent = closedList.get(closedList.size()-1);
        while(parent != null){
            path.add(0, parent);
            parent = parent.getParent();
        }
        
        return path;
    }
    
    // Finds the surronding nodes for a given node
    // Done but need to make look different
    public void findNeighbors(Node current){
        int row = current.getRow();
        int col = current.getCol();
        int up = row-1;
        int mid = row;
        int low = row +1;
        
        // Get the neighbors above and add to the closed list if in bounds or not blocked
        if(up>=0){  // in Bounds
            if(col -1 >= 0){ // if top left is in bounds
                // See if the node is walkable and not already in the closedlist
                isValid(current, up, col -1, dCost);
            }
            if(col + 1 < 15){ // top right
                isValid(current, up, col +1, dCost);
            }
            // upper middle
            isValid(current, up, col, vhCost);
        }
        
        // Get the neighbors besides and add to the closed list if in bounds or not blocked
        if(col-1 >= 0){ // Middle left
            isValid(current, mid, col-1, vhCost);
        }
        if(col+1<15){ // Middle right
            isValid(current, mid, col+1, vhCost);
        }
        
        // Get the neighbors below and add to the closed list if in bounds or not blocked
        if(low<15){  // in Bounds
            if(col -1 >= 0){ // if bottom left is in bounds
                // See if the node is walkable and not already in the closedlist
                isValid(current, low, col -1, dCost);
            }
            if(col + 1 < 15){ // bottom right
                isValid(current, low, col +1, dCost);
            }
            // bottom middle
            isValid(current, low, col, vhCost);
        }
        
    }
    
    public void isValid(Node current, int row, int col, int cost){
        Node adjacent =  map[row][col];
        // If the node is not blocked and not in the closed list
        if(adjacent.getWalkable() && !closedList.contains(adjacent)){
            if(!openList.contains(adjacent)){
                adjacent.setG(adjacent.getG()+cost);
                adjacent.setF();
                adjacent.setParent(current);
                openList.add(adjacent);
            }
        }
    }
    
    
    public Node[][] createMap(){
        Node[][] area = new Node[15][15];
        Random rand = new Random();
        
        // Create the Board and blockers
        for(int row =0; row<15; row++){
            for(int col =0; col < 15; col++){
                area[row][col]=new Node(row,col);
                // First the h cost with respect to the goal node
                area[row][col].setH(Math.abs(area[row][col].getRow()-goalNode.getRow())+ Math.abs(area[row][col].getCol()-goalNode.getCol()));
            }
        }
        // create random blockers for area
        for(int i =0; i<23; i++){
            int row= rand.nextInt(14);
            int col= rand.nextInt(14);
            if(area[row][col].getWalkable() == false){
                i++;
            }
            else{
                area[row][col].setWalkable(false);
            }   
        }
        return area;
    }
    
    public void printBoard(){
        System.out.println("Starting Board");
        for(int i =0; i<15; i++){
            for(int j =0; j< 15; j++){
               if (i == startNode.getRow() && j == startNode.getCol())     //  Place start
                    System.out.print("S ");
                else if (i == goalNode.getRow() && j == goalNode.getCol())    //  Place goal
                    System.out.print("G ");
                else if (map[i][j].getWalkable() == false)
                    System.out.print("1 ");
                else
                    System.out.print("0 "); 
            }
            System.out.println();
        }
    }
    public void printPath(){
        
        ArrayList<Node> reverse = new ArrayList<>();
        
        Node parent = closedList.get(closedList.size()-1);
        while (parent != null) {
            reverse.add(0, parent);
            parent = parent.getParent();
        }
        System.out.println("Path:");
        for(int i =0; i <reverse.size(); i++){
            System.out.print(reverse.get(i)+" -> ");
        }
        
    }
    
    public void printPathBoard(){
        
        ArrayList<Node> reverse = new ArrayList<>();
        
        Node parent = closedList.get(closedList.size()-1);
        while (parent != null) {
            reverse.add(0, parent);
            parent = parent.getParent();
        }
        
        System.out.println("Board with Path");
        for(int i =0; i<15; i++){
            for(int j =0; j< 15; j++){
               if (i == startNode.getRow() && j == startNode.getCol())     //  Place start
                    System.out.print("S ");
                else if (i == goalNode.getRow() && j == goalNode.getCol())    //  Place goal
                    System.out.print("G ");
                else if(reverse.contains(map[i][j]))
                    System.out.print("X ");
                else if ((map[i][j].getWalkable() == false))
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
    }
    
    
}
