package com.bridgelabz.employeepayrollapp.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.employeepayrollapp.ResponseEntity;
import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.dto.LoginDto;
import com.bridgelabz.employeepayrollapp.model.EmployeeModel;
import com.bridgelabz.employeepayrollapp.service.IEmployeeService;


@RestController
@CrossOrigin("http://localhost:3000")
public class EmployeeController {

	@Autowired
	IEmployeeService employeeService;

	@PostMapping("/registerEmployee")
	public ResponseEntity register(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto employee = employeeService.register(employeeDto);
		return new ResponseEntity(employee, "User added succesfully");
	}

	@GetMapping("/listAllEmployee")
	public List<EmployeeDto> getAllEmployee() {
		return this.employeeService.getAllEmployee();
	}

	@GetMapping("/searchEmployee/{name}")
	public ResponseEntity getUserByName(@PathVariable String name) {
		EmployeeDto employee = employeeService.getEmployeeByName(name);
		System.out.println("User fetched successfully");
		return new ResponseEntity(employee, "Employee details is fetched successfully");
	}

	@DeleteMapping("/deleteEmployee/{employeeId}")
	@Transactional
	public ResponseEntity deleteEmployee(@PathVariable int employeeId) {
		Optional<EmployeeModel> model = employeeService.deleteEmployee(employeeId);
		return new ResponseEntity(model, "Deleted successfully");
	}

	@GetMapping("/login")
	public ResponseEntity loginEmployee(@RequestBody LoginDto loginDTO) {
		String login = employeeService.loginEmployee(loginDTO);
		System.out.println("Login SuccessFully!!!");
		return new ResponseEntity(login, "Login successfully");
	}

	@PutMapping("/updateEmployeeByToken")
	public ResponseEntity updateUserByToken(@RequestBody EmployeeDto employeeDto, @RequestHeader String token) {
		EmployeeDto register = employeeService.update(employeeDto, token);
		return new ResponseEntity(register, "User updated successfully");
	}

	@GetMapping("/logout")
	public ResponseEntity logout(@RequestHeader String token) {
		LoginDto logout = employeeService.logout(token);
		System.out.println("Logout SuccessFully!!!");
		return new ResponseEntity(logout, "Logout successfully");
	}
	
	@GetMapping("/getEmployee/{id}")
	public ResponseEntity getEmployeeById(@PathVariable int id) {
		EmployeeDto employee = employeeService.getEmployeeById(id);
		System.out.println("User fetched successfully");
		return new ResponseEntity(employee, "Employee details is fetched successfully");
	}
	
}
