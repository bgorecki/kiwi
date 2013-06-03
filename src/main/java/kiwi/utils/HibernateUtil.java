package kiwi.utils;

import kiwi.dao.GenericDao;
import kiwi.models.DbLotniskoEntity;
import kiwi.models.DbPrzewoznikEntity;
import kiwi.models.DbUzytkownikEntity;

import org.hibernate.Session;
import org.hibernate.context.internal.ThreadLocalSessionContext;

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

 // TODO Usunąć w późniejszej fazie
    static public void createDataForTesting() {
    	Session s = DatabaseConnector.getInstance().getSessionFactory().openSession();
    	ThreadLocalSessionContext.bind(s);
        s.beginTransaction();

	    GenericDao<DbLotniskoEntity, Integer> lotniskoDao = new GenericDao<DbLotniskoEntity, Integer>(DbLotniskoEntity.class);
	    DbLotniskoEntity lotnisko;
	    lotniskoDao.create(lotnisko = new DbLotniskoEntity().withNazwa("Balice").withPanstwo("POLAND").withMiasto("Kraków"));
	    lotniskoDao.create(new DbLotniskoEntity().withNazwa("Okęcie").withPanstwo("POLAND").withMiasto("Warszawa"));
	    lotniskoDao.create(new DbLotniskoEntity().withNazwa("Frankfurt Airport").withPanstwo("GERMANY").withMiasto("Frankfurt"));
	    lotniskoDao.create(new DbLotniskoEntity().withNazwa("Jasionka").withPanstwo("POLAND").withMiasto("Rzeszów"));
	    lotniskoDao.create(new DbLotniskoEntity().withNazwa("Abu Dhabi").withPanstwo("UEA").withMiasto("Abu Dhabi"));
	    lotniskoDao.create(new DbLotniskoEntity().withNazwa("Bangkok").withPanstwo("THAILAND").withMiasto("Bangkok"));

	    GenericDao<DbPrzewoznikEntity, Integer> przewoznikDao = new GenericDao<DbPrzewoznikEntity, Integer>(DbPrzewoznikEntity.class);
	    DbPrzewoznikEntity przewoznik;
	    przewoznikDao.create(przewoznik = new DbPrzewoznikEntity().withKraj("POLAND").withNazwa("LOT"));
	    przewoznikDao.create(new DbPrzewoznikEntity().withKraj("POLAND").withNazwa("Euro LOT"));
	    przewoznikDao.create(new DbPrzewoznikEntity().withKraj("GERMANY").withNazwa("Luftwafe"));
	    przewoznikDao.create(new DbPrzewoznikEntity().withKraj("UAE").withNazwa("Etihad"));
	    przewoznikDao.create(new DbPrzewoznikEntity().withKraj("PHILIPPINES").withNazwa("Philippines Airlines"));
	    przewoznikDao.create(new DbPrzewoznikEntity().withKraj("USA").withNazwa("United Airlines"));

	    GenericDao<DbUzytkownikEntity, Integer> uzytkownikDao = new GenericDao<DbUzytkownikEntity, Integer>(DbUzytkownikEntity.class);
	    DbUzytkownikEntity uzytkownik = new DbUzytkownikEntity();
	    uzytkownik.setLogin("admin1"); uzytkownik.setHaslo("admin1"); uzytkownik.setRola(DbUzytkownikEntity.ADMIN_ROLE);
	    uzytkownikDao.create(uzytkownik);
	    uzytkownik = new DbUzytkownikEntity();
	    uzytkownik.setLogin("jan kowalski"); uzytkownik.setHaslo("123"); uzytkownik.setRola(DbUzytkownikEntity.PRZEWOZNIK_ROLE);
	    uzytkownik.setPrzewoznikByIdPrzewoznika(przewoznik);
	    uzytkownikDao.create(uzytkownik);
	    
		ThreadLocalSessionContext.unbind(DatabaseConnector.getInstance().getSessionFactory());
		s.getTransaction().commit();
    }
}
