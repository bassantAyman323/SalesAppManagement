package com.example.salesApp.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="sales")
public class sales {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id;

	    @Column(name = "name")
	    private String name;

	    @Column(name = "date_created")
	    @CreationTimestamp
	    private Date dateCreated;

	    @Column(name = "total")
	    private Double total;
	    

	    
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sales")
	    private List<client> clients;

	    private String sellerName;


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



		public Date getDateCreated() {
			return dateCreated;
		}



		public void setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
		}



		public Double getTotal() {
			return total;
		}



		public void setTotal(Double total) {
			this.total = total;
		}



		public List<client> getClients() {
			return clients;
		}



		public void setClients(List<client> clients) {
			this.clients = clients;
		}



		public String getSeller() {
			return sellerName;
		}



		public void setSeller(String seller) {
			this.sellerName = seller;
		}


	

}
