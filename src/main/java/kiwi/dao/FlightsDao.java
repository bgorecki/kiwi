package kiwi.dao;

import com.google.common.collect.Iterables;
import kiwi.dijkstra.Finder;
import kiwi.dijkstra.Vertex;
import kiwi.models.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: scroot
 * Date: 02.06.13
 * Time: 18:25
 */
public class FlightsDao extends GenericDao<DbLotEntity, Integer>
{

	public FlightsDao()
	{
		super(DbLotEntity.class);
	}

	@SuppressWarnings("unchecked")
	public List<DbLotEntity> getAll()
	{
		return getSession().createQuery("from DbLotEntity").list();
	}

	public List<DbLotEntity> getAllByUser(DbUzytkownikEntity user)
	{

		List<DbLotEntity> flights = (List<DbLotEntity>) getSession().createQuery("select lot from kiwi.models.DbLotEntity lot join lot.przewoznikByIdPrzew p join p.uzytkowniksByIdPrzewoznika where p = :prz").setParameter("prz", user.getPrzewoznikByIdPrzewoznika()).list();
		return flights;
	}

	public List<List<DbLotEntity>> findFlightsByAttributes(SearchForm sf)
	{
		Date result = null;
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime date = DateTime.parse(sf.getData(), fmt);

		Finder finder = new Finder(getAllinDay(date));

		List<Vertex> path = finder.Find(sf.getFromLotnisko(), sf.getToLotnisko());
		if (path == null) return null;

		List<List<Vertex>> paths = new ArrayList<List<Vertex>>();
		paths.add(path);


		List<List<DbLotEntity>> loty = new ArrayList<List<DbLotEntity>>();
		List<DbLotEntity> lot = new ArrayList<DbLotEntity>();
		for (Integer j = path.size() - 2; j >= 0; j--)
		{
			lot.add(path.get(j).getEdge().getLot());
		}
		loty.add(lot);

		if (path == null)
		{
			return null;
		} else
		{
			for (int i = path.size() - 2; i >= 0; i--)
			{
				finder.reset();
				finder.removeEdge(path.get(i + 1), path.get(i));
				List<Vertex> path2 = finder.Find(sf.getFromLotnisko(), sf.getToLotnisko());
				if (path2 != null && !paths.contains(path2))
				{
					paths.add(path2);

					lot = new ArrayList<DbLotEntity>();
					for (Integer j = path2.size() - 2; j >= 0; j--)
					{
						lot.add(path2.get(j).getEdge().getLot());
					}
					loty.add(lot);
				}
				finder.restore();
			}

			if (paths.size() > 1)
			{
				for (int j = path.size() - 2; j >= 0; j--)
				{
					finder.removeEdge(path.get(j + 1), path.get(j));
					List<Vertex> path3 = paths.get(1);
					for (int i = path3.size() - 2; i >= 0; i--)
					{
						finder.reset();
						finder.removeEdge(path3.get(i + 1), path3.get(i));
						List<Vertex> path2 = finder.Find(sf.getFromLotnisko(), sf.getToLotnisko());
						if (path2 != null && !paths.contains(path2))
						{
							paths.add(path2);
							lot = new ArrayList<DbLotEntity>();
							for (Integer i1 = path2.size() - 2; i1 >= 0; i1--)
							{
								lot.add(path2.get(i1).getEdge().getLot());
							}
							loty.add(lot);
						}
						finder.restore();
					}

				}
			}
		}


		return loty;
	}

	@SuppressWarnings("unchecked")
	private List<DbLotEntity> getAllinDay(DateTime date)
	{
		return getSession().createQuery("from DbLotEntity where dzienTygodnia = :dzien").setParameter("dzien", date.getDayOfWeek()).list();
	}

	public DbLotEntity findFlightsByAirportNames(SearchForm sf)
	{
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime date = DateTime.parse(sf.getData(), fmt);

		return Iterables.<DbLotEntity>getFirst(getSession().createQuery("from DbLotEntity where lotniskoByPrzylot = :lotniskoByPrzylot and lotniskoByWylot = :lotniskoByWylot and dzienTygodnia = : dzien").setParameter("dzien", date.getDayOfWeek()).setParameter("lotniskoByPrzylot", sf.getToLotnisko()).setParameter("lotniskoByWylot", sf.getFromLotnisko()).list(), null);
	}

