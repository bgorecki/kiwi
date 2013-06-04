package kiwi.models;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import kiwi.dao.DbUzytkownikEntityDao;

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
	public static final String ADMIN_ROLE = "administrator";
	public static final String PRZEWOZNIK_ROLE = "przewoznik";
	
	private Integer idUzytkownika;
	private String login;
	private String haslo;
	private String rola;
	private Timestamp ostatnieLogowanie;
	private DbPrzewoznikEntity przewoznikByIdPrzewoznika;
	
	public static DbUzytkownikEntity authenticate(String username, String password) {
		return new DbUzytkownikEntityDao().findByUsernameAndPassword(username, password);
	}

	@javax.persistence.Column(name = "id_uzytkownika", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Id @GeneratedValue
	public Integer getIdUzytkownika()
	{
		return idUzytkownika;
	}

	public void setIdUzytkownika(Integer idUzytkownika)
	{
		this.idUzytkownika = idUzytkownika;
	}

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


	@ManyToOne(cascade= CascadeType.PERSIST)
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
