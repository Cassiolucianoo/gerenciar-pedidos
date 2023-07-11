package com.cassio.orderapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private User user;
    
    private String item_description;
    private int item_quantity;
    private double total_value;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    
    public Order() {
    	
    }
    
    
	public Order(Long id, User user, String item_description, int item_quantity, double item_price, double total_value,
			LocalDateTime created_at, LocalDateTime updated_at) {
		super();
		this.id = id;
		this.user = user;
		this.item_description = item_description;
		this.item_quantity = item_quantity;
		this.total_value = total_value;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	
	// getters e setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public int getItem_quantity() {
		return item_quantity;
	}

	public void setItem_quantity(int item_quantity) {
		this.item_quantity = item_quantity;
	}

	public double getTotal_value() {
		return total_value;
	}

	public void setTotal_value(double total_value) {
		this.total_value = total_value;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
	
}
