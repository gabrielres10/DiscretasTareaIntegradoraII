package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.Gender;
import model.Nationality;
import model.Person;
import ui.AmericaDataBase;

public class NewPersonWindow implements Initializable {
	@FXML
	private DatePicker birthDateDP;

	@FXML
	private ChoiceBox<Gender> genderCB;

	@FXML
	private TextField heightTF;

	@FXML
	private TextField lastNameTF;

	@FXML
	private TextField nameTF;

	@FXML
	private ChoiceBox<Nationality> nationalityCB;

	@FXML
	private WebView webView;

	@FXML
	void accept(ActionEvent event) {

		String name = nameTF.getText();

		String lastName = lastNameTF.getText();

		Gender gender = genderCB.getValue();

		LocalDate birthDate = birthDateDP.getValue();

		double height = Double.parseDouble(heightTF.getText());

		Nationality nationality = nationalityCB.getValue();

		Person person = new Person(name, lastName, gender, birthDate, height, nationality);

		AmericaDataBase.insert(person);
		
		try {
			previousWindow(event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
    void goBack(ActionEvent event) {
		try {
			previousWindow(event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void registerUser(ActionEvent event) {
    	
    }

	

	public void previousWindow(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(AmericaDataBase.class.getResource("../view/SearchWindow.fxml"));

        Parent parent = (Parent) loader.load();;


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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			loadImage();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		birthDateDP.setValue(LocalDate.now());

		ArrayList<Gender> genders = new ArrayList<>();

		ArrayList<Nationality> nationalities = new ArrayList<>();

		for (Gender g : Gender.values()) {
			genders.add(g);
		}

		for (Nationality n : Nationality.values()) {
			nationalities.add(n);
		}
		
		ObservableList<Gender> gender = FXCollections.observableArrayList();
		gender.addAll(genders);
		genderCB.setItems(gender);
		ObservableList<Nationality> nats = FXCollections.observableArrayList();
		nats.addAll(nationalities);
		nationalityCB.setItems(nats);
	}

	private void loadImage() throws InterruptedException {
		webView.getEngine().load("https://thispersondoesnotexist.com/");
	}

}