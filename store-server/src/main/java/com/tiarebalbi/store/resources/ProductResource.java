package com.tiarebalbi.store.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.tiarebalbi.store.api.PageRequest;
import com.tiarebalbi.store.core.catalog.Product;
import com.tiarebalbi.store.db.ProductDAO;
import com.tiarebalbi.store.util.Assert;
import io.dropwizard.hibernate.UnitOfWork;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
@Path("/api/v1/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

	public static final int DEFAULT_NUMBER_OF_TUPLES = 20;

	private final ProductDAO productDAO;

	public ProductResource(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@GET
	@Timed
	@UnitOfWork
	public List<Product> getProducts(@QueryParam("page") int page,
			@QueryParam("size") int size) {

		if (size <= 0) {
			size = DEFAULT_NUMBER_OF_TUPLES;
		}
		PageRequest pagination = PageRequest.builder().page(page).size(size).build();
		return this.productDAO.getList(pagination);
	}

	@GET
	@Timed
	@Path("/{id}")
	@UnitOfWork
	public Product getInfo(@PathParam("id") Long id) {
		Product product = this.productDAO.findById(id);
		return product;
	}

	@GET
	@Timed
	@Path("/{id}/pictures")
	@UnitOfWork
	public List<String> getPictures(@PathParam("id") Long id) {
		return this.productDAO.getPictures(id);
	}

	@POST
	@Timed
	@UnitOfWork
	public Product save(@Valid Product product) {
		return this.productDAO.save(product);
	}

	@PUT
	@Timed
	@Path("/{id}")
	@UnitOfWork
	public Product update(@Valid Product product, @PathParam("id") Long id) {
		Assert.notNull(product.getId(), "Should inform the ID of this product.");
		Assert.isTrue(id == product.getId(), "You can't update different products.");

		return this.productDAO.save(product);
	}

}
