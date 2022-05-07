package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import generics.Node;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.DataBase;
import model.PeopleData;
import model.Person;

public class AmericaDataBase extends Application {
	private static DataBase myDataBase;

	public AmericaDataBase() {
		myDataBase = new DataBase();
	}

	public static void main(String[] args) {

		AmericaDataBase amd = new AmericaDataBase();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(AmericaDataBase.class.getResource("../view/GeneratePersonsWindow.fxml"));

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
	 * This method initializes the data base with the information into the file
	 * "data/
	 */
	public static void initDB(int limit) {
		myDataBase.generateDataBase(limit);
		/*myDataBase.showAllNames();
		System.out.println("preorden: ");
		myDataBase.getDataSortedByName().preOrder(myDataBase.getDataSortedByName().getRoot());
		*/
		
	}

	public static void searchPersons(String fullName, int type) {
		Node<Person> coincidence = null;
		coincidence = myDataBase.searchByPiece(fullName,type);
		System.out.println(type);
		
		ArrayList<Person> twentyPersons = myDataBase.getNextCoincidences(coincidence, fullName, type);
		PeopleData.personsData.setAll(twentyPersons);
		/*for (Person a : twentyPersons) {
			System.out.println(a);
		}*/
		
	}
	
	public static void insert(Person newPerson) {
		myDataBase.getDataSortedByFullName().insert(newPerson);
		myDataBase.getDataSortedByName().insert(newPerson);
		myDataBase.getDataSortedByLastName().insert(newPerson);
		myDataBase.getDataSortedById().insert(newPerson);
	}
	


}
