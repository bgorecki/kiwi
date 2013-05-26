package kiwi.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UserCarrier")
public class UserCarrier extends User { 
	@ManyToOne  
    @JoinColumn(name = "carrier_id")
	private Carrier carrier;

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	
}
