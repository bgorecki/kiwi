package kiwi.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("airlineCompany")
public class AirlineCompanyUser extends User {

	@Override
	public String getType() {
		return "airlineCompany";
	}

}
