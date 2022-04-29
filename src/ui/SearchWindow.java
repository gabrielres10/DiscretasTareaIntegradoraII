package ui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Gender;
import model.Nationality;
import model.Person;


public class SearchWindow implements Initializable{

    @FXML
    private TableView<Person> seaarchTV;

    @FXML
    private TableColumn<Person, String> nameC;

    @FXML
    private TableColumn<Person, String> lastNameC;

    @FXML
    private TableColumn<Person, Gender> genderC;

    @FXML
    private TableColumn<Person, LocalDate> birthDateC;

    @FXML
    private TableColumn<Person, Integer> ageC;

    @FXML
    private TableColumn<Person, Double> heightC;

    @FXML
    private TableColumn<Person, Nationality> nationalityC;

    @FXML
    private TextField searchTF;

    @FXML
    private ImageView searchIMG;

    @FXML
    private Button addPersonBTN;
    
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }



}