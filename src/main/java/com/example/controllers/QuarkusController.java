package com.example.controllers;

import com.example.exceptions.MyException;
import com.example.services.HibernateTestService;
import com.example.services.RemoteService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class QuarkusController {

    @RestClient
    RemoteService service;

    @Inject
    HibernateTestService hibernateTestService;

    @ConfigProperty(name = "greeting")
    String greeting;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello " + greeting;
    }

    @GET
    @Path("/fr")
    @Produces(MediaType.TEXT_PLAIN)
    public String bonjour() {
        return "Bonjour " + greeting;
    }

    @GET
    @Path("/from-remote")
    @Produces(MediaType.TEXT_PLAIN)
    public String fromRemote() {
        return service.getRemoteHello();
    }

    @GET
    @Path("/with-exception")
    @Produces(MediaType.TEXT_PLAIN)
    public String withException() throws MyException {
        if (true) {
            throw new MyException("Exception handling");
        }
        return "never print";
    }
}