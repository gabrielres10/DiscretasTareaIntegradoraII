package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataBase {

	private AVLTree<Person> personsDataBase;
	private BufferedReader bf;
	private String personDataPath;
	
	public DataBase() {
		bf = null;
		personsDataBase = new AVLTree<>();
		personDataPath = "data/personsDataBase.csv";
	}

	/**
	 * @return the personsDataBase
	 */
	public AVLTree<Person> getPersonsDataBase() {
		return personsDataBase;
	}

	/**
	 * @param personsDataBase the personsDataBase to set
	 */
	public void setPersonsDataBase(AVLTree<Person> personsDataBase) {
		this.personsDataBase = personsDataBase;
	}
	
	/**
	 * This method runs the necessary methods in order to create the data base of the system
	 */
	public void generateDataBase() {
		try {
			generateDataBase(readNames(), readLastNames(), readPopProp());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method returns the node which contains the name entered as a parameter
	 * @param fullName, String, this is the full name to find
	 * @return (Person) NodeAVL, this is the node that contains the full name 
	 */
	public NodeAVL<Person> search(String fullName){
		
		return personsDataBase.search(fullName);
		
	}
	
	/**
	 * This method generates the persons database from the local data base, which
	 * contains both the names and the last names
	 * 
	 * @param names, (String) ArrayList, these are the names contained in the names local dataset
	 * @param lastnames, (String) ArrayList, these are the last names contained in the last names local dataset
	 * @param popProp, int[], this is the array that contains the intervals according to the actual proportion of the Americas Continent
	 * @throws Exception, this is the exception thrown by the system when an error occurs
	 */
	private void generateDataBase(ArrayList<String> names, ArrayList<String> lastnames, int[] popProp) throws Exception {
		// TODO Auto-generated method stub
		personsDataBase = new AVLTree<>();
		int namesLength = names.size();
		int lastnamesLength = lastnames.size();
		int x = 0;
		BufferedWriter writer = new BufferedWriter(new FileWriter(personDataPath));
		for (int i = 0; i < namesLength; i++) {
			for (int j = 0; j < lastnamesLength; j++) {
				Person current = new Person(x + "", names.get((int) Math.random() * namesLength + i).split(",")[0],
						lastnames.get(j + (int) Math.random() * lastnamesLength),
						names.get(i + (int) Math.random() * namesLength).split(",")[1], popProp, x);
				writer.write(current.toString() + "\n");
				personsDataBase.insert(current);
				x++;
				if (x == 1048576) {
					i = namesLength;
					j = lastnamesLength;
				}
			}
		}
		writer.close();
	}

	/**
	 * This is the method that reads the actual proportion of the Americas Continent in a dataset
	 * @return int [], this is the vector of the Americas Continent population proportion
	 */
	private int[] readPopProp() {
		// TODO Auto-generated method stub
		int[] popProp = new int[55];

		// Reads the information from a CSV file
		try {
			// Open .csv in buffer's reading mode
			bf = new BufferedReader(new FileReader("data/popPropPeque.csv"));

			// Read a file line
			String currentLine = bf.readLine();

			// if the line is not empty we keep reading the file
			int i = 0;
			while (currentLine != null) {
				popProp[i] = Integer.parseInt(currentLine);
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
	
	/**
	 * This method reads the names from a dataset, and saves the information into an ArrayList
	 * @return (String) ArrayList, this is the array with all the names in the dataset
	 */
	private ArrayList<String> readNames() {
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
	
	/**
	 * This method the last names from a dataset, and saves the information into an ArrayList
	 * @return (String) ArrayList, this is the array with all the last names in the dataset
	 */
	private ArrayList<String> readLastNames() {
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
				if (i != 0 && i != 162254) {
					String[] recordSplit = currentLine.split(",");
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

}
