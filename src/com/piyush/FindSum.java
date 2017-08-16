package com.piyush;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used to take the first element, common difference and number of elements as an input, calculate the AP, 
 * break the number of elements of that AP and pass them on to individual threads to calculate their sum.
 * @author Piyush Chugh
 *
 */
public class FindSum {

	public static void main(String[] args) {
		
		System.out.println("\nHello "+System.getProperty("user.name")+",\n");	
		System.out.println("\nPlease enter the number of elements in the AP (including the 1st element): ");
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		System.out.println("\nPlease enter the first element in the AP:  ");
		int first = sc.nextInt();
		System.out.println("\nPlease enter the common difference:  ");
		int difference = sc.nextInt();
		sc.close();
		
		ArrayList<Integer> arithmeticProgression = calcluateAP(count, first, difference);		
		findSum(count, arithmeticProgression);		
		printSum();
	}

	/**
	 * This method prints the total sum, as calculated by the individual threads.
	 */
	private static void printSum() {
		System.out.println("Sum by individual threads: T1-"+MyThread.sumArray.get(0)+", T2-"+MyThread.sumArray.get(1)+", T3-"+MyThread.sumArray.get(2));
		Integer totalSum = 0;
		for (Integer i : MyThread.sumArray) {
		    totalSum += i;
		}
		
		System.out.println("\nTotal Sum = "+totalSum);
	}

	/**
	 * This method is used to run 3 threads, which in turn calculate the sum of elements of the AP.
	 * I divide the total count of AP by 3, and these threads find sum on these individual counts.
	 * @param count
	 * @param arithmeticProgression
	 */
	private static void findSum(int count, ArrayList<Integer> arithmeticProgression) {
		int numbersPerThread = count/3;
		
		MyThread R1 = new MyThread(0, numbersPerThread-1, arithmeticProgression, "T1");
		R1.start();
		
		MyThread R2 = new MyThread(numbersPerThread, numbersPerThread+numbersPerThread-1, arithmeticProgression, "T2");
		R2.start();
		
		MyThread R3 = new MyThread(numbersPerThread*2, count-1, arithmeticProgression, "T3");
		R3.start();
		
		try {
			R1.join();
			R2.join();
			R3.join();
		} catch (InterruptedException e) {
			System.out.println("Caught exception: "+e.getMessage());
		}
	}

	/**
	 * This method is used to calculate the AP
	 * @param count
	 * @param first
	 * @param difference
	 * @return ArrayList<Integer> 
	 */
	private static ArrayList<Integer> calcluateAP(int count, int first, int difference) {
		ArrayList<Integer> arithmeticProgression = new ArrayList<Integer>();
		arithmeticProgression.add(first);
		for(int i = 1; i<count ; i++) {
			arithmeticProgression.add(first+(i*difference));
		}
		System.out.println("\nArithmetic Progression:"+arithmeticProgression);
		return arithmeticProgression;
	}
	
}
