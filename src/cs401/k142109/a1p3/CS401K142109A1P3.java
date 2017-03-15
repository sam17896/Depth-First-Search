package cs401.k142109.a1p3;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

class Node{
    int x;
    int y;
    int value;
    boolean visited;
    Node parent;
    Node(int x , int y){
        this.x = x;
        this.y = y;
        value = 1;
        parent = null;
        visited = false;
    }
    Node getParent(){
        return parent;
    }
    void setParent(Node n){
        parent = n;
    }
    int getX(){
        return x;
    }
    int getY(){
        return y;
    }
    void setX(int x){
        this.x = x;
    }
    void setY(int y){
        this.y = y;
    }
     int getValue(){
         return value;
     }
     boolean isVisited(){
         return visited;
     }
    void setValue(int x) {
        value = x;
    }
}
class DFS{
    Stack<Node> stack = new Stack();  
    Node[] nodes;
    int[][] maze;
    Node size;
    Node start;
    Node goal;
    Node curr;
    int count=0;
    void Action(){
        int a = curr.getX();
        int b = curr.getY();
        int sx = size.getX();
        int sy = size.getY();
        int x;
        int y;
        
        //DownRight
        x = a+1;
        y = b+1;
        if(y < sy && x < sx){
            if(nodes[x*sy+y].getValue() == 0 && !nodes[x*sy+y].isVisited()){
              stack.push(nodes[x*sy+y]);
              nodes[x*sy+y].visited = true;
              nodes[x*sy+y].setParent(curr);
      //        System.out.println(a+" "+b+" " + "RIGHTDOWN");
            }
        }
        
         
        //RightUP
        x = a-1;
        y = b+1;
        if(x > -1 && y < sy){
            if(nodes[x*sy+y].getValue() == 0 && !nodes[x*sy+y].isVisited()){
              stack.push(nodes[x*sy+y]);
              nodes[x*sy+y].visited = true;
              nodes[x*sy+y].setParent(curr);
       //       System.out.println(a+" "+b+" " + "RightUP");
            }
        }
        
       //DownLeft
        
        x = a+1;
        y = b-1;
        
        if(y > -1 && x < sx){
            if(nodes[x*sy+y].getValue() == 0 && !nodes[x*sy+y].isVisited()){
              stack.push(nodes[x*sy+y]);
              nodes[x*sy+y].visited = true;
              nodes[x*sy+y].setParent(curr);
     //         System.out.println(a+" "+b+" " + "DownLeft");
            }
        }
        
        
        //UPLEFT
        
        x = a-1;
        y = b-1;
        if(x > -1 && y > -1){
            if(nodes[x*sy+y].getValue() == 0 && !nodes[x*sy+y].isVisited()){
              stack.push(nodes[x*sy+y]);
              nodes[x*sy+y].visited = true;
              nodes[x*sy+y].setParent(curr);
       //       System.out.println(a+" "+b+" " + "UPLEFT");
            }
        }
        
        //Left
        x = a;
        y = b-1;
        if(y > -1){
            if(nodes[x*sy+y].getValue() == 0 && !nodes[x*sy+y].isVisited()){
              stack.push(nodes[x*sy+y]);
              nodes[x*sy+y].visited = true;
              nodes[x*sy+y].setParent(curr);
    //          System.out.println(a+" "+b+" " + "LEFT");
            }
        }
        
        
        //right
        x = a;
        y = b+1;
        
        if(y < sy){
            if(nodes[x*sy+y].getValue() == 0 && !nodes[x*sy+y].isVisited()){
              stack.push(nodes[x*sy+y]);
              nodes[x*sy+y].visited = true;
              nodes[x*sy+y].setParent(curr);
    //          System.out.println(a+" "+b+" " + "right");
            }
        }
        
        
        
        
        //down
        x = a+1;
        y = b;
        if(x < sx){
            if(nodes[x*sy+y].getValue() == 0 && !nodes[x*sy+y].isVisited()){
              stack.push(nodes[x*sy+y]);
              nodes[x*sy+y].visited = true;
              nodes[x*sy+y].setParent(curr);
    //          System.out.println(a+" "+b+" " + "Down");
           }
        }
       
        //up
        x = a-1;
        y = b;
        if(x > -1 && y > -1){
            if(nodes[x*sy+y].getValue() == 0 && !nodes[x*sy+y].isVisited()){
              stack.push(nodes[x*sy+y]);
              nodes[x*sy+y].visited = true;
              nodes[x*sy+y].setParent(curr);
    //          System.out.println(a+" "+b+" " + "UP");
            }
        }
        
        
    }
    
