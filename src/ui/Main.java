package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.AVLTree;
import model.Person;
import model.RedBlackTree;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader bf = null;
		/*RedBlackTree<Integer> arbolito = new RedBlackTree<>();
		arbolito.insert(10);
		arbolito.insert(5);
		arbolito.insert(13);
		arbolito.insert(1);
		arbolito.insert(6);
		arbolito.insert(17);
		arbolito.insert(16);
		System.out.print("PREORDEN: ");
		arbolito.preOrden(arbolito.getRoot());
		//System.out.println("Altura: " + arbolito.height());
		*/
		
		createFullNames(readNames(bf), readLastNames(bf), bf);
	}

	private static void createFullNames(ArrayList<String> names, ArrayList<String> lastnames, BufferedReader bf) {
		// TODO Auto-generated method stub
		RedBlackTree<Person> fullNames = new RedBlackTree<>();
		int namesLength = names.size();
		int lastnamesLength = lastnames.size(); 
		int x = 0;
		int population = namesLength * lastnamesLength;
		double[] popProp = readPopProp(bf);
		String a = ".";
		for(int i = 0; i<namesLength; i++) {
			for(int j = 0; j<lastnamesLength; j++) {
				fullNames.insert(new Person(names.get(i).split(",")[0], lastnames.get(j), names.get(i).split(",")[1], population, popProp, x));
				x++;
			}
			System.out.println(x);
		}
		
		//System.out.println("height: "+ fullNames.height());
	}

	
	private static double[] readPopProp(BufferedReader bf) {
		// TODO Auto-generated method stub
				double[] popProp = new double[55];

				// Reads the information from a CSV file
				try {
					// Open .csv in buffer's reading mode
					bf = new BufferedReader(new FileReader("data/pobProp.csv"));

					// Read a file line
					String currentLine = bf.readLine();

					// if the line is not empty we keep reading the file
					int i = 0;
					while (currentLine != null) {
						popProp[i] = Double.parseDouble(currentLine);
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
				return popProp;
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
				namesDB.add(currentLine);
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
