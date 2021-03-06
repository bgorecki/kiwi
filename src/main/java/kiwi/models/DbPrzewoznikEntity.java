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
@javax.persistence.Table(name = "przewoznik", schema = "", catalog = "kiwi")
@Entity
public class DbPrzewoznikEntity
{
	private Integer idPrzewoznika;
	private String nazwa;
	private String kraj;
	private Collection<DbLotEntity> lotsByIdPrzewoznika;
	private Collection<DbModyfikatorEntity> modyfikatorsByIdPrzewoznika;
	private Collection<DbPracownikEntity> pracowniksByIdPrzewoznika;
	private Collection<DbSamolotEntity> samolotsByIdPrzewoznika;
	private Collection<DbUzytkownikEntity> uzytkowniksByIdPrzewoznika;
	
	@javax.persistence.Column(name = "id_przewoznika", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Id
	@GeneratedValue
	public Integer getIdPrzewoznika()
	{
		return idPrzewoznika;
	}

	public void setIdPrzewoznika(Integer idPrzewoznika)
	{
		this.idPrzewoznika = idPrzewoznika;
	}

	@javax.persistence.Column(name = "nazwa", nullable = false, insertable = true, updatable = true, length = 250, precision = 0)
	@Basic
	public String getNazwa()
	{
		return nazwa;
	}

	public void setNazwa(String nazwa)
	{
		this.nazwa = nazwa;
	}

	@javax.persistence.Column(name = "kraj", nullable = false, insertable = true, updatable = true, length = 250, precision = 0)
	@Basic
	public String getKraj()
	{
		return kraj;
	}

	public void setKraj(String kraj)
	{
		this.kraj = kraj;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idPrzewoznika == null) ? 0 : idPrzewoznika.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DbPrzewoznikEntity other = (DbPrzewoznikEntity) obj;
		if (idPrzewoznika == null) {
			if (other.idPrzewoznika != null)
				return false;
		} else if (!idPrzewoznika.equals(other.idPrzewoznika))
			return false;
		return true;
	}

	@OneToMany(mappedBy = "przewoznikByIdPrzew",cascade=CascadeType.ALL)
	public Collection<DbLotEntity> getLotsByIdPrzewoznika()
	{
		return lotsByIdPrzewoznika;
	}

	public void setLotsByIdPrzewoznika(Collection<DbLotEntity> lotsByIdPrzewoznika)
	{
		this.lotsByIdPrzewoznika = lotsByIdPrzewoznika;
	}

	@OneToMany(mappedBy = "przewoznikByIdPrzew",cascade=CascadeType.ALL)
	public Collection<DbModyfikatorEntity> getModyfikatorsByIdPrzewoznika()
	{
		return modyfikatorsByIdPrzewoznika;
	}

	public void setModyfikatorsByIdPrzewoznika(Collection<DbModyfikatorEntity> modyfikatorsByIdPrzewoznika)
	{
		this.modyfikatorsByIdPrzewoznika = modyfikatorsByIdPrzewoznika;
	}

	@OneToMany(mappedBy = "przewoznikByIdPrzew",cascade=CascadeType.ALL)
	public Collection<DbPracownikEntity> getPracowniksByIdPrzewoznika()
	{
		return pracowniksByIdPrzewoznika;
	}

	public void setPracowniksByIdPrzewoznika(Collection<DbPracownikEntity> pracowniksByIdPrzewoznika)
	{
		this.pracowniksByIdPrzewoznika = pracowniksByIdPrzewoznika;
	}

	@OneToMany(mappedBy = "przewoznikByIdPrzew",cascade=CascadeType.ALL)
	public Collection<DbSamolotEntity> getSamolotsByIdPrzewoznika()
	{
		return samolotsByIdPrzewoznika;
	}

	public void setSamolotsByIdPrzewoznika(Collection<DbSamolotEntity> samolotsByIdPrzewoznika)
	{
		this.samolotsByIdPrzewoznika = samolotsByIdPrzewoznika;
	}

	@OneToMany(mappedBy = "przewoznikByIdPrzewoznika",cascade=CascadeType.ALL)
	public Collection<DbUzytkownikEntity> getUzytkowniksByIdPrzewoznika()
	{
		return uzytkowniksByIdPrzewoznika;
	}

	public void setUzytkowniksByIdPrzewoznika(Collection<DbUzytkownikEntity> uzytkowniksByIdPrzewoznika)
	{
		this.uzytkowniksByIdPrzewoznika = uzytkowniksByIdPrzewoznika;
	}

	public DbPrzewoznikEntity withIdPrzewoznika(final Integer idPrzewoznika)
	{
		this.idPrzewoznika = idPrzewoznika;
		return this;
	}

	public DbPrzewoznikEntity withNazwa(final String nazwa)
	{
		this.nazwa = nazwa;
		return this;
	}

	public DbPrzewoznikEntity withKraj(final String kraj)
	{
		this.kraj = kraj;
		return this;
	}

	public DbPrzewoznikEntity withLotsByIdPrzewoznika(final Collection<DbLotEntity> lotsByIdPrzewoznika)
	{
		this.lotsByIdPrzewoznika = lotsByIdPrzewoznika;
		return this;
	}

	public DbPrzewoznikEntity withModyfikatorsByIdPrzewoznika(final Collection<DbModyfikatorEntity> modyfikatorsByIdPrzewoznika)
	{
		this.modyfikatorsByIdPrzewoznika = modyfikatorsByIdPrzewoznika;
		return this;
	}

	public DbPrzewoznikEntity withPracowniksByIdPrzewoznika(final Collection<DbPracownikEntity> pracowniksByIdPrzewoznika)
	{
		this.pracowniksByIdPrzewoznika = pracowniksByIdPrzewoznika;
		return this;
	}

	public DbPrzewoznikEntity withSamolotsByIdPrzewoznika(final Collection<DbSamolotEntity> samolotsByIdPrzewoznika)
	{
		this.samolotsByIdPrzewoznika = samolotsByIdPrzewoznika;
		return this;
	}

	public DbPrzewoznikEntity withUzytkowniksByIdPrzewoznika(final Collection<DbUzytkownikEntity> uzytkowniksByIdPrzewoznika)
	{
		this.uzytkowniksByIdPrzewoznika = uzytkowniksByIdPrzewoznika;
		return this;
	}


}
