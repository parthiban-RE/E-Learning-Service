package com.elearning.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="uploaddoc")
public class Uploaddoc {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;	  
	    private String fileName;
	    private String coursename;
	    private String chapter;
	    private long studentid;
	    
	    
	    @ManyToOne (fetch = FetchType.LAZY)
	   	@JoinColumn(name="staff_id")
	   	private Staff  staff ;
		
	    
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getCoursename() {
			return coursename;
		}
		public void setCoursename(String coursename) {
			this.coursename = coursename;
		}
		public String getChapter() {
			return chapter;
		}
		public void setChapter(String chapter) {
			this.chapter = chapter;
		}
		public Staff getStaff() {
			return staff;
		}
		public void setStaff(Staff staff) {
			this.staff = staff;
		}
		public long getStudentid() {
			return studentid;
		}
		public void setStudentid(long studentid) {
			this.studentid = studentid;
		}
		
	    
	    
	    
}