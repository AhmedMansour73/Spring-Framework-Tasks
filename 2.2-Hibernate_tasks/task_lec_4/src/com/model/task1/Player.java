package com.model.task1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary Key auto increment
    @Column(name = "player_id")
    private Long id;

    @Column(nullable = false) // name NOT NULL
    private String name;

    @Column(length = 10) // max length 10 characters
    private String age;

    @Column(nullable = false) // boolean status
    private boolean status;

    public Player() {	}
    
    public Player(String name, String age, boolean status) {
		this.name = name;
		this.age = age;
		this.status = status;
	}
	// Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
