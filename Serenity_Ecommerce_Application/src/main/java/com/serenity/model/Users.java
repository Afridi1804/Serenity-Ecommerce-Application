package com.serenity.model;

import java.time.LocalDate;
import java.util.*;


import org.springframework.format.annotation.DateTimeFormat;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.Digits;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;

//	@NotNull(message = "firstname cannot be null")
	private String firstname;

	private String lastname;

//	@NotNull(message = "age cannot be null")
//	@Min(value = 18, message = "Age should be 18 or greater")
	private int age;
	
//	@NotNull(message = "gender cannot be null")
	@Enumerated(EnumType.STRING)
	private GenderTypeEnum gender; // Enum

//	@NotNull(message = "address cannot be null")
//	private String address;
	
//	@NotNull(message = "email cannot be null")
//	@Email(message = "Invalid email format")
	@Column(unique = true)
	private String email;

//	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%!]).{8,}$", message = "Password must have at least 8 characters, one uppercase letter, one lowercase letter, one number, and one special character")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

//	@NotNull(message = "phone cannot be null")
//	@Digits(integer = 10, fraction = 0, message = "Phone number should have exactly 10 digits")
	@Column(unique = true)
	private long phone;



//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private LocalDate DateofBirth;

	@Column(name = "role", nullable = true, columnDefinition = "VARCHAR(255) DEFAULT 'user'")
	private String role;


	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customers")
	private Address address;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customers")
	private Cart cart;

	@OneToMany(mappedBy = "customers", cascade = CascadeType.ALL)
	private List<Orders> orders;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Feedback> feedbacks;
	
	@Override
	public String toString() {
		return "Users [employeeId=" + employeeId + ", firstname=" + firstname + ", lastname=" + lastname + ", age="
				+ age + ", gender=" + gender + ", address=" + address + ", email=" + email + ", password=" + password
				+ ", phone=" + phone + "]";
	}

}
