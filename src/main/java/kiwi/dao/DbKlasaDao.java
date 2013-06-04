package kiwi.dao;

import com.google.common.collect.Iterables;
import kiwi.models.DbKlasaEntity;

/**
 * User: scroot
 * Date: 04.06.13
 * Time: 22:04
 */
public class DbKlasaDao extends GenericDao<DbKlasaEntity, Integer>
{

	public DbKlasaDao()
	{
		super(DbKlasaEntity.class);
	}


	public DbKlasaEntity getById(Integer klasa)
	{
		return Iterables.<DbKlasaEntity>getFirst(getSession().createQuery("from DbKlasaEntity where idKlasy = :id").setParameter("id", klasa).list(), null);
	}
}
