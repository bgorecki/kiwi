package kiwi.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: scroot
 * Date: 02.06.13
 * Time: 09:22
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "lotnisko", schema = "", catalog = "kiwi")
@Entity
public class DbLotniskoEntity
{

	private Integer idLotniska;

	@javax.persistence.Column(name = "id_lotniska", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Id
	@GeneratedValue
	public Integer getIdLotniska()
	{
		return idLotniska;
	}

	public void setIdLotniska(Integer idLotniska)
	{
		this.idLotniska = idLotniska;
	}

	private String panstwo;

	@javax.persistence.Column(name = "panstwo", nullable = false, insertable = true, updatable = true, length = 250, precision = 0)
	@Basic
	public String getPanstwo()
	{
		return panstwo;
	}

	public void setPanstwo(String panstwo)
	{
		this.panstwo = panstwo;
	}

	private String miasto;

	@javax.persistence.Column(name = "miasto", nullable = false, insertable = true, updatable = true, length = 250, precision = 0)
	@Basic
	public String getMiasto()
	{
		return miasto;
	}

	public void setMiasto(String miasto)
	{
		this.miasto = miasto;
	}

	private String nazwa;

	@javax.persistence.Column(name = "nazwa", nullable = false, insertable = true, updatable = true, length = 250, precision = 0)
	@Basic
	public String getNazwa()
	{
		return nazwa;
	}

	public void setNazwa(String nazwa)
	{
		this.nazwa = nazwa;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DbLotniskoEntity that = (DbLotniskoEntity) o;

		if (idLotniska != null ? !idLotniska.equals(that.idLotniska) : that.idLotniska != null) return false;
		if (miasto != null ? !miasto.equals(that.miasto) : that.miasto != null) return false;
		if (nazwa != null ? !nazwa.equals(that.nazwa) : that.nazwa != null) return false;
		if (panstwo != null ? !panstwo.equals(that.panstwo) : that.panstwo != null) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = idLotniska != null ? idLotniska.hashCode() : 0;
		result = 31 * result + (panstwo != null ? panstwo.hashCode() : 0);
		result = 31 * result + (miasto != null ? miasto.hashCode() : 0);
		result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
		return result;
	}

	private Collection<DbLotEntity> lotsByIdLotniska;

	@OneToMany(mappedBy = "lotniskoByWylot",cascade=CascadeType.ALL)
	public Collection<DbLotEntity> getLotsByIdLotniska()
	{
		return lotsByIdLotniska;
	}

	public void setLotsByIdLotniska(Collection<DbLotEntity> lotsByIdLotniska)
	{
		this.lotsByIdLotniska = lotsByIdLotniska;
	}

	private Collection<DbLotEntity> lotsByIdLotniska_0;

	@OneToMany(mappedBy = "lotniskoByPrzylot",cascade=CascadeType.ALL)
	public Collection<DbLotEntity> getLotsByIdLotniska_0()
	{
		return lotsByIdLotniska_0;
	}

	public void setLotsByIdLotniska_0(Collection<DbLotEntity> lotsByIdLotniska_0)
	{
		this.lotsByIdLotniska_0 = lotsByIdLotniska_0;
	}

	public DbLotniskoEntity withIdLotniska(final Integer idLotniska)
	{
		this.idLotniska = idLotniska;
		return this;
	}

	public DbLotniskoEntity withPanstwo(final String panstwo)
	{
		this.panstwo = panstwo;
		return this;
	}

	public DbLotniskoEntity withMiasto(final String miasto)
	{
		this.miasto = miasto;
		return this;
	}

	public DbLotniskoEntity withNazwa(final String nazwa)
	{
		this.nazwa = nazwa;
		return this;
	}

	public DbLotniskoEntity withLotsByIdLotniska(final Collection<DbLotEntity> lotsByIdLotniska)
	{
		this.lotsByIdLotniska = lotsByIdLotniska;
		return this;
	}

	public DbLotniskoEntity withLotsByIdLotniska_0(final Collection<DbLotEntity> lotsByIdLotniska_0)
	{
		this.lotsByIdLotniska_0 = lotsByIdLotniska_0;
		return this;
	}


}
