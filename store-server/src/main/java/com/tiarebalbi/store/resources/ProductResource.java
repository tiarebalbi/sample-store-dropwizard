package com.tiarebalbi.store.resources;

import com.tiarebalbi.store.db.ProductDAO;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
@Path("/api/v1/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    private final ProductDAO productDAO;


    public ProductResource(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
}
