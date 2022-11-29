package com.bridgelabz.employeepayrollapp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.model.EmployeeModel;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeModel, Integer> {

	Optional<EmployeeModel> findByEmployeeName(String employeeName);

	EmployeeDto save(EmployeeDto employee);

	Optional<EmployeeModel> findByEmployeeIdAndEmployeeName(int employeeId, String employeeName);

	Optional<EmployeeModel> findByEmployeeId(int employeeId);

	Optional<EmployeeModel> findByEmailAndPassword(String email, String password);

	Optional<EmployeeModel> findByEmail(String email);

	Optional<EmployeeModel> findByPassword(String password);


}
