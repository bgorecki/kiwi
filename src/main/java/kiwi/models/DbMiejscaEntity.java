package kiwi.models;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: scroot
 * Date: 02.06.13
 * Time: 09:22
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "miejsca", schema = "", catalog = "kiwi")
@Entity
public class DbMiejscaEntity
{

	private Integer idMiejsca;

	@javax.persistence.Column(name = "id_miejsca", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Id
	@GeneratedValue
	public Integer getIdMiejsca()
	{
		return idMiejsca;
	}

	public void setIdMiejsca(Integer idMiejsca)
	{
		this.idMiejsca = idMiejsca;
	}

	private Integer ilosc;

	@javax.persistence.Column(name = "ilosc", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Basic
	public Integer getIlosc()
	{
		return ilosc;
	}

	public void setIlosc(Integer ilosc)
	{
		this.ilosc = ilosc;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DbMiejscaEntity that = (DbMiejscaEntity) o;

		if (idMiejsca != null ? !idMiejsca.equals(that.idMiejsca) : that.idMiejsca != null) return false;
		if (ilosc != null ? !ilosc.equals(that.ilosc) : that.ilosc != null) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = idMiejsca != null ? idMiejsca.hashCode() : 0;
		result = 31 * result + (ilosc != null ? ilosc.hashCode() : 0);
		return result;
	}

	private DbSamolotEntity samolotByIdSam;

	@ManyToOne(cascade=CascadeType.PERSIST)
	@javax.persistence.JoinColumn(name = "id_sam", referencedColumnName = "id_samolotu", nullable = false)
	public DbSamolotEntity getSamolotByIdSam()
	{
		return samolotByIdSam;
	}

	public void setSamolotByIdSam(DbSamolotEntity samolotByIdSam)
	{
		this.samolotByIdSam = samolotByIdSam;
	}

	private DbKlasaEntity klasaByIdKlas;

	@ManyToOne(cascade= CascadeType.PERSIST)
	@javax.persistence.JoinColumn(name = "id_klas", referencedColumnName = "id_klasy", nullable = false)
	public DbKlasaEntity getKlasaByIdKlas()
	{
		return klasaByIdKlas;
	}

	public void setKlasaByIdKlas(DbKlasaEntity klasaByIdKlas)
	{
		this.klasaByIdKlas = klasaByIdKlas;
	}

	public DbMiejscaEntity withIdMiejsca(final Integer idMiejsca)
	{
		this.idMiejsca = idMiejsca;
		return this;
	}

	public DbMiejscaEntity withIlosc(final Integer ilosc)
	{
		this.ilosc = ilosc;
		return this;
	}

	public DbMiejscaEntity withSamolotByIdSam(final DbSamolotEntity samolotByIdSam)
	{
		this.samolotByIdSam = samolotByIdSam;
		return this;
	}

	public DbMiejscaEntity withKlasaByIdKlas(final DbKlasaEntity klasaByIdKlas)
	{
		this.klasaByIdKlas = klasaByIdKlas;
		return this;
	}


}
