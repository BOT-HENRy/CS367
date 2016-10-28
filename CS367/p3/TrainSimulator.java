///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Train
// Files:            TrainSimulator.java
// Semester:         CS367 Fall 2014
//
// Author:           Yizhe Qu
// Email:            qu23@wisc.edu
// CS Login:         qu
// Lecturer's Name:  Jim Skrentny
// 
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Xuxiang Wu
// Email:            xwu247@wisc.edu
// CS Login:         xuxiang
// Lecturer's Name:  Jim Skrentny
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          none
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TrainSimulator {

	public static void main(String args[]){
		
		Scanner stdin = new Scanner(System.in);	
		
		String operation = args[0];
		String Stations_File_Name = args[1];
		String Trains_File_Name = args[2];

		Station[] stations = null;
		Train[] trains = null;
		int numS = 0; //to store number of stations
		int numT = 0; //to store number of trains
		
		//Import station files and create stations
		File srcFile = new File(Stations_File_Name);
		try {
			Scanner fileIn = new Scanner(srcFile);
			String currline = fileIn.nextLine();
			numS = Integer.parseInt(currline);
			stations =  new Station[numS];
			
			for (int i = 0; i < numS; i ++)
			{
				currline = fileIn.nextLine();
				int lineLength = currline.length();
				int id = 0;
				int capacity = 0;
				for(int k = 0; k < lineLength; k ++)
				{
					if(currline.charAt(k) == ',')
					{
						id = Integer.parseInt(currline.substring(0, k));
						capacity = Integer.parseInt
								(currline.substring(k + 1, lineLength));
						stations[i] = new Station(id, capacity);
						continue;
					}
				}				
			}
		} catch (FileNotFoundException e) {
			
		}	
			
		//Import train files and create trains
		srcFile = new File(Trains_File_Name);
		try {
			Scanner fileIn = new Scanner(srcFile);
			String currline = fileIn.nextLine();
			numT = Integer.parseInt(currline);
			trains =  new Train[numT];
			
			for (int i = 0; i < numT; i ++)
			{
				currline = fileIn.nextLine();
				int lineLength = currline.length();
				int index = 0;
				int id = 0;
				for(int k = 0; k < lineLength; k ++)
				{
					if(currline.charAt(k) == ',' && index ==0)
					{
						id = Integer.parseInt(currline.substring(0, k));
						trains[i] = new Train(id);						
						index = k + 1;
						continue; 
					}
					if(currline.charAt(k) == ',' && index != 0)
					{
						int ETD = Integer.parseInt
								(currline.substring(index, k));
						trains[i].getETD().add(ETD);
						index = k + 1;
						continue;
					}
					if(k == lineLength - 1 && index != 0)
					{
						int ETD = Integer.parseInt
								(currline.substring(index, k + 1));
						trains[i].getETD().add(ETD);
						continue;
					}
				}				
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not Found!");
		}
		
		//put trains into the first station
		for(int i = trains.length - 1; i >= 0; i --)
		{
			try {
				stations[0].getPlatform().put(trains[i]);
			} catch (FullPlatformException e) {
				
			}
		}
		
		//initialize time, trains on the road, events strings 
		int time = 0;
		//store train on the road
		SimpleQueue<Train> trainMid = new SimpleQueue<Train>(numT);
		//store events happened at stations
		String eventS[][] = new String[numS][numT * 2]; 
		for(int i = 0; i < numS; i ++)
		{
			for(int j = 0; j < numT * 2; j ++)
			{
				eventS[i][j] = "0";
			}
		}
		
		//TrainSimulator! Check the status of each station and train 
		//and then implementing by correct order
		while(!stations[numS-1].getPlatform().isFull())
		{
			
			for(int i = numS - 1; i > 0; i--)
			{
				//regular departure 
				if(!stations[i].getPlatform().isFull() && 
						!stations[i - 1].getPlatform().isEmpty())
				{
					//prevent trains leave earlier than its ATA
					if(stations[i - 1].getPlatform().check().getATA().size() >= 1)
					{
						if(stations[i - 1].getPlatform().check().getATA().get(i - 2) == time)
						{
							continue;
						}
						//train leave station
						while(!stations[i - 1].getPlatform().isEmpty() && 
								time >= stations[i - 1].getPlatform().check().getETD().get(i - 1) && 
								stations[i - 1].getPlatform().check().getATA().get(i-2) <= time)
						{
							stations[i - 1].getPlatform().check().getATD().add(time);
							for(int z = 0; z < numT * 2; z ++)
							{
								//storing leaving information
								if(eventS[i-1][z].equals("0"))
								{
									eventS[i-1][z]=time + ":	Train " + 
								stations[i - 1].getPlatform().check().getId() 
								+ " has exited from station " + (i) + ".";
									break;
								}
							}
							//Calculate the arrive time and put the train into the next station
							try {	
								stations[i].getPlatform().put(stations[i - 1].getPlatform().get());
								stations[i].getPlatform().check().getATA().add(time + 10);
								for(int z = 0; z < numT * 2; z ++)
								{
									//storing arriving information
									if(eventS[i][z].equals("0"))
									{
										eventS[i][z]=(time+10) + ":	Train " + 
									stations[i].getPlatform().check().getId() 
									+ " has been parked at station " + (i + 1) + ".";
										break;
									}
								}
								
							} catch (FullPlatformException e) {
								
							}
						}
					}else
					{
						while(!stations[i - 1].getPlatform().isEmpty() && 
								time >= stations[i - 1].getPlatform().check().getETD().get(i - 1))
						{
							stations[i - 1].getPlatform().check().getATD().add(time);
							//storing leaving information
							for(int z = 0; z < numT * 2; z++)
							{
								if(eventS[i-1][z].equals("0"))
								{
									eventS[i-1][z]=time + ":	Train " + 
								stations[i - 1].getPlatform().check().getId() 
								+ " has exited from station " + (i) + ".";
									break;
								}
							}
							//Calculate the arrive time and put the train into the next station
							try {
								stations[i].getPlatform().put(stations[i - 1].getPlatform().get());
								stations[i].getPlatform().check().getATA().add(time + 10);
								//storing arriving information
								for(int z = 0; z < numT * 2; z ++)
								{
									if(eventS[i][z].equals("0"))
									{
										eventS[i][z]=(time+10) + ":	Train " + 
									stations[i].getPlatform().check().getId() 
									+ " has been parked at station " + (i + 1) + ".";
										break;
									}
								}
							} catch (FullPlatformException e) {
								
							}
						}
					}
							
				}
				//Simulate trains which leaves the current station and can not enter the next station
				while(!stations[i - 1].getPlatform().isEmpty() && 
						stations[i].getPlatform().isFull () && 
						stations[i - 1].getPlatform().check().getETD().get(i-1) <= time )
				{
					stations[i - 1].getPlatform().check().getATD().add(time);
					//storing leaving information
					for(int z = 0; z < numT * 2; z ++)
					{
						if(eventS[i-1][z].equals("0"))
						{
							eventS[i-1][z]=time + ":	Train " + 
						stations[i - 1].getPlatform().check().getId() 
						+ " has exited from station " + (i) + ".";
							break;
						}
					}
					//put trains on the road into the a Queue
					try {
						trainMid.enqueue(stations[i - 1].getPlatform().get());
					} catch (FullQueueException e) {
						
					}
				}
				
				//Simulate when trains wait on the road can enter the station
				try {
					if(!trainMid.isEmpty() && i == trainMid.peek().getATD().size() 
							&& !stations[i].getPlatform().isFull())
					{
						while(!stations[i].getPlatform().isFull() && 
								!trainMid.isEmpty() && 
								i == trainMid.peek().getATD().size())
						{
							
							try {
								trainMid.peek().getATA().add(time);
								stations[i].getPlatform().put(trainMid.dequeue());
								//storing arriving information
								for(int z = 0; z < numT * 2; z ++)
								{
									if(eventS[i][z].equals("0"))
									{
										eventS[i][z]=time + ":	Train " + 
									stations[i].getPlatform().check().getId() 
									+ " has been parked at station " + (i + 1) + ".";
										break;
									}
								}
								
							} catch (FullPlatformException e) {
								
							} catch (EmptyQueueException e) {
								
							}
							
						}
					
					}
				} catch (EmptyQueueException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//exit when all the trains arrive final station
				if(stations[numS-1].getPlatform().isFull())
				{
					break;
				}
			
			}
			time ++;
			
		}
		
		//create new train list
		Train[] train = new Train[numT];
		for(int i = 0; i < numT; i ++)
		{
			int pos = stations[numS - 1].getPlatform().check().getId();
			train[pos - 1] = stations[numS - 1].getPlatform().get();
		}
		
		//function 0 
		if(operation.equals("0"))
		{
			for(int i = 0; i < eventS.length; i++)
			{
				for(int j = 0; j < eventS[i].length; j ++)
				{
					if(!eventS[i][j].equals("0")){
						System.out.println(eventS[i][j]);
					}
					
				}
			
			}
		}
			
		//function 1 
		else if(operation.equals("1"))
		{
			for(int j = 0; j < numT; j ++)
			{
				System.out.print("[");
				
				for(int k = 0; k < train[j].getATD().size() - 1; k ++)
				{
					System.out.print(train[j].getATD().get(k) + ", " );
				}
				System.out.print(train[j].getATD().get(train[j].getATD().size() - 1) + "]" );
				System.out.println("");
			}
		}
				
		//function 2
		else if(operation.equals("2"))
		{
			for(int j = 0; j < numT; j ++)
			{
				System.out.print("[");
				
				for(int k = 0; k < train[j].getATA().size() - 1; k ++)
				{
					System.out.print(train[j].getATA().get(k) + ", " );
				}
				System.out.print(train[j].getATA().get(train[j].getATA().size() - 1) + "]" );
				System.out.println("");
			}
		}	
		
		else
		{
			System.out.println("Invalid Command");
		}
	}
}
