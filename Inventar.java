package firma;

import java.time.LocalDate;

public class Inventar {
	
	private int id;
	private String naziv;
	private String prostorija;
	private String imePrezimeZaduzenika;
	private double vrednost;
	private LocalDate datumNabavke;
	
	public Inventar(int id) {
		this.id = id;
	}
	
	public Inventar () {
		this.naziv = "";
		this.prostorija = "";
		this.imePrezimeZaduzenika = "";
		this.datumNabavke = LocalDate.now();
	}
	
	/**
	 * @param id
	 * @param naziv
	 * @param prostorija
	 * @param imePrezimeZaduzenika
	 * @param vrednost
	 * @param datumNabavke
	 */
	public Inventar(int id, String naziv, String prostorija, String imePrezimeZaduzenika, double vrednost,
			LocalDate datumNabavke) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.prostorija = prostorija;
		this.imePrezimeZaduzenika = imePrezimeZaduzenika;
		this.vrednost = vrednost;
		this.datumNabavke = datumNabavke;
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
	 * @return the naziv
	 */
	public String getNaziv() {
		return naziv;
	}
	/**
	 * @param naziv the naziv to set
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	/**
	 * @return the prostorija
	 */
	public String getProstorija() {
		return prostorija;
	}
	/**
	 * @param prostorija the prostorija to set
	 */
	public void setProstorija(String prostorija) {
		this.prostorija = prostorija;
	}
	/**
	 * @return the imePrezimeZaduzenika
	 */
	public String getImePrezimeZaduzenika() {
		return imePrezimeZaduzenika;
	}
	/**
	 * @param imePrezimeZaduzenika the imePrezimeZaduzenika to set
	 */
	public void setImePrezimeZaduzenika(String imePrezimeZaduzenika) {
		this.imePrezimeZaduzenika = imePrezimeZaduzenika;
	}
	/**
	 * @return the vrednost
	 */
	public double getVrednost() {
		return vrednost;
	}
	/**
	 * @param vrednost the vrednost to set
	 */
	public void setVrednost(double vrednost) {
		this.vrednost = vrednost;
	}
	/**
	 * @return the datumNabavke
	 */
	public LocalDate getDatumNabavke() {
		return datumNabavke;
	}
	/**
	 * @param datumNabavke the datumNabavke to set
	 */
	public void setDatumNabavke(LocalDate datumNabavke) {
		this.datumNabavke = datumNabavke;
	}
	@Override
	public String toString() {
		return String .format("%15s %15s %15s %20s %15s %20s", this.id, this.naziv, this.prostorija, this.imePrezimeZaduzenika, this.vrednost, this.datumNabavke);
	}

	
}
