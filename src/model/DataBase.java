package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import generics.AVLTree;
import generics.Node;

public class DataBase {

	private AVLTree<Person> dataSortedByName;
	private AVLTree<Person> dataSortedByLastName;
	private AVLTree<Person> dataSortedByFullName;
	private AVLTree<Person> dataSortedById;
	private BufferedReader bf;
	private String personDataPath;

	public DataBase() {
		bf = null;
		dataSortedByName = new AVLTree<>(new NameComparator());
		dataSortedByLastName = new AVLTree<>(new LastNameComparator());
		dataSortedByFullName = new AVLTree<>(new FullNameComparator());
		dataSortedById = new AVLTree<>(new IdComparator());
		personDataPath = "data/personsDataBase.csv";
	}

	class IdComparator implements Comparator<Person> {

		@Override
		public int compare(Person o1, Person o2) {
			return o1.getId().compareTo(o2.getId());
		}
	}

	class FullNameComparator implements Comparator<Person> {

		@Override
		public int compare(Person o1, Person o2) {
			return o1.getFullName().compareTo(o2.getFullName());
		}

	}

	class LastNameComparator implements Comparator<Person> {

		@Override
		public int compare(Person o1, Person o2) {
			return o1.getLastName().compareTo(o2.getLastName());
		}
	}

	class NameComparator implements Comparator<Person> {

		@Override
		public int compare(Person o1, Person o2) {
			if (o1.getName().compareTo(o2.getName()) == 0) {
				return o1.getLastName().compareTo(o2.getLastName());
			} else {
				return o1.getName().compareTo(o2.getName());
			}
		}

	}

	// GETTERS AND SETTERS

	/**
	 * @return the personDataPath
	 */
	public String getPersonDataPath() {
		return personDataPath;
	}

	/**
	 * @return the dataSortedByName
	 */
	public AVLTree<Person> getDataSortedByName() {
		return dataSortedByName;
	}

	/**
	 * @param dataSortedByName the dataSortedByName to set
	 */
	public void setDataSortedByName(AVLTree<Person> dataSortedByName) {
		this.dataSortedByName = dataSortedByName;
	}

	/**
	 * @return the dataSortedByLastName
	 */
	public AVLTree<Person> getDataSortedByLastName() {
		return dataSortedByLastName;
	}

	/**
	 * @param dataSortedByLastName the dataSortedByLastName to set
	 */
	public void setDataSortedByLastName(AVLTree<Person> dataSortedByLastName) {
		this.dataSortedByLastName = dataSortedByLastName;
	}

	/**
	 * @return the dataSortedByFullName
	 */
	public AVLTree<Person> getDataSortedByFullName() {
		return dataSortedByFullName;
	}

	/**
	 * @param dataSortedByFullName the dataSortedByFullName to set
	 */
	public void setDataSortedByFullName(AVLTree<Person> dataSortedByFullName) {
		this.dataSortedByFullName = dataSortedByFullName;
	}

	/**
	 * @return the dataSortedById
	 */
	public AVLTree<Person> getDataSortedById() {
		return dataSortedById;
	}

	/**
	 * @param dataSortedById the dataSortedById to set
	 */
	public void setDataSortedById(AVLTree<Person> dataSortedById) {
		this.dataSortedById = dataSortedById;
	}

	/**
	 * @return the bf
	 */
	public BufferedReader getBf() {
		return bf;
	}

	/**
	 * @param bf the bf to set
	 */
	public void setBf(BufferedReader bf) {
		this.bf = bf;
	}

	/**
	 * @param personDataPath the personDataPath to set
	 */
	public void setPersonDataPath(String personDataPath) {
		this.personDataPath = personDataPath;
	}

	// ---------- end of Getters and Setters