	public Integer getFreeSeatsCountInClass(DbLotEntity lot, DbKlasaEntity klasa)
	{
		Integer zajete = ((Long) getSession().createQuery("select count(*) from DbRekordyLotuEntity where lotByIdLot = :lot and klasaByIdKlas = :klasa")
				                         .setParameter("lot", lot).setParameter("klasa", klasa).uniqueResult()).intValue();

		Integer posiadane = (Integer) getSession().createQuery("select m.ilosc from DbMiejscaEntity m join m.samolotByIdSam s join s.lspsByIdSamolotu lsp where lsp.samolotByIdSam = s and lsp.lotByIdLot = :lot and m.klasaByIdKlas = :klasa")
				                              .setParameter("klasa", klasa).setParameter("lot", lot).uniqueResult();
        if(posiadane == null) posiadane = 0;
        if(zajete == null) zajete = 0;
		return posiadane - zajete;
	}

	@SuppressWarnings("unchecked")
	public Integer getSeatsCount(DbLotEntity lot)
	{
		Integer count = 0;
		for (Integer i : (List<Integer>) getSession().createQuery("select m.ilosc from DbMiejscaEntity m join m.samolotByIdSam s join s.lspsByIdSamolotu lsp where lsp.samolotByIdSam = s and lsp.lotByIdLot = :lot group by m.idMiejsca").setParameter("lot", lot).list())
		{
			count += i.intValue();
		}

		return count;
	}

	public boolean isFullyFilled(DbLotEntity flight)
	{
		if (flight.getCenaStatyczna() == null || flight.getCenaStatyczna().equals(""))
			return false;
		if (flight.getCzasPodrozy() == null || flight.getCzasPodrozy().equals(""))
			return false;
		if (flight.getDzienTygodnia() == null || flight.getDzienTygodnia().equals(""))
			return false;
		if (flight.getGodzinaPrzylotu() == null || flight.getGodzinaPrzylotu().equals(""))
			return false;
		if (flight.getGodzinaWylotu() == null || flight.getGodzinaWylotu().equals(""))
			return false;
		if (flight.getLotniskoByWylot() == null || flight.getLotniskoByWylot().equals(""))
			return false;
		if (flight.getLotniskoByPrzylot() == null || flight.getLotniskoByPrzylot().equals(""))
			return false;
		if (flight.getPrzewoznikByIdPrzew() == null || flight.getPrzewoznikByIdPrzew().equals(""))
			return false;
		return true;
	}

	public Integer getReservationsCountForDate(DbLotEntity lot, java.sql.Date date)
	{
		return ((Long) getSession().createQuery("select count(rekordy)from DbRekordyLotuEntity rekordy where dataWylotu = :data and rekordy.lotByIdLot = :lot").setParameter("data", date).setParameter("lot", lot).uniqueResult()).intValue();
	}

	public DateTime getErliestFlight(DbLotEntity flight)
	{
		return new DateTime((Date) getSession().createQuery("select rekordy.dataWylotu from DbRekordyLotuEntity rekordy where dataWylotu = min(dataWylotu) and rekordy.lotByIdLot = :lot").setParameter("lot", flight).uniqueResult());
	}

	@SuppressWarnings("unchecked")
	public Float[] getPrices(DbLotEntity lot, DbKlasaEntity klasa)
	{
		Float[] prices = new Float[2];
		DbModyfikatorEntity mod = Iterables.<DbModyfikatorEntity>getFirst(getSession().createQuery("select modyfikator from DbModyfikatorEntity modyfikator join modyfikator.klasaByIdKlas klasa join klasa.miejscasByIdKlasy miejsca join miejsca.samolotByIdSam sam join sam.lspsByIdSamolotu lsp join lsp.lotByIdLot where modyfikator.klasaByIdKlas = :klasa and lsp.lotByIdLot = :lot").setParameter("klasa", klasa).setParameter("lot", lot).list(), null);
		System.out.println("mod=" + mod + " lot=" + lot + "klasa=" + klasa);
		prices[0] = lot.getCenaStatyczna() + mod.getWartoscMod();
		prices[1] = lot.getCenaStatyczna() + mod.getDziecko();

		return prices;
	}

	public Float getPrice(DbLotEntity lot, SearchForm sf)
	{
		Float[] prices = getPrices(lot, sf.getKlasaDb());
		Float price = sf.getIlosc() * prices[0] + sf.getIlosc_dz() * prices[1] + sf.getIlosc_inf() * prices[1];
		return price;
	}

	public Float getFlightPrice(List<DbLotEntity> lot, SearchForm sf)
	{

		Float price = 0F;
		for (DbLotEntity i : lot)
		{
			price += getPrice(i, sf) * (1F - .05F * lot.size());
		}
		return price;
	}

	public DbLotEntity getById(int id)
	{
		return Iterables.<DbLotEntity>getFirst(getSession().createQuery("from DbLotEntity where idLotu = :id").setParameter("id", id).list(), null);
	}

	public void remove(DbLotEntity lot) {
		//lot.getPrzewoznikByIdPrzew().getLotsByIdPrzewoznika().remove(lot);
		getSession().beginTransaction();
		delete(lot);
		getSession().getTransaction().commit();
	}
}
