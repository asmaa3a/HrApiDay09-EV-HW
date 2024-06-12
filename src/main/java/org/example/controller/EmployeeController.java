package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import org.example.dao.EmployeeDAO;
import org.example.dto.employeefilterDto;
import org.example.models.employee;

import java.util.ArrayList;

@Path("/employees")
public class EmployeeController {

    EmployeeDAO dao = new EmployeeDAO();

    @GET
    @Path("{employee_id}")
    public employee getemployee(@PathParam("employee_id") int employee_id) {

        try {
            return dao.selectEmpl(employee_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GET
    public Response getemployeehireandjob(@BeanParam employeefilterDto emp) {

        GenericEntity<ArrayList<employee>> data;
        try {
            if(emp == null)
            {
                data = new GenericEntity<ArrayList<employee>>(dao.selectAllempls()){};

            }
            data = new GenericEntity<ArrayList<employee>>(dao.selectAllEmpByHAndJob(emp)){};

            return Response .ok(data).build();

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
