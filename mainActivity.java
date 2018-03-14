import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

//Uncomment Println's to help understand the code

//-----TODO-----
//Add dynamism (float[3][5] is static for now for example)
//Test this better
//This is for Max function only, implement Min
//Pos-Analise (change function values w/o re-doing it all again)
//--------------
class Main 
{
    static int rows=1;
    static int collumns=1;
    static float[][] simplex;
    static float[] choosePivot;
    static int[] PivotPos;
    static float[] divLines;
    //static float simplex[][]=new float[rows][collumns];
    //static float choosePivot[]=new float[rows-1];
    //static ArrayList<float[]> divLines = new ArrayList<float[rows]>();
    //static float divLines[]=new float[rows];
    //static int PivotPos[]=new int[rows-1];
    static int negPos=0;
    static int posMinFinal = 0;

  public static void Simplex() 
  {
    //Find min in z
    float min = simplex[rows-1][0];
    int pos=0;
    for (int j=simplex.length-1; j > -1; j--) 
    {
        if (simplex[rows-1][j] < min) 
        { 
            min = simplex[rows-1][j];
            pos=j;
        }
    }
    //System.out.println("\nmin:"+min+" pos: "+pos);
    
    //Finding pivot (< first collumn / min value in z)
    for (int i=0;i<rows-1;i++)
    {
      choosePivot[i]=simplex[i][0]/simplex[i][pos];
      PivotPos[i]=i;
      //System.out.println(simplex[i][0]/simplex[i][pos]);
    }
    
    //Saving both the Pivot value and position
    float minPivot = choosePivot[0];
    int posPivot=0;
    for (int j=0; j < choosePivot.length; j++)
    {
      
        if (choosePivot[j] < minPivot) 
        {  
          //System.out.println(choosePivot[j]);
            minPivot = choosePivot[j];
            posPivot=j;
        }
    }
    //System.out.println("\nmin:"+minPivot+" pos: "+PivotPos[posPivot]);
    
    //Gauss law (finding by which ammount i should divide/multiply by, and saving them in an array)
    int x=0;
    for (int i=0;i<simplex.length;i++)
    {
      if (i != PivotPos[posPivot])
        {
          
          divLines[x]=-1*(simplex[i][pos])/simplex[PivotPos[posPivot]][pos];
          //System.out.println(divLines[x]);
          x++;
        }
    }
    divLines[x]=1/simplex[PivotPos[posPivot]][pos];
    
    //Gauss law (Using previous array to execute law)
    for (int i=0;i<simplex[0].length;i++)
    {
      System.out.print("\n");
      for (int j=0;j<simplex.length;j++)
      {
        if (j != PivotPos[posPivot])
        {
          if(j==0)
            simplex[j][i]=simplex[j][i]+divLines[j]*simplex[PivotPos[posPivot]][i];
            else
              simplex[j][i]=simplex[j][i]+divLines[j-1]*simplex[PivotPos[posPivot]][i];

        }
          
      }
    }
    
    //Fixing pivot to 1 (and his row)
    float temp=(1/simplex[PivotPos[posPivot]][pos]);//Had to do this here, wasn't working when i made the calculation all toghether down there (classic java hehe)
    for (int i=0;i<simplex[PivotPos[posPivot]].length;i++)
    {
      simplex[PivotPos[posPivot]][i]=temp*simplex[PivotPos[posPivot]][i];
    }
    
    //Show Simplex table
    for (int i=0;i<rows;i++)
    {
      System.out.print("\n ");
      for(int j=0;j<collumns;j++)
       System.out.print(simplex[i][j]+" ");
    }
    
    //Finding the lowest value (only used if it's negative)
    float minFinal = simplex[simplex.length-1][0];
    for (int i=0;i<simplex[simplex.length-1].length;i++)
    {
      if (simplex[simplex.length-1][i] < minFinal) 
        {  
          
            posMinFinal=i;
            minFinal = simplex[simplex.length-1][i];
  
        }
    }
    
  }
  public static void main(String[] args) 
  {
    
    Scanner reader = new Scanner(System.in);
    System.out.println("Enter number of rows: (3)");
    rows = reader.nextInt();
    System.out.println("Enter number of collumns: (5)");
    collumns = reader.nextInt();
    
    choosePivot = new float[rows-1];
    simplex = new float[rows][collumns];
    PivotPos = new int[rows-1];
    divLines = new float[rows];
    
    //Temporarily declaring restrictions
    simplex[0][0]=120;
    simplex[0][1]=3;
    simplex[0][2]=10;
    simplex[0][3]=5;
    simplex[0][4]=1;
    simplex[0][5]=0;
    simplex[0][6]=0;
    
    simplex[1][0]=6;
    simplex[1][1]=5;
    simplex[1][2]=2;
    simplex[1][3]=8;
    simplex[1][4]=0;
    simplex[1][5]=1;
    simplex[1][6]=0;
    
    simplex[2][0]=105;
    simplex[2][1]=8;
    simplex[2][2]=10;
    simplex[2][3]=3;
    simplex[2][4]=0;
    simplex[2][5]=0;
    simplex[2][6]=1;
    
    simplex[3][0]=0;
    simplex[3][1]=-3;
    simplex[3][2]=-4;
    simplex[3][3]=-1;
    simplex[3][4]=0;
    simplex[3][5]=0;
    simplex[3][6]=0;

    
    
    //Show Simplex table
    for (int i=0;i<rows;i++)
    {
      System.out.print("\n ");
      for(int j=0;j<collumns;j++)
       System.out.print(simplex[i][j]+" ");
    }
    
    Simplex();
    

    //If lowest value is negative then we will repeat last methods
    if(simplex[simplex.length-1][posMinFinal]<0)
    {
      System.out.println("\nRepete a parte de cima");
      Simplex();
    }
    
  }
}
