package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

public class InfoTable2 implements Initializable {

	private static String text;
	
    String soapEndpointUrl = "https://tt.poczta-polska.pl/Sledzenie/services/Sledzenie?wsdl";
    String soapAction = "sprawdzPrzesylkePl";
	
	String[] numery;
	
		@FXML
		private TableView<PocztaPolska> table234;
	    @FXML private TableColumn<PocztaPolska, String> numerCol;
	    @FXML private TableColumn<PocztaPolska, String> statusCol;

	  private static final ObservableList<PocztaPolska> data = FXCollections.observableArrayList();
	
	public void setText(String text) throws UnirestException {
		
		this.text = text;	
		numery = text.split("\\n");
		
		
		for(int i=0;i<numery.length;i++)
			callSoapWebService(soapEndpointUrl, soapAction, numery[i]);
	
		
		
		
		

	}
	
	
	public InfoTable2() {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    table234.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    numerCol.setCellValueFactory(new PropertyValueFactory<PocztaPolska, String>("numerPrzesylki"));
	    statusCol.setCellValueFactory(new PropertyValueFactory<PocztaPolska, String>("ostatniStatus"));
	    data.clear();
	    table234.getItems().setAll(data);
	    table234.setItems(data);
	    //System.out.println("weszlo tutaj wgl2"+table234);
	    
	    
	    table234.setRowFactory(tv -> {
	        TableRow<PocztaPolska> row = new TableRow<>();
	        row.setOnMouseClicked(event -> {
	            if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
	                 && event.getClickCount() == 2) {

	            	PocztaPolska clickedRow = row.getItem();
	                System.out.println("Wybrany wiersz "+clickedRow.getNumerPrzesylki());
	                
	            	FXMLLoader Loader = new FXMLLoader();
	            	Loader.setLocation(getClass().getResource("WidokInpost.fxml"));
	            	try {
	            		
	            		Loader.load();
	            		
	            	}
	            	
	            	catch(IOException ex) {
	            		
	            		System.out.println(ex);
	            		
	            	}
	            	
	            	
	            	InPostDeatilsController dfds = Loader.getController();
	            	dfds.setPackage(clickedRow);
	            	
	            	Parent nowy = Loader.getRoot();
	            	Stage nowyStage = new Stage();
	            	nowyStage.setScene(new Scene(nowy));
	            	nowyStage.setTitle("Poczta Polska "+clickedRow.getNumerPrzesylki());
	            	nowyStage.show();
	                
	            }
	        });
	        return row ;
	    });
	
	    
	    
/*        String soapEndpointUrl = "https://tt.poczta-polska.pl/Sledzenie/services/Sledzenie?wsdl";
        String soapAction = "sprawdzPrzesylkePl";

        callSoapWebService(soapEndpointUrl, soapAction, "testp1");	*/
	    
		
	}
	
	
	
    private static void createSoapEnvelope(SOAPMessage soapMessage, String nrpacz) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "sled";
        String myNamespaceURI = "http://sledzenie.pocztapolska.pl";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPHeader header = envelope.getHeader();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
        //QName sdf = new QName("https://ws.service.com/", "TicketHeader");
        
        //header.addChildElement(new QName("https://ws.service.com/", "TicketHeader"));
        
        
        
        
        
        SOAPHeaderElement wsseSecurity = header.addHeaderElement(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Security", "wsse"));
        wsseSecurity.setMustUnderstand(true);
        //wsseSecurity.setActor(null);
        SOAPElement sub = wsseSecurity.addChildElement(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","UsernameToken", "wsse"));
        sub.setAttribute("xmlns:wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        sub.setAttribute("wsu:Id", "UsernameToken-2");
        SOAPElement userElement = sub.addChildElement(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Username", "wsse"));
        userElement.addTextNode("sledzeniepp");         
        SOAPElement pwdElement = sub.addChildElement(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Password", "wsse"));
        pwdElement.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
        pwdElement.addTextNode("PPSA");
        //_stub.setHeader(wsseSecurity);    


            /*
            Constructed SOAP Request Message:
            <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:myNamespace="http://www.webserviceX.NET">
                <SOAP-ENV:Header/>
                <SOAP-ENV:Body>
                    <myNamespace:GetInfoByCity>
                        <myNamespace:USCity>New York</myNamespace:USCity>
                    </myNamespace:GetInfoByCity>
                </SOAP-ENV:Body>
            </SOAP-ENV:Envelope>
            */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("sprawdzPrzesylke", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("numer", myNamespace);
        soapBodyElem1.addTextNode(nrpacz);
    }

    private static void callSoapWebService(String soapEndpointUrl, String soapAction, String nrpacz) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction,nrpacz), soapEndpointUrl);

            // Print the SOAP Response
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println(soapResponse.getSOAPBody().getElementsByTagName("ax21:status").item(0).getTextContent());
            
            if(soapResponse.getSOAPBody().getElementsByTagName("ax21:status").item(0).getTextContent().equals("1") || 
            		soapResponse.getSOAPBody().getElementsByTagName("ax21:status").item(0).getTextContent().equals("0")) {
            	
            	PocztaPolska paczka = new PocztaPolska();
            	paczka.setStatus(soapResponse.getSOAPBody().getElementsByTagName("ax21:status").item(0).getTextContent());
            	paczka.setNumerPrzesylki(nrpacz);
            	
            	List<TrackingDetail> listOfDetails = new ArrayList<>();
            	
            	
                NodeList lusta = soapResponse.getSOAPBody().getElementsByTagName("ax21:zdarzenie");
                int do_ilu = soapResponse.getSOAPBody().getElementsByTagName("ax21:zdarzenie").getLength();
                for(int i=0;i<do_ilu;i++) {
                	
                	Node node = lusta.item(i);
                	Element docElement = (Element)node;
                	TrackingDetail detale = new TrackingDetail();
                	detale.setDatetime(docElement.getElementsByTagName("ax21:czas").item(0).getTextContent());
                	detale.setStatus(docElement.getElementsByTagName("ax21:nazwa").item(1).getTextContent());
                	listOfDetails.add(detale);
                	
                	
                }
                
                
            	
                
                paczka.setZdarzenia(listOfDetails);

                paczka.setOstatniStatus(listOfDetails.get(listOfDetails.size()-1).getStatus());
                
                
                paczka.setUrzadNadania(soapResponse.getSOAPBody().getElementsByTagName("ax21:urzadNadania").item(0).getChildNodes().item(1).getTextContent());
                paczka.setUrzadPrzeznaczenia(soapResponse.getSOAPBody().getElementsByTagName("ax21:urzadPrzezn").item(0).getChildNodes().item(1).getTextContent());
                
                paczka.setDataNadania(soapResponse.getSOAPBody().getElementsByTagName("ax21:dataNadania").item(0).getTextContent());
                paczka.setRodzPrzes(soapResponse.getSOAPBody().getElementsByTagName("ax21:rodzPrzes").item(0).getTextContent());
                
                data.add(paczka);
            	
            }
            
           
            
/*            NodeList lusta = soapResponse.getSOAPBody().getElementsByTagName("ax21:zdarzenie");
            int do_ilu = soapResponse.getSOAPBody().getElementsByTagName("ax21:zdarzenie").getLength();
            for(int i=0;i<do_ilu;i++) {
            	
            	Node node = lusta.item(i);
            	Element docElement = (Element)node;
            	System.out.println(docElement.getElementsByTagName("ax21:czas").item(0).getTextContent());
            	System.out.println(docElement.getElementsByTagName("ax21:nazwa").item(1).getTextContent());
            	
            	
            }*/

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSOAPRequest(String soapAction, String nrpacz) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        createSoapEnvelope(soapMessage,nrpacz);

        //MimeHeaders headers = soapMessage.getMimeHeaders();
        //headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");

        return soapMessage;
    }
	
}
