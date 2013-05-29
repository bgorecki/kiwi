package kiwi.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class AdminUser extends User {

	@Override
	public String getType() {
		return "admin";
	}

}
