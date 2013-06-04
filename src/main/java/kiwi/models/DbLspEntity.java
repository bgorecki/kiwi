package kiwi.models;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: scroot
 * Date: 02.06.13
 * Time: 09:22
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "lsp", schema = "", catalog = "kiwi")
@Entity
public class DbLspEntity
{

	private Integer idLsp;

	@javax.persistence.Column(name = "id_lsp", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Id
	@GeneratedValue
	public Integer getIdLsp()
	{
		return idLsp;
	}

	public void setIdLsp(Integer idLsp)
	{
		this.idLsp = idLsp;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DbLspEntity that = (DbLspEntity) o;

		if (idLsp != null ? !idLsp.equals(that.idLsp) : that.idLsp != null) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		return idLsp != null ? idLsp.hashCode() : 0;
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

	private DbPracownikEntity pracownikByIdPrac;

	@ManyToOne(cascade=CascadeType.ALL)
	@javax.persistence.JoinColumn(name = "id_prac", referencedColumnName = "id_pracownika", nullable = false)
	public DbPracownikEntity getPracownikByIdPrac()
	{
		return pracownikByIdPrac;
	}

	public void setPracownikByIdPrac(DbPracownikEntity pracownikByIdPrac)
	{
		this.pracownikByIdPrac = pracownikByIdPrac;
	}

	private DbSamolotEntity samolotByIdSam;

	@ManyToOne(cascade= CascadeType.ALL)
	@javax.persistence.JoinColumn(name = "id_sam", referencedColumnName = "id_samolotu", nullable = false)
	public DbSamolotEntity getSamolotByIdSam()
	{
		return samolotByIdSam;
	}

	public void setSamolotByIdSam(DbSamolotEntity samolotByIdSam)
	{
		this.samolotByIdSam = samolotByIdSam;
	}

	public DbLspEntity withIdLsp(final Integer idLsp)
	{
		this.idLsp = idLsp;
		return this;
	}

	public DbLspEntity withLotByIdLot(final DbLotEntity lotByIdLot)
	{
		this.lotByIdLot = lotByIdLot;
		return this;
	}

	public DbLspEntity withPracownikByIdPrac(final DbPracownikEntity pracownikByIdPrac)
	{
		this.pracownikByIdPrac = pracownikByIdPrac;
		return this;
	}

	public DbLspEntity withSamolotByIdSam(final DbSamolotEntity samolotByIdSam)
	{
		this.samolotByIdSam = samolotByIdSam;
		return this;
	}

}
