package application;
	
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
    	
    	Unirest.setObjectMapper(new ObjectMapper() {
    	    private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
    	                = new com.fasterxml.jackson.databind.ObjectMapper();

    	    public <T> T readValue(String value, Class<T> valueType) {
    	        try {
    	            return jacksonObjectMapper.readValue(value, valueType);
    	        } catch (IOException e) {
    	            throw new RuntimeException(e);
    	        }
    	    }

    	    public String writeValue(Object value) {
    	        try {
    	            return jacksonObjectMapper.writeValueAsString(value);
    	        } catch (JsonProcessingException e) {
    	            throw new RuntimeException(e);
    	        }
    	    }
    	});
    	
    	
    	
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        
        stage.setTitle("Monitoring Paczek");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.show();
    }
}
