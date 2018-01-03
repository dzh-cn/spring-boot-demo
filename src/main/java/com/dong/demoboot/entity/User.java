package com.dong.demoboot.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class User {
	@Id
	private Integer id;
	@Column
	private String username;
	@Column
	private String name;
	@Column
	private int age;
	@Column
	private BigDecimal balance;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", name='" + name + '\'' +
				", age=" + age +
				", balance=" + balance +
				'}';
	}
}
