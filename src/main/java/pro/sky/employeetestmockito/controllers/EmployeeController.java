package pro.sky.employeetestmockito.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.employeetestmockito.model.Employee;
import pro.sky.employeetestmockito.service.EmployeeService;

@RestController
@RequestMapping(path = "employee")
@ResponseStatus
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Object addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("salary") double salary,
                              @RequestParam("department") int department) {
        try {
            Employee employee = employeeService.addEmployee(firstName, lastName, salary, department);
            return employee;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/remove")
    public Object removeEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        try {
            Employee employee = employeeService.removeEmployee(firstName, lastName);
            return employee.toString();
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/find")
    public Object findEmployee(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName) {
        try {
            Employee employee = employeeService.findEmployee(firstName, lastName);
            return employee;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/getEmployees")
    public Object getEmployees() {
        return employeeService.getEmployees();
    }
}
