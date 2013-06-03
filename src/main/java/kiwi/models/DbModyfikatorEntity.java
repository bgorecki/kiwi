package kiwi.models;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: scroot
 * Date: 02.06.13
 * Time: 09:22
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "modyfikator", schema = "", catalog = "kiwi")
@Entity
public class DbModyfikatorEntity
{
	private Integer idModyfikatora;

	@javax.persistence.Column(name = "id_modyfikatora", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Id
	public Integer getIdModyfikatora()
	{
		return idModyfikatora;
	}

	public void setIdModyfikatora(Integer idModyfikatora)
	{
		this.idModyfikatora = idModyfikatora;
	}

	private String mod;
	// nazwa zmieniona na modd bo w mysql mod to nazwa zastrzeżona
	@javax.persistence.Column(name = "modd", nullable = false, insertable = true, updatable = true, length = 3, precision = 0)
	@Basic
	public String getMod()
	{
		return mod;
	}

	public void setMod(String mod)
	{
		this.mod = mod;
	}

	private Float wartoscMod;

	@javax.persistence.Column(name = "wartosc_mod", nullable = false, insertable = true, updatable = true, length = 8, precision = 2)
	@Basic
	public Float getWartoscMod()
	{
		return wartoscMod;
	}

	public void setWartoscMod(Float wartoscMod)
	{
		this.wartoscMod = wartoscMod;
	}

	private Integer dziecko;

	@javax.persistence.Column(name = "dziecko", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Basic
	public Integer getDziecko()
	{
		return dziecko;
	}

	public void setDziecko(Integer dziecko)
	{
		this.dziecko = dziecko;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DbModyfikatorEntity that = (DbModyfikatorEntity) o;

		if (dziecko != null ? !dziecko.equals(that.dziecko) : that.dziecko != null) return false;
		if (idModyfikatora != null ? !idModyfikatora.equals(that.idModyfikatora) : that.idModyfikatora != null)
			return false;
		if (mod != null ? !mod.equals(that.mod) : that.mod != null) return false;
		if (wartoscMod != null ? !wartoscMod.equals(that.wartoscMod) : that.wartoscMod != null) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = idModyfikatora != null ? idModyfikatora.hashCode() : 0;
		result = 31 * result + (mod != null ? mod.hashCode() : 0);
		result = 31 * result + (wartoscMod != null ? wartoscMod.hashCode() : 0);
		result = 31 * result + (dziecko != null ? dziecko.hashCode() : 0);
		return result;
	}

	private DbKlasaEntity klasaByIdKlas;

	@ManyToOne(cascade=CascadeType.ALL)
	@javax.persistence.JoinColumn(name = "id_klas", referencedColumnName = "id_klasy", nullable = false)
	public DbKlasaEntity getKlasaByIdKlas()
	{
		return klasaByIdKlas;
	}

	public void setKlasaByIdKlas(DbKlasaEntity klasaByIdKlas)
	{
		this.klasaByIdKlas = klasaByIdKlas;
	}

	private DbPrzewoznikEntity przewoznikByIdPrzew;

	@ManyToOne(cascade= CascadeType.ALL)
	@javax.persistence.JoinColumn(name = "id_przew", referencedColumnName = "id_przewoznika", nullable = false)
	public DbPrzewoznikEntity getPrzewoznikByIdPrzew()
	{
		return przewoznikByIdPrzew;
	}

	public void setPrzewoznikByIdPrzew(DbPrzewoznikEntity przewoznikByIdPrzew)
	{
		this.przewoznikByIdPrzew = przewoznikByIdPrzew;
	}
}
