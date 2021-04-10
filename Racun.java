package elektro;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Racun {
	
	private int id;
	private String nazivVlasnika;
	private int visaTarifa;
	private int nizaTarifa;
	private double ukupnaPotrosnja;
	private int mesec;
	private LocalDate datum;
	
	public Racun() {
		this.nazivVlasnika = "";
		this.datum = LocalDate.now();
	}

	/**
	 * @param id
	 * @param nazivVlasnika
	 * @param visaTarifa
	 * @param nizaTarifa
	 * @param ukupnaPotrosnja
	 * @param mesec
	 * @param datum
	 */
	public Racun(int id, String nazivVlasnika, int visaTarifa, int nizaTarifa, double ukupnaPotrosnja, int mesec,
			LocalDate datum) {
		super();
		this.id = id;
		this.nazivVlasnika = nazivVlasnika;
		this.visaTarifa = visaTarifa;
		this.nizaTarifa = nizaTarifa;
		this.ukupnaPotrosnja = ukupnaPotrosnja;
		this.mesec = mesec;
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
	 * @return the nazivVlasnika
	 */
	public String getNazivVlasnika() {
		return nazivVlasnika;
	}

	/**
	 * @param nazivVlasnika the nazivVlasnika to set
	 */
	public void setNazivVlasnika(String nazivVlasnika) {
		this.nazivVlasnika = nazivVlasnika;
	}

	/**
	 * @return the visaTarifa
	 */
	public int getVisaTarifa() {
		return visaTarifa;
	}

	/**
	 * @param visaTarifa the visaTarifa to set
	 */
	public void setVisaTarifa(int visaTarifa) {
		this.visaTarifa = visaTarifa;
	}

	/**
	 * @return the nizaTarifa
	 */
	public int getNizaTarifa() {
		return nizaTarifa;
	}

	/**
	 * @param nizaTarifa the nizaTarifa to set
	 */
	public void setNizaTarifa(int nizaTarifa) {
		this.nizaTarifa = nizaTarifa;
	}

	/**
	 * @return the ukupnaPotrosnja
	 */
	public double getUkupnaPotrosnja() {
		return ukupnaPotrosnja;
	}

	/**
	 * @param ukupnaPotrosnja the ukupnaPotrosnja to set
	 */
	public void setUkupnaPotrosnja(double ukupnaPotrosnja) {
		this.ukupnaPotrosnja = ukupnaPotrosnja;
	}

	/**
	 * @return the mesec
	 */
	public int getMesec() {
		return mesec;
	}

	/**
	 * @param mesec the mesec to set
	 */
	public void setMesec(int mesec) {
		this.mesec = mesec;
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
		String temp = "Racun: \n";
		temp += "ID racuna: "+ this.id + "\n" + "Naziv vlasnika racuna: " + this.nazivVlasnika + "\n";
		temp += "Potrosnja visa tarifa : " + this.visaTarifa + " Potrosnja niza tarifa: " + this.nizaTarifa + "\n";
		temp += "Ukupna cena po racunu: " + this.ukupnaPotrosnja + " za mesec br: " + this.mesec + "\n";
		temp += "Datum izdavanja racuna: " + this.datum.format(dtf);
		
		return temp;	}
	
	

}
