package com.tiarebalbi.store.resources;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import com.tiarebalbi.store.api.PageRequest;
import com.tiarebalbi.store.core.catalog.Product;
import com.tiarebalbi.store.db.ProductDAO;
import io.dropwizard.testing.junit.ResourceTestRule;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
public class ProductResourceTest {

	@Mock
	private static final ProductDAO dao = mock(ProductDAO.class);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
			.addResource(new ProductResource(dao)).build();

	private final Product product = Product.builder().name("Product 1").id(1l)
			.description("Description product 1").price(new BigDecimal("2.3")).build();

	@Test
	public void shouldFindTheProductById() {

		when(dao.findById(eq(1l))).thenReturn(product);

		Product response = resources.client().target("/api/v1/product/1").request()
				.get(Product.class);

		assertThat(response, equalTo(this.product));

		verify(dao).findById(1l);
		reset(dao);
	}

	@Test
	public void shouldGetProducts() {

		List<Product> products = new ArrayList<>();
		products.add(this.product);

		when(dao.getList(any(PageRequest.class))).thenReturn(products);

		List<LinkedHashMap<String, String>> response = resources.client()
				.target("/api/v1/product?page=0&size=20").request()
				.accept(MediaType.APPLICATION_JSON_TYPE).get(List.class);

		assertEquals(1, response.size());
		assertEquals("Product 1", response.get(0).get("name"));
		verify(dao).getList(any(PageRequest.class));
		reset(dao);
	}

	@Test
	public void shouldSaveProduct() {
		when(this.dao.save(any(Product.class))).thenReturn(this.product);

		Product response = resources.client().target("/api/v1/product").request()
				.post(Entity.json(product), Product.class);

		assertThat(response, equalTo(product));

		verify(dao).save(product);
		reset(dao);

	}

	@Test
	public void shouldUpdateAProduct() {
        Product p1 = Product.builder().name("Product AA2").description("@@@222").price(new BigDecimal("13.3")).picture("picture1.jpg").id(2l).build();

        when(this.dao.save(any(Product.class))).thenReturn(p1);

        Product response = resources.client().target("/api/v1/product/2").request()
                .put(Entity.json(p1), Product.class);

        assertNotNull(response.getId());
        assertThat(p1, equalTo(response));

        verify(dao).save(p1);
        reset(dao);
	}

	@Test
	public void shouldFailTryUpdateANewProduct() {
        Product p2 = Product.builder().name("Product AA2").description("@@@222").price(new BigDecimal("13.3")).picture("picture1.jpg").build();

        when(this.dao.save(any(Product.class))).thenReturn(p2);

        thrown.expect(ProcessingException.class);
        thrown.expectMessage("Server-side request processing failed with an error.");

        resources.client().target("/api/v1/product/2").request()
                .put(Entity.json(product), Product.class);

        verifyNoMoreInteractions(dao);
        reset(dao);

        fail("Should fail because the product id is NULL");
	}

	@Test
	public void shouldFailUpdateDifferentProducts() {
        Product p3 = Product.builder().name("Product 332").description("Dawdaw").price(new BigDecimal("23.3")).id(new Long(3)).picture("picture2.jpg").build();

        when(this.dao.save(any(Product.class))).thenReturn(p3);

        thrown.expect(ProcessingException.class);
        thrown.expectMessage("Server-side request processing failed with an error.");

        resources.client().target("/api/v1/product/2").request()
                .put(Entity.json(product), Product.class);

        verifyNoMoreInteractions(dao);
        reset(dao);

        fail("Should fail because the ID is different!!");


	}

}