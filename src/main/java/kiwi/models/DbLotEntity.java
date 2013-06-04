package kiwi.models;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: scroot
 * Date: 02.06.13
 * Time: 09:22
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "lot", schema = "", catalog = "kiwi")
@Entity
public class DbLotEntity
{

	private Integer idLotu;

	@javax.persistence.Column(name = "id_lotu", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Id
	@GeneratedValue
	public Integer getIdLotu()
	{
		return idLotu;
	}

	public void setIdLotu(Integer idLotu)
	{
		this.idLotu = idLotu;
	}

	private Time godzinaWylotu;

	@javax.persistence.Column(name = "godzina_wylotu", nullable = false, insertable = true, updatable = true, length = 8, precision = 0)
	@Basic
	public Time getGodzinaWylotu()
	{
		return godzinaWylotu;
	}

	public void setGodzinaWylotu(Time godzinaWylotu)
	{
		this.godzinaWylotu = godzinaWylotu;
	}

	private Time godzinaPrzylotu;

	@javax.persistence.Column(name = "godzina_przylotu", nullable = false, insertable = true, updatable = true, length = 8, precision = 0)
	@Basic
	public Time getGodzinaPrzylotu()
	{
		return godzinaPrzylotu;
	}

	public void setGodzinaPrzylotu(Time godzinaPrzylotu)
	{
		this.godzinaPrzylotu = godzinaPrzylotu;
	}

	private Integer czasPodrozy;

	@javax.persistence.Column(name = "czas_podrozy", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Basic
	public Integer getCzasPodrozy()
	{
		return czasPodrozy;
	}

	public void setCzasPodrozy(Integer czasPodrozy)
	{
		this.czasPodrozy = czasPodrozy;
	}

	private Float cenaStatyczna;

	@javax.persistence.Column(name = "cena_statyczna", nullable = false, insertable = true, updatable = true, length = 10, precision = 2)
	@Basic
	public Float getCenaStatyczna()
	{
		return cenaStatyczna;
	}

	public void setCenaStatyczna(Float cenaStatyczna)
	{
		this.cenaStatyczna = cenaStatyczna;
	}

	private Integer dzienTygodnia;

	@javax.persistence.Column(name = "dzien_tygodnia", nullable = false, insertable = true, updatable = true, length = 1, precision = 0)
	@Basic
	public Integer getDzienTygodnia()
	{
		return dzienTygodnia;
	}

	public void setDzienTygodnia(Integer dzienTygodnia)
	{
		this.dzienTygodnia = dzienTygodnia;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DbLotEntity that = (DbLotEntity) o;

		if (cenaStatyczna != null ? !cenaStatyczna.equals(that.cenaStatyczna) : that.cenaStatyczna != null)
			return false;
		if (czasPodrozy != null ? !czasPodrozy.equals(that.czasPodrozy) : that.czasPodrozy != null) return false;
		if (dzienTygodnia != null ? !dzienTygodnia.equals(that.dzienTygodnia) : that.dzienTygodnia != null)
			return false;
		if (godzinaPrzylotu != null ? !godzinaPrzylotu.equals(that.godzinaPrzylotu) : that.godzinaPrzylotu != null)
			return false;
		if (godzinaWylotu != null ? !godzinaWylotu.equals(that.godzinaWylotu) : that.godzinaWylotu != null)
			return false;
		if (idLotu != null ? !idLotu.equals(that.idLotu) : that.idLotu != null) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = idLotu != null ? idLotu.hashCode() : 0;
		result = 31 * result + (godzinaWylotu != null ? godzinaWylotu.hashCode() : 0);
		result = 31 * result + (godzinaPrzylotu != null ? godzinaPrzylotu.hashCode() : 0);
		result = 31 * result + (czasPodrozy != null ? czasPodrozy.hashCode() : 0);
		result = 31 * result + (cenaStatyczna != null ? cenaStatyczna.hashCode() : 0);
		result = 31 * result + (dzienTygodnia != null ? dzienTygodnia.hashCode() : 0);
		return result;
	}

	private DbLotniskoEntity lotniskoByWylot;

	@ManyToOne(cascade=CascadeType.PERSIST)
	@javax.persistence.JoinColumn(name = "wylot", referencedColumnName = "id_lotniska", nullable = false)
	public DbLotniskoEntity getLotniskoByWylot()
	{
		return lotniskoByWylot;
	}

	public void setLotniskoByWylot(DbLotniskoEntity lotniskoByWylot)
	{
		this.lotniskoByWylot = lotniskoByWylot;
	}

	private DbLotniskoEntity lotniskoByPrzylot;

	@ManyToOne(cascade=CascadeType.PERSIST)
	@javax.persistence.JoinColumn(name = "przylot", referencedColumnName = "id_lotniska", nullable = false)
	public DbLotniskoEntity getLotniskoByPrzylot()
	{
		return lotniskoByPrzylot;
	}

	public void setLotniskoByPrzylot(DbLotniskoEntity lotniskoByPrzylot)
	{
		this.lotniskoByPrzylot = lotniskoByPrzylot;
		this.lotniskoByPrzylot = lotniskoByPrzylot;
	}

	private DbPrzewoznikEntity przewoznikByIdPrzew;

	@ManyToOne(cascade=CascadeType.ALL)
	@javax.persistence.JoinColumn(name = "id_przew", referencedColumnName = "id_przewoznika", nullable = false)
	public DbPrzewoznikEntity getPrzewoznikByIdPrzew()
	{
		return przewoznikByIdPrzew;
	}

	public void setPrzewoznikByIdPrzew(DbPrzewoznikEntity przewoznikByIdPrzew)
	{
		this.przewoznikByIdPrzew = przewoznikByIdPrzew;
	}

	private Collection<DbLspEntity> lspsByIdLotu;

	@OneToMany(mappedBy = "lotByIdLot",cascade=CascadeType.ALL)
	public Collection<DbLspEntity> getLspsByIdLotu()
	{
		return lspsByIdLotu;
	}

	public void setLspsByIdLotu(Collection<DbLspEntity> lspsByIdLotu)
	{
		this.lspsByIdLotu = lspsByIdLotu;
	}

	private Collection<DbRekordyLotuEntity> rekordyLotusByIdLotu;

	@OneToMany(mappedBy = "lotByIdLot",cascade=CascadeType.ALL)
	public Collection<DbRekordyLotuEntity> getRekordyLotusByIdLotu()
	{
		return rekordyLotusByIdLotu;
	}

	public void setRekordyLotusByIdLotu(Collection<DbRekordyLotuEntity> rekordyLotusByIdLotu)
	{
		this.rekordyLotusByIdLotu = rekordyLotusByIdLotu;
	}

	public DbLotEntity withIdLotu(final Integer idLotu)
	{
		this.idLotu = idLotu;
		return this;
	}

	public DbLotEntity withGodzinaWylotu(final Time godzinaWylotu)
	{
		this.godzinaWylotu = godzinaWylotu;
		return this;
	}

	public DbLotEntity withGodzinaPrzylotu(final Time godzinaPrzylotu)
	{
		this.godzinaPrzylotu = godzinaPrzylotu;
		return this;
	}

	public DbLotEntity withCzasPodrozy(final Integer czasPodrozy)
	{
		this.czasPodrozy = czasPodrozy;
		return this;
	}

	public DbLotEntity withCenaStatyczna(final Float cenaStatyczna)
	{
		this.cenaStatyczna = cenaStatyczna;
		return this;
	}

	public DbLotEntity withDzienTygodnia(final Integer dzienTygodnia)
	{
		this.dzienTygodnia = dzienTygodnia;
		return this;
	}

	public DbLotEntity withLotniskoByWylot(final DbLotniskoEntity lotniskoByWylot)
	{
		this.lotniskoByWylot = lotniskoByWylot;
		return this;
	}

	public DbLotEntity withLotniskoByPrzylot(final DbLotniskoEntity lotniskoByPrzylot)
	{
		this.lotniskoByPrzylot = lotniskoByPrzylot;
		return this;
	}

	public DbLotEntity withPrzewoznikByIdPrzew(final DbPrzewoznikEntity przewoznikByIdPrzew)
	{
		this.przewoznikByIdPrzew = przewoznikByIdPrzew;
		return this;
	}

	public DbLotEntity withLspsByIdLotu(final Collection<DbLspEntity> lspsByIdLotu)
	{
		this.lspsByIdLotu = lspsByIdLotu;
		return this;
	}

	public DbLotEntity withRekordyLotusByIdLotu(final Collection<DbRekordyLotuEntity> rekordyLotusByIdLotu)
	{
		this.rekordyLotusByIdLotu = rekordyLotusByIdLotu;
		return this;
	}


}
