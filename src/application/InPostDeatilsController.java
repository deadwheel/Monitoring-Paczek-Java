package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class InPostDeatilsController implements Initializable {
	
	private String text;
	
	InpostPackage paczka;
	PocztaPolska paczka2;
	InPostStatusy statusy;
	
	
		@FXML
		private TableView<TrackingDetail> tabela_zdarzen;
	    @FXML private TableColumn<TrackingDetail, String> data_zd;
	    @FXML private TableColumn<TrackingDetail, String> zd_zd;
	    @FXML private Label numer_nad;
	    @FXML private Label data_nad;
	    @FXML private Label rodzaj_przes;
	    @FXML private GridPane gridd;

	  private final ObservableList<TrackingDetail> data = FXCollections.observableArrayList();
	  

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tabela_zdarzen.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		data_zd.setCellValueFactory(new PropertyValueFactory<TrackingDetail, String>("datetime"));
		zd_zd.setCellValueFactory(new PropertyValueFactory<TrackingDetail, String>("status"));
		tabela_zdarzen.getItems().setAll(data);
		tabela_zdarzen.setItems(data);
	    //System.out.println("weszlo tutaj wgl2"+table234);
		
		
		
	}
	
	
	public void setPackage(InpostPackage paczka, InPostStatusy statusy) {
		this.paczka = paczka;
		this.statusy = statusy;
		numer_nad.setText(this.paczka.getTrackingNumber());
		data_nad.setText(this.paczka.getCreatedAt());
		rodzaj_przes.setText(this.paczka.getType());
		
		if(!this.paczka.getCustomAttributes().getDropoffMachineId().equals("")) {
			
			Label gabarytT = new Label("Paczkomat nadawczy:");
			gridd.add(gabarytT, 0, 3);
			
			Label gabarytC = new Label(this.paczka.getCustomAttributes().getDropoffMachineId()+" "+this.paczka.getCustomAttributes().getDropoffMachineDetail().getAddress().getLine1()+" "+this.paczka.getCustomAttributes().getDropoffMachineDetail().getAddress().getLine2());
			gridd.add(gabarytC, 1, 3);
		
		}
		
		
		if(!this.paczka.getCustomAttributes().getTargetMachineId().equals("")) {
		
			Label gabarytT = new Label("Paczkomat odbiorczy:");
			gridd.add(gabarytT, 0, 4);
			
			Label gabarytC = new Label(this.paczka.getCustomAttributes().getTargetMachineId()+" "+this.paczka.getCustomAttributes().getTargetMachineDetail().getAddress().getLine1()+" "+this.paczka.getCustomAttributes().getTargetMachineDetail().getAddress().getLine2());
			gridd.add(gabarytC, 1, 4);
		
		}
		

		for(TrackingDetail t: this.paczka.getTrackingDetails()) {
			
			for(InPostStatus stat: statusy.getItems()) {
				
				if(t.getStatus().equals(stat.getName())) {
					
					t.setStatus(stat.getTitle());
					break;
					
				}
				
			}
			
			//t.setStatus("ALA");
			
		}
		
		data.addAll(this.paczka.getTrackingDetails());
		
	}
	
	public void setPackage(PocztaPolska paczka) {
		this.paczka2 = paczka;
		numer_nad.setText(this.paczka2.getNumerPrzesylki());
		data_nad.setText(this.paczka2.getDataNadania());
		rodzaj_przes.setText(this.paczka2.getRodzPrzes());
		
		Label urzadTitle = new Label("Urz¹d nadania:");
		gridd.add(urzadTitle, 0, 3);
		
		Label urzadContent = new Label(this.paczka2.getUrzadNadania());
		gridd.add(urzadContent, 1, 3);
		
		
		Label urzadTitle2 = new Label("Urz¹d przeznaczenia:");
		gridd.add(urzadTitle2, 0, 4);
		
		Label urzadContent2 = new Label(this.paczka2.getUrzadPrzeznaczenia());
		gridd.add(urzadContent2, 1, 4);
		
		
		
		List<TrackingDetail> rev = this.paczka2.getZdarzenia();
		Collections.reverse(rev);
		data.addAll(rev);
	}
	

}
