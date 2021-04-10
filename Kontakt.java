package imenik;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Kontakt {
	
	private int id;
	private String ime;
	private String prezime;
	private String radnoMesto;
	private String brojProstorije;
	private int lokal;
	private LocalDate datum;
	
	public Kontakt(int id) {
		this.id = id;
	}
	
	public Kontakt () {
		this.ime = "";
		this.prezime = "";
		this.radnoMesto = "";
		this.brojProstorije = "";
		this.datum = LocalDate.now();
	}
	
	/**
	 * @param id
	 * @param ime
	 * @param prezime
	 * @param radnoMesto
	 * @param brojProstorije
	 * @param lokal
	 * @param datum
	 */
	public Kontakt(int id, String ime, String prezime, String radnoMesto, String brojProstorije, int lokal,
			LocalDate datum) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.radnoMesto = radnoMesto;
		this.brojProstorije = brojProstorije;
		this.lokal = lokal;
		this.datum = datum;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the ime
	 */
	public String getIme() {
		return ime;
	}
	/**
	 * @param ime the ime to set
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}
	/**
	 * @return the prezime
	 */
	public String getPrezime() {
		return prezime;
	}
	/**
	 * @param prezime the prezime to set
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	/**
	 * @return the radnoMesto
	 */
	public String getRadnoMesto() {
		return radnoMesto;
	}
	/**
	 * @param radnoMesto the radnoMesto to set
	 */
	public void setRadnoMesto(String radnoMesto) {
		this.radnoMesto = radnoMesto;
	}
	/**
	 * @return the brojProstorije
	 */
	public String getBrojProstorije() {
		return brojProstorije;
	}
	/**
	 * @param brojProstorije the brojProstorije to set
	 */
	public void setBrojProstorije(String brojProstorije) {
		this.brojProstorije = brojProstorije;
	}
	/**
	 * @return the lokal
	 */
	public int getLokal() {
		return lokal;
	}
	/**
	 * @param lokal the lokal to set
	 */
	public void setLokal(int lokal) {
		this.lokal = lokal;
	}
	/**
	 * @return the datum
	 */
	public LocalDate getDatum() {
		return datum;
	}
	/**
	 * @param datum the datum to set
	 */
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	@Override
	public String toString() {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		return String.format("%15s %15s %15s %20s %15s %15s %20s", this.id, this.ime, this.prezime, this.radnoMesto, this.brojProstorije, this.lokal, dtf.format(this.datum));
	}
	
	
	
	

}
