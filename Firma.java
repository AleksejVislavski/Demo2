package firma;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Firma {

	private ArrayList<Inventar> listaInventara;

	public Firma() {
		this.listaInventara = new ArrayList<>();
	}

	/**
	 * @param listaInventara
	 */
	public Firma(ArrayList<Inventar> listaInventara) {
		super();
		this.listaInventara = listaInventara;
	}

	/**
	 * @return the listaInventara
	 */
	public ArrayList<Inventar> getListaInventara() {
		return listaInventara;
	}

	/**
	 * @param listaInventara the listaInventara to set
	 */
	public void setListaInventara(ArrayList<Inventar> listaInventara) {
		this.listaInventara = listaInventara;
	}

	public void load(String path) {
		// TODO Auto-generated method stub


		this.listaInventara = new ArrayList<Inventar>();
		List<String> lines;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		try {
			lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());


			for (String line: lines) {
				String[] attributes = line.split(";");


				int id = Integer.parseInt(attributes[0]); 
				String naziv = attributes[1];
				String prostorija = attributes[2]; 
				String imePrezimeZaduznika = attributes[3]; 
				double vrednost = Double.parseDouble(attributes[4]); 
				LocalDate datumUnosa = LocalDate.parse(attributes[5], dtf);


				Inventar inventar = new Inventar(id, naziv, prostorija, imePrezimeZaduznika, vrednost, datumUnosa);
				this.listaInventara.add(inventar);

			}
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronađena.");
		} catch (Exception e) {
			System.out.println("Uc?itan datum nije u odgovarajućem formatu.");
		}


	}

	public void save(String path) {
		// TODO Auto-generated method stub

		ArrayList<String> lines = new ArrayList<String>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		for (int i = 0; i < this.listaInventara.size(); i++) {

			Inventar inventar = this.listaInventara.get(i); 
			int id = inventar.getId();
			String naziv = inventar.getNaziv();
			String prostorija = inventar.getProstorija();
			String imePrezimeZaduznika = inventar.getImePrezimeZaduzenika();
			double vrednost = inventar.getVrednost();
			LocalDate datumUnosa = inventar.getDatumNabavke();

			String line = id  + ";" + naziv + ";" + prostorija + ";"
					+ imePrezimeZaduznika + ";" + vrednost + ";"  + dtf.format(datumUnosa);
			lines.add(line);


		}

		try {
			Files.write(Paths.get(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronađena.");
		}

	}

	public boolean dodavanjeInventara(Inventar inventar) {
		// TODO Auto-generated method stub

		for (int i = 0; i < this.listaInventara.size(); i++) {
			if (this.listaInventara.get(i).getId() == inventar.getId()) {
				return false;
			}
		}
		this.listaInventara.add(inventar);
		return true;
	}

	public void ispisSvihInventara() {
		// TODO Auto-generated method stub

		System.out.printf("%15s %15s %20s %25s %15s %15s\n", "ID", "NAZIV ARTIKLA", "PROSTORIJA", "IME I PREZIME ZADUZENIKA", "VREDNOST",  "DATUM UNOSA");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

		for (int i = 0; i < this.listaInventara.size(); i++) {
			Inventar inventar = listaInventara.get(i);

			System.out.println(inventar);

		}

	}

	public Inventar izmenaInventara(Inventar inventar) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < this.listaInventara.size(); i++) {
			if ((this.listaInventara.get(i)).getId() == inventar.getId()) {
				return this.listaInventara.set(i, inventar);
			}
		}
		
		return null;
	}

	public Inventar brisanjeKontakta(Inventar inventar) {
		// TODO Auto-generated method stub

		for (int i = 0; i < this.listaInventara.size(); i++) {
			if ((this.listaInventara.get(i)).getId() == inventar.getId()) {
				return this.listaInventara.remove(i);
			}
		}
		
		return null;
		
	}

	public void pretragaPoProstoriji(String prostorija) {
		// TODO Auto-generated method stub
		
		System.out.printf("%15s %15s %20s %25s %15s %15s\n", "ID", "NAZIV ARTIKLA", "PROSTORIJA", "IME I PREZIME ZADUZENIKA", "VREDNOST",  "DATUM UNOSA");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

		
		for (int i = 0; i < this.listaInventara.size(); i++) {
			if (this.listaInventara.get(i).getProstorija().equals(prostorija)) {
				System.out.println(this.listaInventara.get(i));
			}
		}
		
	}

	public void pretragaPoNazivu(String naziv) {
		// TODO Auto-generated method stub
		
		System.out.printf("%15s %15s %20s %25s %15s %15s\n", "ID", "NAZIV ARTIKLA", "PROSTORIJA", "IME I PREZIME ZADUZENIKA", "VREDNOST",  "DATUM UNOSA");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
		
		for (int i = 0; i < this.listaInventara.size(); i++) {
			if((this.listaInventara.get(i)).getNaziv().equalsIgnoreCase(naziv));
			Inventar artikal = this.listaInventara.get(i);
			
			System.out.println(artikal);
		}
		
	}



}
