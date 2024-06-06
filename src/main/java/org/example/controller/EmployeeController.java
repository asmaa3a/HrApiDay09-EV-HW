package org.example.controller;

import jakarta.ws.rs.*;
import org.example.dao.EmployeeDAO;
import org.example.models.employee;

import java.util.ArrayList;

@Path("/employees")
public class EmployeeController {

    EmployeeDAO dao = new EmployeeDAO();

    @GET
    public ArrayList<employee> getAllempl() {

        try {
            return dao.selectAllempls();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{employee_id}")
    public employee getemployee(@PathParam("employee_id") int employee_id) {

        try {
            return dao.selectEmpl(employee_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("{employee_id}")
    public void deleteempl(@PathParam("employee_id") int employee_id) {

        try {
            dao.deleteEmpl(employee_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @POST
    public void insertempl(employee empls) {

        try {
            dao.insertEmpl(empls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{employee_id}")
    public void updateempl(@PathParam("employee_id") int employee_id, employee empls) {

        try {
            empls.setEmployee_id(employee_id);
            dao.updateEmpl(empls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
