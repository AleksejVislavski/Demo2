package elektro;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Elektrodistribucija {

	private String naziv;
	private String adresa; 
	private String telefon;
	private ArrayList<Racun> listaRacuna;

	public Elektrodistribucija() {
		this.naziv = "";
		this.adresa = "";
		this.telefon = "";
		this.listaRacuna = new ArrayList<Racun>();
	}

	public Elektrodistribucija(String naziv, String adresa, String telefon) {
		this.naziv = "";
		this.adresa = "";
		this.telefon = "";

	}

	/**
	 * @param naziv
	 * @param adresa
	 * @param telefon
	 * @param listaRacuna
	 */
	public Elektrodistribucija(String naziv, String adresa, String telefon, ArrayList<Racun> listaRacuna) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.listaRacuna = listaRacuna;
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
	 * @return the adresa
	 */
	public String getAdresa() {
		return adresa;
	}

	/**
	 * @param adresa the adresa to set
	 */
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	/**
	 * @return the telefon
	 */
	public String getTelefon() {
		return telefon;
	}

	/**
	 * @param telefon the telefon to set
	 */
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	/**
	 * @return the listaRacuna
	 */
	public ArrayList<Racun> getListaRacuna() {
		return listaRacuna;
	}

	/**
	 * @param listaRacuna the listaRacuna to set
	 */
	public void setListaRacuna(ArrayList<Racun> listaRacuna) {
		this.listaRacuna = listaRacuna;
	}

	@Override
	public String toString() {
		String temp = "Elektro distribucija: \n";
		temp += "Naziv: " + this.naziv +" || Adresa: "+ this.adresa + " || Telefon: "+ this.telefon +  "\n";
		for (int i = 0; i < this.listaRacuna.size(); i++) {
			temp += this.listaRacuna.get(i) + "\n";
		}
		return temp;}

	public void load(String path) {
		// TODO Auto-generated method stub

		this.listaRacuna = new ArrayList<Racun>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
			for (String line : lines) {
				String[] attributes = line.split(";");

				int id = Integer.parseInt(attributes[0]);
				String vlasnik = attributes[1];
				int visaTarifa = Integer.parseInt(attributes[2]);
				int nizaTarifa = Integer.parseInt(attributes[3]);
				double ukupnaPotrosnja = Double.parseDouble(attributes[4]);
				int mesec = Integer.parseInt(attributes[5]);
				String datumString = attributes[6];
				LocalDate datumZaCuvanje = null;
				try {
					datumZaCuvanje = LocalDate.parse(datumString, dtf);
				} catch (Exception e) {
					e.printStackTrace();
				}

				Racun racun = new Racun(id, vlasnik, visaTarifa, nizaTarifa, ukupnaPotrosnja, mesec, datumZaCuvanje);
				this.listaRacuna.add(racun);

			}
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronadjena.");
		}


	}

	public void save(String path) {
		// TODO Auto-generated method stub

		ArrayList<String> lines = new ArrayList<String>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		for (int i = 0; i < this.listaRacuna.size(); i++) {
			Racun racun = this.listaRacuna.get(i);
			int id = racun.getId();
			String vlasnik = racun.getNazivVlasnika();
			int visaTarifa = racun.getVisaTarifa();
			int nizaTarifa = racun.getNizaTarifa();
			double ukupnaPotrosnja = racun.getUkupnaPotrosnja();
			int mesec = racun.getMesec();
			String formatiraniDatum = dtf.format(racun.getDatum());
			String line = id + ";" + vlasnik + ";" + visaTarifa + ";" + nizaTarifa + ";" + ukupnaPotrosnja + ";" + mesec
					+ ";" + formatiraniDatum;
			lines.add(line);
		}

		try {
			Files.write(Paths.get(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronadjena.");
		}

	}

	public boolean dodavanjeRacuna(Racun racun) {
		return this.listaRacuna.add(racun);
	}

	public void ispisRacuna() {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.listaRacuna.size(); i++) {
			System.out.println(this.listaRacuna.get(i));
		}

	}

	public void pretragaTarifeNizeVise(int visaTarifaMin, int visaTarifaMax, int nizaTarifaMin, int nizaTarifaMax) {
		// TODO Auto-generated method stub

		for (int i = 0; i < this.listaRacuna.size(); i++) {
			Racun racun = this.listaRacuna.get(i);
			if (racun.getVisaTarifa() > visaTarifaMin && racun.getVisaTarifa() < visaTarifaMax && racun.getNizaTarifa() > nizaTarifaMin
					&& racun.getNizaTarifa() < nizaTarifaMax) {
				System.out.println(racun); {

				}
			}

		}
	}

	public ArrayList<Racun> pretragaPoMesecu(int mesec) {
		// TODO Auto-generated method stub
		ArrayList<Racun> listaRezultata = new ArrayList<Racun>();
		for (int i = 0; i < this.listaRacuna.size(); i++) {
			if (mesec == this.listaRacuna.get(i).getMesec()) {
				listaRezultata.add(this.listaRacuna.get(i));
			}
		}
		
		return listaRezultata;
	}

	public ArrayList<Racun> pretragaPoDatumima(LocalDate min, LocalDate max) {
		// TODO Auto-generated method stub
		ArrayList<Racun> listaRezultata = new ArrayList<Racun>();
		for (int i = 0; i < this.listaRacuna.size(); i++) {
			if (this.listaRacuna.get(i).getDatum().compareTo(min) >= 0
					&& this.listaRacuna.get(i).getDatum().compareTo(max) <= 0) {
				listaRezultata.add(this.listaRacuna.get(i));
			}
		}
		
		return listaRezultata;
	}


}
