package com.example.salesApp.models;

import java.util.List;

public class salesRespose {
	  private String name;

	    private Double total;
	    
	    private List<clientRespose> clients;
	    private String seller;


		public String getSeller() {
			return seller;
		}



		public void setSeller(String seller) {
			this.seller = seller;
		}



		public String getName() {
			return name;
		}



		public void setName(String name) {
			this.name = name;
		}



		public Double getTotal() {
			return total;
		}



		public void setTotal(Double total) {
			this.total = total;
		}



		public List<clientRespose> getClients() {
			return clients;
		}



		public void setClients(List<clientRespose> clients) {
			this.clients = clients;
		}



  
}
