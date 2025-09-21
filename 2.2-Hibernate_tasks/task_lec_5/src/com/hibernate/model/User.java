package com.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name ="users_")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Long id;

    private String name;
    private Integer age;

    /*
     * cascade use If you perform an operation on a primary entity, 
     * Hibernate automatically performs the same operation on the entities associated with it.
     *  1- PERSIST use to save
     *  2- MERGE to update
     *  3- REFRESH to if get data from DB and make update on it YOU WANT TO MAK THIS UPDAE IN JAVA COD NOT DATA 
     *  	and need to return ORIGANIL DATA MAKE refresh
     *  4- REMOVE
     *  5- DETACH
     *  6 - ALL
     */
    
    
    // OneToOne -> User has only one UserDetails
    @OneToOne(mappedBy = "user" , cascade = {CascadeType.PERSIST , CascadeType.MERGE , CascadeType.DETACH} ,fetch = FetchType.LAZY)
    private UserDetails userDetails;

    // ManyToMany -> User has many Friends, and Friends has many Users
    @ManyToMany(cascade = {CascadeType.PERSIST , CascadeType.MERGE } )
    @JoinTable(
        name = "user_friends",
        joinColumns = @JoinColumn(name = "user_id" , nullable = false),
        inverseJoinColumns = @JoinColumn(name = "friend_id" , nullable = false)
    )
    private List<Friends> friends;

    // OneToMany -> User has many posts
    @OneToMany(mappedBy = "user" , cascade = {CascadeType.PERSIST , CascadeType.MERGE } )
    private List<Post> posts;
    
    /*
     * 
     */
    public User() {}
    
    public User(String name, Integer age) {
		this.name = name;
		this.age = age;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public List<Friends> getFriends() {
		return friends;
	}

	public void setFriends(List<Friends> friends) {
		this.friends = friends;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
    
    
    
    
}
