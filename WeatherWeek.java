import java.io.*;
import java.util.*;
import java.text.DecimalFormat;  



public class WeatherWeek {
	
	private static final DecimalFormat decfor = new DecimalFormat("0.00");//decimal format method to max. 2 decimals
	
	private static final DecimalFormat notDec = new DecimalFormat("0");//decimal format method to max. 2 decimals

	public static void main(String[] args){
		
		double [][] data = readFile("input.txt", 0 );//save archive data in double array with archive name parameter and 0
		returnValues(data);//This void method return the data and print in terminal


	}

	public static double [][] readFile(String data, int row){
		double  [][] data_base = new double[12][3];//double multiple array to save inf. of input.txt
		try{
			File database = new File(data);
			Scanner reader = new Scanner(database);//read file scanner
			
			while(reader.hasNextLine()){//check if have one more line

				String numbers = reader.nextLine();//save info from line in string
				Scanner breakNumber = new Scanner(numbers);//this method break the string

				for (int col = 0;col< data_base[row].length;col++ ) {//this method use a loop to save information in a double multiple array
					data_base[row][col] = breakNumber.nextDouble();//convert string in double values
				}
				row++;//this row is a counter to not out Of array length
			}
		}catch(FileNotFoundException e){ System.out.println("File not exist"); }

		return data_base;//this return array with values
	}

	public static void returnValues(double [][] returnValues){
		int countMonths_Wind = 0;//counter
		int countMonths_Rain = 0;//counter

		String [] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};//string array to write months
		double avgWind = 0; double avgRain = 0;//this create 2 double var. to save the results
		for (int row = 0;row<returnValues.length ;row++ ) {
			avgWind+= returnValues[row][1];//this sum all values of all row 'x' and column 1
			avgRain+= returnValues[row][2];//this sum all values of all row 'x' and column 2
		}

		double avgFinalWind = (avgWind/returnValues.length);//average of value of line 50
		double avgFinalRain = (avgRain/returnValues.length);//average of value of line 51



		System.out.println("\nThe average windspeed for the year is: " + decfor.format(avgFinalWind) + "\n\nMonths that had more than the average windspeed:\nIndex 	Month Name");
		
			for (int col = 0;col<returnValues.length ;col++ ) {//this for loop read all returnValues array
				if(returnValues[col][1]>avgFinalWind){//this is conditional to see if value of the month is greater than average
					System.out.println(notDec.format(returnValues[col][0]) + " 	" + months[(col)]);//print number of month of returnValues and month of months array
					countMonths_Wind++;
				}
			}
		
		System.out.println("\n"+ countMonths_Wind + " months had more than average windspeed in that year.\n\n\nThe average rainfall for the year is: " + avgFinalRain + "\n\nMonths that had more than the average rainfall:\nIndex 	Month Name");

			for (int col = 0;col<returnValues.length ;col++ ) {//this for loop read all returnValues array
				if(returnValues[col][2]>avgFinalRain){//this is conditional to see if value of the month is greater than average
					System.out.println(notDec.format(returnValues[col][0]) + " 	" + months[(col)]);//print number of month of returnValues and month of months array
					countMonths_Rain++;
				}
			}

		System.out.println("\n" + countMonths_Rain + " months had more than average rainfall in that year.");

	}
    
}
