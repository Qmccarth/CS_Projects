package Timefall.edu.usc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {
	/**
	 * Use user-input to create a WristCuff object.
	 */
	public static WristCuff getWristCuff() throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.print("Please provide timefall shelter data source: ");
		String path = scan.next();
		WristCuff wc = new WristCuff(path);
		return wc;
	 }
	
	/**
	 * Use user-input to create a list of supported Chiral Frequencies
	 */
	public static List<Integer> getChiralFrequencies() throws IOException {		
		List<Integer> freq = new ArrayList<Integer>();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please provide supported chiral frequencies: ");
			String fString = scanner.nextLine();
			String f[] = fString.split(", ");
			for(int i =0; i< f.length;i++) {
			Integer sup = Integer.parseInt(f[i]);
			try {
				freq.add(sup);
			}
			catch(InputMismatchException e) {
				System.out.println("Not an int");
			}
			}
		return freq;
	}
	
	public static void main(String[] args)throws IOException {
		
	   	System.out.println("Welcome to Bridges Link.\n");
		 WristCuff wCuff = getWristCuff();
	     List<Integer> supportedIntegers = getChiralFrequencies();
		 wCuff.findShelter(supportedIntegers);
		 wCuff.save();
	}

}
