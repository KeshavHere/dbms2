/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_lab_2;
//hablanir@rknec.edu
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

class state
{
  int pid,sid,cost;
  
  state()
  {
    pid = -1; sid=0; cost =0;
  }
  state(int x,int y,int z)
  {
    pid = x; sid =y;cost =z;
  }
  
  
   public static state isFrontier(PriorityQueue<state> x,state y)
    {
        //PriorityQueue<state> x = new PriorityQueue<state>();
        //x = p;
        
        int length = x.size();    
        Iterator<state> value = x.iterator(); 
        while (value.hasNext()) 
        { 
            state ss = value.next(); 
            if(ss.sid == y.sid && ss.cost>y.cost)
            { x.remove(ss);}
            
        } 
       return y;
    }
  
}
class The_Comparator implements Comparator<state>
    { 

        public int compare(state c1, state c2) 
        {
            return (c1.cost - c2.cost);
        }
    }
public class AI_lab_2 
{

    public static void main(String[] args)
    {
        PriorityQueue<state> frontier = new PriorityQueue<state>(7, new The_Comparator());
        //int[][] cost_mat = { {0,3,5,0,0},{3,0,0,3,0},{5,0,0,0,6},{0,3,0,0,3},{0,0,6,3,0} };
       int[][] cost_mat = { 
           {0,3,5,0,0},
           {3,0,0,3,0},
           {5,0,0,0,6},
           {0,3,0,0,3},
           {0,0,6,3,0} };
       /// A-B 3   . A-D 5
       //  B-c 3
       //  C-E 9
       
       
       Queue<state> explored = new LinkedList<>();
        state current = new state() ; //adding the current i.e first state in the frontier 
        frontier.add(current);
        
        while(true)
        {
      //  PriorityQueue<state> ttt = new PriorityQueue<state>(7, new The_Comparator());
            
//            while(!frontier.isEmpty())
//            {
//                state x = frontier.poll();
//                System.out.println(x.sid);
//                ttt.add(x);
//                
//            }
//            
//            while(!ttt.isEmpty())
//            {
//                state x = ttt.poll();
////                System.out.println(x.pid);
//                frontier.add(x);
//                
//            }
            
            
            System.out.println("frontier peek = > ");
            state from_f = frontier.poll();//taking the minimum cost one from the priority queue.
            System.out.println("after from_f");
            //System.out.println("frontier to be added "+ " Cost = >"+ from_f.cost+" PID => "+ from_f.pid+" SID => "+ from_f.sid);
            explored.add(from_f);//adding the minimum in the explored node.
           //in graph search we check first and then only explore that node.
                //apply goal test
            if(goal_test(from_f))//checking if the node is the end node or not.
                break;
            else
            {
                for (int i = 0; i < 5; i++) 
                {
                    if((cost_mat[from_f.sid][i])!=0)
                    {
                        state from_mat = new state();
                        System.out.println("MY : "+cost_mat[from_f.sid][i]+' ' +from_f.cost);
                        from_mat.cost = cost_mat[from_f.sid][i]+from_f.cost;
                        from_mat.pid = from_f.sid;
                        from_mat.sid = i;
                        System.out.println("frontier to be added "+ " Cost = >"+ from_mat.cost+" PID => "+ from_mat.pid+" SID => "+ from_mat.sid);
                        System.out.println("1 NUmber of elements "+ frontier.size());
                        from_mat = state.isFrontier(frontier,from_mat);
                        System.out.println("3 NUmber of elements "+ frontier.size());
                        if(!inExplored(explored,from_mat))
                         frontier.add(from_mat);
                         System.out.println("2 NUmber of elements "+ frontier.size());
                        System.out.println("frontier added "+ " Cost = >"+ from_mat.cost+" PID => "+ from_mat.pid+" SID => "+ from_mat.sid);
                        System.out.println("===============================================================================================");
                    }
                }
               // System.out.println("NUmber of elements "+ frontier.size());
                //System.out.println("Print the frontier");
                //printfrontier(frontier);
            }

        }
            System.out.println("After while");
            System.out.println("Printing the path and the cost ");
            printIt(explored);
    }
    
    public static boolean inExplored(Queue<state> x,state to_check)
      {
          
          for(state xx : x)
          {
                              System.out.print("EXplored:- " + xx.sid+"\n");

                  if(xx.sid == to_check.sid)
                        return true;
          }
                    return false;

          
          
          
//          while(!x.isEmpty())
//            {
//                
//                state ss = x.poll();
//                System.out.print("EXplored:- " + ss.sid+"\n");
//                if(ss.sid == to_check.sid)
//                    return true;
//                //System.out.println("COST => "+ss.cost+" SID => "+ss.sid+ " PID => "+ss.pid);
//            }
//          return false;
      }
    
    public static void printfrontier(PriorityQueue<state> x)
      {
          System.out.println("Number of elements => "+x.size());
           while(!x.isEmpty())
            {
                state ss = x.poll();
                System.out.println("COST => "+ss.cost+" SID => "+ss.sid+ " PID => "+ss.pid);
            }
           
          System.out.println("DOne printing");
      }
       
    public static void printIt(Queue<state> x)
      {
          
          //System.out.println("The total cost is => "+ss.cost);
          Stack<state> stack = new Stack<state>();
         
           while(!x.isEmpty())
          {
               stack.push(x.poll());
          }
           state ss = stack.pop();
           System.out.println("The total cost is => "+ss.cost);
          while(!stack.empty())
          {
                state check = stack.pop();
                if(ss.pid == check.sid)
                {
                    System.out.println("SID => "+ ss.sid +"PID => "+ ss.pid);
                    ss = check;
                }
          }
      }
        
   
    
    public static boolean goal_test(state x)
    {
        if(x.sid == 4)
            return true;
        else
            return false;
    }
    

}
