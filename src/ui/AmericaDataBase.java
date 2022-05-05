package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import generics.NodeAVL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataBase;
import model.PeopleData;
import model.Person;

public class AmericaDataBase extends Application implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static DataBase myDataBase;

	public AmericaDataBase() {
		myDataBase = new DataBase();
	}

	public static void main(String[] args) {

		AmericaDataBase amd = new AmericaDataBase();
		launch(args);

	}

	public static void loadData() {
		if (fileExists("data/dataBase.data")) {
			loadDataBase();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(AmericaDataBase.class.getResource("../view/FirstWindow.fxml"));

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

	/**
	 * This method confirms if the file named "data" exists
	 * 
	 * @return out, true if the data file exists
	 */
	private static boolean fileExists(String path) {
		boolean out = false;
		File aux = new File(path);
		if (aux.exists()) {
			out = true;
		}
		return out;

	}

	/**
	 * This Method loads the serialized information in the static variable
	 * AmericaDataBase for its later use in execution
	 */
	private static void loadDataBase() {

		try {
			File file = new File("data/dataBase.data");
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			DataBase dataBase = (DataBase) ois.readObject();
			AmericaDataBase.myDataBase = dataBase;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes the data base with the information into the file
	 * "data/
	 */
	public static void initDB(int limit) {
		myDataBase.generateDataBase(limit);
	}

	public static void searchPersons(String fullName, int type) {
		NodeAVL<Person> coincidence = null;
		switch (type) {
		case 0:
			coincidence = myDataBase.searchByName(fullName);
			System.out.println("0");
			break;
		case 1:
			coincidence = myDataBase.searchByLastName(fullName);
			System.out.println("1");
			break;
		case 2:
			coincidence = myDataBase.searchByFullName(fullName);
			System.out.println("2");
			break;
		case 3:
			coincidence = myDataBase.searchById(fullName);
			System.out.println("3");
			break;
		}

		ArrayList<Person> twentyPersons = myDataBase.getNextCoincidences(coincidence, fullName, type);
		PeopleData.personsData.setAll(twentyPersons);
		for (Person a : twentyPersons) {
			System.out.println(a);
		}

		if (coincidence == null) {
			System.out.println("no hay coincidencia");
		}
	}

	/**
	 * This method saves all the data contained on the PersonsDataBase
	 * "AmericaDataBase.myDataBase"
	 */
	public static void saveShowsAsJavaByteCode() {
		try {
			DataBase database = myDataBase;
			File ref = new File("data/dataBase.data");
			FileOutputStream fos = new FileOutputStream(ref);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(database);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void insert(Person newPerson) {
		myDataBase.getDataSortedByFullName().insert(newPerson);
		myDataBase.getDataSortedByName().insert(newPerson);
		myDataBase.getDataSortedByLastName().insert(newPerson);
		myDataBase.getDataSortedById().insert(newPerson);

		// saveShowsAsJavaByteCode(); // Se intentó serializar pero no sabemos el porqué
		// no deja, dice algo de que el bufferedReader no es serializable pero ya
		// intntamos de todo y nada

	}

}
