package com.elearning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "joincourse")
public class Joincourses {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable = false, updatable = false)
	
	private long id;
	private String coursename;
	private String description;
	private String staffUsername;
	private String studentname;
	private long studentid;
	private long staffid;
	@Column
	private String status;
    
      
    public Joincourses() {
   	 this.status = "Pending";
   	 
   	    }
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStaffUsername() {
		return staffUsername;
	}
	public void setStaffUsername(String staffUsername) {
		this.staffUsername = staffUsername;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public long getStaffid() {
		return staffid;
	}


	public void setStaffid(long staffid) {
		this.staffid = staffid;
	}


	public String getStudentname() {
		return studentname;
	}


	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}


	public long getStudentid() {
		return studentid;
	}


	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}
	
	
	
	
}
