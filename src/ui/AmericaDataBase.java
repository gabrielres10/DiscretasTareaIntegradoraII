package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DataBase;
import model.GraphicThread;
import model.Person;

public class AmericaDataBase {
	
	private static DataBase myDataBase;
	private ObservableList<Person> listaDeCoicidencias;
	
	public AmericaDataBase() {
		listaDeCoicidencias = FXCollections.observableArrayList();
		myDataBase = new DataBase();
	}
	
	public static void main(String [] args) {
		
		AmericaDataBase amd = new AmericaDataBase();
		Thread graphicThread = new Thread(new GraphicThread());
		graphicThread.start();
		amd.initDB();
	}
	
	/**
	 * This method initializes the data base with the information into the file "data/
	 */
	public void initDB() {
		myDataBase.generateDataBase();
	}
	
	
	public static void searchWithName(String fullName) {
		System.out.println(myDataBase.search(fullName).getValue().toString());
	}
}
