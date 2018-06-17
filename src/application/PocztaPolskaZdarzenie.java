package application;

public class PocztaPolskaZdarzenie {

	private String czas;
	private String status;
	public String getCzas() {
		return czas;
	}
	public void setCzas(String czas) {
		this.czas = czas;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public PocztaPolskaZdarzenie(String czas, String status) {
		super();
		this.czas = czas;
		this.status = status;
	}
	
	public PocztaPolskaZdarzenie() {
		
		super();
	}
	
		
}
