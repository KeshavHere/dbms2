package com.patterns.visitor;
import static java.lang.Math.abs;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import org.w3c.dom.css.Counter;

class The_Comparator implements Comparator<Puzzle8Matrix> { 

        public int compare(Puzzle8Matrix c1, Puzzle8Matrix c2) 
        {
            return (c1.h_value - c2.h_value);
      }
    }

class Puzzle8Matrix 
{
    int[][] state = new int[4][4];
    Puzzle8Matrix parent ;
    int h_value =0;
    
    public Puzzle8Matrix() 
    {
        for (int i = 0; i < 4; i++)  {for (int j = 0; j < 4; j++) {state[i][j] =0; } }
    }
    
    public Puzzle8Matrix(int[] list)
    {
        int counter = 0;
      for (int i = 0; i < 4; i++)  {
            for (int j = 0; j < 4; j++) {
                state[i][j] =list[counter++];
            }
        }
    }
    
    
    public static int heuristic(Puzzle8Matrix a,Puzzle8Matrix b)
    {
        int value =0;    
        for (int i = 0; i < 4; i++)  {
            for (int j = 0; j < 4; j++) {
                int temp = a.state[i][j];
                
                for (int ii = 0; ii < 4; ii++)  {
                 for (int jj = 0; jj < 4; jj++) {
                   
                     if(temp == b.state[ii][jj]&& temp!=0)
                         value = value+(abs(ii -i)+abs(jj-j));
            }  }
               
               // System.out.println("The h(n) after "+temp+" is =>" +value);
        }
    }
        return value;
   }
    
