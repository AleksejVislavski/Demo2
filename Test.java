package imenik;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Test {
	
	public static Scanner sc = new Scanner(System.in);
	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Imenik imenik = new Imenik();
		imenik.load("kontakti.txt");
		
		String answer = null;
		
		
		do {
			
			System.out.println("MENI");
			System.out.println("1. Unos kontakta");
			System.out.println("2. Ispis kontakata");
			System.out.println("3. Izmena kontakta");
			System.out.println("4. Brisanje kontakta");
			System.out.println("5. Pretraga po lokalu");
			System.out.println("6. Pretraga po radnom mestu");
			System.out.println("7. Pretraga po navedenom imenu");
			System.out.println("8. Pretraga po imenu i radnom mestu");
			System.out.println("9. Pretraga po intervalu");
			System.out.println("x");
			
			System.out.println("--------------------------------------");
			System.out.print("Opcija: ");
			
			answer = sc.nextLine();

			
			switch (answer) {
			case "1":
				unosKontakta(imenik);
				imenik.save("kontakti.txt");
				break;
			case "2":
				imenik.ispisKontakata();
				break;
			case "3":
				izmenaKontakta(imenik);
				imenik.save("kontakti.txt");
				break;
			case "4":
				brisanjeKontakta(imenik);
				imenik.save("kontakti.txt");
				break;
			case "5":
				pretragaPoLokalu(imenik);
				break;
			case "6":
				pretragaPoRadnomMestu(imenik);
				break;
			case "7":
				pretragaPoImenu(imenik);
				break;
			case "8":
				pretragaPoImenuIradnomMestu(imenik);
				break;
			case "9":
				pretragaPoIntervalu(imenik);
				break;
			case "x":
				
				break;

			default:
				break;
			}
			
		}while(!answer.equals("x"));
		
		imenik.save("kontakti.txt");

		sc.close();

	}

	private static void pretragaPoIntervalu(Imenik imenik) {
		// TODO Auto-generated method stub
		LocalDate datumPocetka = null;
		LocalDate datumKraja = null;
		String datumPocetkaStr = null;
		do {
			System.out.println("Datum po�?etka intervala: ");
			datumPocetkaStr = sc.nextLine();
		} while (!isValidDate(datumPocetkaStr));
		
		try {
			datumPocetka = LocalDate.parse(datumPocetkaStr, dtf);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		String datumKrajaStr = null;
		do {
			System.out.println("Datum kraja intervala: ");
			datumKrajaStr = sc.nextLine();
		} while (!isValidDate(datumKrajaStr));
		
		try {
			datumKraja = LocalDate.parse(datumKrajaStr, dtf);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		imenik.ispisKontakataUIntervalu(datumPocetka, datumKraja);
	}

	private static void pretragaPoImenuIradnomMestu(Imenik imenik) {
		// TODO Auto-generated method stub
		System.out.println("Unesite ime: ");
		String ime = sc.nextLine();
		System.out.println("Unesite prezime: ");
		String prezime = sc.nextLine();
		System.out.println("Unesite radno mesto: ");
		String radnoMesto = sc.nextLine();
		
		imenik.pretragaPoImenuIradnomMestu(ime, prezime , radnoMesto);
		
	}

	private static void pretragaPoImenu(Imenik imenik) {
		// TODO Auto-generated method stub
		System.out.println("Unesite ime: ");
		String ime = sc.nextLine();
		imenik.pretragaPoImenu(ime);
		
	}

	private static void pretragaPoRadnomMestu(Imenik imenik) {
		// TODO Auto-generated method stub
		System.out.println("Unesite radno mesto: ");
		String radnoMesto = sc.nextLine();
		imenik.pretragaPoRadnomMestu(radnoMesto);
		
	}

	private static void pretragaPoLokalu(Imenik imenik) {
		// TODO Auto-generated method stub
		
		String brLokalaS = null;
		do {
			System.out.println("Unesite broj lokala: ");
			brLokalaS = sc.nextLine();
		} while (!isValidId(brLokalaS));
		int brLokala = Integer.parseInt(brLokalaS);
		imenik.pretragaPoLokalu(brLokala);
		
	}

	private static void brisanjeKontakta(Imenik imenik) {
		// TODO Auto-generated method stub
		int idKontakta = 0;
		String idKontaktaS = null;
		do {
			System.out.println("Unesite identifikacioni broj kontakta za brisanje: ");
			idKontaktaS = sc.nextLine();
		} while (!isValidId(idKontaktaS));
		idKontakta = Integer.valueOf(idKontaktaS);
		
		Kontakt kontakt = new Kontakt(idKontakta);
		Kontakt provera = imenik.brisanjeKontakta(kontakt);
		if(provera != null) {
			System.out.println("Brisanje je uspešno izvršeno.");
		} else {
			System.out.println("Brisanje nije uspesno izvrseno izvršeno.");
		}
	}

	private static void izmenaKontakta(Imenik imenik) {
		// TODO Auto-generated method stub
		int idKontakta = 0;
		String idKontaktaS = null;
		do {
			System.out.println("Unesite identifikacioni broj kontakta za izmenu: ");
			idKontaktaS = sc.nextLine();
		} while (!isValidId(idKontaktaS));
		idKontakta = Integer.valueOf(idKontaktaS);
		
		String ime = null;
		String prezime = null;
		String radnoMesto = null;
		String brojProstorije = null;
		
		System.out.println("Unesite ime: ");
		ime = sc.nextLine();
		
		System.out.println("Unesite prezime: ");
		prezime = sc.nextLine();
		
		System.out.println("Unesite radno mesto: ");
		radnoMesto = sc.nextLine();
		
		System.out.println("Unesite broj prostorije: ");
		brojProstorije = sc.nextLine();
		
		int brojLokala = 0;
		String brojLokalaS = null;
		do {
			System.out.println("Unesite broj lokala: ");
			brojLokalaS = sc.nextLine();
		} while (!isValidId(brojLokalaS));
		brojLokala = Integer.valueOf(brojLokalaS);
		
		LocalDate datum = null;
		String datumS = null;
		do {
			System.out.println("Unesite datum:");
			datumS = sc.nextLine();
		} while (!isValidDate(datumS));
		try {
			datum = LocalDate.parse(datumS, dtf);

		} catch (Exception e) {
			System.out.println("Doslo je do greske prilikom ucitavanja datuma.");
		}

		
		Kontakt kontakt = new Kontakt(idKontakta, ime, prezime, radnoMesto, brojProstorije, brojLokala, datum);
		Kontakt provera = imenik.izmenaKontakta(kontakt);
		if(provera != null) {
			System.out.println("Izmena je uspešno izvršena.");
		} else {
			System.out.println("Izmena je neuspešno izvršena.");
		}
	}

	private static void unosKontakta(Imenik imenik) {
		// TODO Auto-generated method stub
		
		int id = 0;
		String idS = null;
		String ime = null;
		String prezime = null;
		String radnoMesto = null;
		String brojProstorije = null;
		int brojLokala = 0;
		String brojLokalaS = null;
		LocalDate datum = null;
		String datumS = null;
		
		do {
			System.out.println("Unesite identifikacioni broj: ");
			idS = sc.nextLine();
		} while (!isValidId(idS));
		id = Integer.parseInt(idS);
		
		System.out.println("Unesite ime: ");
		ime = sc.nextLine();
		
		System.out.println("Unesite prezime: ");
		prezime = sc.nextLine();
		
		System.out.println("Unesite radno mesto: ");
		radnoMesto = sc.nextLine();
		
		System.out.println("Unesite broj prostorije: ");
		brojProstorije = sc.nextLine();
		
		do {
			System.out.println("Unesite broj lokala: ");
			brojLokalaS = sc.nextLine();
		} while (!isValidId(brojLokalaS));
		brojLokala = Integer.parseInt(brojLokalaS);
		
		do {
			System.out.println("Unesite datum: ");
			datumS = sc.nextLine();
		} while (!isValidDate(datumS));
		datum = LocalDate.parse(datumS, dtf);
		
		Kontakt kontakt = new Kontakt(id, ime, prezime, radnoMesto, brojProstorije, brojLokala, datum);
		boolean provera = imenik.dodavanjeKontakta(kontakt);
		if (provera) {
			System.out.println("Kontakt je uspesno dodat.");
		}else {
			System.out.println("Kontakt nije uspesno dodat.");
		}
			
		
		
	}

	private static boolean isValidDate(String datumS) {
		// TODO Auto-generated method stub
		try {
			LocalDate.parse(datumS, dtf);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	private static boolean isValidId(String idS) {
		// TODO Auto-generated method stub
		try {
			int id = Integer.parseInt(idS);
			if (id > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}

}