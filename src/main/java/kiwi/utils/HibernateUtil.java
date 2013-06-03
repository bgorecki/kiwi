package kiwi.utils;

import kiwi.dao.GenericDao;
import kiwi.models.DbKlasaEntity;
import kiwi.models.DbLotEntity;
import kiwi.models.DbLotniskoEntity;
import kiwi.models.DbPrzewoznikEntity;
import kiwi.models.DbSamolotEntity;
import kiwi.models.DbUzytkownikEntity;

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

	    GenericDao<DbSamolotEntity, Integer> samolotDao  = new GenericDao<DbSamolotEntity, Integer>(DbSamolotEntity.class);
	    samolotDao.create(new DbSamolotEntity().withNazwa("Boeing 747").withWaga(Float.valueOf("184600")).withWielkosc(Float.valueOf("68.5")).withZuzyciePaliwa(Float.valueOf("13000")).withPrzewoznikByIdPrzew(new DbPrzewoznikEntity().withKraj("POLAND").withNazwa("LOT")));
	    samolotDao.create(new DbSamolotEntity().withNazwa("Airbus A380").withWaga(Float.valueOf("276000")).withWielkosc(Float.valueOf("79.8")).withZuzyciePaliwa(Float.valueOf("15000")).withPrzewoznikByIdPrzew(new DbPrzewoznikEntity().withKraj("POLAND").withNazwa("LOT")));
	    samolotDao.create(new DbSamolotEntity().withNazwa("An-148").withWaga(Float.valueOf("70000")).withWielkosc(Float.valueOf("28.91")).withZuzyciePaliwa(Float.valueOf("7")).withPrzewoznikByIdPrzew(new DbPrzewoznikEntity().withKraj("POLAND").withNazwa("LOT")));

	    
	    GenericDao<DbPrzewoznikEntity, Integer> przewoznikDao = new GenericDao<DbPrzewoznikEntity, Integer>(DbPrzewoznikEntity.class);
	    DbPrzewoznikEntity lot, eurolot, luftwafe, ey, pr, ua;
	    przewoznikDao.create(lot = new DbPrzewoznikEntity().withKraj("POLAND").withNazwa("LOT"));
	    przewoznikDao.create(eurolot = new DbPrzewoznikEntity().withKraj("POLAND").withNazwa("Euro LOT"));
	    przewoznikDao.create(luftwafe = new DbPrzewoznikEntity().withKraj("GERMANY").withNazwa("Luftwafe"));
	    przewoznikDao.create(ey = new DbPrzewoznikEntity().withKraj("UAE").withNazwa("Etihad"));
	    przewoznikDao.create(pr = new DbPrzewoznikEntity().withKraj("PHILIPPINES").withNazwa("Philippines Airlines"));
	    przewoznikDao.create(ua = new DbPrzewoznikEntity().withKraj("USA").withNazwa("United Airlines"));

	    GenericDao<DbLotEntity, Integer> lotDao = new GenericDao<DbLotEntity, Integer>(DbLotEntity.class);
	    lotDao.create(new DbLotEntity().withCenaStatyczna(750F).withCzasPodrozy(1).withDzienTygodnia(6).withGodzinaPrzylotu(new Time(godz(1))).withGodzinaWylotu(new Time(godz(2))).withLotniskoByPrzylot(balice).withLotniskoByWylot(jasionka).withPrzewoznikByIdPrzew(lot));
	    lotDao.create(new DbLotEntity().withCenaStatyczna(750F).withCzasPodrozy(2).withDzienTygodnia(6).withGodzinaPrzylotu(new Time(godz(3))).withGodzinaWylotu(new Time(godz(5))).withLotniskoByPrzylot(balice).withLotniskoByWylot(okecie).withPrzewoznikByIdPrzew(eurolot));
	    lotDao.create(new DbLotEntity().withCenaStatyczna(750F).withCzasPodrozy(3).withDzienTygodnia(6).withGodzinaPrzylotu(new Time(godz(6))).withGodzinaWylotu(new Time(godz(9))).withLotniskoByPrzylot(frnakfurt).withLotniskoByWylot(balice).withPrzewoznikByIdPrzew(luftwafe));
	    lotDao.create(new DbLotEntity().withCenaStatyczna(750F).withCzasPodrozy(9).withDzienTygodnia(6).withGodzinaPrzylotu(new Time(godz(11))).withGodzinaWylotu(new Time(godz(20))).withLotniskoByPrzylot(jfk).withLotniskoByWylot(frnakfurt).withPrzewoznikByIdPrzew(ua));
	    lotDao.create(new DbLotEntity().withCenaStatyczna(750F).withCzasPodrozy(9).withDzienTygodnia(6).withGodzinaPrzylotu(new Time(godz(9))).withGodzinaWylotu(new Time(godz(20))).withLotniskoByPrzylot(jfk).withLotniskoByWylot(jasionka).withPrzewoznikByIdPrzew(luftwafe));

	    GenericDao<DbKlasaEntity, Integer> klasaDao = new GenericDao<DbKlasaEntity, Integer>(DbKlasaEntity.class);
	    klasaDao.create(new DbKlasaEntity().withNazwa("Economy"));
	    klasaDao.create(new DbKlasaEntity().withNazwa("Economy Plus"));
	    klasaDao.create(new DbKlasaEntity().withNazwa("Comfort"));
	    klasaDao.create(new DbKlasaEntity().withNazwa("Business"));
	    klasaDao.create(new DbKlasaEntity().withNazwa("Business Premium"));
	    klasaDao.create(new DbKlasaEntity().withNazwa("Diamond First"));

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
