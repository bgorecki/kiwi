package kiwi.dao;

import com.google.common.collect.Iterables;
import kiwi.controller.Search;
import kiwi.dijkstra.Finder;
import kiwi.dijkstra.Vertex;
import kiwi.models.DbKlasaEntity;
import kiwi.models.DbLotEntity;
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

	public List<List<DbLotEntity>> findFlightsByAttributes(Search.SearchForm sf)
	{
		Date result = null;
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime date = DateTime.parse(sf.getData(), fmt);

		Finder finder = new Finder(getAll());

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

	public DbLotEntity findFlightsByAirportNames(Search.SearchForm sf)
	{
		return Iterables.<DbLotEntity>getFirst(getSession().createQuery("from DbLotEntity where lotniskoByPrzylot = :lotniskoByPrzylot and lotniskoByWylot = :lotniskoByWylot").setParameter("lotniskoByPrzylot", sf.getToLotnisko()).setParameter("lotniskoByWylot", sf.getFromLotnisko()).list(), null);
	}

	public Integer getFreeSeatsCount(DbLotEntity lot, DbKlasaEntity klasa)
	{
		Integer zajete = ((Long) getSession().createQuery("select count(*) from DbRekordyLotuEntity where lotByIdLot = :lot and klasaByIdKlas = :klasa")
				                 .setParameter("lot", lot).setParameter("klasa", klasa).uniqueResult()).intValue();

		Integer posiadane = (Integer) getSession().createQuery("select m.ilosc from DbMiejscaEntity m join m.samolotByIdSam s join s.lspsByIdSamolotu lsp where lsp.samolotByIdSam = s and lsp.lotByIdLot = :lot and m.klasaByIdKlas = :klasa")
				                              .setParameter("klasa", klasa).setParameter("lot", lot).uniqueResult();

		return posiadane-zajete;
	}
}
