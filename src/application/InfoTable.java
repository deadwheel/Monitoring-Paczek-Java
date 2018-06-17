package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class InfoTable implements Initializable {

	private String text;
	private InPostStatusy statusy;
	
	String[] numery;
	
		@FXML
		private TableView<InpostPackage> table234;
	    @FXML private TableColumn<InpostPackage, String> numerCol;
	    @FXML private TableColumn<InpostPackage, String> statusCol;

	  private final ObservableList<InpostPackage> data = FXCollections.observableArrayList();
	
	public void setText(String text) throws UnirestException {
		
		this.text = text;	
		numery = text.split("\\n");
		
		
		for(int i=0;i<numery.length;i++) {
		
/*		HttpResponse<JsonNode> jsonResponse = Unirest.get("https://api-shipx-pl.easypack24.net/v1/tracking/{id}")
				  .header("accept", "application/json")
				  .routeParam("id", "688888243788888000010864")
				  .asJson();*/
		
			InpostPackage nowapaczka = null;
		
		HttpResponse<InpostPackage> jsonResponse = Unirest.get("https://api-shipx-pl.easypack24.net/v1/tracking/{id}")
				  .header("accept", "application/json")
				  .routeParam("id", numery[i])
				  .asObject(InpostPackage.class);
		
					if(jsonResponse.getStatus() == 200) {
						
						nowapaczka=jsonResponse.getBody();
						for(InPostStatus stat : statusy.getItems()) {
							
							if(stat.getName().equals(nowapaczka.getStatus())) {
								
								nowapaczka.setStatus(stat.getTitle());
								
							}
						}
						data.add(nowapaczka);
						
					}
					
		//System.out.println(jsonResponse.getBody());
		
		}
	
		
		
		
		

	}
	
	
	public InfoTable() {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    // Set the columns width auto size
	    table234.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    numerCol.setCellValueFactory(new PropertyValueFactory<InpostPackage, String>("trackingNumber"));
	    statusCol.setCellValueFactory(new PropertyValueFactory<InpostPackage, String>("status"));
	    table234.getItems().setAll(data);
	    table234.setItems(data);
	    System.out.println("weszlo tutaj wgl2"+table234);
	    
	    
	    table234.setRowFactory(tv -> {
	        TableRow<InpostPackage> row = new TableRow<>();
	        row.setOnMouseClicked(event -> {
	            if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
	                 && event.getClickCount() == 2) {

	            	InpostPackage clickedRow = row.getItem();
	                System.out.println("Wybrany wiersz "+clickedRow.getTrackingNumber());
	                
	            	FXMLLoader Loader = new FXMLLoader();
	            	Loader.setLocation(getClass().getResource("WidokInpost.fxml"));
	            	try {
	            		
	            		Loader.load();
	            		
	            	}
	            	
	            	catch(IOException ex) {
	            		
	            		System.out.println(ex);
	            		
	            	}
	            	
	            	
	            	InPostDeatilsController dfds = Loader.getController();
	            	dfds.setPackage(clickedRow, this.statusy);
	            	
	            	
	            	Parent nowy = Loader.getRoot();
	            	Stage nowyStage = new Stage();
	            	nowyStage.setScene(new Scene(nowy));
	            	nowyStage.setTitle("InPost "+clickedRow.getTrackingNumber());
	            	nowyStage.show();
	                
	            }
	        });
	        return row ;
	    });
		
	    
	    

			try {
				HttpResponse<InPostStatusy> jsonResponse = Unirest.get("https://api-shipx-pl.easypack24.net/v1/statuses")
						  .header("accept", "application/json")
						  .asObject(InPostStatusy.class);
						  
				
					if(jsonResponse.getStatus() == 200) {
						
						this.statusy = jsonResponse.getBody();
						
					}
				
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
		
	}
	
}
