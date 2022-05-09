package ui;

import java.io.IOException;
import java.util.ArrayList;

import generics.Node;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		new Thread(new Runnable() {
			@Override
			public void run() {
				myDataBase.generateDataBase(limit);
				;
			}
		}).start();

		

	}

	/**
	 * This method searches a person by a criterion
	 * 
	 * @param value, String, this is the value of the person to be searched (it
	 *               corresponds to a search criteria)
	 * @param type, int, this is an integer to identify the search criterion
	 */
	public static void searchPersons(String value, int type) {
		Node<Person> coincidence = null;
		coincidence = myDataBase.searchByPiece(value, type);

		ArrayList<Person> twentyPersons = myDataBase.getNextCoincidences(coincidence, value, type);
		PeopleData.personsData.setAll(twentyPersons);

	}
	
	/**
	 * This method deletes a person by executing deletePerson in DataBase class
	 * @param person, this is the person to be deleted
	 */
	public static void deletePerson(Person person) {
		myDataBase.deletePerson(person);
	}
	
	/**
	 * This method adds a new person by executing addPerson in DataBase class
	 * @param newPerson, this is the new person 
	 */
	public static void addPerson(Person newPerson) {
		myDataBase.addPerson(newPerson);
	}

}
