import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class AStarAlgorithm {

	public AStarAlgorithm() {
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		int xStart =0, yStart =0, xEnd =0, yEnd =0;
        String fileName = "E:/Workspace/ShortestPath/files/map2.txt"; 
        try {
        	FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Scanner inFile = new Scanner(bufferedReader);

            for(int i=0; inFile.hasNextLine();i++) {
              Scanner matCol = new Scanner(inFile.nextLine());
              ArrayList<String> colData = new ArrayList<String>();
              for(int j=0; matCol.hasNext();j++)
              {
            	  String nextValue = matCol.next();
            	  if(!nextValue.equals(" ")) {
            		  colData.add(nextValue);
            		  if(nextValue.equals("S")) {
            			  xStart = i;
            			  yStart = j;
            		  }else if(nextValue.equals("E")) {
            			  xEnd = i;
            			  yEnd = j;
            		  }
            	  }
              }
              arr.add(colData);
            }
            inFile.close();
        }catch(Exception e) {
        	System.out.println("Error");
        }
		NodeDetails map2d = new NodeDetails(arr);
		printMatrix(arr);
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		map2d.findPath(xStart, yStart, xEnd, yEnd);
		printMatrix(arr);
		
	}
	
	public static void printMatrix(ArrayList<ArrayList<String>> matrix) {
	      for (int r = 0; r < matrix.size(); r++){
	        for (int c = 0; c < matrix.get(r).size(); c++) {
	        	 System.out.print(matrix.get(r).get(c) + " ");
	        } 
	        System.out.println();
	      }
	    }

	public static void main(String[] args) {
		new AStarAlgorithm();
	}
}
