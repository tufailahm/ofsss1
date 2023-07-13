package com.training.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity  
@Table(name="contractemployee")  
@PrimaryKeyJoinColumn(name="ID")  
public class Contract_Employee extends Employee{  
      
    @Column(name="pay_per_hour")  
    private float pay_per_hour;  
      
    @Column(name="contract_duration")  
    private int contract_duration;

	public float getPay_per_hour() {
		return pay_per_hour;
	}

	public void setPay_per_hour(float pay_per_hour) {
		this.pay_per_hour = pay_per_hour;
	}

	public int getContract_duration() {
		return contract_duration;
	}

	public void setContract_duration(int contract_duration) {
		this.contract_duration = contract_duration;
	}  
  
    //setters and getters  
    
    
}  