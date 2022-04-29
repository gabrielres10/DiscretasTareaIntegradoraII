package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.AVLTree;
import model.Person;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
		BufferedReader bf = null;
		createFullNames(readNames(bf), readLastNames(bf), readPopProp(bf));
	}

	private static void createFullNames(ArrayList<String> names, ArrayList<String> lastnames, int[] popProp) {
		// TODO Auto-generated method stub
		AVLTree<Person> fullNames = new AVLTree<>();
		int namesLength = names.size();
		int lastnamesLength = lastnames.size(); 
		int x = 0;
		int population = namesLength * lastnamesLength;
		String a = ".";
		for(int i = 0; i<namesLength; i++) {
			for(int j = 0; j<lastnamesLength; j++) {
				fullNames.insert(new Person(names.get(i).split(",")[0], lastnames.get(j), names.get(i).split(",")[1],popProp, x));
				x++;
			}
			System.out.println(x);
		}
		System.out.println("x= " + x + " -  nQ=" + fullNames.getNodesQuant() + "height= "+ fullNames.getHeight(fullNames.getRoot()));
	}

	
	private static int[] readPopProp(BufferedReader bf) {
		// TODO Auto-generated method stub
				int[] popProp = new int[55];

				// Reads the information from a CSV file
				try {
					// Open .csv in buffer's reading mode
					bf = new BufferedReader(new FileReader("data/pobProp.csv"));

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

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/SearchWindow.fxml"));

		Parent parent = null;
		try {
			parent = (Parent) loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Stage stage = new Stage();

		Scene scene = new Scene(parent);

		stage.setScene(scene);

		stage.show();
	}

}
