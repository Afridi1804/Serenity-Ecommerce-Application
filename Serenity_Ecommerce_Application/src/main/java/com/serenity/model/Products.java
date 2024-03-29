package com.serenity.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String image1;
	private String image2;
	private String image3;
	private String productBrand;
	private String productName;
	private String productType;
	private double salePrice;
	private double markedPrice;
	private double discountPercentage;
	private int quantity;
//	@Value("${yourEntityClass.isAvailable:true}")
	private Boolean isAvailable;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate manufacturedDate;
	private double rating;
	private String description;
	private int totalSold;
	@CreationTimestamp
	private LocalDateTime productCreatedDate;
	@UpdateTimestamp
	private LocalDateTime productUpdatedDate;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "orderId")
	private Orders orders;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> cartItems;

	@ManyToOne
	@JoinColumn(name = "cartId")
	private Cart cart;

}
