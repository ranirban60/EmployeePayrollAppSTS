package com.bridgelabz.employeepayrollapp.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bridgelabz.employeepayrollapp.empenum.Role;

@Entity
public class EmployeeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeId;
	private String employeeName;
	private String email;
	private String password;
	private String profilePic;
	private String department;
	private String gender;
	private Long salary;
	private LocalDate startDate = LocalDate.now();
	private String notes;
	private Boolean IsVerified = false;
	private Boolean IsLogin;
	private Role role;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Boolean getIsVerified() {
		return IsVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		IsVerified = isVerified;
	}

	public Boolean getIsLogin() {
		return IsLogin;
	}

	public void setIsLogin(Boolean isLogin) {
		IsLogin = isLogin;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
