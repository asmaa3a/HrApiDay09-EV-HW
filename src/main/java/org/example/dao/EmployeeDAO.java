package org.example.dao;


import org.example.dto.employeefilterDto;
import org.example.models.employee;
import org.example.models.jobs;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {


    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\HrApiDay07\\src\\main\\java\\org\\example\\hr.db";
    private static final String SELECT_ALL_EMPL = "select * from employees";
    private static final String SELECT_ONE_EMPL2 = "select * from employees where hire_date like ? || '%'";
    private static final String SELECT_ONE_EMPL = "select * from employees where employee_id = ?";
    private static final String SELECT_ONE_EMPL3 = "select * from employees where job_id = ?";
    private static final String INSERT_EMPL = "insert into employees values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_EMPL = "update employees set first_name = ?, last_name = ? email = ? phone_number = ? hire_date = ? job_id = ? salary = ? manager_id = ? department_id =? where employee_id = ?";
    private static final String DELETE_EMPL = "delete from employees where employee_id = ?";
    private static final String SELECT_ONE_JOBS_JOIN_EMP = "select * from jobs join employees on jobs.job_id = employees.job_id where employee_id = ?";
    private static final String SELECT_ONE_JOBS_JOIN_EMP2 = "select * from jobs join employees on jobs.Hire_date = employees.Hire_date where employee_id = ?";

    public void insertEmpl(employee d) throws SQLException , ClassNotFoundException  {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_EMPL);
        st.setInt(1, d.getEmployee_id());
        st.setString(2, d.getFirst_name());
        st.setString(3, d.getLast_name());
        st.setString(4,d.getEmail());
        st.setString(5,d.getPhone_number());
        st.setString(6,d.getHire_date());
        st.setInt(7,d.getJob_id());
        st.setDouble(8,d.getSalary());
        st.setInt(9,d.getManager_id());
        st.setInt(10,d.getDepartment_id());

        st.executeUpdate();
    }

    public void updateEmpl(employee d) throws SQLException , ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_EMPL);
        st.setInt(2, d.getEmployee_id());
        st.setString(1, d.getFirst_name());
        st.setString(3, d.getLast_name());
        st.setString(4,d.getEmail());
        st.setString(6,d.getPhone_number());
        st.setString(5,d.getHire_date());
        st.setInt(7,d.getJob_id());
        st.setDouble(8,d.getSalary());
        st.setInt(10,d.getManager_id());
        st.setInt(9,d.getDepartment_id());

        st.executeUpdate();
    }

    public void deleteEmpl(int employee_id) throws SQLException , ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DELETE_EMPL);
        st.setInt(1, employee_id);
        st.executeUpdate();
    }

    public employee selectEmpl(int employee_id) throws SQLException , ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_JOBS_JOIN_EMP);
        //PreparedStatement st = conn.prepareStatement(SELECT_ONE_JOBS_JOIN_EMP2);
        st.setInt(1, employee_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new employee(rs);
        }
        else {
            return null;
        }


    }

    public ArrayList<employee> selectAllempls() throws SQLException , ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ALL_EMPL);
        ResultSet rs = st.executeQuery();
        ArrayList<employee> empls = new ArrayList<>();
        while (rs.next()) {
            empls.add(new employee(rs));
        }

        return empls;
    }


    public ArrayList<employee> selectAllEmpByHAndJob(employeefilterDto filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st;
        if(filter.getHireYear() != null) {
            st = conn.prepareStatement(SELECT_ONE_EMPL2);
            st.setInt(1, filter.getHireYear());
        }
else if (filter. getJobId() != null) {
            st = conn.prepareStatement(SELECT_ONE_EMPL3);
            st.setInt(1, filter. getJobId());

        }
else {
            st = conn.prepareStatement(SELECT_ALL_EMPL);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<employee> employee = new ArrayList<>();
        while (rs.next()) {
            employee.add(new employee(rs));
        }

        return employee;
    }


}



