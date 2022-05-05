package view;
import java.io.IOException;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.AmericaDataBase;

public class GeneratePersonsWindow implements Initializable{

    @FXML
    private TextField amountOfPersonsTF;

    @FXML
    private Button generateDataBaseBTN;
    
    @FXML
    private ProgressBar progressBar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
    void generateDataBase(ActionEvent event) {
		int limit = 0;
    	if(amountOfPersonsTF.getText().toUpperCase().equals("MAX")) {
    		limit = 6782 * 162253;
    		
    	}else {
    		limit = Integer.parseInt(amountOfPersonsTF.getText());
    		if(limit>1048576) {
    			Alert alert = new Alert(Alert.AlertType.WARNING);
    			alert.setHeaderText("Warning");
    			alert.setTitle("Maximum amount of persons reached!");
    	        alert.setContentText("The maximum amount of persons is " + 6782 * 162253);
    	        Optional<ButtonType> result = alert.showAndWait();
    		}
    	}
    	AmericaDataBase.initDB(limit);
		launchSearchWindow(event);
    }
	
	/**
	 * This method launches search window
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void launchSearchWindow(ActionEvent event) {
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

}

