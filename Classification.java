
package classification;

import java.util.Scanner;

public class Classification {

    public static double calculate(double[] w,double[] x){
   double y=0;
   double net =w[0]*x[0]+w[1]*x[1]+w[2]*x[2];
    if(net<0)
        y=-1;
    else if(net>0)
        y=1;
    return y;
    }
    
    public static double[] train(double[] desired){
        double[] w=new double[]{0.3,0.5,0.7};
        double[][] xtrain=new double[][]{{-1,-1},{-1,1},{1,-1},{1,1}};
        double x[]=new double[3];
        double e=0;
        double[] delw=new double[3];
        double net=0;
        double y=0;
        double etotal=999;
        while(etotal>0){
            etotal=0;
            for(int i=0;i<4;i++){
                double d=desired[i];
                x[0]=xtrain[i][0];
                x[1]=xtrain[i][1];
                x[2]=1;
                net=w[0]*x[0]+w[1]*x[1]+w[2];
                if(net<0)
                    y=-1;
                else if(net>0)
                    y=1;
                for(int j=0;j<3;j++){
                    delw[j]=(d-y)*x[j];
                }
                for(int j=0;j<3;j++){
                    w[j]=w[j]+delw[j];
                }
                e=0.5*(y-d)*(y-d);
                etotal=etotal+e;
            }
        }
        return w;
    }
    
    public static void main(String[] args) {
        double y=0;
        double x[]=new double[3];
        double w[]=new double[3];
        Scanner in=new Scanner(System.in);
        System.out.println("1.AND classification\n2.OR classidication\n3.NAND classification\n"
                + "4.NOR classification\n5.XOR classification\n6.Exit");
        int ch=0;
        while(ch!=6){
        System.out.println("Enter the type of classification :");
        ch=in.nextInt();
        switch(ch){
            case 1:{
             //int[] w=new int[]{1,1,-1};
             //y=calculate(w,x);
             System.out.println("Enter the input:");
             x[0]=in.nextDouble();
             x[1]=in.nextDouble();
             x[2]=1;
             double desired[]=new double[]{-1,-1,-1,1};
             w=train(desired);
             System.out.println("Weights: "+ w[0]+","+w[1]+","+w[2]);
             y=calculate(w,x);
             break;
            }
            case 2:{
            //int[] w=new int[]{1,1,1};
            //y=calculate(w,x);
            System.out.println("Enter the input:");
            x[0]=in.nextDouble();
            x[1]=in.nextDouble();
            x[2]=1;
            double desired[]=new double[]{-1,1,1,1};
            w=train(desired);
            System.out.println("Weights: "+ w[0]+","+w[1]+","+w[2]);
            y=calculate(w,x);
            break;
            }
            case 3:{
            //int[] w=new int[]{1,1,1};
            //y=calculate(w,x);
            System.out.println("Enter the input:");
            x[0]=in.nextDouble();
            x[1]=in.nextDouble();
            x[2]=1;
            double desired[]=new double[]{1,1,1,-1};
            w=train(desired);
            System.out.println("Weights: "+ w[0]+","+w[1]+","+w[2]);
            y=calculate(w,x);
            break;
            }
            case 4:{
            //int[] w=new int[]{1,1,1};
            //y=calculate(w,x);
            System.out.println("Enter the input:");
            x[0]=in.nextDouble();
            x[1]=in.nextDouble();
            x[2]=1;
            double desired[]=new double[]{1,-1,-1,-1};
            w=train(desired);
            System.out.println("Weights: "+ w[0]+","+w[1]+","+w[2]);
            y=calculate(w,x);
            break;
            }
            case 5:{
            //int[] w=new int[]{1,1,1};
            //y=calculate(w,x);
            System.out.println("Enter the input:");
            x[0]=in.nextDouble();
            x[1]=in.nextDouble();
            x[2]=1;
            double desired[]=new double[]{-1,1,1,-1};
            w=train(desired);
            System.out.println("Weights: "+ w[0]+","+w[1]+","+w[2]);
            y=calculate(w,x);
            break;
            }
            case 6:System.exit(0);
        }
        System.out.println("Output: "+y);
    }
    }
}
