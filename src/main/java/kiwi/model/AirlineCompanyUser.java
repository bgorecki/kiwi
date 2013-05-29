package kiwi.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("airlineCompany")
public class AirlineCompanyUser extends User {
	@ManyToOne  
    @JoinColumn(name = "airlineCompanyId")
	private AirlineCompany airlineCompany;

	public AirlineCompany getCarrier() {
		return airlineCompany;
	}

	public void setCarrier(AirlineCompany airlineCompany) {
		this.airlineCompany = airlineCompany;
	}
	@Override
	public String getType() {
		return "airlineCompany";
	}

}
