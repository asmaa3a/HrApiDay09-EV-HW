package org.example.controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.JobDAO;
import org.example.dto.JobfilterDto;
import org.example.dto.jobsdto1;
import org.example.models.jobs;


import java.sql.SQLException;
import java.net.URI;

import java.util.ArrayList;

@Path("/jobs")
public class jobcontroller {


    JobDAO dao = new JobDAO();
    @Context
    UriInfo uriInfo;
    @Context
    HttpHeaders headers;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getselectAllJOBS(
            @BeanParam JobfilterDto filter
    ) {
        try {
            GenericEntity<ArrayList<jobs>> jobs = new GenericEntity<ArrayList<jobs>>(dao.selectAllJOBS(filter)) {
            };
            if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(jobs)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }

            return Response
                    .ok(jobs, MediaType.APPLICATION_JSON)
                    .build();
            /// public ArrayList<jobs> getselectAllJOBS(@QueryParam("minsalary") Integer minsalary,
            //    @QueryParam("limit") Integer limit,
            //   @QueryParam("offset") int offset) {

            // try {
            //   return dao.selectAllJOBS();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addLinks(jobsdto1 dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI empsUri = uriInfo.getAbsolutePathBuilder()
                .path(EmployeeController.class)
                .build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(empsUri.toString(), "employees");
    }
    //}

    @GET
    @Path("{job_ID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getjob(@PathParam("job_ID") int job_ID) {

        try {
            jobs jobs = dao.selectjobs(job_ID);
            if (jobs == null) {

            }
            jobsdto1 dto = new jobsdto1();
            dto.setJob_ID(jobs.getJob_ID());
            dto.setJob_title(jobs.getJob_title());
            dto.setMin_salary(jobs.getMin_salary());

            addLinks(dto);

            return Response.ok(dto).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("{job_ID}")
    public void deletejob(@PathParam("job_ID") int job_ID) {

        try {
            dao.deletejobs(job_ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //  @POST
//        @Consumes(MediaType.APPLICATION_XML)
    //  public void insertjob(jobs j) {

    //   try {
    //       dao.insertjobs(j);
    //   } catch (Exception e) {
    //      throw new RuntimeException(e);
    ///  }
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response insertjob(jobs j) {

        try {
            dao.insertjobs(j);
            NewCookie cookie = (new NewCookie("username", "OOOOO"));
            URI uri = uriInfo.getAbsolutePathBuilder().path(j.getJob_ID() + "").build();
            return Response
//                    .status(Response.Status.CREATED)
                    .created(uri)
//                    .cookie(new NewCookie("username", "OOOOO"))
                    .cookie(cookie)
                    .header("Created by", "Wael")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{job_ID}")
    public void updatejob(@PathParam("job_ID") int job_ID, jobs j) {

        try {
            j.setJob_ID(job_ID);
            dao.updatejobs(j);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Path("{job_ID}/employees")
    public EmployeeController getEmployeeController(){
        return new EmployeeController();

    }
}


