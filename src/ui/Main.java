package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import model.AVLTree;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader bf = null;
		createFullNames(readNames(bf), readLastNames(bf));
	}

	private static void createFullNames(ArrayList<String> names, ArrayList<String> lastnames) {
		// TODO Auto-generated method stub
		AVLTree<String> fullNames = new AVLTree<>();
		int namesLength = names.size();
		int lastnamesLength = lastnames.size(); 
		int x = 0;
		String a = ".";
		for(int i = 0; i<namesLength; i++) {
			for(int j = 0; j<lastnamesLength; j++) {
				fullNames.insert(names.get(i) + "," + lastnames.get(j));
				x++;
			}
			System.out.println(x);
		}
		System.out.println(x);
		//System.out.println(fullNames.size());
	}

	private static ArrayList<String> readLastNames(BufferedReader bf) {
		// TODO Auto-generated method stub
		ArrayList<String> lastnamesDB = new ArrayList<>();

		// Reads the information from a CSV file
		try {
			// Open .csv in buffer's reading mode
			bf = new BufferedReader(new FileReader("data/lastnames.csv"));

			// Read a file line
			String currentLine = bf.readLine();

			// if the line is not empty we keep reading the file
			int i = 0;
			while (currentLine != null) {
				if(i!=0 && i!=162254) {
					String [] recordSplit = currentLine.split(",");
					lastnamesDB.add(recordSplit[0]);
				}
				// Read the next file line
				currentLine = bf.readLine();
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close the buffer reader
			if (bf != null) {
				try {
					bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return lastnamesDB;
	}

	public static ArrayList<String> readNames(BufferedReader bf) {
		ArrayList<String> namesDB = new ArrayList<>();

		// Reads the information from a CSV file
		try {
			// Open .csv in buffer's reading mode
			bf = new BufferedReader(new FileReader("data/names.csv"));

			// Read a file line
			String currentLine = bf.readLine();

			// if the line is not empty we keep reading the file
			while (currentLine != null) {
				
				String [] recordSplit = currentLine.split(",");
				namesDB.add(recordSplit[0]);
				// Read the next file line
				currentLine = bf.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close the buffer reader
			if (bf != null) {
				try {
					bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return namesDB;
	}

}
