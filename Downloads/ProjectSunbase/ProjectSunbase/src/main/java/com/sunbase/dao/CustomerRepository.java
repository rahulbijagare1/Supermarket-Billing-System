package com.sunbase.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.sunbase.entities.Customer;
import com.sunbase.entities.User;



public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	//pagination...
	
		@Query("from Customer as c where c.user.id =:userId")
		//currentPage-page
		//Customer Per page - 5
		public Page<Customer> findCustomersByUser(@Param("userId")int userId, Pageable pePageable);
		
		//search
		public List<Customer> findByNameContainingAndUser(String name,User user);
		
}
