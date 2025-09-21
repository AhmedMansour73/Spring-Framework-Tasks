package com.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="details_id")
    private Long id;

    private String address;
    private String phone;

    // OneToOne -> linked with User
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , unique = true ,nullable = false)
    private User user;

    /*
     * 
     */

	public UserDetails() {}
    
	public UserDetails(String address, String phone) {
		this.address = address;
		this.phone = phone;
	}
    
    
 // Getters and Setters
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    
}

