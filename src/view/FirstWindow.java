package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class FirstWindow implements Initializable{
	
	@FXML
	private AnchorPane buttonAnchor;

	@FXML
	private Button generateDataBTN;

	@FXML
	private Button loadDataBTN;

	@FXML
	private AnchorPane principalPane;

	@FXML
	void generateNewData(ActionEvent event) {
		
	}

	@FXML
	void loadData(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
