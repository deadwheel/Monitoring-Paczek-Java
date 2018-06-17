package application;

import java.util.List;

public class PocztaPolska {

	private String numerPrzesylki;
	private String dataNadania;
	private String rodzPrzes;
	private String urzadNadania;
	private String urzadPrzeznaczenia;
	private String status;
	private String ostatniStatus;
	public String getOstatniStatus() {
		return ostatniStatus;
	}
	public void setOstatniStatus(String ostatniStatus) {
		this.ostatniStatus = ostatniStatus;
	}

	private List<TrackingDetail> zdarzenia;
	
	
	public String getNumerPrzesylki() {
		return numerPrzesylki;
	}
	public void setNumerPrzesylki(String numerPrzesylki) {
		this.numerPrzesylki = numerPrzesylki;
	}
	public String getDataNadania() {
		return dataNadania;
	}
	public void setDataNadania(String dataNadania) {
		this.dataNadania = dataNadania;
	}
	public String getRodzPrzes() {
		return rodzPrzes;
	}
	public void setRodzPrzes(String rodzPrzes) {
		this.rodzPrzes = rodzPrzes;
	}
	public String getUrzadNadania() {
		return urzadNadania;
	}
	public void setUrzadNadania(String urzadNadania) {
		this.urzadNadania = urzadNadania;
	}
	public String getUrzadPrzeznaczenia() {
		return urzadPrzeznaczenia;
	}
	public void setUrzadPrzeznaczenia(String urzadPrzeznaczenia) {
		this.urzadPrzeznaczenia = urzadPrzeznaczenia;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<TrackingDetail> getZdarzenia() {
		return zdarzenia;
	}
	public void setZdarzenia(List<TrackingDetail> zdarzenia) {
		this.zdarzenia = zdarzenia;
	}
	public PocztaPolska(String numerPrzesylki, String dataNadania, String rodzPrzes, String urzadNadania,
			String urzadPrzeznaczenia, String status, List<TrackingDetail> zdarzenia, String ostatniStatus) {
		super();
		this.numerPrzesylki = numerPrzesylki;
		this.dataNadania = dataNadania;
		this.rodzPrzes = rodzPrzes;
		this.urzadNadania = urzadNadania;
		this.urzadPrzeznaczenia = urzadPrzeznaczenia;
		this.status = status;
		this.zdarzenia = zdarzenia;
		this.ostatniStatus = ostatniStatus;
	} 
	
	public PocztaPolska() { super(); }
	
	
	
	
}
