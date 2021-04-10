package katastar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Nepokretnost {
	
	private int idBroj;
	private String nazivVlasnika;
	private double povrsinaParcele;
	private String brojParcele;
	private String ulicaParcele;
	private LocalDate datumPoslednjeIzmene;
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
	
	public Nepokretnost() {
		this.nazivVlasnika = "";
		this.brojParcele = "";
		this.ulicaParcele = "";
		this.datumPoslednjeIzmene = LocalDate.now();
	}
	
	/**
	 * @param idBroj
	 * @param nazivVlasnika
	 * @param povrsinaParcele
	 * @param brojParcele
	 * @param ulicaParcele
	 * @param datumPoslednjeIzmene
	 */
	public Nepokretnost(int idBroj, String nazivVlasnika, double povrsinaParcele, String brojParcele,
			String ulicaParcele, LocalDate datumPoslednjeIzmene) {
		super();
		this.idBroj = idBroj;
		this.nazivVlasnika = nazivVlasnika;
		this.povrsinaParcele = povrsinaParcele;
		this.brojParcele = brojParcele;
		this.ulicaParcele = ulicaParcele;
		this.datumPoslednjeIzmene = datumPoslednjeIzmene;
	}

	/**
	 * @return the idBroj
	 */
	public int getIdBroj() {
		return idBroj;
	}

	/**
	 * @param idBroj the idBroj to set
	 */
	public void setIdBroj(int idBroj) {
		this.idBroj = idBroj;
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
	 * @return the povrsinaParcele
	 */
	public double getPovrsinaParcele() {
		return povrsinaParcele;
	}

	/**
	 * @param povrsinaParcele the povrsinaParcele to set
	 */
	public void setPovrsinaParcele(double povrsinaParcele) {
		this.povrsinaParcele = povrsinaParcele;
	}

	/**
	 * @return the brojParcele
	 */
	public String getBrojParcele() {
		return brojParcele;
	}

	/**
	 * @param brojParcele the brojParcele to set
	 */
	public void setBrojParcele(String brojParcele) {
		this.brojParcele = brojParcele;
	}

	/**
	 * @return the ulicaParcele
	 */
	public String getUlicaParcele() {
		return ulicaParcele;
	}

	/**
	 * @param ulicaParcele the ulicaParcele to set
	 */
	public void setUlicaParcele(String ulicaParcele) {
		this.ulicaParcele = ulicaParcele;
	}

	/**
	 * @return the datumPoslednjeIzmene
	 */
	public LocalDate getDatumPoslednjeIzmene() {
		return datumPoslednjeIzmene;
	}

	/**
	 * @param datumPoslednjeIzmene the datumPoslednjeIzmene to set
	 */
	public void setDatumPoslednjeIzmene(LocalDate datumPoslednjeIzmene) {
		this.datumPoslednjeIzmene = datumPoslednjeIzmene;
	}

	@Override
	public String toString() {
		return String.format("%15d %15s %15.2f %15s %15s %16s", this.idBroj, this.nazivVlasnika, this.povrsinaParcele, this.brojParcele, this.ulicaParcele, dtf.format(this.datumPoslednjeIzmene));
	}
	
	
	
}
