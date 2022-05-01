package ui;

import java.util.ArrayList;

import generics.NodeAVL;
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
		System.out.println(myDataBase.getPersonsDataBase().getHeight(myDataBase.getPersonsDataBase().getRoot()));
		//myDataBase.showAllNames();
	}
	
	
	public static void searchWithName(String fullName) {
		if(myDataBase.search(fullName) != null) {
			NodeAVL<Person> coincidence = myDataBase.search(fullName);
			String found = coincidence.getValue().toString();
			System.out.println(found.equals(null)? "null": found);
			ArrayList<String> twentyPersons = myDataBase.getTwentyPersons(coincidence);
			for(String a : twentyPersons) {
				System.out.println(a);
			}
		}else {
			System.out.println("no hay coincidencia");
		}
	}
}
