package kiwi.models;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: scroot
 * Date: 02.06.13
 * Time: 09:22
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "rekordy_lotu", schema = "", catalog = "kiwi")
@Entity
public class DbRekordyLotuEntity
{

	private Integer idRlotu;

	@javax.persistence.Column(name = "id_rlotu", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Id
	public Integer getIdRlotu()
	{
		return idRlotu;
	}

	public void setIdRlotu(Integer idRlotu)
	{
		this.idRlotu = idRlotu;
	}

	private Float cenaDynamiczna;

	@javax.persistence.Column(name = "cena_dynamiczna", nullable = false, insertable = true, updatable = true, length = 20, precision = 2)
	@Basic
	public Float getCenaDynamiczna()
	{
		return cenaDynamiczna;
	}

	public void setCenaDynamiczna(Float cenaDynamiczna)
	{
		this.cenaDynamiczna = cenaDynamiczna;
	}

	private Date dataWylotu;

	@javax.persistence.Column(name = "data_wylotu", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Basic
	public Date getDataWylotu()
	{
		return dataWylotu;
	}

	public void setDataWylotu(Date dataWylotu)
	{
		this.dataWylotu = dataWylotu;
	}

	private Date dataPrzylotu;

	@javax.persistence.Column(name = "data_przylotu", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Basic
	public Date getDataPrzylotu()
	{
		return dataPrzylotu;
	}

	public void setDataPrzylotu(Date dataPrzylotu)
	{
		this.dataPrzylotu = dataPrzylotu;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DbRekordyLotuEntity that = (DbRekordyLotuEntity) o;

		if (cenaDynamiczna != null ? !cenaDynamiczna.equals(that.cenaDynamiczna) : that.cenaDynamiczna != null)
			return false;
		if (dataPrzylotu != null ? !dataPrzylotu.equals(that.dataPrzylotu) : that.dataPrzylotu != null) return false;
		if (dataWylotu != null ? !dataWylotu.equals(that.dataWylotu) : that.dataWylotu != null) return false;
		if (idRlotu != null ? !idRlotu.equals(that.idRlotu) : that.idRlotu != null) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = idRlotu != null ? idRlotu.hashCode() : 0;
		result = 31 * result + (cenaDynamiczna != null ? cenaDynamiczna.hashCode() : 0);
		result = 31 * result + (dataWylotu != null ? dataWylotu.hashCode() : 0);
		result = 31 * result + (dataPrzylotu != null ? dataPrzylotu.hashCode() : 0);
		return result;
	}

	private DbLotEntity lotByIdLot;

	@ManyToOne(cascade=CascadeType.ALL)
	@javax.persistence.JoinColumn(name = "id_lot", referencedColumnName = "id_lotu", nullable = false)
	public DbLotEntity getLotByIdLot()
	{
		return lotByIdLot;
	}

	public void setLotByIdLot(DbLotEntity lotByIdLot)
	{
		this.lotByIdLot = lotByIdLot;
	}

	private DbPasazerEntity pasazerByIdOs;

	@ManyToOne(cascade=CascadeType.ALL)
	@javax.persistence.JoinColumn(name = "id_os", referencedColumnName = "id_pasazera", nullable = false)
	public DbPasazerEntity getPasazerByIdOs()
	{
		return pasazerByIdOs;
	}

	public void setPasazerByIdOs(DbPasazerEntity pasazerByIdOs)
	{
		this.pasazerByIdOs = pasazerByIdOs;
	}

	private DbRezerwacjaEntity rezerwacjaByIdRez;

	@ManyToOne(cascade=CascadeType.ALL)
	@javax.persistence.JoinColumn(name = "id_rez", referencedColumnName = "id_rezerwacji", nullable = false)
	public DbRezerwacjaEntity getRezerwacjaByIdRez()
	{
		return rezerwacjaByIdRez;
	}

	public void setRezerwacjaByIdRez(DbRezerwacjaEntity rezerwacjaByIdRez)
	{
		this.rezerwacjaByIdRez = rezerwacjaByIdRez;
	}

	private DbKlasaEntity klasaByIdKlas;

	@ManyToOne(cascade= CascadeType.ALL)
	@javax.persistence.JoinColumn(name = "id_klas", referencedColumnName = "id_klasy", nullable = false)
	public DbKlasaEntity getKlasaByIdKlas()
	{
		return klasaByIdKlas;
	}

	public void setKlasaByIdKlas(DbKlasaEntity klasaByIdKlas)
	{
		this.klasaByIdKlas = klasaByIdKlas;
	}
}
