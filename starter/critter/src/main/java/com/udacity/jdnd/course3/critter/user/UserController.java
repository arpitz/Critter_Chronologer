package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.User;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        User user = userService.saveUser(convertFromCustomerDTO(customerDTO));
        return convertToCustomerDTO(user);
    }

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> list = convertToCustomerDTOList(userService.retrieveAllCustomers());
        return list;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable Long petId){
        User user = userService.getOwnerByPet(petId);
        return (user != null) ? convertToCustomerDTO(user) : null;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        User user = userService.saveUser(convertFromEmployeeDTO(employeeDTO));
        return convertToEmployeeDTO(user);
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeDTO> employeeDTOList = convertToEmployeeDTOList(userService.retrieveAllEmployees());
        return employeeDTOList;
    }

//    -- Done Above
    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable Long employeeId) {
        userService.setAvailabilityForEmployee(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }
// Shift to service
    private List<CustomerDTO> convertToCustomerDTOList(List<User> users) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (User user: users) {
            CustomerDTO cd = new CustomerDTO();
            BeanUtils.copyProperties(user, cd);
            customerDTOList.add(cd);
        }
        return customerDTOList;
    }

    private Customer convertFromCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    private CustomerDTO convertToCustomerDTO(User user) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(user, customerDTO);
        return customerDTO;
    }

    private Employee convertFromEmployeeDTO(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    private EmployeeDTO convertToEmployeeDTO(User user) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(user, employeeDTO);
        return employeeDTO;
    }

    private List<EmployeeDTO> convertToEmployeeDTOList(List<User> users) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (User user: users) {
            EmployeeDTO cd = new EmployeeDTO();
            BeanUtils.copyProperties(user, cd);
            employeeDTOList.add(cd);
        }
        return employeeDTOList;
    }

}