    public void print()
    {
      for (int i = 0; i < 4; i++)  {
            for (int j = 0; j < 4; j++) {
                System.out.print(state[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
}


public class A_star_8_puzzle 
{

    public static void main(String[] args) 
    {
        
        System.out.println("Strating");
      System.out.println("Enter the 4 by 4 matrix for the 8 puzzle");
        Scanner sc = new Scanner(System.in);
        int[][] in_mat = new int[4][4];
        int[] as_list =  new int[16];int numb = 0;
        //taking the input from the user.
        for (int i = 0; i < 4; i++)  {
            for (int j = 0; j < 4; j++)  {
                    in_mat[i][j] = sc.nextInt();
                    as_list[numb++] = in_mat[i][j];
            }
        }
        
        check_parity cp =  new check_parity();
        String p = cp.parity(in_mat);
        System.out.println("The parity is => "+p);
        //till here the parity of the input matrix is known successfully.
        
        //creating the goal state
        int[] pass = new int[16];
        if(p=="even")
            pass = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
        else
            pass = new int[]{1,2,3,4,12,13,14,5,11,16,15,6,10,9,8,0};

        Puzzle8Matrix goal_state =  new Puzzle8Matrix(pass);
        Puzzle8Matrix initial_state = new Puzzle8Matrix(as_list);
        //goal and initial state created
        System.out.println("The goal state is =>"); goal_state.print();
        System.out.println("The initial state is =>"); initial_state.print(); 
        int heuristic_value = Puzzle8Matrix.heuristic(goal_state, initial_state);
        
        main2.doit(initial_state, goal_state);
        
     }
            
}

class main2
{
   public static void doit(Puzzle8Matrix initial,Puzzle8Matrix goal)
   {
        PriorityQueue<Puzzle8Matrix > frontier = new PriorityQueue<Puzzle8Matrix >(50, new The_Comparator());
        Puzzle8Matrix dummy = new Puzzle8Matrix();
       
       Queue<Puzzle8Matrix> explored = new LinkedList<>();
        frontier.add(initial);
        int counter =0;
        while(true)
        {
           counter++;
            Puzzle8Matrix from_f = new Puzzle8Matrix();
            if(!frontier.isEmpty())
               from_f= frontier.poll();//taking the minimum cost one from the priority queue.
            from_f.print();
            explored.add(from_f);//adding the minimum in the explored node.
           //in graph search we check first and then only explore that node.
                //apply goal test
            if(dummy.heuristic(from_f,goal)==0 )//checking if the node is the end node or not.
                break;
            else
            {
//                System.out.println("In else");
                Puzzle8Matrix xx = up(from_f);
                xx.parent = from_f;
                
                isFrontier(frontier,xx);
                if(dummy.heuristic(from_f,xx)!= 0 && inExplored(explored, xx)== false)
                {
                    System.out.println("In if");
                    xx.h_value = dummy.heuristic(from_f,goal)+counter; 
                    frontier.add(xx); 
                    System.out.println("After doing the up we are adding in the frontier");
                    xx.print();
                }
                
                xx = down(from_f);
                xx.parent = from_f;
                isFrontier(frontier,xx);
                   if(dummy.heuristic(from_f,xx)!= 0 && inExplored(explored, xx)== false)
                {
                    xx.h_value = dummy.heuristic(from_f,goal)+counter; 
                    frontier.add(xx); 
                    System.out.println("After doing the down we are adding in the frontier");
                    xx.print();
                }                   
                xx = right(from_f);
                xx.parent = from_f;
                isFrontier(frontier,xx);
                   if(dummy.heuristic(from_f,xx)!= 0 && inExplored(explored, xx)== false)
                {
                    xx.h_value = dummy.heuristic(from_f,goal)+counter; 
                    frontier.add(xx); 
                    System.out.println("After doing the right we are adding in the frontier");
                    xx.print();
                }
                   
                xx = left(from_f);
                xx.parent = from_f;
                isFrontier(frontier,xx);
                if(dummy.heuristic(from_f,xx)!= 0 && inExplored(explored, xx)== false)
                {
                    xx.h_value = dummy.heuristic(from_f,goal)+counter; 
                    frontier.add(xx); 
                    System.out.println("After doing the left we are adding in the frontier");
                    xx.print();
                }
                System.out.println("==========================================================================");
            }

        }
        System.out.println("Printing all the explored nodes => ");
       // printfrontier(explored);
        printIt(explored);
        
    }
   
   public static void printfrontier(Queue<Puzzle8Matrix> x)
      {
          System.out.println("Number of elements => "+x.size());
          while(!x.isEmpty())
            {
                Puzzle8Matrix ss = x.poll();
                ss.print();
                System.out.println("=======================");
            }
          System.out.println("DOne printing");
      }
   
   public static boolean inExplored(Queue<Puzzle8Matrix> x,Puzzle8Matrix to_check)
      {
          for(Puzzle8Matrix xx : x)
          {
                if(Puzzle8Matrix.heuristic(xx,to_check)==0)
                        return true;
          }
                    return false;
      }
   
   public static void isFrontier(PriorityQueue<Puzzle8Matrix> x,Puzzle8Matrix y)
    {
        
        int length = x.size();    
//       Iterator<Puzzle8Matrix> value = x.iterator(); 
//        while (value.hasNext()) 
//        { 
//            Puzzle8Matrix ss = value.next(); 
//            if( Puzzle8Matrix.heuristic(ss,y)==0&& ss.h_value>y.h_value)
//            { x.remove(ss);}
//            
//        } 

         for(Puzzle8Matrix ss : x)
          {
                    if( Puzzle8Matrix.heuristic(ss,y)==0&& ss.h_value>y.h_value)
           { x.remove(ss);break;}         
          }
    
    }
   
   public static void printIt(Queue<Puzzle8Matrix> x)
      {
          
          //System.out.println("The total cost is => "+ss.cost);
          
          System.out.println("FINAL \n ==============================================");
          Stack<Puzzle8Matrix> stack = new Stack<Puzzle8Matrix>();
         
           while(!x.isEmpty())
          {
               stack.push(x.poll());
          }
           Puzzle8Matrix ss = stack.pop();
           int counterkk =0 ;
           while(!stack.empty())
          {
                Puzzle8Matrix check = stack.pop();
                if(Puzzle8Matrix.heuristic(ss.parent, check)==0)
                {
                    counterkk++;
                    check.print();
                    ss = check;
                }
                System.out.println("============================");
          }
           System.out.println("The total cost is => "+(counterkk+1));
           
      }
        
   public static Puzzle8Matrix up(Puzzle8Matrix b)
   {
       int[] input_list = new int[16]; int couter =0;
       for (int i = 0; i < 4; i++)  {
            for (int j = 0; j < 4; j++) {
                    input_list[couter++] = b.state[i][j];
            }
         }
       Puzzle8Matrix a = new Puzzle8Matrix(input_list);
       
     
      int y_of_0 =0,x_of_0=0;
      for (int i = 0; i < 4; i++)  {
            for (int j = 0; j < 4; j++) {
                if(a.state[i][j] == 0)
                { y_of_0 = i; x_of_0 = j; break;}
            }
        }
      if(y_of_0 != 0)
      {
         int temp = a.state[y_of_0-1][x_of_0];
         a.state[y_of_0-1][x_of_0] = 0;
         a.state[y_of_0][x_of_0] = temp;
      }
      
     return a;
   }
   
   public static Puzzle8Matrix down(Puzzle8Matrix b)
   {
      int[] input_list = new int[16]; int couter =0;
       for (int i = 0; i < 4; i++)  {
            for (int j = 0; j < 4; j++) {
                    input_list[couter++] = b.state[i][j];
            }
         }
       Puzzle8Matrix a = new Puzzle8Matrix(input_list);
      int y_of_0 =0,x_of_0=0;
      for (int i = 0; i < 4; i++)  {
            for (int j = 0; j < 4; j++) {
                if(a.state[i][j] == 0)
                { y_of_0 = i; x_of_0 = j; break;}
            }
        }
      if(y_of_0 != 3)
      {
         int temp = a.state[y_of_0+1][x_of_0];
         a.state[y_of_0+1][x_of_0] = 0;
         a.state[y_of_0][x_of_0] = temp;
      }
     return a;
   }
   
   public static Puzzle8Matrix left(Puzzle8Matrix b)
   {
       int[] input_list = new int[16]; int couter =0;
       for (int i = 0; i < 4; i++)  {
            for (int j = 0; j < 4; j++) {
                    input_list[couter++] = b.state[i][j];
            }
         }
       Puzzle8Matrix a = new Puzzle8Matrix(input_list);
      int y_of_0 =0,x_of_0=0;
      for (int i = 0; i < 4; i++)  {
            for (int j = 0; j < 4; j++) {
                if(a.state[i][j] == 0)
                { y_of_0 = i; x_of_0 = j; break;}
            }
        }
      if(x_of_0 != 0)
      {
         int temp = a.state[y_of_0][x_of_0-1];
         a.state[y_of_0][x_of_0-1] = 0;
         a.state[y_of_0][x_of_0] = temp;
      }
     return a;
   }
   
   public static Puzzle8Matrix right(Puzzle8Matrix b)
   {
       int[] input_list = new int[16]; int couter =0;
       for (int i = 0; i < 4; i++)  {
            for (int j = 0; j < 4; j++) {
                    input_list[couter++] = b.state[i][j];
            }
         }
       Puzzle8Matrix a = new Puzzle8Matrix(input_list);
      int y_of_0 =0,x_of_0=0;
      for (int i = 0; i < 4; i++)  {
            for (int j = 0; j < 4; j++) {
                if(a.state[i][j] == 0)
                { y_of_0 = i; x_of_0 = j; break;}
            }
        }
      if(x_of_0 != 3)
      {
         int temp = a.state[y_of_0][x_of_0+1];
         a.state[y_of_0][x_of_0+1] = 0;
         a.state[y_of_0][x_of_0] = temp;
      }
     return a;
   }
   
   
   
}

class check_parity {
    
    public String parity( int[][] mat)
    { 
        String pp = new String();
        
        int[] mat_list =  new int[mat[0].length*mat.length];
        System.out.println("The length is => "+mat_list.length);
        int counter = 0;
        System.out.println("In parity function");
        //making the list of the numbers.
        System.out.println("The list formed is => ");
         for (int i = 0; i < mat.length; i++) 
         {
              for (int j = 0; j < mat.length; j++) 
              {
                  
                 if(mat[i][j] !=0)
                 {
                    mat_list[counter] = mat[i][j];
                    System.out.print(mat_list[counter]+" ");
                    counter++;
                 }
                  
              }
              
         }
         
         //now seeing how many parity is there in the input given
         int count_parity =0;
         System.out.println("");
         for (int i = 0; i < mat_list.length; i++) 
         {
             for (int j = i; j < mat_list.length; j++) 
              {
                 
                  //System.out.println(mat_list[i]);
                  if(mat_list[i]>mat_list[j])  
                  {
                      count_parity++;
                      System.out.println("changing => "+mat_list[i]+" "+mat_list[j]);
                  }
              }
         }
         
         if(count_parity %2 ==0)
             return "even";
         
         return "odd";
    }
}
