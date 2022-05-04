package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

public class ProgressBarWindow implements Initializable {


	@FXML
	private ProgressBar progressBarPB;

	class bg_Thread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < 100; i++) {
				progressBarPB.setProgress(i / 100.0);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			}
		}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		progressBarPB.setProgress(0.0);
		Thread th = new Thread(new bg_Thread());
		th.start();
	}
}