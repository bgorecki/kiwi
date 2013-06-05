package kiwi.dao;

import java.util.List;

import kiwi.models.DbLspEntity;

public class DbLSPDao extends GenericDao<DbLspEntity, Integer> {

	public DbLSPDao() {
		super(DbLspEntity.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<DbLspEntity> getAll() {
		List<DbLspEntity> lsp = (List<DbLspEntity>)getSession().createQuery("from kiwi.models.DbLspEntity").list();
    	return lsp;
	}
	
	
}
