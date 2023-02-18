1. CollegeserviceApplication.java (default we dont create it)

package com.tns.Collegeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CollegeserviceApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(CollegeserviceApplication.class, args);
	}
}


2.College.java (create a class program with variables)

package com.tns.Collegeservice;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class College
{
	private Integer C_id;
	private String C_name;
	
	
	public College() 
	{
		super();
	}
	
	public College(Integer c_id, String c_name)
	{
		super();
		C_id = c_id;
		C_name = c_name;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getS_id() {
		return C_id;
	}
	public void setS_id(Integer s_id) {
		S_id = c_id;
	}
	public String getC_name() {
		return C_name;
	}
	public void setC_name(String c_name)
	{
		C_name = c_name;
	}
	@Override
	public String toString()
	{
		return "College[College id:"+C_id+" College name"+College_name+"]";
	}
}


3.College_Service_Repository.java (interface)

package com.tns.Collegeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface College_Service_Repository extends JpaRepository<College, Integer> 
{

}


4. College_Service.java (create service)

package com.tns.Collegeservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Transactional
public class College_Service 
{
	@Autowired
	private College_Service_Repository repo;
	
	public List<College> listAll()
	{
		return repo.findAll();
	}
	
	public void save(College stud)
	{
		repo.save(stud);
	}
	
	public College get(Integer c_id)
	{
		return repo.findById(c_id).get();
	}
	public void delete(Integer c_id)
	{
		repo.deleteById(c_id);
	}
	
}


5. College_service_Controller.java (controller)

package com.tns.Collegeservice;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class College_service_Controller
{
	@Autowired(required=true)
	private College_Service service;
	
	@GetMapping("/collegeservice")
	public java.util.List<Student> list()
	{
		return service.listAll();
	}
	
	@GetMapping("/collegeservice/{s_id}")
	public ResponseEntity<College> get(@PathVariable Integer S_id)
	{
		try
		{
			College stud=service.get(C_id);
			return new ResponseEntity<College>(stud,HttpStatus.OK);
		}
		catch(NoResultException e)
		{
			return new ResponseEntity<College>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/collegeservice")
	public void add(@RequestBody College stud)
	{
		service.save(stud);
	}
	
	@PutMapping("/Collegeservice/{c_id}")
	public ResponseEntity<?> update(@RequestBody College stud, @PathVariable Integer C_id)
	{
		College existstud=service.get(C_id);
		service.save(existstud);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/collegeservice/{c_id}")
	public void delete(@PathVariable Integer s_id)
	{
		service.delete(s_id);
	}
}
-------------------------------------------------------------------------------

Go to src/resource folder

-application.properties

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/collegeservice
spring.datasource.username=root
spring.datasource.password=Tamil@123
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
server.port=8080









