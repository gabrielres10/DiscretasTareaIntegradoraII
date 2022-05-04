package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import generics.AVLTree;
import generics.NodeAVL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
	 * This method runs the necessary methods in order to create the data base of
	 * the system
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
	 * This method returns the String of the node (Person) which contains the name
	 * entered as a parameter
	 * 
	 * @param fullName, String, this is the full name to find
	 * @return String, this the full name of the searched node
	 */
	public String searchPerson(String fullName) {

		return search(personsDataBase.getRoot(), fullName).toString();

	}

	/**
	 * This method runs the function that gets the next coincidences of a person
	 * from a searched node
	 * 
	 * @param node, this is the node from which the method gets the next
	 *              coincidences
	 * @return(Person) ObservableList, this is the ObservableList of persons
	 */
	public ObservableList<Person> getNextCoincidences(NodeAVL<Person> node, String toSearch) {
		ObservableList<Person> coincidencesList = FXCollections.observableArrayList();
		return getNextCoincidences(coincidencesList, node, toSearch);
	}

	/**
	 * This method gets the next persons of the AVL tree from a specific node as an
	 * ObservableList of persons
	 * 
	 * @param node, this is the node that is parent of the twenty persons
	 * @return (Person) ObservableList, this is the ObservableList of persons
	 */
	public ObservableList<Person> getNextCoincidences(ObservableList<Person> output, NodeAVL<Person> node,
			String toSearch) {
		if (node != null) {

			NodeAVL<Person> foundNode = search(node, toSearch);
			if (foundNode != null) {
				output.add(foundNode.getValue());
			}
			if (output.size() > 15) {
				System.out.println(" llego ");
				return output;
			}
			getNextCoincidences(output, node.getLeftChild(), toSearch);
			getNextCoincidences(output, node.getRightChild(), toSearch);
		}
		return output;

	}

	/**
	 * This method runs the method to show all the persons registered in the AVL
	 * tree
	 */
	public void showAllNames() {
		NodeAVL<Person> root = personsDataBase.getRoot();
		showAllNames(root);
	}

	/**
	 * This method shows all the names of the persons registered in the AVL tree on
	 * a preOrden way
	 * 
	 * @param root, this is the root of the AVL tree of persons
	 */
	public void showAllNames(NodeAVL<Person> root) {
		if (root != null) {
			System.out.println(root.getValue().getFullName() + " ");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			showAllNames(root.getLeftChild());
			showAllNames(root.getRightChild());
		}
	}

	/**
	 * This method searches a node with a given value
	 * 
	 * @param value,   T, this is the value to be found
	 * @param current, (T) NodeAVL this is the node of the iteration, in the first
	 *                 time it will always be the root of the node
	 * @return (T) NodeAVL, this is the node that contains the searched value
	 */
	private NodeAVL<Person> search(NodeAVL<Person> current, String toSearch) {
		// Stop conditions of the search method
		int srchLength = toSearch.length();
		//System.out.println(toSearch + " tiene " + srchLength + " letras");
		//System.out.println(current.getValue().getFullName() + " tiene " + current.getValue().getFullName().length() + " letras");
		System.out.println(current.getValue().getFullName() + " es igual " + toSearch + "?");
		if (personsDataBase.getRoot() == null || current == null) {
			System.out.println(" current es null ");
			return null;
		} else if (srchLength > current.getValue().getFullName().length()) {
			System.out.println(" el largo de la busqueda es mayor que el largo del nombre de current ");
			return null;
		}

		String nodeName = current.getValue().getFullName().toUpperCase();
		String coincidence = nodeName.substring(0, srchLength);
		if (coincidence.equals(toSearch)) {
			
			System.out.println(coincidence + " es igual " + toSearch);
			return current;
			// Iteration of the search method
		} else if ((coincidence).compareTo(toSearch) < 0) {
			// if the value of the current node is smaller than the value that is being
			// searched
			System.out.println(coincidence + " es mayor que " + toSearch);
			return search(current.getRightChild(), toSearch);
		} else {
			System.out.println(coincidence + " es menor que " + toSearch);
			// the value of the current node is bigger
			return search(current.getLeftChild(), toSearch);
		}

	}

	/**
	 * This method generates the persons database from the local data base, which
	 * contains both the names and the last names
	 * 
	 * @param names,     (String) ArrayList, these are the names contained in the
	 *                   names local dataset
	 * @param lastnames, (String) ArrayList, these are the last names contained in
	 *                   the last names local dataset
	 * @param popProp,   int[], this is the array that contains the intervals
	 *                   according to the actual proportion of the Americas
	 *                   Continent
	 * @throws Exception, this is the exception thrown by the system when an error
	 *                    occurs
	 */
	private void generateDataBase(ArrayList<String> names, ArrayList<String> lastnames, int[] popProp)
			throws Exception {
		// TODO Auto-generated method stub
		personsDataBase = new AVLTree<>();
		int namesLength = names.size();
		int lastnamesLength = lastnames.size();
		System.out.println("tama�o de nombres: " + names.size());
		System.out.println("tama�o de apellidos: " + lastnames.size());
		int x = 0;
		BufferedWriter writer = new BufferedWriter(new FileWriter(personDataPath));
		for (int i = 0; i < namesLength; i++) {
			for (int j = 0; j < lastnamesLength; j++) {
				Person current = new Person(names.get((int) Math.random() * namesLength + i).split(",")[0],
						lastnames.get(j + (int) Math.random() * lastnamesLength),
						names.get(i + (int) Math.random() * namesLength).split(",")[1], popProp, x);
				// System.out.println(current.toString());
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
	 * This is the method that reads the actual proportion of the Americas Continent
	 * in a dataset
	 * 
	 * @return int [], this is the vector of the Americas Continent population
	 *         proportion
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
	 * This method reads the names from a dataset, and saves the information into an
	 * ArrayList
	 * 
	 * @return (String) ArrayList, this is the array with all the names in the
	 *         dataset
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
	 * This method the last names from a dataset, and saves the information into an
	 * ArrayList
	 * 
	 * @return (String) ArrayList, this is the array with all the last names in the
	 *         dataset
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