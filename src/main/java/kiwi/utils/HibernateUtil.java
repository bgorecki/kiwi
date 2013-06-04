package kiwi.utils;

import kiwi.dao.GenericDao;
import kiwi.models.*;

import org.hibernate.Session;
import org.hibernate.context.internal.ThreadLocalSessionContext;

import java.sql.Time;

public class HibernateUtil {

	public Session getSession() {
		Session session = DatabaseConnector.getInstance().getSession();
		if (!session.isOpen())
			session=newSession();

		return session;
	}

	protected Session newSession() {
		return DatabaseConnector.getInstance().getSessionFactory().openSession();
	}

	protected static int godz(int godz) {
		return godz*60*60*1000;
	}

	protected static int min(int godz) {
		return godz*60*1000;
	}

 // TODO Usunąć w późniejszej fazie
    static public void createDataForTesting() {
    	Session s = DatabaseConnector.getInstance().getSessionFactory().openSession();
    	ThreadLocalSessionContext.bind(s);
        s.beginTransaction();

	    GenericDao<DbLotniskoEntity, Integer> lotniskoDao = new GenericDao<DbLotniskoEntity, Integer>(DbLotniskoEntity.class);
	    DbLotniskoEntity balice, okecie, frnakfurt, jasionka, auh, bkk, jfk;
	    lotniskoDao.create(balice = new DbLotniskoEntity().withNazwa("Balice").withPanstwo("POLAND").withMiasto("Kraków"));
	    lotniskoDao.create(okecie = new DbLotniskoEntity().withNazwa("Okęcie").withPanstwo("POLAND").withMiasto("Warszawa"));
	    lotniskoDao.create(frnakfurt = new DbLotniskoEntity().withNazwa("Frankfurt Airport").withPanstwo("GERMANY").withMiasto("Frankfurt"));
	    lotniskoDao.create(jasionka = new DbLotniskoEntity().withNazwa("Jasionka").withPanstwo("POLAND").withMiasto("Rzeszów"));
	    lotniskoDao.create(auh = new DbLotniskoEntity().withNazwa("Abu Dhabi").withPanstwo("UEA").withMiasto("Abu Dhabi"));
	    lotniskoDao.create(bkk = new DbLotniskoEntity().withNazwa("Bangkok").withPanstwo("THAILAND").withMiasto("Bangkok"));
	    lotniskoDao.create(jfk = new DbLotniskoEntity().withNazwa("JFK").withPanstwo("usa").withMiasto("Nowy Jork"));

	    GenericDao<DbPrzewoznikEntity, Integer> przewoznikDao = new GenericDao<DbPrzewoznikEntity, Integer>(DbPrzewoznikEntity.class);
	    DbPrzewoznikEntity lot, eurolot, luftwafe, ey, pr, ua;
	    przewoznikDao.create(lot = new DbPrzewoznikEntity().withKraj("POLAND").withNazwa("LOT"));
	    przewoznikDao.create(eurolot = new DbPrzewoznikEntity().withKraj("POLAND").withNazwa("Euro LOT"));
	    przewoznikDao.create(luftwafe = new DbPrzewoznikEntity().withKraj("GERMANY").withNazwa("Luftwafe"));
	    przewoznikDao.create(ey = new DbPrzewoznikEntity().withKraj("UAE").withNazwa("Etihad"));
	    przewoznikDao.create(pr = new DbPrzewoznikEntity().withKraj("PHILIPPINES").withNazwa("Philippines Airlines"));
	    przewoznikDao.create(ua = new DbPrzewoznikEntity().withKraj("USA").withNazwa("United Airlines"));

	    GenericDao<DbSamolotEntity, Integer> samolotDao  = new GenericDao<DbSamolotEntity, Integer>(DbSamolotEntity.class);
	    DbSamolotEntity boeingLot, boeingEurolot, boeingLuftwafe, boeingUA, airbusLuftwafe, antekEurolot;
	    samolotDao.create(boeingLot = new DbSamolotEntity().withNazwa("Boeing 747").withWaga(Float.valueOf("184600")).withWielkosc(Float.valueOf("68.5")).withZuzyciePaliwa(Float.valueOf("13000")).withPrzewoznikByIdPrzew(lot));
	    samolotDao.create(boeingEurolot = new DbSamolotEntity().withNazwa("Boeing 747").withWaga(Float.valueOf("184600")).withWielkosc(Float.valueOf("68.5")).withZuzyciePaliwa(Float.valueOf("13000")).withPrzewoznikByIdPrzew(eurolot));
	    samolotDao.create(airbusLuftwafe = new DbSamolotEntity().withNazwa("Airbus A380").withWaga(Float.valueOf("276000")).withWielkosc(Float.valueOf("79.8")).withZuzyciePaliwa(Float.valueOf("15000")).withPrzewoznikByIdPrzew(luftwafe));
	    samolotDao.create(boeingLuftwafe = new DbSamolotEntity().withNazwa("Boeing 747").withWaga(Float.valueOf("184600")).withWielkosc(Float.valueOf("68.5")).withZuzyciePaliwa(Float.valueOf("13000")).withPrzewoznikByIdPrzew(luftwafe));
	    samolotDao.create(antekEurolot = new DbSamolotEntity().withNazwa("An-148").withWaga(Float.valueOf("70000")).withWielkosc(Float.valueOf("28.91")).withZuzyciePaliwa(Float.valueOf("7")).withPrzewoznikByIdPrzew(eurolot));
	    samolotDao.create(boeingUA = new DbSamolotEntity().withNazwa("Boeing 747").withWaga(Float.valueOf("184600")).withWielkosc(Float.valueOf("68.5")).withZuzyciePaliwa(Float.valueOf("13000")).withPrzewoznikByIdPrzew(ua));

	    GenericDao<DbLotEntity, Integer> lotDao = new GenericDao<DbLotEntity, Integer>(DbLotEntity.class);
	    DbLotEntity lotJB, lotOB, lotOJ, lotBF, lotFJFK, lotJJFK;
	    lotDao.create(lotJB = new DbLotEntity().withCenaStatyczna(700F).withCzasPodrozy(1).withDzienTygodnia(6).withGodzinaPrzylotu(new Time(godz(2))).withGodzinaWylotu(new Time(godz(1))).withLotniskoByPrzylot(balice).withLotniskoByWylot(jasionka).withPrzewoznikByIdPrzew(lot));
	    lotDao.create(lotOB = new DbLotEntity().withCenaStatyczna(750F).withCzasPodrozy(2).withDzienTygodnia(6).withGodzinaPrzylotu(new Time(godz(5))).withGodzinaWylotu(new Time(godz(3))).withLotniskoByPrzylot(balice).withLotniskoByWylot(okecie).withPrzewoznikByIdPrzew(eurolot));
	    lotDao.create(lotOJ = new DbLotEntity().withCenaStatyczna(850F).withCzasPodrozy(2).withDzienTygodnia(6).withGodzinaPrzylotu(new Time(godz(5))).withGodzinaWylotu(new Time(godz(3))).withLotniskoByPrzylot(jasionka).withLotniskoByWylot(okecie).withPrzewoznikByIdPrzew(eurolot));
	    lotDao.create(lotBF = new DbLotEntity().withCenaStatyczna(70F).withCzasPodrozy(3).withDzienTygodnia(6).withGodzinaPrzylotu(new Time(godz(9))).withGodzinaWylotu(new Time(godz(6))).withLotniskoByPrzylot(frnakfurt).withLotniskoByWylot(balice).withPrzewoznikByIdPrzew(luftwafe));
	    lotDao.create(lotFJFK = new DbLotEntity().withCenaStatyczna(150F).withCzasPodrozy(9).withDzienTygodnia(6).withGodzinaPrzylotu(new Time(godz(20))).withGodzinaWylotu(new Time(godz(11))).withLotniskoByPrzylot(jfk).withLotniskoByWylot(frnakfurt).withPrzewoznikByIdPrzew(ua));
	    lotDao.create(lotJJFK = new DbLotEntity().withCenaStatyczna(950F).withCzasPodrozy(9).withDzienTygodnia(6).withGodzinaPrzylotu(new Time(godz(15))).withGodzinaWylotu(new Time(godz(6))).withLotniskoByPrzylot(jfk).withLotniskoByWylot(jasionka).withPrzewoznikByIdPrzew(luftwafe));

	    GenericDao<DbKlasaEntity, Integer> klasaDao = new GenericDao<DbKlasaEntity, Integer>(DbKlasaEntity.class);
	    DbKlasaEntity k1, k2, k3, k4;
	    klasaDao.create(k1 = new DbKlasaEntity().withNazwa("Economy"));
	    klasaDao.create(k2 = new DbKlasaEntity().withNazwa("Comfort"));
	    klasaDao.create(k3 = new DbKlasaEntity().withNazwa("Business"));
	    klasaDao.create(k4 = new DbKlasaEntity().withNazwa("Diamond First"));

	    GenericDao<DbPracownikEntity, Integer> pracownikDao = new GenericDao<DbPracownikEntity, Integer>(DbPracownikEntity.class);
	    DbPracownikEntity p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14;
	    pracownikDao.create(p1 = new DbPracownikEntity().withNazwisko("Górecki").withImie("Bartosz").withPensja(500F).withPrzewoznikByIdPrzew(lot).withStanowisko("Pilot"));
	    pracownikDao.create(p2 = new DbPracownikEntity().withNazwisko("Górecka").withImie("Bartoszyca").withPensja(1000F).withPrzewoznikByIdPrzew(lot).withStanowisko("Pilot"));
	    pracownikDao.create(p3 = new DbPracownikEntity().withNazwisko("Pery").withImie("Katy").withPensja(500F).withPrzewoznikByIdPrzew(lot).withStanowisko("Stewardesa"));
	    pracownikDao.create(p4 = new DbPracownikEntity().withNazwisko("Spears").withImie("Britney").withPensja(1000F).withPrzewoznikByIdPrzew(lot).withStanowisko("Stewardesa"));

	    pracownikDao.create(p5 = new DbPracownikEntity().withNazwisko("Mozil").withImie("Czesław").withPensja(1000F).withPrzewoznikByIdPrzew(luftwafe).withStanowisko("Pilot"));
	    pracownikDao.create(p6 = new DbPracownikEntity().withNazwisko("Mozila").withImie("Czesława").withPensja(1000F).withPrzewoznikByIdPrzew(luftwafe).withStanowisko("Pilot"));
	    pracownikDao.create(p7 = new DbPracownikEntity().withNazwisko("Tymbark").withImie("Britney").withPensja(500F).withPrzewoznikByIdPrzew(luftwafe).withStanowisko("Stewardesa"));
	    pracownikDao.create(p8 = new DbPracownikEntity().withNazwisko("Nescafe").withImie("Britney").withPensja(500F).withPrzewoznikByIdPrzew(luftwafe).withStanowisko("Stewardesa"));

	    pracownikDao.create(p9 = new DbPracownikEntity().withNazwisko("Pilot").withImie("Jeden").withPensja(2000F).withPrzewoznikByIdPrzew(eurolot).withStanowisko("Pilot"));
	    pracownikDao.create(p10 = new DbPracownikEntity().withNazwisko("Pilot").withImie("Dwa").withPensja(2000F).withPrzewoznikByIdPrzew(eurolot).withStanowisko("Pilot"));
	    pracownikDao.create(p11 = new DbPracownikEntity().withNazwisko("NiePilot").withImie("Dwa").withPensja(2000F).withPrzewoznikByIdPrzew(eurolot).withStanowisko("Stewardesa"));
	    pracownikDao.create(p12 = new DbPracownikEntity().withNazwisko("TezNiePilot").withImie("Dwa").withPensja(2000F).withPrzewoznikByIdPrzew(eurolot).withStanowisko("Stewardesa"));

	    pracownikDao.create(p13 = new DbPracownikEntity().withNazwisko("Pilot").withImie("Ci").withPensja(2500F).withPrzewoznikByIdPrzew(ua).withStanowisko("Pilot"));
	    pracownikDao.create(p14 = new DbPracownikEntity().withNazwisko("Eeee Liiii").withImie("Kung Fu").withPensja(2500F).withPrzewoznikByIdPrzew(ua).withStanowisko("Stewardesa"));

	    GenericDao<DbLspEntity, Integer> lspDao = new GenericDao<DbLspEntity, Integer>(DbLspEntity.class);
	    lspDao.create(new DbLspEntity().withLotByIdLot(lotJB).withPracownikByIdPrac(p1).withSamolotByIdSam(boeingLot));
	    lspDao.create(new DbLspEntity().withLotByIdLot(lotJB).withPracownikByIdPrac(p2).withSamolotByIdSam(boeingLot));
	    lspDao.create(new DbLspEntity().withLotByIdLot(lotJB).withPracownikByIdPrac(p3).withSamolotByIdSam(boeingLot));
	    lspDao.create(new DbLspEntity().withLotByIdLot(lotJB).withPracownikByIdPrac(p4).withSamolotByIdSam(boeingLot));

	    lspDao.create(new DbLspEntity().withLotByIdLot(lotBF).withPracownikByIdPrac(p5).withSamolotByIdSam(boeingLuftwafe));
	    lspDao.create(new DbLspEntity().withLotByIdLot(lotBF).withPracownikByIdPrac(p7).withSamolotByIdSam(boeingLuftwafe));
	    lspDao.create(new DbLspEntity().withLotByIdLot(lotJJFK).withPracownikByIdPrac(p6).withSamolotByIdSam(airbusLuftwafe));
	    lspDao.create(new DbLspEntity().withLotByIdLot(lotJJFK).withPracownikByIdPrac(p8).withSamolotByIdSam(airbusLuftwafe));

	    lspDao.create(new DbLspEntity().withLotByIdLot(lotOB).withPracownikByIdPrac(p9).withSamolotByIdSam(antekEurolot));
	    lspDao.create(new DbLspEntity().withLotByIdLot(lotOB).withPracownikByIdPrac(p10).withSamolotByIdSam(antekEurolot));

	    lspDao.create(new DbLspEntity().withLotByIdLot(lotOJ).withPracownikByIdPrac(p11).withSamolotByIdSam(boeingEurolot));
	    lspDao.create(new DbLspEntity().withLotByIdLot(lotOJ).withPracownikByIdPrac(p12).withSamolotByIdSam(boeingEurolot));

	    lspDao.create(new DbLspEntity().withLotByIdLot(lotFJFK).withPracownikByIdPrac(p13).withSamolotByIdSam(boeingUA));
	    lspDao.create(new DbLspEntity().withLotByIdLot(lotFJFK).withPracownikByIdPrac(p14).withSamolotByIdSam(boeingUA));

	    GenericDao<DbMiejscaEntity, Integer> miejscaDao = new GenericDao<DbMiejscaEntity, Integer>(DbMiejscaEntity.class);
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k4).withSamolotByIdSam(boeingLot));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k3).withSamolotByIdSam(boeingLot));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k2).withSamolotByIdSam(boeingLot));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k1).withSamolotByIdSam(boeingLot));

	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k4).withSamolotByIdSam(boeingEurolot));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k3).withSamolotByIdSam(boeingEurolot));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k2).withSamolotByIdSam(boeingEurolot));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k1).withSamolotByIdSam(boeingEurolot));

	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k4).withSamolotByIdSam(boeingLuftwafe));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k3).withSamolotByIdSam(boeingLuftwafe));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k2).withSamolotByIdSam(boeingLuftwafe));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k1).withSamolotByIdSam(boeingLuftwafe));

	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k4).withSamolotByIdSam(boeingUA));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k3).withSamolotByIdSam(boeingUA));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k2).withSamolotByIdSam(boeingUA));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k1).withSamolotByIdSam(boeingUA));

	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k4).withSamolotByIdSam(airbusLuftwafe));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k3).withSamolotByIdSam(airbusLuftwafe));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k2).withSamolotByIdSam(airbusLuftwafe));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k1).withSamolotByIdSam(airbusLuftwafe));

	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k4).withSamolotByIdSam(antekEurolot));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k3).withSamolotByIdSam(antekEurolot));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k2).withSamolotByIdSam(antekEurolot));
	    miejscaDao.create(new DbMiejscaEntity().withIlosc(10).withKlasaByIdKlas(k1).withSamolotByIdSam(antekEurolot));

	    GenericDao<DbModyfikatorEntity, Integer> modyfikatorDao = new GenericDao<>(DbModyfikatorEntity.class);
	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k1).withDziecko(0).withWartoscMod(0F).withPrzewoznikByIdPrzew(lot));
	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k2).withDziecko(100).withWartoscMod(50F).withPrzewoznikByIdPrzew(lot));
	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k3).withDziecko(200).withWartoscMod(100F).withPrzewoznikByIdPrzew(lot));
	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k4).withDziecko(400).withWartoscMod(200F).withPrzewoznikByIdPrzew(lot));

	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k1).withDziecko(0).withWartoscMod(0F).withPrzewoznikByIdPrzew(eurolot));
	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k2).withDziecko(100).withWartoscMod(50F).withPrzewoznikByIdPrzew(eurolot));
	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k3).withDziecko(200).withWartoscMod(100F).withPrzewoznikByIdPrzew(eurolot));
	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k4).withDziecko(400).withWartoscMod(200F).withPrzewoznikByIdPrzew(eurolot));

	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k1).withDziecko(0).withWartoscMod(0F).withPrzewoznikByIdPrzew(luftwafe));
	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k2).withDziecko(100).withWartoscMod(50F).withPrzewoznikByIdPrzew(luftwafe));
	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k3).withDziecko(200).withWartoscMod(100F).withPrzewoznikByIdPrzew(luftwafe));
	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k4).withDziecko(400).withWartoscMod(200F).withPrzewoznikByIdPrzew(luftwafe));

	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k1).withDziecko(0).withWartoscMod(0F).withPrzewoznikByIdPrzew(ua));
	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k2).withDziecko(100).withWartoscMod(50F).withPrzewoznikByIdPrzew(ua));
	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k3).withDziecko(200).withWartoscMod(100F).withPrzewoznikByIdPrzew(ua));
	    modyfikatorDao.create(new DbModyfikatorEntity().withKlasaByIdKlas(k4).withDziecko(400).withWartoscMod(200F).withPrzewoznikByIdPrzew(ua));

	    GenericDao<DbUzytkownikEntity, Integer> uzytkownikDao = new GenericDao<DbUzytkownikEntity, Integer>(DbUzytkownikEntity.class);
	    DbUzytkownikEntity uzytkownik = new DbUzytkownikEntity();
	    uzytkownik.setLogin("admin1"); uzytkownik.setHaslo("admin1"); uzytkownik.setRola(DbUzytkownikEntity.ADMIN_ROLE);
	    uzytkownikDao.create(uzytkownik);
	    uzytkownik = new DbUzytkownikEntity();
	    uzytkownik.setLogin("jan kowalski"); uzytkownik.setHaslo("123"); uzytkownik.setRola(DbUzytkownikEntity.PRZEWOZNIK_ROLE);
	    uzytkownik.setPrzewoznikByIdPrzewoznika(lot);
	    uzytkownikDao.create(uzytkownik);
	    
		ThreadLocalSessionContext.unbind(DatabaseConnector.getInstance().getSessionFactory());
		s.getTransaction().commit();
    }
}
