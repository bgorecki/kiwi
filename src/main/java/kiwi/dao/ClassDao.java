package kiwi.dao;

import kiwi.models.DbKlasaEntity;

import java.util.List;

/**
 * User: scroot
 * Date: 02.06.13
 * Time: 19:59
 */
public class ClassDao extends GenericDao<DbKlasaEntity, Integer>
{

	public ClassDao()
	{
		super(DbKlasaEntity.class);
	}

	@SuppressWarnings("unchecked")
	public List<DbKlasaEntity> getAll() {
		return getSession().createQuery("from DbKlasaEntity").list();
	}
}
