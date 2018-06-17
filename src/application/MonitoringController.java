package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;

import com.mashape.unirest.http.exceptions.UnirestException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MonitoringController implements Initializable {

    @FXML private TextArea actiontarget;
    @FXML private Button batton;
    @FXML private Button wczytaj;
    
    @FXML protected void handleClickSearchF(ActionEvent event) {
    	
    	final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt")
            );
        
        File file = fileChooser.showOpenDialog(actiontarget.getParent().getScene().getWindow());
        if (file != null) {
            System.out.println("Wybrano plil "+file.getName() );
            List<String> lines;
			try {
				lines = Files.readAllLines(file.toPath());
				actiontarget.clear();
	            for(String line : lines){

	                actiontarget.setText(actiontarget.getText()+line+"\n");
	                
	             }
			} catch (IOException e) {
				e.printStackTrace();
			}

        }
    	
    }
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws UnirestException {
        
    	FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("seeep.fxml"));
    	try {
    		
    		Loader.load();
    		
    	}
    	
    	catch(IOException ex) {
    		
    		System.out.println(ex);
    		
    	}
    	
    	
    	InfoTable dfds = Loader.getController();
    	dfds.setText(actiontarget.getText());
    	
    	Parent nowy = Loader.getRoot();
    	Stage nowyStage = new Stage();
    	nowyStage.setScene(new Scene(nowy));
    	nowyStage.setTitle("InPost");
    	nowyStage.show();
    	
    	
    	Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("seeep2.fxml"));
    	try {
    		
    		Loader.load();
    		
    	}
    	
    	catch(IOException ex) {
    		
    		System.out.println(ex);
    		
    	}
    	
    	
    	InfoTable2 dfds2 = Loader.getController();
    	dfds2.setText(actiontarget.getText());
    	
    	nowy = Loader.getRoot();
    	nowyStage = new Stage();
    	nowyStage.setScene(new Scene(nowy));
    	nowyStage.setTitle("Poczta Polska");
    	nowyStage.show();

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		actiontarget.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	if(actiontarget.getText().length() >= 5) batton.setDisable(false);
		    	else batton.setDisable(true);
				
			}
			
			
			
		});
		
		actiontarget.setFocusTraversable(false); 
		
	}
	
}
