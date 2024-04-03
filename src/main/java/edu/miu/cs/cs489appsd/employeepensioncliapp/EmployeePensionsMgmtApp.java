package edu.miu.cs.cs489appsd.employeepensioncliapp;

import edu.miu.cs.cs489appsd.employeepensioncliapp.model.Employee;
import edu.miu.cs.cs489appsd.employeepensioncliapp.model.PensionPlan;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EmployeePensionsMgmtApp {
    public static void jsonPrint(List<Employee> employees, Map<Long, PensionPlan> pensionPlans) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Employee employee : employees) {
            sb.append("\n{");
            sb.append("\"employeeId\":").append(employee.getEmployeeId()).append(",");
            sb.append("\"firstName\":\"").append(employee.getFirstName()).append("\",");
            sb.append("\"lastName\":\"").append(employee.getLastName()).append("\",");
            sb.append("\"employmentDate\":\"").append(employee.getEmploymentDate()).append("\",");
            sb.append("\"yearlySalary\":").append(employee.getYearlySalary()).append(",");
            sb.append("\"pensionPlan\":").append("{");
            PensionPlan pensionPlan = pensionPlans.get(employee.getEmployeeId());
            if (pensionPlan != null) {
                sb.append("\"planReferenceNumber\":\"").append(pensionPlan.getPlanReferenceNumber()).append("\",");
                sb.append("\"enrollmentDate\":\"").append(pensionPlan.getEnrollmentDate()).append("\",");
                sb.append("\"monthlyContribution\":").append(pensionPlan.getMonthlyContribution());
            } else {
                sb.append("null");
            }
            sb.append("}");
            sb.append("},");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("\n]");
        System.out.println(sb.toString());

    }
    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeService();
        PensionPlanService pensionPlanService = new PensionPlanService();

        employeeService.addEmployee(new Employee(1000L, "Daniel", "Agar", LocalDate.of(2018, 1, 17), 105945.50));
        employeeService.addEmployee(new Employee(1001L, "Benard", "Shaw", LocalDate.of(2018, 10, 03), 197750.00));
        employeeService.addEmployee(new Employee(1002L, "Carly", "Agar", LocalDate.of(2014, 5, 16), 842000.75));
        employeeService.addEmployee(new Employee(1003L, "Wesley", "Schneider", LocalDate.of(2018, 11, 2), 74500.00));


        pensionPlanService.addPensionPlan(1000L,new PensionPlan("EX1089", LocalDate.of(2023, 1, 17), 100.00));
        pensionPlanService.addPensionPlan(1002L,new PensionPlan("SM2307", LocalDate.of(2019, 11, 4), 1555.50));

        //Task 1: Print the list of employees and their pension plan details as JSON string
        System.out.println("Employees and their pension plan details:");
        System.out.println("Sorted By Last Name:");
        jsonPrint(employeeService.getEmployeesSortedByLastName(), pensionPlanService.getPensionPlans());
        System.out.println("---------------------------------------------");
        System.out.println("Sorted By Salary:");
        jsonPrint(employeeService.getEmployeesSortedBySalary(), pensionPlanService.getPensionPlans());
        System.out.println("---------------------------------------------");
        //Task 2: Print the list of employees who are eligible for pension enrollment in the upcoming month
        System.out.println("Employees who are eligible for pension enrollment in the upcoming month:");
        System.out.println(pensionPlanService.getMonthlyUpcomingEnrolleesReport());
    }
}