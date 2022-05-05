package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.AmericaDataBase;

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
	void loadData(ActionEvent event) {
		
		AmericaDataBase.loadData();
		
		FXMLLoader loader = new FXMLLoader(AmericaDataBase.class.getResource("../view/SearchWindow.fxml"));

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
		
		try {
			Node source = (Node) event.getSource();
			Stage old = (Stage) source.getScene().getWindow();
			old.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void generateNewData(ActionEvent event) {
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
		
	}

}
