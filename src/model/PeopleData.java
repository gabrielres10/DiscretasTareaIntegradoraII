package model;

import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PeopleData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static ObservableList<Person> personsData = FXCollections.observableArrayList();
}
