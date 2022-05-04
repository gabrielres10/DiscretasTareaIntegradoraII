package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class GenerateDataWindow implements Initializable{
	@FXML
	private TextField amountOfPeopleTF;

	@FXML
	private Button generateBTN;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private Label timeLB;

	@FXML
	void generateData(ActionEvent event) {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
