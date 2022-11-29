package com.bridgelabz.employeepayrollapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.dto.LoginDto;
import com.bridgelabz.employeepayrollapp.exception.UserException;
import com.bridgelabz.employeepayrollapp.model.EmployeeModel;
import com.bridgelabz.employeepayrollapp.repo.IEmployeeRepository;
import com.bridgelabz.employeepayrollapp.utility.JwtTokenUtil;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	IEmployeeRepository repo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	JwtTokenUtil tokenUtil;

	@Override
	public EmployeeDto register(EmployeeDto employeeDto) {
		Optional<EmployeeModel> empModel = repo.findByEmployeeName(employeeDto.getEmployeeName());
		if (empModel.isPresent()) {
			throw new UserException("Username already exists!!", "Enter new User");
		}
		EmployeeModel registeredEmployee = modelMapper.map(employeeDto, EmployeeModel.class);
		repo.save(registeredEmployee);
		System.out.println("Successfully registered");
		return employeeDto;

	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
//		if (role.equals("Admin")) {
		return repo.findAll().stream().map(employee -> modelMapper.map(employee, EmployeeDto.class))
				.collect(Collectors.toList());
//		} else {
//			throw new UserException("Not admin", "Check your role");
//		}
	}

	@Override
	public EmployeeDto getEmployeeByName(String name) {
		Optional<EmployeeModel> findByName = repo.findByEmployeeName(name);
		if (findByName.isEmpty()) {
			throw new UserException("User does not exist", "Enter valid user name");
		}
		EmployeeDto employeeDto = modelMapper.map(findByName.get(), EmployeeDto.class);
		return employeeDto;
	}

	@Override
	public Optional<EmployeeModel> deleteEmployee(int employeeId) {
		Optional<EmployeeModel> empModel = repo.findByEmployeeId(employeeId);
		if (empModel.isEmpty()) {
			throw new UserException("Employee doesn't exist!!!", "Please give proper details");
		}
		repo.deleteById(employeeId);
		return empModel;
	}

	@Override
	public String loginEmployee(LoginDto loginDto) {
		Optional<EmployeeModel> user = repo.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
		if (user.isEmpty()) {
			Optional<EmployeeModel> userEmail = repo.findByEmail(loginDto.getEmail());
			Optional<EmployeeModel> userPassword = repo.findByPassword(loginDto.getPassword());
			if (userEmail.isEmpty()) {
				throw new UserException("Email is incorrect", "Give correct Email");
			} else if (userPassword.isEmpty()) {
				throw new UserException("Password is incorrect", "Give correct password");
			}
		}
		String token = tokenUtil.generateToken(loginDto);
		user.get().setIsLogin(true);
		repo.save(user.get());
		System.out.println("Check the user is login or not " + user.get().getIsLogin());

		return token;
	}

	@Override
	public EmployeeDto update(EmployeeDto employeeDto, String token) {
		LoginDto loginUser = tokenUtil.decode(token);
		EmployeeModel users = modelMapper.map(employeeDto, EmployeeModel.class);

		if (repo.findByEmailAndPassword(loginUser.getEmail(), loginUser.getPassword()).isPresent()
				&& repo.findByEmailAndPassword(loginUser.getEmail(), loginUser.getPassword()).get().getIsLogin()) {
			users.setEmployeeId(
					repo.findByEmailAndPassword(loginUser.getEmail(), loginUser.getPassword()).get().getEmployeeId());
			users.setIsVerified(true);
			users.setIsLogin(true);
			repo.save(users);
			return employeeDto;
		} else {
			throw new UserException("Not logged in", "Please login again");
		}
	}

	@Override
	public LoginDto logout(String token) {
		LoginDto loginDto = tokenUtil.decode(token);
		Optional<EmployeeModel> checkUserDetails = repo.findByEmailAndPassword(loginDto.getEmail(),
				loginDto.getPassword());
		LoginDto logout = modelMapper.map(checkUserDetails, LoginDto.class);
		checkUserDetails.get().setIsLogin(false);
		repo.save(checkUserDetails.get());
		return logout;
	}

	@Override
	public EmployeeDto getEmployeeById(int id) {
		Optional<EmployeeModel> findByEmpId = repo.findById(id);
		if (findByEmpId.isEmpty()) {
			throw new UserException("User does not exist", "Enter valid user name");
		}
		EmployeeDto employeeDto = modelMapper.map(findByEmpId.get(), EmployeeDto.class);
		return employeeDto;
	}

}