package ui;

import model.*;
import threads.*;
import generics.*;

public class AmericaDataBase {
	
	private static DataBase myDataBase;
	
	public AmericaDataBase() {
		myDataBase = new DataBase();
	}
	
	public static void main(String [] args) {
		
		AmericaDataBase amd = new AmericaDataBase();
		
		Thread firstThread = new Thread(new FirstThread());
		
		firstThread.start();
	}
	
	/**
	 * This method initializes the data base with the information into the file "data/
	 */
	public static void initDB() {
		myDataBase.generateDataBase();
	}
	
}