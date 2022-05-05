package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import generics.AVLTree;
import generics.NodeAVL;

public class DataBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AVLTree<Person, NameComparator> dataSortedByName;
	private AVLTree<Person, LastNameComparator> dataSortedByLastName;
	private AVLTree<Person, FullNameComparator> dataSortedByFullName;
	private AVLTree<Person, IdComparator> dataSortedById;
	private String personDataPath;

	transient InputStreamReader isr = new InputStreamReader(System.in);
	transient BufferedReader bf = new BufferedReader(isr);

	public DataBase() {
		dataSortedByName = new AVLTree<>(new NameComparator());
		dataSortedByLastName = new AVLTree<>(new LastNameComparator());
		dataSortedByFullName = new AVLTree<>(new FullNameComparator());
		dataSortedById = new AVLTree<>(new IdComparator());
		personDataPath = "data/personsDataBase.csv";
	}

	class IdComparator implements Comparator<Person>, Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(Person o1, Person o2) {
			// TODO Auto-generated method stub
			return o1.getId().compareTo(o2.getId());
		}
	}

	class FullNameComparator implements Comparator<Person>, Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(Person o1, Person o2) {
			// TODO Auto-generated method stub
			return o1.getFullName().compareTo(o2.getFullName());
		}

	}

	class LastNameComparator implements Comparator<Person>, Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(Person o1, Person o2) {
			// TODO Auto-generated method stub
			return o1.getLastName().compareTo(o2.getLastName());
		}
	}

	class NameComparator implements Comparator<Person>, Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(Person o1, Person o2) {
			if (o1.getName().compareTo(o2.getName()) == 0) {
				return o1.getLastName().compareTo(o2.getLastName());
			} else {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}
		}

	}

	/**
	 * @return the dataSortedByName
	 */
	public AVLTree<Person, NameComparator> getDataSortedByName() {
		return dataSortedByName;
	}

	/**
	 * @param dataSortedByName the dataSortedByName to set
	 */
	public void setDataSortedByName(AVLTree<Person, NameComparator> dataSortedByName) {
		this.dataSortedByName = dataSortedByName;
	}

	/**
	 * @return the dataSortedByLastName
	 */
	public AVLTree<Person, LastNameComparator> getDataSortedByLastName() {
		return dataSortedByLastName;
	}

	/**
	 * @param dataSortedByLastName the dataSortedByLastName to set
	 */
	public void setDataSortedByLastName(AVLTree<Person, LastNameComparator> dataSortedByLastName) {
		this.dataSortedByLastName = dataSortedByLastName;
	}

	/**
	 * @return the dataSortedByFullName
	 */
	public AVLTree<Person, FullNameComparator> getDataSortedByFullName() {
		return dataSortedByFullName;
	}

	/**
	 * @param dataSortedByFullName the dataSortedByFullName to set
	 */
	public void setDataSortedByFullName(AVLTree<Person, FullNameComparator> dataSortedByFullName) {
		this.dataSortedByFullName = dataSortedByFullName;
	}

	/**
	 * @return the dataSortedById
	 */
	public AVLTree<Person, IdComparator> getDataSortedById() {
		return dataSortedById;
	}

	/**
	 * @param dataSortedById the dataSortedById to set
	 */
	public void setDataSortedById(AVLTree<Person, IdComparator> dataSortedById) {
		this.dataSortedById = dataSortedById;
	}

	/**
	 * @return the personDataPath
	 */
	public String getPersonDataPath() {
		return personDataPath;
	}

	/**
	 * @param personDataPath the personDataPath to set
	 */
	public void setPersonDataPath(String personDataPath) {
		this.personDataPath = personDataPath;
	}

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
		System.out.println("altura por nombre: " + dataSortedByName.getHeight(dataSortedByName.getRoot()));
		System.out.println("altura por apellido: " + dataSortedByLastName.getHeight(dataSortedByLastName.getRoot()));
		System.out.println("altura por nombre comp: " + dataSortedByFullName.getHeight(dataSortedByFullName.getRoot()));
		System.out.println("altura por codigo: " + dataSortedById.getHeight(dataSortedById.getRoot()));
	}

	/**
	 * This method returns the node which contains the name entered as a parameter
	 * 
	 * @param fullName, String, this is the full name to find
	 * @return (Person) NodeAVL, this is the node that contains the full name
	 */
	public NodeAVL<Person> searchByName(String name) {
		return searchByName(dataSortedByName.getRoot(), name);
	}

	/**
	 * This method searches a node with a given value
	 * 
	 * @param value,   T, this is the value to be found
	 * @param current, (T) NodeAVL this is the node of the iteration, in the first
	 *                 time it will always be the root of the node
	 * @return (T) NodeAVL, this is the node that contains the searched value
	 */
	public NodeAVL<Person> searchByName(NodeAVL<Person> raizTmp, String name) {
		if (dataSortedByName == null) {
			return null;
		}

		while (raizTmp.getRightChild() != null || raizTmp.getLeftChild() != null) {

			if (name.compareTo(raizTmp.getValue().getName()) > 0) {
				if (raizTmp.getRightChild() != null) {
					raizTmp = raizTmp.getRightChild();
				} else {
					return null;
				}
			} else if (name.compareTo(raizTmp.getValue().getName()) < 0) {
				if (raizTmp.getLeftChild() != null) {
					raizTmp = raizTmp.getLeftChild();
				} else {
					return null;
				}
			}

			if (name.compareTo(raizTmp.getValue().getName()) == 0) {
				return raizTmp;
			}
		}

		return raizTmp;
	}

	/**
	 * This method returns the node which contains the name entered as a parameter
	 * 
	 * @param fullName, String, this is the full name to find
	 * @return (Person) NodeAVL, this is the node that contains the full name
	 */
	public NodeAVL<Person> searchByLastName(String lastName) {
		return searchByLastName(dataSortedByLastName.getRoot(), lastName);
	}

	/**
	 * This method searches a node with a given value
	 * 
	 * @param value,   T, this is the value to be found
	 * @param current, (T) NodeAVL this is the node of the iteration, in the first
	 *                 time it will always be the root of the node
	 * @return (T) NodeAVL, this is the node that contains the searched value
	 */
	public NodeAVL<Person> searchByLastName(NodeAVL<Person> raizTmp, String name) {
		if (dataSortedByLastName == null) {
			return null;
		}

		while (raizTmp.getRightChild() != null || raizTmp.getLeftChild() != null) {

			if (name.compareTo(raizTmp.getValue().getLastName()) > 0) {
				if (raizTmp.getRightChild() != null) {
					raizTmp = raizTmp.getRightChild();
				} else {
					return null;
				}
			} else if (name.compareTo(raizTmp.getValue().getLastName()) < 0) {
				if (raizTmp.getLeftChild() != null) {
					raizTmp = raizTmp.getLeftChild();
				} else {
					return null;
				}
			}

			if (name.compareTo(raizTmp.getValue().getLastName()) == 0) {
				return raizTmp;
			}
		}

		return raizTmp;
	}

	/**
	 * This method returns the node which contains the name entered as a parameter
	 * 
	 * @param fullName, String, this is the full name to find
	 * @return (Person) NodeAVL, this is the node that contains the full name
	 */
	public NodeAVL<Person> searchByFullName(String fullName) {
		return searchByFullName(dataSortedByFullName.getRoot(), fullName);
	}

	/**
	 * This method searches a node with a given value
	 * 
	 * @param value,   T, this is the value to be found
	 * @param current, (T) NodeAVL this is the node of the iteration, in the first
	 *                 time it will always be the root of the node
	 * @return (T) NodeAVL, this is the node that contains the searched value
	 */
	public NodeAVL<Person> searchByFullName(NodeAVL<Person> raizTmp, String name) {
		if (dataSortedByFullName == null) {
			return null;
		}

		while (raizTmp.getRightChild() != null || raizTmp.getLeftChild() != null) {

			if (name.compareTo(raizTmp.getValue().getFullName()) > 0) {
				if (raizTmp.getRightChild() != null) {
					raizTmp = raizTmp.getRightChild();
				} else {
					return null;
				}
			} else if (name.compareTo(raizTmp.getValue().getFullName()) < 0) {
				if (raizTmp.getLeftChild() != null) {
					raizTmp = raizTmp.getLeftChild();
				} else {
					return null;
				}
			}

			if (name.compareTo(raizTmp.getValue().getFullName()) == 0) {
				return raizTmp;
			}
		}

		return raizTmp;
	}

	/**
	 * This method returns the node which contains the name entered as a parameter
	 * 
	 * @param fullName, String, this is the full name to find
	 * @return (Person) NodeAVL, this is the node that contains the full name
	 */
	public NodeAVL<Person> searchById(String id) {
		return searchById(dataSortedById.getRoot(), id);
	}

	/**
	 * This method searches a node with a given value
	 * 
	 * @param value,   T, this is the value to be found
	 * @param current, (T) NodeAVL this is the node of the iteration, in the first
	 *                 time it will always be the root of the node
	 * @return (T) NodeAVL, this is the node that contains the searched value
	 */
	public NodeAVL<Person> searchById(NodeAVL<Person> raizTmp, String name) {
		if (dataSortedById == null) {
			return null;
		}

		while (raizTmp.getRightChild() != null || raizTmp.getLeftChild() != null) {

			if (name.compareTo(raizTmp.getValue().getId()) > 0) {
				if (raizTmp.getRightChild() != null) {
					raizTmp = raizTmp.getRightChild();
				} else {
					return null;
				}
			} else if (name.compareTo(raizTmp.getValue().getId()) < 0) {
				if (raizTmp.getLeftChild() != null) {
					raizTmp = raizTmp.getLeftChild();
				} else {
					return null;
				}
			}

			if (name.compareTo(raizTmp.getValue().getId()) == 0) {
				return raizTmp;
			}
		}

		return raizTmp;
	}

	/**
	 * This method runs the function that gets the next coincidences of a person
	 * from a searched node
	 * 
	 * @param node, this is the node from which the method gets the next
	 *              coincidences
	 * @return(Person) ObservableList, this is the ObservableList of persons
	 */
	public ArrayList<Person> getNextCoincidences(NodeAVL<Person> node, String toSearch, int type) {
		ArrayList<Person> coincidencesList = new ArrayList<>();
		return getNextCoincidences(coincidencesList, node, toSearch, type);
	}

	/**
	 * This method gets the next persons of the AVL tree from a specific node as an
	 * ObservableList of persons
	 * 
	 * @param node, this is the node that is parent of the twenty persons
	 * @return (Person) ObservableList, this is the ObservableList of persons
	 */
	public ArrayList<Person> getNextCoincidences(ArrayList<Person> output, NodeAVL<Person> node, String toSearch,
			int type) {
		if (node != null) {
			NodeAVL<Person> foundNode = null;
			if (type == 0) {
				foundNode = searchByName(node, toSearch);
			} else if (type == 1) {
				foundNode = searchByLastName(node, toSearch);
			} else if (type == 2) {
				foundNode = searchByFullName(node, toSearch);
			} else {
				foundNode = searchById(node, toSearch);
			}

			if (foundNode != null) {
				output.add(foundNode.getValue());
			}

			getNextCoincidences(output, node.getLeftChild(), toSearch, type);
			getNextCoincidences(output, node.getRightChild(), toSearch, type);
		}

		return output;

	}

	/**
	 * This method runs the method to show all the persons registered in the AVL
	 * tree
	 */
	public void showAllNames() {
		NodeAVL<Person> root = dataSortedByName.getRoot();
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
				Person current = new Person(x + "", names.get(i).split(",")[0], lastnames.get(j),
						names.get(i).split(",")[1], popProp, x, limit);
				dataSortedByName.insert(current);
				dataSortedByLastName.insert(current);
				dataSortedByFullName.insert(current);
				dataSortedById.insert(current);
				x++;
				if (x == limit) {
					i = namesLength;
					j = lastnamesLength;
				}
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
