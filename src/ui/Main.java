package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AVLTree;
import model.Hilo;
import model.Person;

public class Main{

	public static String personDataPath = "data/personDataPeque.csv";

	public static AVLTree<Person> personData;
	
	public static ObservableList<Person> listaDeCoicidencias = FXCollections.observableArrayList();

	public static String toSearch;//Buscar en el nombre.subString(toSearch.lenght)

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedReader bf = null;
		try {
			createFullNames(readNames(bf), readLastNames(bf), readPopProp(bf));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread hiloGrafico = new Thread(new Hilo());

		hiloGrafico.start();
		
		
		listaDeCoicidencias.add(buscarCoincidencias());

	}

	private static void createFullNames(ArrayList<String> names, ArrayList<String> lastnames, int[] popProp)
			throws Exception {
		// TODO Auto-generated method stub
		personData = new AVLTree<>();
		int namesLength = names.size();
		int lastnamesLength = lastnames.size();
		int x = 0;
		BufferedWriter writer = new BufferedWriter(new FileWriter(personDataPath));
		for (int i = 0; i < namesLength; i++) {
			for (int j = 0; j < lastnamesLength; j++) {
				Person current = new Person(names.get(i + (int) Math.random() * namesLength).split(",")[0],
						lastnames.get(j + (int) Math.random() * lastnamesLength),
						names.get(i + (int) Math.random() * namesLength).split(",")[1], popProp, x);
				writer.write(current.toString() + "\n");
				personData.insert(current);
				x++;
				if (x == 1048576) {
					i = namesLength;
					j = lastnamesLength;
				}
			}
		}
		writer.close();
	}
	
	public static Person buscarCoincidencias() {
		return personData.search(new Person(toSearch),personData.getRoot(),toSearch).getValue();
	}

	private static int[] readPopProp(BufferedReader bf) {
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