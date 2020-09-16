package Timefall.edu.usc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WristCuff {	
	/**
	 * Loads shelters specified in path (JSON file)
	 */
	
	public Shelter[] shelters;
	public FastList timefall;
	public String fileString;
	
	public WristCuff(String path) throws IOException {
		Scanner scanner = new Scanner(System.in);
		Gson gs = new Gson();
		String jsonIn = null;
		fileString = path;
		boolean h = false;
		while(!h) {
			h= true;
		try 
		{
		jsonIn = new String(Files.readAllBytes(Paths.get(path)));
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("Could not open file.");
			h = false;
		}
		catch (InputMismatchException e)
		{
			System.out.println("Wrong input.");
			h = false;
		}
		catch(Exception e)
		{
			System.out.println("Wrong input. Enter again");
			path = scanner.next();
			h = false;
		}
		}
		
		System.out.println("== DATA ACCEPTED ==");
		shelters = gs.fromJson(jsonIn, Shelter[].class);
	}
	
	/**
	 * Finds an available shelter that matches one of the provided Chiral Frequencies
	 */
	public Shelter findShelter(List<Integer> chiralFrequencies) {
		
		timefall = new FastList();
		for(int i=0;i<shelters.length; i++) timefall.insert(shelters[i]);
		System.out.println("=== Commencing timefall shelter search ===");
		for (int i = 0; i < chiralFrequencies.size(); i++)
		{
		if (timefall.contains(chiralFrequencies.get(i)) != null)
		{
		Shelter temp = (Shelter) timefall.contains(chiralFrequencies.get(i));
		System.out.println();
		System.out.println("=== Matching timefall shelter found! ===");
		System.out.println(temp.toString());
		
		if (temp.getTimefall() == false)
		{
		System.out.println("=== Commencing chiral jump, see you on the beach. ===");
		return temp;
		}
		else
		{
		System.out.println("=== Target shelter Chiral signal unstable, Chiral jump unavailable. ===");
		System.out.println("=== Removing target shelter from the list of shelters and saving updated list. ===");
		timefall.delete(temp);
		}
		}
		}
		System.out.println("=== No shelter available. You are DOOMED. ===");
		return null;
	}
	
		
	/**
	 * Saves the updated list of shelters to disk
	 * @throws IOException 
	 */
	public void save() throws IOException {
		
		List<Shelter> c = timefall.get();
		StringBuffer stringBuf = new StringBuffer();
		if (c.size() != 0)
		{
		stringBuf.append("[");
		stringBuf.append(System.lineSeparator());
		}
		for (int i = 0; i <c.size(); i++)
		{
		if (c.get(i) != null)
		{
		stringBuf.append(c.get(i).toStringPrune());

		if (c.size() - 1 != i) {
		stringBuf.append(",");
		}
		stringBuf.append(System.lineSeparator());
		}
		}
		if (c.size() != 0)
		{
		stringBuf.append("]");
		}
		String out = stringBuf.toString();
		File currFile = new File(fileString);
		FileWriter fileWrite = new FileWriter(currFile);
		try {
			fileWrite.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileWrite.close();
		
		
	}
}
