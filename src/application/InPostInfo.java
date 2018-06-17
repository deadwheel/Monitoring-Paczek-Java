package application;

import javafx.beans.property.SimpleStringProperty;

public class InPostInfo {
    private final SimpleStringProperty numer;
    private final SimpleStringProperty ostatniStatus;

    public InPostInfo(String fName, String lName) {
        this.numer = new SimpleStringProperty(fName);
        this.ostatniStatus = new SimpleStringProperty(lName);
    }

	public String  getNumer() {
		return numer.get();
	}

	public String  getOstatniStatus() {
		return ostatniStatus.get();
	}
    
    
	
	
}
