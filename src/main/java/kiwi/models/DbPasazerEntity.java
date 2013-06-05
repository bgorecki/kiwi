package kiwi.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: scroot
 * Date: 02.06.13
 * Time: 09:22
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "pasazer", schema = "", catalog = "kiwi")
@Entity
public class DbPasazerEntity
{
	private Integer idPasazera;
	private String imie;
	private String nazwisko;
	private String wiek;
	private Date dataUr;
	private DbRezerwacjaEntity rezerwacjaByIdRezerw;
	private Collection<DbRekordyLotuEntity> rekordyLotusByIdPasazera;

	@javax.persistence.Column(name = "id_pasazera", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)

	@Id @GeneratedValue
	public Integer getIdPasazera()
	{
		return idPasazera;
	}

	public void setIdPasazera(Integer idPasazera)
	{
		this.idPasazera = idPasazera;
	}

	@javax.persistence.Column(name = "imie", nullable = false, insertable = true, updatable = true, length = 250, precision = 0)
	@Basic
	public String getImie()
	{
		return imie;
	}

	public void setImie(String imie)
	{
		this.imie = imie;
	}

	@javax.persistence.Column(name = "nazwisko", nullable = false, insertable = true, updatable = true, length = 250, precision = 0)
	@Basic
	public String getNazwisko()
	{
		return nazwisko;
	}

	public void setNazwisko(String nazwisko)
	{
		this.nazwisko = nazwisko;
	}

	@javax.persistence.Column(name = "wiek", nullable = false, insertable = true, updatable = true, length = 18, precision = 0)
	@Basic
	public String getWiek()
	{
		return wiek;
	}

	public void setWiek(String wiek)
	{
		this.wiek = wiek;
	}

	@javax.persistence.Column(name = "data_ur", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
	@Basic
	public Date getDataUr()
	{
		return dataUr;
	}

	public void setDataUr(Date dataUr)
	{
		this.dataUr = dataUr;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DbPasazerEntity that = (DbPasazerEntity) o;

		if (dataUr != null ? !dataUr.equals(that.dataUr) : that.dataUr != null) return false;
		if (idPasazera != null ? !idPasazera.equals(that.idPasazera) : that.idPasazera != null) return false;
		if (imie != null ? !imie.equals(that.imie) : that.imie != null) return false;
		if (nazwisko != null ? !nazwisko.equals(that.nazwisko) : that.nazwisko != null) return false;
		if (wiek != null ? !wiek.equals(that.wiek) : that.wiek != null) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = idPasazera != null ? idPasazera.hashCode() : 0;
		result = 31 * result + (imie != null ? imie.hashCode() : 0);
		result = 31 * result + (nazwisko != null ? nazwisko.hashCode() : 0);
		result = 31 * result + (wiek != null ? wiek.hashCode() : 0);
		result = 31 * result + (dataUr != null ? dataUr.hashCode() : 0);
		return result;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	@javax.persistence.JoinColumn(name = "id_rezerw", referencedColumnName = "id_rezerwacji", nullable = false)
	public DbRezerwacjaEntity getRezerwacjaByIdRezerw()
	{
		return rezerwacjaByIdRezerw;
	}

	public void setRezerwacjaByIdRezerw(DbRezerwacjaEntity rezerwacjaByIdRezerw)
	{
		this.rezerwacjaByIdRezerw = rezerwacjaByIdRezerw;
	}

	@OneToMany(mappedBy = "pasazerByIdOs",cascade=CascadeType.ALL)
	public Collection<DbRekordyLotuEntity> getRekordyLotusByIdPasazera()
	{
		return rekordyLotusByIdPasazera;
	}

	public void setRekordyLotusByIdPasazera(Collection<DbRekordyLotuEntity> rekordyLotusByIdPasazera)
	{
		this.rekordyLotusByIdPasazera = rekordyLotusByIdPasazera;
	}

	public DbPasazerEntity withIdPasazera(final Integer idPasazera)
	{
		this.idPasazera = idPasazera;
		return this;
	}

	public DbPasazerEntity withImie(final String imie)
	{
		this.imie = imie;
		return this;
	}

	public DbPasazerEntity withNazwisko(final String nazwisko)
	{
		this.nazwisko = nazwisko;
		return this;
	}

	public DbPasazerEntity withWiek(final String wiek)
	{
		this.wiek = wiek;
		return this;
	}

	public DbPasazerEntity withDataUr(final Date dataUr)
	{
		this.dataUr = dataUr;
		return this;
	}

	public DbPasazerEntity withRezerwacjaByIdRezerw(final DbRezerwacjaEntity rezerwacjaByIdRezerw)
	{
		this.rezerwacjaByIdRezerw = rezerwacjaByIdRezerw;
		return this;
	}

	public DbPasazerEntity withRekordyLotusByIdPasazera(final Collection<DbRekordyLotuEntity> rekordyLotusByIdPasazera)
	{
		this.rekordyLotusByIdPasazera = rekordyLotusByIdPasazera;
		return this;
	}
}
