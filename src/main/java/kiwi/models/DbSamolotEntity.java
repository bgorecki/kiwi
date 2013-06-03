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
@javax.persistence.Table(name = "samolot", schema = "", catalog = "kiwi")
@Entity
public class DbSamolotEntity
{

	private Integer idSamolotu;

	@javax.persistence.Column(name = "id_samolotu", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Id @GeneratedValue
	public Integer getIdSamolotu()
	{
		return idSamolotu;
	}

	public void setIdSamolotu(Integer idSamolotu)
	{
		this.idSamolotu = idSamolotu;
	}

	private String nazwa;

	@javax.persistence.Column(name = "nazwa", nullable = false, insertable = true, updatable = true, length = 200, precision = 0)
	@Basic
	public String getNazwa()
	{
		return nazwa;
	}

	public void setNazwa(String nazwa)
	{
		this.nazwa = nazwa;
	}

	private Float wielkosc; // rozpiętość skrzydeł

	@javax.persistence.Column(name = "wielkosc", nullable = true, insertable = true, updatable = true, length = 10, precision = 2)
	@Basic
	public Float getWielkosc()
	{
		return wielkosc;
	}

	public void setWielkosc(Float wielkosc)
	{
		this.wielkosc = wielkosc;
	}

	private Float waga;

	@javax.persistence.Column(name = "waga", nullable = true, insertable = true, updatable = true, length = 10, precision = 2)
	@Basic
	public Float getWaga()
	{
		return waga;
	}

	public void setWaga(Float waga)
	{
		this.waga = waga;
	}

	private Float zuzyciePaliwa;

	@javax.persistence.Column(name = "zuzycie_paliwa", nullable = false, insertable = true, updatable = true, length = 20, precision = 2)
	@Basic
	public Float getZuzyciePaliwa()
	{
		return zuzyciePaliwa;
	}

	public void setZuzyciePaliwa(Float zuzyciePaliwa)
	{
		this.zuzyciePaliwa = zuzyciePaliwa;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DbSamolotEntity that = (DbSamolotEntity) o;

		if (idSamolotu != null ? !idSamolotu.equals(that.idSamolotu) : that.idSamolotu != null) return false;
		if (nazwa != null ? !nazwa.equals(that.nazwa) : that.nazwa != null) return false;
		if (waga != null ? !waga.equals(that.waga) : that.waga != null) return false;
		if (wielkosc != null ? !wielkosc.equals(that.wielkosc) : that.wielkosc != null) return false;
		if (zuzyciePaliwa != null ? !zuzyciePaliwa.equals(that.zuzyciePaliwa) : that.zuzyciePaliwa != null)
			return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = idSamolotu != null ? idSamolotu.hashCode() : 0;
		result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
		result = 31 * result + (wielkosc != null ? wielkosc.hashCode() : 0);
		result = 31 * result + (waga != null ? waga.hashCode() : 0);
		result = 31 * result + (zuzyciePaliwa != null ? zuzyciePaliwa.hashCode() : 0);
		return result;
	}

	private Collection<DbLspEntity> lspsByIdSamolotu;

	@OneToMany(mappedBy = "samolotByIdSam",cascade=CascadeType.ALL)
	public Collection<DbLspEntity> getLspsByIdSamolotu()
	{
		return lspsByIdSamolotu;
	}

	public void setLspsByIdSamolotu(Collection<DbLspEntity> lspsByIdSamolotu)
	{
		this.lspsByIdSamolotu = lspsByIdSamolotu;
	}

	private Collection<DbMiejscaEntity> miejscasByIdSamolotu;

	@OneToMany(mappedBy = "samolotByIdSam",cascade=CascadeType.ALL)
	public Collection<DbMiejscaEntity> getMiejscasByIdSamolotu()
	{
		return miejscasByIdSamolotu;
	}

	public void setMiejscasByIdSamolotu(Collection<DbMiejscaEntity> miejscasByIdSamolotu)
	{
		this.miejscasByIdSamolotu = miejscasByIdSamolotu;
	}

	private DbPrzewoznikEntity przewoznikByIdPrzew;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_przew", referencedColumnName = "id_przewoznika", nullable = false)
	public DbPrzewoznikEntity getPrzewoznikByIdPrzew()
	{
		return przewoznikByIdPrzew;
	}

	public void setPrzewoznikByIdPrzew(DbPrzewoznikEntity przewoznikByIdPrzew)
	{
		this.przewoznikByIdPrzew = przewoznikByIdPrzew;
	}
	
	public DbSamolotEntity withNazwa(final String nazwa)
	{
		this.nazwa = nazwa;
		return this;
	}
	
	public DbSamolotEntity withWielkosc(final Float wielkosc)
	{
		this.wielkosc = wielkosc;
		return this;
	}
	
	public DbSamolotEntity withWaga(final Float waga)
	{
		this.waga = waga;
		return this;
	}
	
	public DbSamolotEntity withZuzyciePaliwa(final Float zuzyciePaliwa)
	{
		this.zuzyciePaliwa = zuzyciePaliwa;
		return this;
	}
	
	public DbSamolotEntity withPrzewoznikByIdPrzew(final DbPrzewoznikEntity przewoznikByIdPrzew)
	{
		this.przewoznikByIdPrzew = przewoznikByIdPrzew;
		return this;
	}
}
