package com.tiarebalbi.store.resources;

import com.codahale.metrics.annotation.Timed;
import com.tiarebalbi.store.api.PageRequest;
import com.tiarebalbi.store.api.ResponseEntity;
import com.tiarebalbi.store.core.Product;
import com.tiarebalbi.store.db.ProductDAO;
import com.tiarebalbi.store.util.Assert;
import io.dropwizard.hibernate.UnitOfWork;
import org.eclipse.jetty.http.HttpStatus;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
@Path("/api/v1/product")
@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    public static final int DEFAULT_NUMBER_OF_TUPLES = 20;
    private final ProductDAO productDAO;

    public ProductResource(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    @GET
    @Timed
    @UnitOfWork
    public ResponseEntity<List<Product>> getProducts(@QueryParam("page") int page, @QueryParam("size") int size) {

        if (size <= 0) {
            size = DEFAULT_NUMBER_OF_TUPLES;
        }

        PageRequest pagination = PageRequest.builder().page(page).size(size).build();
        List<Product> products = this.productDAO.getList(pagination);
        return new ResponseEntity<>(products, HttpStatus.OK_200);
    }


    @GET
    @Timed
    @Path("/{id}")
    @UnitOfWork
    public ResponseEntity<Product> getInfo(@PathParam("id") Long id) {
        return new ResponseEntity<>(this.productDAO.findById(id));
    }

    @POST
    @Timed
    @UnitOfWork
    public ResponseEntity<Product> save(@Valid Product product) {
        try {
            return new ResponseEntity<>(this.productDAO.save(product));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR_500);
        }
    }

    @PUT
    @Timed
    @Path("/{id}")
    @UnitOfWork
    public ResponseEntity<Product> update(@Valid Product product, @PathParam("id") Long id) {
        try {

            Assert.notNull(id, "Should inform the ID of this product.");
            Assert.notNull(product.getId(), "Should inform the ID of this product.");

            return new ResponseEntity<>(this.productDAO.save(product));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR_500);
        }
    }

}
