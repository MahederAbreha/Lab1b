package edu.miu.cs.cs489appsd.employeepensioncliapp;

import java.util.ArrayList;
import java.util.List;
import edu.miu.cs.cs489appsd.employeepensioncliapp.model.Employee;

public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
    //Print Ascending order of lastName
    public List<Employee> getEmployeesSortedByLastName() {
        employees.sort((e1, e2) -> e1.getLastName().compareTo(e2.getLastName()));
        return employees;
    }
    //Print Descending order of salary
    public List<Employee> getEmployeesSortedBySalary() {
        employees.sort((e1, e2) -> Double.compare(e2.getYearlySalary(), e1.getYearlySalary()));
        return employees;
    }

    //All employees in JSON format
    public String getEmployeesAsJson(List<Employee> employees) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Employee employee : employees) {
            sb.append("{");
            sb.append("\"employeeId\":").append(employee.getEmployeeId()).append(",");
            sb.append("\"firstName\":\"").append(employee.getFirstName()).append("\",");
            sb.append("\"lastName\":\"").append(employee.getLastName()).append("\",");
            sb.append("\"employmentDate\":\"").append(employee.getEmploymentDate()).append("\",");
            sb.append("\"yearlySalary\":").append(employee.getYearlySalary());
            sb.append("},");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }

}
