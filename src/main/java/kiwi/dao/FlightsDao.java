package kiwi.dao;

import kiwi.models.DbLotEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: scroot
 * Date: 02.06.13
 * Time: 18:25
 * To change this template use File | Settings | File Templates.
 */
public class FlightsDao extends GenericDao<DbLotEntity, Integer>
{

	public FlightsDao()
	{
		super(DbLotEntity.class);
	}

	public List<DbLotEntity> getAll() {
		return getSession().createQuery("from DbLotEntity").list();
	}
}
