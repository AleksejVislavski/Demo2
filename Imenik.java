package imenik;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Imenik {
	
	private ArrayList<Kontakt> listaKontakata;
	
	public Imenik() {
		this.listaKontakata = new ArrayList<>();
	}

	/**
	 * @param listaKontakata
	 */
	public Imenik(ArrayList<Kontakt> listaKontakata) {
		super();
		this.listaKontakata = listaKontakata;
	}

	/**
	 * @return the listaKontakata
	 */
	public ArrayList<Kontakt> getListaKontakata() {
		return listaKontakata;
	}

	/**
	 * @param listaKontakata the listaKontakata to set
	 */
	public void setListaKontakata(ArrayList<Kontakt> listaKontakata) {
		this.listaKontakata = listaKontakata;
	}

	public void load(String path) {
		// TODO Auto-generated method stub
		
		this.listaKontakata = new ArrayList<Kontakt>();
		List<String> lines;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		try {
			//učitavaju se sve linije iz fajla
			lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());

			/*
			 * prolazi se for petljom kroz svaku liniju koja je u formatu:
			 * identifikacioniBroj;imeInventara;prostorija;imeIPrezimeOsobeKojaDuziInventar;procenjenaVrednost;godinaProizvodnje;datum
			 */
			for (String line: lines) {
				//svaka linija se "secka" na re�?i koje su odvojene znakom ; pomoću metode split klase String i tako se dobija niz stringova
				String[] attributes = line.split(";");

				/*
				 * Pošto znamo redosled vrednosti koje su pisane u datoteci znamo kojim redom da ih preuzimamo iz niza attributes
				 */
				int id = Integer.parseInt(attributes[0]); 
				String ime = attributes[1];
				String prezime = attributes[2]; 
				String radnoMesto = attributes[3]; 
				String brojProstorije = attributes[4]; 
				int brojLokala = Integer.parseInt(attributes[5]);
				/*
				 * Potrebno je pretvoriti string učitan iz datoteke u objekat klase LocalDate.
				 * Metoda parse iz klase LocalDate (koja vrši konverziju) zahteva try-catch blok.
				 * Try blok već imamo, dok je catch blok dodat na kraj metode da hvata izuzetak tipa Exception.
				 */
				LocalDate datumUnosa = LocalDate.parse(attributes[6], dtf);

				/*
				 * Pomoću izdvojenih vrednosti iz niza kreiramo novi objekat tipa Kontakt i smeštamo u listu kontakata
				 */
				Kontakt kontakt = new Kontakt(id, ime, prezime, radnoMesto, brojProstorije, brojLokala, datumUnosa);
				this.listaKontakata.add(kontakt);

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
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaKontakata.size(); i++) {
			/*
			 * Kreiramo String line koji predstavlja jedan red u tekstualnoj datoteci a sadrži informacije o pojedinačnom kontaktu
			 * Kod koji radi istu stvar je zakomentarisan:
			 */ 
			Kontakt kontakt = this.listaKontakata.get(i); //očitavamo objekat tipa Kontakt
			int identifikacioniBroj = kontakt.getId();
			String imeKontakta = kontakt.getIme();
			String prezimeKontakta = kontakt.getPrezime();
			String nazivRadnogMesta = kontakt.getRadnoMesto();
			String brojProstorije = kontakt.getBrojProstorije();
			int brojLokala = kontakt.getLokal();
			LocalDate datumUnosa = kontakt.getDatum();
			
			String line = identifikacioniBroj  + ";" + imeKontakta + ";" + prezimeKontakta + ";"
					+ nazivRadnogMesta + ";" + brojProstorije + ";" + brojLokala + ";" + dtf.format(datumUnosa);
			lines.add(line);

		
		}

		try {
			Files.write(Paths.get(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronađena.");
		}
		
	}

	public boolean dodavanjeKontakta(Kontakt kontakt) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.listaKontakata.size(); i++) {
			if (this.listaKontakata.get(i).getId() == kontakt.getId()) {
				return false;
			}
		}
		this.listaKontakata.add(kontakt);
		return true;
	}

	public void ispisKontakata() {
		// TODO Auto-generated method stub
		
		System.out.printf("%15s %15s %15s %25s %15s %15s %14s\n", "Id", "Ime kontakta", "Prezime kontakta", "Naziv radnog mesta", "Broj prostorije", "Broj lokala", "Datum unosa");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
		
		for (int i = 0; i < this.listaKontakata.size(); i++) {
			Kontakt kontakt = listaKontakata.get(i);
			
			System.out.println(kontakt);
		}
		
	}

	public Kontakt izmenaKontakta(Kontakt kontakt) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < this.listaKontakata.size(); i++) {
			if ((this.listaKontakata.get(i)).getId() == kontakt.getId()) {
				return this.listaKontakata.set(i, kontakt);
			}
		}
		
		return null;
	}

	public Kontakt brisanjeKontakta(Kontakt kontakt) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < this.listaKontakata.size(); i++) {
			if ((this.listaKontakata.get(i)).getId() == kontakt.getId()) {
				return this.listaKontakata.remove(i);
			}
		}
		
		return null;
	}

	public void pretragaPoLokalu(int brLokala) {
		// TODO Auto-generated method stub
		
		System.out.printf("%15s %15s %15s %25s %15s %15s %14s\n", "Id", "Ime kontakta", "Prezime kontakta", "Naziv radnog mesta", "Broj prostorije", "Broj lokala", "Datum unosa");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
		
		for (int i = 0; i < this.listaKontakata.size(); i++) {
				if ((this.listaKontakata.get(i)).getLokal() == brLokala) {
					System.out.println(this.listaKontakata.get(i));
				}
			
			
		}
		
	}

	public void pretragaPoRadnomMestu(String radnoMesto) {
		// TODO Auto-generated method stub
		System.out.printf("%15s %15s %15s %25s %15s %15s %14s\n", "Id", "Ime kontakta", "Prezime kontakta", "Naziv radnog mesta", "Broj prostorije", "Broj lokala", "Datum unosa");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
		
		for (int i = 0; i < this.listaKontakata.size(); i++) {
			if ((this.listaKontakata.get(i)).getRadnoMesto().equalsIgnoreCase(radnoMesto)) {
				System.out.println(this.listaKontakata.get(i));
			}
		}
	}

	public void pretragaPoImenu(String ime) {
		// TODO Auto-generated method stub
		System.out.printf("%15s %15s %15s %25s %15s %15s %14s\n", "Id", "Ime kontakta", "Prezime kontakta", "Naziv radnog mesta", "Broj prostorije", "Broj lokala", "Datum unosa");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
		
		for (int i = 0; i < this.listaKontakata.size(); i++) {
			if ((this.listaKontakata.get(i)).getIme().equalsIgnoreCase(ime)) {
				System.out.println(this.listaKontakata.get(i));
			}
		}
		
	}

	public void pretragaPoImenuIradnomMestu(String ime, String prezime, String radnoMesto) {
		// TODO Auto-generated method stub
		System.out.printf("%15s %15s %15s %25s %15s %15s %14s\n", "Id", "Ime kontakta", "Prezime kontakta", "Naziv radnog mesta", "Broj prostorije", "Broj lokala", "Datum unosa");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
		
		for (int i = 0; i < this.listaKontakata.size(); i++) {
			if (this.listaKontakata.get(i).getIme().equalsIgnoreCase(ime) &&
					this.listaKontakata.get(i).getPrezime().equalsIgnoreCase(prezime) &&
					this.listaKontakata.get(i).getRadnoMesto().startsWith(radnoMesto)) {
				System.out.println(this.listaKontakata.get(i));
			}
			
		}
		
	}

	public void ispisKontakataUIntervalu(LocalDate datumPocetka, LocalDate datumKraja) {
		// TODO Auto-generated method stub
		System.out.printf("%15s %15s %15s %25s %15s %15s %14s\n", "Id", "Ime kontakta", "Prezime kontakta", "Naziv radnog mesta", "Broj prostorije", "Broj lokala", "Datum unosa");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
		
		for (int i = 0; i < this.listaKontakata.size(); i++) {
			if ((this.listaKontakata.get(i)).getDatum().compareTo(datumPocetka) > 0 &&
					(this.listaKontakata.get(i)).getDatum().compareTo(datumKraja) < 0) {
				System.out.println(this.listaKontakata.get(i));
			}
		}

	}

	
	
}
