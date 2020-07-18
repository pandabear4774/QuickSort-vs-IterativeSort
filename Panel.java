import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;
public class Panel extends JPanel implements ActionListener{
    private Random random;
    private Timer timer;
    private int[][] unsortedArrays;
    private ArrayList<SortTracker> sortTracker = new ArrayList<SortTracker>();
    private Rect[] fastRectangles,iterativeRectangles,beadRectangles;
    private boolean[] temp = {true,true,true,false,false,false};
    private int[] arrayAccessCount = new int[3];
    private String[] labels = new String[3];
    private boolean cont = true;
    private int counter, iterativeCurrentIndex,length;
    public Panel(int x, int y) {
    	setSize(x,y);
    	setVisible(true);
    	random = new Random();
    	timer = new Timer(25,this);
    	labels[0] = "Regular Sort (What I Used To Do :(";
    	labels[1] = "Quick Sort (What I Learned And Will Do :D)";
    	labels[2] = "Bead Sort (SATISFYING AS F**K BOI !!! ;P)";
    	for(int i =0; i < 3;i++){
    	    temp[i] = true;
    	}
    	length = (x-100)/5;
    	unsortedArrays = new int[3][length];
    	fastRectangles = new Rect[length];
    	iterativeRectangles = new Rect[length];
    	beadRectangles = new Rect[length];
    	Integer[] arr = new Integer[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Collections.shuffle(Arrays.asList(arr));
    	for(int i = 0; i < length;i++) {
    	    unsortedArrays[0][i] = arr[i];
    	    iterativeRectangles[i] = new Rect(i*5,unsortedArrays[0][i],750);
    	    unsortedArrays[1][i] = unsortedArrays[0][i];
    	    fastRectangles[i] = new Rect(i*5,unsortedArrays[1][i],400);
            unsortedArrays[2][i] = unsortedArrays[0][i];
            beadRectangles[i] = new Rect(i*5,unsortedArrays[2][i],50);
    	}
    	timer.start();
    }
    public void slowSort() {
    	int lowestValue = unsortedArrays[0][iterativeCurrentIndex];
    	int lowestIndex = iterativeCurrentIndex;
    	for(int i = iterativeCurrentIndex; i < iterativeRectangles.length;i++) {
    	    if(unsortedArrays[0][i] < lowestValue) {
 		lowestValue = unsortedArrays[0][i];
    		lowestIndex = i;
       	    }
       	    arrayAccessCount[0]++;
    	}
    	unsortedArrays[0][lowestIndex] = unsortedArrays[0][iterativeCurrentIndex];
    	unsortedArrays[0][iterativeCurrentIndex] = lowestValue;
    	iterativeCurrentIndex++;
    }
    public void partition(int low, int high){
        int pivot = unsortedArrays[1][high];
        int numbersBelowPivot = low-1;
        for(int i = low; i < high; i++){
            if(unsortedArrays[1][i]<pivot){
                numbersBelowPivot++;
                int temp = unsortedArrays[1][i];
                unsortedArrays[1][i] = unsortedArrays[1][numbersBelowPivot];
                unsortedArrays[1][numbersBelowPivot] = temp;
            }
            arrayAccessCount[1]++;
        }
        unsortedArrays[1][high] = unsortedArrays[1][numbersBelowPivot+1];
        unsortedArrays[1][numbersBelowPivot+1] = pivot;
        if(low < numbersBelowPivot){
            sortTracker.add(new SortTracker(low,numbersBelowPivot));
        }
        if(numbersBelowPivot+2<high){
            sortTracker.add(new SortTracker(numbersBelowPivot+2,high));
        }
    }
    public void beadSort(){
        cont = false;
        int lastChangedIndex = unsortedArrays[2].length-1;
        for(int i = lastChangedIndex; i >0;i--){
            if(unsortedArrays[2][i]< unsortedArrays[2][i-1]){
                int temp = unsortedArrays[2][i];
                unsortedArrays[2][i] = unsortedArrays[2][i-1];
                unsortedArrays[2][i-1] = temp;
                cont = true;
            }
            arrayAccessCount[2]++;
        }
    }
    public void actionPerformed(ActionEvent ev){
        if(ev.getSource()==timer){
            if(iterativeCurrentIndex < unsortedArrays[0].length-1){
                slowSort();
            } else if(temp[0]){
                algoComplete("ITERATIVE SORT",0);
                timer.stop();
            }
            if(sortTracker.size()>0){
                partition(sortTracker.get(0).getLow(),sortTracker.get(0).getHigh());
                sortTracker.remove(0);
            } else if(counter==0){
                partition(0,unsortedArrays[1].length-1);
            } else if(temp[1]){
                algoComplete("QUICK SORT",1);
            }
            if(cont){
                beadSort();
            } else if(temp[2]){
                algoComplete("BEAD SORT",2);
            }
            setRectangles();
            System.out.println(++counter);
        }
    }
    public void algoComplete(String algoName, int index1){
        System.out.print(algoName + " DONE");
        temp[index1] = false;
        temp[3+index1] = true;
        labels[index1] = algoName + ": Loops: " + String.valueOf(counter) + " Comparisons Made: " + String.valueOf(arrayAccessCount[index1]);
    }
    public void setRectangles(){
        for(int i = 0; i < length;i++){
            iterativeRectangles[i].setHeight(unsortedArrays[0][i]);
            fastRectangles[i].setHeight(unsortedArrays[1][i]);
            beadRectangles[i].setHeight(unsortedArrays[2][i]);
        }
        repaint();
    }
    public void paint(Graphics g) {
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
        g.setFont(newFont);
        g.drawString("FPS: 40",50,50);
        g.drawString(labels[0],50,100);
        g.drawString(labels[1],50,450);
        g.drawString(labels[2],50,800);
    	for(int i = 0; i < length;i++) {
    	    //basically if algo is done use green color else use black color
  	    if(temp[3]){
	        g.setColor(Color.green);
	    }
	    //no else cause default color is black
	    iterativeRectangles[i].paint(g);
	    if(temp[4]){
	        g.setColor(Color.green);
	    } else{
	        g.setColor(Color.black);
	   }
  	    fastRectangles[i].paint(g);
	    if(temp[5]){
	        g.setColor(Color.green);
	    } else {
	        g.setColor(Color.black);
	    }
	    beadRectangles[i].paint(g);
	    //sets defualt color back to black
	    g.setColor(Color.black);
    	}
    }
}