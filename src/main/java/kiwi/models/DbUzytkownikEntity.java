package kiwi.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: scroot
 * Date: 02.06.13
 * Time: 09:22
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "uzytkownik", schema = "", catalog = "kiwi")
@Entity
public class DbUzytkownikEntity
{

	private Integer idUzytkownika;

	@javax.persistence.Column(name = "id_uzytkownika", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Id
	public Integer getIdUzytkownika()
	{
		return idUzytkownika;
	}

	public void setIdUzytkownika(Integer idUzytkownika)
	{
		this.idUzytkownika = idUzytkownika;
	}

	private String login;

	@javax.persistence.Column(name = "login", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
	@Basic
	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	private String haslo;

	@javax.persistence.Column(name = "haslo", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
	@Basic
	public String getHaslo()
	{
		return haslo;
	}

	public void setHaslo(String haslo)
	{
		this.haslo = haslo;
	}

	private String rola;

	@javax.persistence.Column(name = "rola", nullable = false, insertable = true, updatable = true, length = 13, precision = 0)
	@Basic
	public String getRola()
	{
		return rola;
	}

	public void setRola(String rola)
	{
		this.rola = rola;
	}

	private Timestamp ostatnieLogowanie;

	@javax.persistence.Column(name = "ostatnie_logowanie", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
	@Basic
	public Timestamp getOstatnieLogowanie()
	{
		return ostatnieLogowanie;
	}

	public void setOstatnieLogowanie(Timestamp ostatnieLogowanie)
	{
		this.ostatnieLogowanie = ostatnieLogowanie;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DbUzytkownikEntity that = (DbUzytkownikEntity) o;

		if (haslo != null ? !haslo.equals(that.haslo) : that.haslo != null) return false;
		if (idUzytkownika != null ? !idUzytkownika.equals(that.idUzytkownika) : that.idUzytkownika != null)
			return false;
		if (login != null ? !login.equals(that.login) : that.login != null) return false;
		if (ostatnieLogowanie != null ? !ostatnieLogowanie.equals(that.ostatnieLogowanie) : that.ostatnieLogowanie != null)
			return false;
		if (rola != null ? !rola.equals(that.rola) : that.rola != null) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = idUzytkownika != null ? idUzytkownika.hashCode() : 0;
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (haslo != null ? haslo.hashCode() : 0);
		result = 31 * result + (rola != null ? rola.hashCode() : 0);
		result = 31 * result + (ostatnieLogowanie != null ? ostatnieLogowanie.hashCode() : 0);
		return result;
	}

	private DbPrzewoznikEntity przewoznikByIdPrzewoznika;

	@ManyToOne(cascade= CascadeType.ALL)
	@javax.persistence.JoinColumn(name = "id_przewoznika", referencedColumnName = "id_przewoznika")
	public DbPrzewoznikEntity getPrzewoznikByIdPrzewoznika()
	{
		return przewoznikByIdPrzewoznika;
	}

	public void setPrzewoznikByIdPrzewoznika(DbPrzewoznikEntity przewoznikByIdPrzewoznika)
	{
		this.przewoznikByIdPrzewoznika = przewoznikByIdPrzewoznika;
	}
}