    boolean DepthFirst(){
        if(maze[start.getX()][start.getY()]==1){
            System.out.println("Invalid start Position!");
            return false;
        }
        if(maze[goal.getX()][goal.getY()]==1){
            System.out.println("Invalid goal Position!");
            return false;
        }
        stack.push(start);
      //  System.out.println("Explored Nodes");
        nodes[start.x*size.y+start.y].visited = true;
        while(!stack.isEmpty()){
            count++;
            curr = stack.pop();
            if(GoalTest(curr))
                return true;
            else
            {    
                Action();
     //           System.out.println("("+curr.getX()+","+curr.getY()+")");
            }
        }
        return false;
    }
    
    boolean GoalTest(Node n){
        return n.getX()==goal.getX()&&n.getY()==goal.getY();
    }
      
    String print(){
         int cost = 0 ;
         String path = ""; 
         Stack<Node> s = new Stack();
         while(curr != null){
             s.push(curr);
             curr = curr.getParent();
             cost++;
         }
         while(!s.isEmpty()){
             Node n = s.pop();
             path += n.getX() + " " + n.getY() + "\n";
         }
         path = path.trim();
         path += "\n";
         path += cost;
         return path;
     }
}
public class CS401K142109A1P3 {
    public static void main(String[] args) {
     int x, y = -1, sx, sy, fx, fy;
     DFS dfs = new DFS();
     int maze[][] = null;
     String words[];
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("input.txt"), charset);) 
        {
            String line;
            int i = 0;
            while((line = reader.readLine()) != null){
                switch(i){
                    case 0:
                        words = line.split(" ");
                        x = Integer.parseInt(words[0]);
                        y = Integer.parseInt(words[1]);
                        if(x > 500 || y > 500 || x < 0 || y < 0){
                            System.out.println("Maze out of size");
                        }
                        else{
                            dfs.maze = new int[x][y];
                            dfs.size = new Node(x,y);
                        }
                        i++;
                        break;
                    case 1:
                        words = line.split(" ");
                        sx = Integer.parseInt(words[0]);
                        sy = Integer.parseInt(words[1]);
                        i++;
                        dfs.start = new Node(sx, sy);
                        break;
                    case 2:
                        words = line.split(" ");
                        fx = Integer.parseInt(words[0]);
                        fy = Integer.parseInt(words[1]);
                        dfs.goal = new Node(fx,fy);
                        i++;
                        break;
                    default:
                        words = line.split(" ");
                        for(int j=0; j<y; j++){
                            dfs.maze[i-3][j] = Integer.parseInt(words[j]);
                        }
                        i++;
                        break;
                }
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        int k = 0;
       dfs.nodes = new Node[dfs.size.getX()*dfs.size.getY()];
       for(int i=0;i<dfs.size.getX();i++){
           for(int j=0;j<dfs.size.getY();j++){
               dfs.nodes[k] = new Node(i,j);
               dfs.nodes[k].setValue(dfs.maze[i][j]);
             //  System.out.print(dfs.nodes[k].getValue()+" ");
               k++;
           }
       //    System.out.println("");
       }
       if(dfs.DepthFirst()){
           String path = dfs.print();
          try(FileWriter out = new FileWriter("CS401-K142109-A1P3Output.txt");){
              out.write(path);
              out.close();
          } 
          catch(Exception e){
              System.out.println(e.getMessage());
          }
       }
       else{
           System.out.println("Path Not Found");
           try(FileWriter out = new FileWriter("CS401-K142109-A1P3Output.txt");){
              out.write("Path Not Found");
              out.close();
          } 
          catch(Exception e){
              System.out.println(e.getMessage());
          }
       }
        System.out.println(dfs.count);
    }
}
