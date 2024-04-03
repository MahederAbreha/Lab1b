package edu.miu.cs.cs489appsd.employeepensioncliapp;

import edu.miu.cs.cs489appsd.employeepensioncliapp.model.Employee;
import edu.miu.cs.cs489appsd.employeepensioncliapp.model.PensionPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PensionPlanService {

    private Map<Long,PensionPlan> pensionPlans = new HashMap<>();

    public void addPensionPlan(Long employeeId,PensionPlan pensionPlan) {
        pensionPlans.put(employeeId, pensionPlan);
    }

    public PensionPlan getPensionPlan(Long employeeId) {
        return pensionPlans.get(employeeId);
    }

    public Map<Long, PensionPlan> getPensionPlans() {
        return pensionPlans;
    }

    public String getMonthlyUpcomingEnrolleesReport() {
        LocalDate firstDayOfNextMonth = LocalDate.now().plusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfNextMonth = firstDayOfNextMonth.plusMonths(1).minusDays(1);
        List<Employee> enrollees = new ArrayList<>();
        for (Employee employee : new EmployeeService().getEmployees()) {
            if (employee.getEmploymentDate().plusYears(5).isAfter(firstDayOfNextMonth.minusDays(1))
                    && employee.getEmploymentDate().plusYears(5).isBefore(lastDayOfNextMonth.plusDays(1))){
                enrollees.add(employee);
            }
        }
        enrollees.sort((e1, e2) -> e1.getEmploymentDate().compareTo(e2.getEmploymentDate()));
        return getMonthlyUpcomingEnrolleesReportAsJson(enrollees);
    }

    // Print the list as JSON string
    public String getMonthlyUpcomingEnrolleesReportAsJson(List<Employee> employees) {
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
