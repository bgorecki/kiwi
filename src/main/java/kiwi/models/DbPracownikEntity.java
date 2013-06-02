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
@javax.persistence.Table(name = "pracownik", schema = "", catalog = "kiwi")
@Entity
public class DbPracownikEntity
{

	private Integer idPracownika;

	@javax.persistence.Column(name = "id_pracownika", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Id
	public Integer getIdPracownika()
	{
		return idPracownika;
	}

	public void setIdPracownika(Integer idPracownika)
	{
		this.idPracownika = idPracownika;
	}

	private String imie;

	@javax.persistence.Column(name = "imie", nullable = false, insertable = true, updatable = true, length = 200, precision = 0)
	@Basic
	public String getImie()
	{
		return imie;
	}

	public void setImie(String imie)
	{
		this.imie = imie;
	}

	private String nazwisko;

	@javax.persistence.Column(name = "nazwisko", nullable = false, insertable = true, updatable = true, length = 200, precision = 0)
	@Basic
	public String getNazwisko()
	{
		return nazwisko;
	}

	public void setNazwisko(String nazwisko)
	{
		this.nazwisko = nazwisko;
	}

	private String stanowisko;

	@javax.persistence.Column(name = "stanowisko", nullable = false, insertable = true, updatable = true, length = 17, precision = 0)
	@Basic
	public String getStanowisko()
	{
		return stanowisko;
	}

	public void setStanowisko(String stanowisko)
	{
		this.stanowisko = stanowisko;
	}

	private Float pensja;

	@javax.persistence.Column(name = "pensja", nullable = false, insertable = true, updatable = true, length = 10, precision = 2)
	@Basic
	public Float getPensja()
	{
		return pensja;
	}

	public void setPensja(Float pensja)
	{
		this.pensja = pensja;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DbPracownikEntity that = (DbPracownikEntity) o;

		if (idPracownika != null ? !idPracownika.equals(that.idPracownika) : that.idPracownika != null) return false;
		if (imie != null ? !imie.equals(that.imie) : that.imie != null) return false;
		if (nazwisko != null ? !nazwisko.equals(that.nazwisko) : that.nazwisko != null) return false;
		if (pensja != null ? !pensja.equals(that.pensja) : that.pensja != null) return false;
		if (stanowisko != null ? !stanowisko.equals(that.stanowisko) : that.stanowisko != null) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = idPracownika != null ? idPracownika.hashCode() : 0;
		result = 31 * result + (imie != null ? imie.hashCode() : 0);
		result = 31 * result + (nazwisko != null ? nazwisko.hashCode() : 0);
		result = 31 * result + (stanowisko != null ? stanowisko.hashCode() : 0);
		result = 31 * result + (pensja != null ? pensja.hashCode() : 0);
		return result;
	}

	private Collection<DbLspEntity> lspsByIdPracownika;

	@OneToMany(mappedBy = "pracownikByIdPrac",cascade=CascadeType.ALL)
	public Collection<DbLspEntity> getLspsByIdPracownika()
	{
		return lspsByIdPracownika;
	}

	public void setLspsByIdPracownika(Collection<DbLspEntity> lspsByIdPracownika)
	{
		this.lspsByIdPracownika = lspsByIdPracownika;
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
}
