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
@javax.persistence.Table(name = "rezerwacja", schema = "", catalog = "kiwi")
@Entity
public class DbRezerwacjaEntity
{

	private Integer idRezerwacji;

	@javax.persistence.Column(name = "id_rezerwacji", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Id
	@GeneratedValue
	public Integer getIdRezerwacji()
	{
		return idRezerwacji;
	}

	public void setIdRezerwacji(Integer idRezerwacji)
	{
		this.idRezerwacji = idRezerwacji;
	}

	private Float cenaCalkowita;

	@javax.persistence.Column(name = "cena_calkowita", nullable = false, insertable = true, updatable = true, length = 20, precision = 2)
	@Basic
	public Float getCenaCalkowita()
	{
		return cenaCalkowita;
	}

	public void setCenaCalkowita(Float cenaCalkowita)
	{
		this.cenaCalkowita = cenaCalkowita;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DbRezerwacjaEntity that = (DbRezerwacjaEntity) o;

		if (cenaCalkowita != null ? !cenaCalkowita.equals(that.cenaCalkowita) : that.cenaCalkowita != null)
			return false;
		if (idRezerwacji != null ? !idRezerwacji.equals(that.idRezerwacji) : that.idRezerwacji != null) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = idRezerwacji != null ? idRezerwacji.hashCode() : 0;
		result = 31 * result + (cenaCalkowita != null ? cenaCalkowita.hashCode() : 0);
		return result;
	}

	private Collection<DbPasazerEntity> pasazersByIdRezerwacji;

	@OneToMany(mappedBy = "rezerwacjaByIdRezerw",cascade=CascadeType.ALL)
	public Collection<DbPasazerEntity> getPasazersByIdRezerwacji()
	{
		return pasazersByIdRezerwacji;
	}

	public void setPasazersByIdRezerwacji(Collection<DbPasazerEntity> pasazersByIdRezerwacji)
	{
		this.pasazersByIdRezerwacji = pasazersByIdRezerwacji;
	}

	private Collection<DbRekordyLotuEntity> rekordyLotusByIdRezerwacji;

	@OneToMany(mappedBy = "rezerwacjaByIdRez",cascade= CascadeType.ALL)
	public Collection<DbRekordyLotuEntity> getRekordyLotusByIdRezerwacji()
	{
		return rekordyLotusByIdRezerwacji;
	}

	public void setRekordyLotusByIdRezerwacji(Collection<DbRekordyLotuEntity> rekordyLotusByIdRezerwacji)
	{
		this.rekordyLotusByIdRezerwacji = rekordyLotusByIdRezerwacji;
	}

	public DbRezerwacjaEntity withIdRezerwacji(final Integer idRezerwacji)
	{
		this.idRezerwacji = idRezerwacji;
		return this;
	}

	public DbRezerwacjaEntity withCenaCalkowita(final Float cenaCalkowita)
	{
		this.cenaCalkowita = cenaCalkowita;
		return this;
	}

	public DbRezerwacjaEntity withPasazersByIdRezerwacji(final Collection<DbPasazerEntity> pasazersByIdRezerwacji)
	{
		this.pasazersByIdRezerwacji = pasazersByIdRezerwacji;
		return this;
	}

	public DbRezerwacjaEntity withRekordyLotusByIdRezerwacji(final Collection<DbRekordyLotuEntity> rekordyLotusByIdRezerwacji)
	{
		this.rekordyLotusByIdRezerwacji = rekordyLotusByIdRezerwacji;
		return this;
	}

}