	/**
	 * This method runs the necessary methods in order to create the data base of
	 * the system
	 */
	public void generateDataBase(int limit) {
		ArrayList<String> names = readNames();
		ArrayList<String> lastNames = readLastNames();
		double[] popProp = readPopProp();
		try {
			initData(names, lastNames, popProp, limit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("altura por nombre: " + dataSortedByName.height(dataSortedByName.getRoot()));
		System.out.println("altura por apellido: " + dataSortedByLastName.height(dataSortedByLastName.getRoot()));
		System.out.println("altura por nombre comp: " + dataSortedByFullName.height(dataSortedByFullName.getRoot()));
		System.out.println("altura por codigo: " + dataSortedById.height(dataSortedById.getRoot()));
	}

	/**
	 * This method returns the node which contains the name entered as a parameter
	 * 
	 * @param fullName, String, this is the full name to find
	 * @return (Person) Node, this is the node that contains the full name
	 */
	public Node<Person> searchBy(String value, int option) {
		switch (option) {
		case 0:
			return searchBy(dataSortedByName.getRoot(), value, option);
		case 1:
			return searchBy(dataSortedByLastName.getRoot(), value, option);
		case 2:
			return searchBy(dataSortedByFullName.getRoot(), value, option);
		case 3:
			return searchBy(dataSortedById.getRoot(), value, option);
		default:
			return null;
		}
	}

	/**
	 * This method searches a node with a given value
	 * 
	 * @param value,   T, this is the value to be found
	 * @param current, (T) Node this is the node of the iteration, in the first time
	 *                 it will always be the root of the node
	 * @return (T) Node, this is the node that contains the searched value
	 */
	public Node<Person> searchBy(Node<Person> root, String value, int option) {
		// Values are equivalent. Return the iterated node
		if (root == null || compareValue(root, value, option) == 0) // 0
			return root;

		// Value is greater than root's value. Search in the right child
		if (compareValue(root, value, option) < 0) // <0
			return searchBy(root.getRight(), value, option);

		// Value is smaller than root's value. Search in the left child
		return searchBy(root.getLeft(), value, option);
	}

	private Integer compareValue(Node<Person> node, String value, int option) {
		switch (option) {
		case 0:
			return node.getValue().getName().toUpperCase().compareTo(value.toUpperCase());
		case 1:
			return node.getValue().getLastName().toUpperCase().compareTo(value.toUpperCase());
		case 2:
			return node.getValue().getFullName().toUpperCase().compareTo(value.toUpperCase());
		case 3:
			return node.getValue().getId().toUpperCase().compareTo(value.toUpperCase());
		default:
			return null;
		}
	}

	/**
	 * This method returns the node which contains the name entered as a parameter
	 * 
	 * @param fullName, String, this is the full name to find
	 * @return (Person) Node, this is the node that contains the full name
	 */
	public Node<Person> searchByPiece(String value, int option) {
		if(value.equals("")) {
			return null;
		}
		switch (option) {
		case 0:
			return searchByPiece(dataSortedByName.getRoot(), value, option);
		case 1:
			return searchByPiece(dataSortedByLastName.getRoot(), value, option);
		case 2:
			return searchByPiece(dataSortedByFullName.getRoot(), value, option);
		case 3:
			return searchByPiece(dataSortedById.getRoot(), value, option);
		default:
			return null;
		}
	}

	/**
	 * This method searches a node with a given value
	 * 
	 * @param value,   T, this is the value to be found
	 * @param current, (T) Node this is the node of the iteration, in the first time
	 *                 it will always be the root of the node
	 * @return (T) Node, this is the node that contains the searched value
	 */
	public Node<Person> searchByPiece(Node<Person> root, String value, int option) {
		// System.out.println("searcByPiece");
		// Values are equivalent. Return the iterated node
		if (root == null) { // 0
			return root;
		} else if (comparePieceOfValue(root, value, option) == 0) {
			return root;
		}
		// Value is greater than root's value. Search in the right child
		if (comparePieceOfValue(root, value, option) < 0) // <0
			return searchByPiece(root.getRight(), value, option);

		// Value is smaller than root's value. Search in the left child
		return searchByPiece(root.getLeft(), value, option);
	}

	private Integer comparePieceOfValue(Node<Person> node, String value, int option) {
		switch (option) {
		case 0:
			if (value.length() <= node.getValue().getName().length()) {
				return node.getValue().getName().toUpperCase().substring(0, value.length())
						.compareTo(value.toUpperCase());
			} else if (startsWith(node, value, option)) {
				return 0;
			} else {
				return node.getValue().getName().toUpperCase().compareTo(value.toUpperCase());
			}
		case 1:
			if (value.length() <= node.getValue().getLastName().length()) {
				return node.getValue().getLastName().toUpperCase().substring(0, value.length())
						.compareTo(value.toUpperCase());
			} else if (startsWith(node, value, option)) {
				return 0;
			} else {
				return node.getValue().getLastName().toUpperCase().compareTo(value.toUpperCase());
			}

		case 2:
			if (value.length() <= node.getValue().getFullName().length()) {
				return node.getValue().getFullName().toUpperCase().substring(0, value.length())
						.compareTo(value.toUpperCase());
			} else if (startsWith(node, value, option)) {
				return 0;
			} else {
				return node.getValue().getFullName().toUpperCase().compareTo(value.toUpperCase());
			}

		case 3:
			if (value.length() <= node.getValue().getId().length()) {
				return node.getValue().getId().toUpperCase().substring(0, value.length())
						.compareTo(value.toUpperCase());
			} else if (startsWith(node, value, option)) {
				return 0;
			} else {
				return node.getValue().getId().toUpperCase().compareTo(value.toUpperCase());
			}
		default:
			return null;
		}
	}

	private boolean startsWith(Node<Person> node, String value, int option) {
		switch (option) {
		case 0:
			return node.getValue().getName().toUpperCase().startsWith(value.toUpperCase());
		case 1:
			return node.getValue().getLastName().toUpperCase().startsWith(value.toUpperCase());
		case 2:
			return node.getValue().getFullName().toUpperCase().startsWith(value.toUpperCase());
		case 3:
			return node.getValue().getId().toUpperCase().startsWith(value.toUpperCase());
		default:
			return false;
		}
	}

	/**
	 * This method runs the function that gets the next coincidences of a person
	 * from a searched node
	 * 
	 * @param node, this is the node from which the method gets the next
	 *              coincidences
	 * @return(Person) ObservableList, this is the ObservableList of persons
	 */
	public ArrayList<Person> getNextCoincidences(Node<Person> node, String toSearch, int type) {
		ArrayList<Person> coincidencesList = new ArrayList<>();
		coincidencesList = getNextCoincidences(coincidencesList, node, toSearch, type);
		return coincidencesList;
	}

	/**
	 * This method gets the next persons of the AVL tree from a specific node as an
	 * ObservableList of persons
	 * 
	 * @param node, this is the node that is parent of the twenty persons
	 * @return (Person) ObservableList, this is the ObservableList of persons
	 */
	public ArrayList<Person> getNextCoincidences(ArrayList<Person> output, Node<Person> node, String toSearch,
			int type) {
		if (node != null && output.size() < 101) {
			Node<Person> foundNode = null;
			foundNode = searchByPiece(node, toSearch, type);
			getNextCoincidences(output, node.getLeft(), toSearch, type);
			if (foundNode != null) {
				if(!output.contains(foundNode.getValue())) {
					output.add(foundNode.getValue());
				}
			}
			getNextCoincidences(output, node.getRight(), toSearch, type);
		}
		return output;

	}

	/**
	 * This method runs the method to show all the persons registered in the AVL
	 * tree
	 */
	public void showAllNames() {
		Node<Person> root = dataSortedByName.getRoot();
		showAllNames(root);
	}

	/**
	 * This method shows all the names of the persons registered in the AVL tree on
	 * a preOrden way
	 * 
	 * @param root, this is the root of the AVL tree of persons
	 */
	public void showAllNames(Node<Person> root) {
		if (root != null) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			showAllNames(root.getLeft());
			System.out.println(root.getValue().getFullName() + " ");
			showAllNames(root.getRight());
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
	 * @param popProp,   double[], this is the array that contains the intervals
	 *                   according to the actual proportion of the Americas
	 *                   Continent
	 * @throws Exception, this is the exception thrown by the system when an error
	 *                    occurs
	 */
	private void initData(ArrayList<String> names, ArrayList<String> lastnames, double[] popProp, int limit)
			throws Exception {
		// TODO Auto-generated method stub
		int namesLength = names.size();
		int lastnamesLength = lastnames.size();
		int x = 0;
		BufferedWriter writer = new BufferedWriter(new FileWriter(personDataPath));
		for (int i = 0; i < namesLength; i++) {
			for (int j = 0; j < lastnamesLength; j++) {
				if (x == limit || limit == 0) {
					i = namesLength;
					break;
				}
				
				Person current = new Person(x + "", names.get(i).split(",")[0], lastnames.get(j),
						names.get(i).split(",")[1], popProp, x, limit);
				dataSortedByName.insert(current);
				dataSortedByLastName.insert(current);
				dataSortedByFullName.insert(current);
				dataSortedById.insert(current);
				x++;
			}
			System.out.println(x);
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
	private double[] readPopProp() {
		// TODO Auto-generated method stub
		double[] popProp = new double[55];

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
