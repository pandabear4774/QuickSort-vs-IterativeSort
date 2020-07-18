import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class QSort{
    private Random random = new Random();
    private int counter = 0;
    private ArrayList<Integer>sortedArrayList = new ArrayList<Integer>();
    private int[] unsortedArray;
    private Scanner scan;
    private String userInput;
    private boolean go;
    public QSort(){
        //if you want the user to enter numbers in to be sorted call this class from the runner class instead of frame
        scan = new Scanner(System.in);
        go = true;
        System.out.println("ENTER NUMBERS TO BE SORTED: ");
        System.out.println("ENTER A LETTER WHEN DONE");
        String g;
        do{
            userInput = scan.nextLine();
            try{
                sortedArrayList.add(Integer.valueOf(userInput));
            }catch(NumberFormatException e){
                go = false;
            }
        }while(go);
        int length = sortedArrayList.size();
        unsortedArray = new int[length];
        for(int i = 0; i < length;i++){
            unsortedArray[i] = sortedArrayList.get(i);
        }
        printArray("UNSORTED NUMBERS");
        sort(unsortedArray,0,unsortedArray.length-1);
        printArray("SORTED NUMBERS");
    }
    public int partition(int[] array, int low, int high){
        int pivot = array[high];
        int numbersBelowPivot = low-1;
        for(int i = low; i < high; i++){
            if(array[i]<pivot){
                numbersBelowPivot++;
                int temp = array[i];
                array[i] = array[numbersBelowPivot];
                array[numbersBelowPivot] = temp;
            }
        }
        array[high] = array[numbersBelowPivot+1];
        array[numbersBelowPivot+1] = pivot;
        return numbersBelowPivot+1;
    }
    public void sort(int[] array,int low, int high){
        counter++;
        if(low < high){
            int index = partition(array,low,high);
            printArray(String.valueOf(index));
            sort(array,low,index-1);
            sort(array,index+1,high);
        }
    }
    public void printArray(String s){
        System.out.println(s);
        for(int i = 0; i < unsortedArray.length; i ++){
            System.out.print(unsortedArray[i] + " ");
        }
        System.out.println();
    }
}