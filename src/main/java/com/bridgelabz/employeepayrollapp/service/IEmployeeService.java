package com.bridgelabz.employeepayrollapp.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.dto.LoginDto;
import com.bridgelabz.employeepayrollapp.model.EmployeeModel;

public interface IEmployeeService {

	public EmployeeDto register(EmployeeDto employeeDto);

	public List<EmployeeDto> getAllEmployee();

	public EmployeeDto getEmployeeByName(String name);

	public Optional<EmployeeModel> deleteEmployee(int employeeId);
	
	public String loginEmployee(LoginDto loginDTO);

	public EmployeeDto update(EmployeeDto employeeDto, String token);
	
	public LoginDto logout(String token);

	public EmployeeDto getEmployeeById(int id);
}
