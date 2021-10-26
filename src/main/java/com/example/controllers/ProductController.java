package com.example.controllers;

import com.example.entity.Product;
import com.example.services.ProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/product")
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        return Response
                .status(Response.Status.OK)
                .entity(productService.getProductsWithPanache())
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProducts(Product product) {
        return Response
                .status(Response.Status.OK)
                .entity(productService.createProductWithPanache(product))
                .build();
    }
}
