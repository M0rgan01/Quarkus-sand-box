package com.example.controllers;

import com.example.exceptions.MyException;
import com.example.services.AsyncService;
import com.example.services.RemoteService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

@Path("/hello")
public class QuarkusController {

    @RestClient
    RemoteService service;

    @Inject
    AsyncService reactService;

    @ConfigProperty(name = "greeting")
    String greeting;

    // Programmation impérative
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello " + greeting;
    }

    // Programmation asynchrone
    @GET
    @Path("/greeting/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public CompletionStage<String> greetings(@PathParam("name") String name) {
       return reactService.getFutureHello();
    }

    // Programmation réactive
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Publisher<String> stream() {
        return null;
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

    @GET
    @Path("/secured")
    @Produces(MediaType.TEXT_PLAIN)
    public String secured() {
        return "secured";
    }
}