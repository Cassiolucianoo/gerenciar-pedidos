package com.cassio.userapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import com.cassio.userapi.util.CpfUtil;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String cpf;

	private String email;
	
	private String cpfErrorMessage;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public User() {
		super();
	}

	public User(Long id, String name, String cpf, String email, String phoneNumber, LocalDateTime createdAt,
			LocalDateTime updatedAt, String cpfErrorMessage) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.cpfErrorMessage = cpfErrorMessage;
	}

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
	     if (CpfUtil.validarCPF(cpf)) {
	            this.cpf = cpf;
	        } else {
	        	this.cpf = null; // Define o CPF como null ou um valor padrão válido
	            this.cpfErrorMessage = "CPF inválido! O CPF deve conter 11 dígitos válidos.";
	        }
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCpfErrorMessage() {
		return cpfErrorMessage;
	}

	public void setCpfErrorMessage(String cpfErrorMessage) {
		this.cpfErrorMessage = cpfErrorMessage;
	}
}



