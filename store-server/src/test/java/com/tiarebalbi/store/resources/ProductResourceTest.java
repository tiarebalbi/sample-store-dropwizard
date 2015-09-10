package com.tiarebalbi.store.resources;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;

import com.tiarebalbi.store.api.ResponseEntity;
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

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
			.addResource(new ProductResource(dao)).build();

	private final Product product = Product.builder().name("Product 1").id(1l)
			.description("Description product 1").price(new BigDecimal("2.3")).build();

	@Test
	public void shouldFindTheProductById() {

		when(dao.findById(eq(1l))).thenReturn(product);

		ResponseEntity<?> response = resources.client().target("/api/v1/product/1")
				.request().get(ResponseEntity.class);

		assertTrue(response.getHttpStatus() == 200);
		LinkedHashMap<String, Object> product = (LinkedHashMap<String, Object>) response
				.getResponse();
		assertEquals(product.get("name"), this.product.getName());

		verify(dao).findById(1l);
		reset(dao);
	}

	@Test
	public void shouldGetProducts() {

		List<Product> products = new ArrayList<>();
		products.add(this.product);

		when(dao.getList(eq(0), eq(20))).thenReturn(products);

		ResponseEntity<?> response = resources.client().target("/api/v1/product")
				.request().accept(MediaType.APPLICATION_JSON_TYPE)
				.get(ResponseEntity.class);

		assertEquals(200, response.getHttpStatus());

		verify(dao).getList(0, 20);
		reset(dao);
	}

}