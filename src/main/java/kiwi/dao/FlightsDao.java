package kiwi.dao;

import kiwi.controller.Search;
import kiwi.models.DbLotEntity;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public List<DbLotEntity> getAll() {
		return getSession().createQuery("from DbLotEntity").list();
	}

	public List<DbLotEntity> findFlightsByAttributes(Search.SearchForm sf)
	{
		Date result = null;
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime date = DateTime.parse(sf.getData(), fmt);



		return null;
	}
}
