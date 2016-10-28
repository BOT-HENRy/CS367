// TODO *** add comments as specified in the commenting guide ***

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class MapBenchmark{

	private static void printInfo(long[] timeList){
		long currMax = (long) 0;
		long currMin = (long) 0;
		long sumTime = (long) 0;
		
		for(int i = 0; i < timeList.length; i++){
			if(i == 0){
				currMax = timeList[i];
				currMin = timeList[i];
			}
			long currTime = timeList[i];
			if (currTime > currMax)
			{
				currMax = currTime;
			}
			if (currTime < currMin)
			{
				currMin = currTime;
			}
			sumTime = sumTime + currTime;
		}
		double avgTime = sumTime / timeList.length;
		double stanDeviation = 0;
		long sumforD = (long) 0;
		for(int i = 0; i < timeList.length; i++){
			sumforD = (long) (sumforD + Math.pow(timeList[i] - avgTime, 2));
		}
		stanDeviation = (long) Math.sqrt(sumforD / timeList.length);
	
		System.out.printf("Min\t: %d\nMax\t: %d\nMean\t: %.3f\nStd Dev\t: %.3f",
				currMin,currMax,avgTime,stanDeviation);
		System.out.println("\n");
	}
	
    public static void main(String[] args) throws FileNotFoundException {
        int numIter; //number of iterations to run
        Scanner in;
		File inFile;

		try{
			inFile = new File(args[0]);
			in = new Scanner(inFile);
			numIter = Integer.parseInt(args[1]);
		}
		catch (FileNotFoundException e){
			System.out.println("File not Found!");
			return;
		}
		
		long[] putHashData = new long[numIter];
		long[] putTreeData = new long[numIter];
		long[] getHashData = new long[numIter];
		long[] getTreeData = new long[numIter];
		long[] floorKeyHashData = new long[numIter];
		long[] floorKeyTreeData = new long[numIter];
		long[] removeHashData = new long[numIter];
		long[] removeTreeData = new long[numIter];
		
		long startTime,endTime;
		
       for(int ndx = 0;ndx < numIter;ndx++){
			//Basic progress bar
            System.out.print(String.format("%.2f",100* ndx/(float)numIter) +
							"% done \r"); 
            
            SimpleMapADT<Integer,String> hashMap = 
            		new SimpleHashMap<Integer,String>();
			SimpleMapADT<Integer,String> treeMap = 
					new SimpleTreeMap<Integer,String>();
			
			//time for put data into HashMap
			try {
				in = new Scanner(inFile);
			} catch (FileNotFoundException e) {
				System.out.print("File not Found!");
				break;
			}
			startTime = System.currentTimeMillis();
			while(in.hasNext()){
				hashMap.put(in.nextInt(),in.next());
			}
			endTime = System.currentTimeMillis();			
			putHashData[ndx] = endTime-startTime;
			
			//time for put data into TreeMap
			in = new Scanner(inFile);
			startTime = System.currentTimeMillis();
			while(in.hasNext()){
				treeMap.put(in.nextInt(),in.next());
			}
			endTime = System.currentTimeMillis();
			putTreeData[ndx] = endTime-startTime;
			
			//time for get method of SimpleHashMap
			in = new Scanner(inFile);
			startTime = System.currentTimeMillis();
			while(in.hasNext()){
				hashMap.get(in.nextInt());
				in.next();
			}
			endTime = System.currentTimeMillis();
			getHashData[ndx] = endTime-startTime;
	
			//time for get method of SimpleTreeMap
			in = new Scanner(inFile);
			startTime = System.currentTimeMillis();
			while(in.hasNext()){
				treeMap.get(in.nextInt());
				in.next();
			}
			endTime = System.currentTimeMillis();
			getTreeData[ndx] = endTime-startTime;
			
			//time for floorKey method of SimpleHashMap
			in = new Scanner(inFile);
			startTime = System.currentTimeMillis();
			while(in.hasNext()){
				hashMap.floorKey(in.nextInt());
				in.next();
			}
			endTime = System.currentTimeMillis();
			floorKeyHashData[ndx] = endTime-startTime;

			//time for floorKey method of SimpleTreeMap
			in = new Scanner(inFile);
			startTime = System.currentTimeMillis();
			while(in.hasNext()){
				treeMap.floorKey(in.nextInt());
				in.next();
			}
			endTime = System.currentTimeMillis();
			floorKeyTreeData[ndx] = endTime-startTime;
			
			//time for remove method of SimpleHashMap
			in = new Scanner(inFile);
			startTime = System.currentTimeMillis();
			while(in.hasNext()){
				hashMap.floorKey(in.nextInt());
				in.next();
			}
			endTime = System.currentTimeMillis();
			removeHashData[ndx] = endTime-startTime;
			
			//time for remove method of SimpleTreeMap
			in = new Scanner(inFile);
			startTime = System.currentTimeMillis();
			while(in.hasNext()){
				treeMap.floorKey(in.nextInt());
				in.next();
			}
			endTime = System.currentTimeMillis();
			removeTreeData[ndx] = endTime-startTime;
		}
       
        System.out.println("Results from "+args[1]+" runs of "+args[0]+"\n");
		System.out.println("HashMap: get\n--------------------");
		printInfo(getHashData);
		System.out.println("HashMap: put\n--------------------");
		printInfo(putHashData);
		System.out.println("HashMap: floorKey\n--------------------");
		printInfo(floorKeyHashData);
		System.out.println("HashMap: remove\n--------------------");
		printInfo(removeHashData);
		System.out.println("TreeMap: get\n--------------------");
		printInfo(getTreeData);
		System.out.println("TreeMap: put\n--------------------");
		printInfo(putTreeData);
		System.out.println("TreeMap: floorKey\n--------------------");
		printInfo(floorKeyTreeData);
		System.out.println("TreeMap: remove\n--------------------");
		printInfo(removeTreeData);		
		in.close();
    }
}