package katastar;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Katastar {
	
	private ArrayList<Nepokretnost> listaNepokretnosti = new ArrayList<>();
	private String nazivKatastra;
	private String adresaKatastra;
	
	public Katastar() {
		this.nazivKatastra = "";
		this.adresaKatastra = "";
		this.listaNepokretnosti = new ArrayList<>();
	}
	
	public Katastar(String naziv, String adresa) {
		this.nazivKatastra = naziv;
		this.adresaKatastra = adresa;
		this.listaNepokretnosti = new ArrayList<>();
	}
	
	/**
	 * @param listaNepokretnosti
	 * @param nazivKatastra
	 * @param adresaKatastra
	 */
	public Katastar(ArrayList<Nepokretnost> listaNepokretnosti, String nazivKatastra, String adresaKatastra) {
		super();
		this.listaNepokretnosti = listaNepokretnosti;
		this.nazivKatastra = nazivKatastra;
		this.adresaKatastra = adresaKatastra;
	}
	
	/**
	 * @return the listaNepokretnosti
	 */
	public ArrayList<Nepokretnost> getListaNepokretnosti() {
		return listaNepokretnosti;
	}
	/**
	 * @param listaNepokretnosti the listaNepokretnosti to set
	 */
	public void setListaNepokretnosti(ArrayList<Nepokretnost> listaNepokretnosti) {
		this.listaNepokretnosti = listaNepokretnosti;
	}
	/**
	 * @return the nazivKatastra
	 */
	public String getNazivKatastra() {
		return nazivKatastra;
	}
	/**
	 * @param nazivKatastra the nazivKatastra to set
	 */
	public void setNazivKatastra(String nazivKatastra) {
		this.nazivKatastra = nazivKatastra;
	}
	/**
	 * @return the adresaKatastra
	 */
	public String getAdresaKatastra() {
		return adresaKatastra;
	}
	/**
	 * @param adresaKatastra the adresaKatastra to set
	 */
	public void setAdresaKatastra(String adresaKatastra) {
		this.adresaKatastra = adresaKatastra;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Naziv katastra: " + this.nazivKatastra + "\n");
		sb.append("Adresa katastra: " + this.adresaKatastra + "\n");
		sb.append("Broj nepokretnosti: " + this.listaNepokretnosti.size() + "\n");
		sb.append("Ukupna povrsina svih nepokretnosti: " + izracunajUkupnuPovrsinu());
		return sb.toString();
	}

	private double izracunajUkupnuPovrsinu() {
		// TODO Auto-generated method stub
		
		double suma = 0.0;
		for (int i = 0; i < this.listaNepokretnosti.size(); i++) {
			suma += this.listaNepokretnosti.get(i).getPovrsinaParcele();
		}
		return suma;
	}

	public boolean dodavanjeNepokretnosti(Nepokretnost nepokretnost) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < this.listaNepokretnosti.size(); i++) {
			if (nepokretnost.getIdBroj() == this.listaNepokretnosti.get(i).getIdBroj()) {
				return false;
			}
		}
		this.listaNepokretnosti.add(nepokretnost);
		return true;
	}

	public void ispisNepokretnosti() {
		
		System.out.printf("%15s %15s %15s %15s %15s %15s\n", "Id", "Vlasnik", "Povrsina", "Broj parcele", "Ulica", "Datum izmene");

		for(int i = 0; i < this.listaNepokretnosti.size(); i++) {
			System.out.println(this.listaNepokretnosti.get(i));
		}
	}
	
	public void save(String path) {

		ArrayList<String> linije = new ArrayList<String>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		linije.add(this.nazivKatastra);
		linije.add(this.adresaKatastra);
		for (int i = 0; i < this.listaNepokretnosti.size(); i++) {
			Nepokretnost nepokretnost = this.listaNepokretnosti.get(i);
			int id = nepokretnost.getIdBroj();
			String vlasnik = nepokretnost.getNazivVlasnika();
			double povrsina = nepokretnost.getPovrsinaParcele();
			String brojParcele = nepokretnost.getBrojParcele();
			String ulica = nepokretnost.getUlicaParcele();
			String formatiraniDatum = dtf.format(nepokretnost.getDatumPoslednjeIzmene()); 
			String linija = id  + ";" + vlasnik + ";" + povrsina + ";" + brojParcele + ";" + ulica + ";" + formatiraniDatum;
			linije.add(linija);
		}

		try {
			Files.write(Paths.get(path), linije, Charset.defaultCharset(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
		} catch (java.io.IOException e) {
			System.out.println("Desila se greska prilikom cuvanja podataka.");
		}
		
	}

	public void load(String path) {
		// TODO Auto-generated method stub
		
		this.listaNepokretnosti = new ArrayList<>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		List<String> linije;
		try {
			linije = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
			this.nazivKatastra = linije.get(0);
			this.adresaKatastra = linije.get(1);
			for (int i = 2; i < linije.size(); i++) {
				String[] attributes = linije.get(i).split(";");
				int id = Integer.parseInt(attributes[0]); 
				String vlasnik = attributes[1];
				double povrsina = Double.parseDouble(attributes[2]);  
				String brojParcele = attributes[3];
				String ulica = attributes[4];
				LocalDate datumIzmene = LocalDate.parse(attributes[5], dtf);

				Nepokretnost nepokretnost = new Nepokretnost(id, vlasnik, povrsina, brojParcele, ulica, datumIzmene);
				this.listaNepokretnosti.add(nepokretnost);

			}
		
		} catch (Exception e) {
			System.out.println("Desila se nepredvidjena greska.");
		}

		
	}

	public Nepokretnost izmenaNepokretnosti(Nepokretnost nepokretnost) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < this.listaNepokretnosti.size(); i++) {
			if (nepokretnost.getIdBroj() == this.listaNepokretnosti.get(i).getIdBroj()) {
				this.listaNepokretnosti.set(i, nepokretnost);
			}
		}
		
		return null;
	}

	public Nepokretnost brisanjeNepokretnosti(int id) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < this.listaNepokretnosti.size(); i++) {
			if (this.listaNepokretnosti.get(i).getIdBroj() == id) {
				this.listaNepokretnosti.remove(i);
			}
		}
		
		return null;
	}

	public Nepokretnost pretragaPoParceli(String broj) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < this.listaNepokretnosti.size(); i++) {
			if ((this.listaNepokretnosti.get(i).getBrojParcele() == broj)) {
				return this.listaNepokretnosti.get(i);
			}
		}
		
		return null;
	}
	
	

}
