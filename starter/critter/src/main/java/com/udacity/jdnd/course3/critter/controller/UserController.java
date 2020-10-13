package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.DTO.CustomerDTO;
import com.udacity.jdnd.course3.critter.DTO.EmployeeDTO;
import com.udacity.jdnd.course3.critter.DTO.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entity.user.Customer;
import com.udacity.jdnd.course3.critter.entity.user.Employee;
import com.udacity.jdnd.course3.critter.service.UserService;
import com.udacity.jdnd.course3.critter.entity.pet.Pet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    private static CustomerDTO convertCustomerDTOToEntity(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        List<Long> petIds = customer.getPets().stream().map(Pet::getId).collect(Collectors.toList());
        customerDTO.setPetIds(petIds);
        return customerDTO;
    }

    private static EmployeeDTO convertEmployeeDTOToEntity(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setNotes(customerDTO.getNotes());
        List<Long> petIds =customerDTO.getPetIds();

       return  convertCustomerDTOToEntity(userService.saveCustomer(customer, petIds));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = userService.getAllCustomers();
        return customers.stream().map(UserController::convertCustomerDTOToEntity).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return convertCustomerDTOToEntity(userService.getCustomerByPetId(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSkills(employeeDTO.getSkills());
        employee.setDaysAvailable(employeeDTO.getDaysAvailable());
       return convertEmployeeDTOToEntity(userService.saveEmployee(employee));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEmployeeDTOToEntity(userService.getEmployeeById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        userService.setEmployeeAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = userService.getAvailableEmployees(employeeDTO.getDate(), employeeDTO.getSkills());
        return employees.stream().map(UserController::convertEmployeeDTOToEntity).collect(Collectors.toList());
    }

}
