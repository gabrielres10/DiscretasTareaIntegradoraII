package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Gender;
import model.Nationality;
import model.PeopleData;
import model.Person;
import ui.AmericaDataBase;

public class SearchWindow implements Initializable {

	private int type = 0;
	// Buttons
	@FXML
	private Button addPersonBTN;
	@FXML
	private Button deletePersonBTN;
	@FXML
	private Button editPersonBTN;

	// Images
	@FXML
	private ImageView searchIMG;
	@FXML
	private ImageView addPersonIMG;
	@FXML
	private ImageView deletePersonIMG;
	@FXML
	private ImageView editPersonIMG;

	// Table View
	@FXML
	private TableView<Person> coincidencesTable;
	@FXML
	private TableColumn<Person, String> idCol;
	@FXML
	private TableColumn<Person, String> nameCol;
	@FXML
	private TableColumn<Person, String> lastNameCol;
	@FXML
	private TableColumn<Person, Gender> genderCol;
	@FXML
	private TableColumn<Person, LocalDate> birthDateCol;
	@FXML
	private TableColumn<Person, Integer> ageCol;
	@FXML
	private TableColumn<Person, Double> heightCol;
	@FXML
	private TableColumn<Person, Nationality> nationalityCol;

	// Text Fields
	@FXML
	private TextField searchTF;

	// Choice Boxes
	@FXML
	private ChoiceBox<String> searchCriteriaCB;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// seaarchTV.setItems(Main.listaDeCoicidencias);
		fillSearchCriteriaCB();
		// Initialization of the columns in the table view
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
		birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
		heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));
		nationalityCol.setCellValueFactory(new PropertyValueFactory<>("nationality"));
	}

	/**
	 * This method adds all the items of the search criteria choice box
	 */
	@FXML
	public void fillSearchCriteriaCB() {
		searchCriteriaCB.getItems().add("Search by name");
		searchCriteriaCB.getItems().add("Search by last name");
		searchCriteriaCB.getItems().add("Search by full name");
		searchCriteriaCB.getItems().add("Search by ID");
	}

	/**
	 * This method sets the prompt text of the search text field
	 * 
	 * @param event
	 */
	@FXML
	void setSearchTF(ActionEvent event) {
		if (searchCriteriaCB.getValue() != null) {
			if (searchCriteriaCB.getValue().equals("Search by name")) {
				type = 0;
			} else if (searchCriteriaCB.getValue().equals("Search by last name")) {
				type = 1;
			} else if (searchCriteriaCB.getValue().equals("Search by full name")) {
				type = 2;
			} else if (searchCriteriaCB.getValue().equals("Search by ID")) {
				type = 3;
			}
		}
		AmericaDataBase.searchPersons(searchTF.getText(), type);
		PeopleData.personsData.removeAll();
		coincidencesTable.setItems(PeopleData.personsData);
	}

	@FXML
	void searchByPiece(KeyEvent event) {
		if (searchCriteriaCB.getValue() != null) {
			if (searchCriteriaCB.getValue().equals("Search by name")) {
				type = 0;
			} else if (searchCriteriaCB.getValue().equals("Search by last name")) {
				type = 1;
			} else if (searchCriteriaCB.getValue().equals("Search by full name")) {
				type = 2;
			} else if (searchCriteriaCB.getValue().equals("Search by ID")) {
				type = 3;
			}
		}
		AmericaDataBase.searchPersons(searchTF.getText(), type);
		PeopleData.personsData.removeAll();
		coincidencesTable.setItems(PeopleData.personsData);
	}


    @FXML
    void launchEditPerson(ActionEvent event) {
    	
    }
	
	
	@FXML
	void launchAddPerson(ActionEvent event) {
		try {
			launchCreatePersonWindow(event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method launches add person window
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void launchCreatePersonWindow(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(AmericaDataBase.class.getResource("../view/NewPersonWindow.fxml"));

		Parent parent = (Parent) loader.load();

		Stage stage = new Stage();

		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();

		try {
			Node source = (Node) event.getSource();
			Stage old = (Stage) source.getScene().getWindow();
			old.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}