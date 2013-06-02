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
@javax.persistence.Table(name = "klasa", schema = "", catalog = "kiwi")
@Entity
public class DbKlasaEntity
{

	private Integer idKlasy;

	@javax.persistence.Column(name = "id_klasy", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Id
	public Integer getIdKlasy()
	{
		return idKlasy;
	}

	public void setIdKlasy(Integer idKlasy)
	{
		this.idKlasy = idKlasy;
	}

	private String nazwa;

	@javax.persistence.Column(name = "nazwa", nullable = false, insertable = true, updatable = true, length = 14, precision = 0)
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

		DbKlasaEntity that = (DbKlasaEntity) o;

		if (idKlasy != null ? !idKlasy.equals(that.idKlasy) : that.idKlasy != null) return false;
		if (nazwa != null ? !nazwa.equals(that.nazwa) : that.nazwa != null) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = idKlasy != null ? idKlasy.hashCode() : 0;
		result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
		return result;
	}

	private Collection<DbMiejscaEntity> miejscasByIdKlasy;

	@OneToMany(mappedBy = "klasaByIdKlas",cascade=CascadeType.ALL)
	public Collection<DbMiejscaEntity> getMiejscasByIdKlasy()
	{
		return miejscasByIdKlasy;
	}

	public void setMiejscasByIdKlasy(Collection<DbMiejscaEntity> miejscasByIdKlasy)
	{
		this.miejscasByIdKlasy = miejscasByIdKlasy;
	}

	private Collection<DbModyfikatorEntity> modyfikatorsByIdKlasy;

	@OneToMany(mappedBy = "klasaByIdKlas",cascade=CascadeType.ALL)
	public Collection<DbModyfikatorEntity> getModyfikatorsByIdKlasy()
	{
		return modyfikatorsByIdKlasy;
	}

	public void setModyfikatorsByIdKlasy(Collection<DbModyfikatorEntity> modyfikatorsByIdKlasy)
	{
		this.modyfikatorsByIdKlasy = modyfikatorsByIdKlasy;
	}

	private Collection<DbRekordyLotuEntity> rekordyLotusByIdKlasy;

	@OneToMany(mappedBy = "klasaByIdKlas",cascade= CascadeType.ALL)
	public Collection<DbRekordyLotuEntity> getRekordyLotusByIdKlasy()
	{
		return rekordyLotusByIdKlasy;
	}

	public void setRekordyLotusByIdKlasy(Collection<DbRekordyLotuEntity> rekordyLotusByIdKlasy)
	{
		this.rekordyLotusByIdKlasy = rekordyLotusByIdKlasy;
	}
}
