package com.tiarebalbi.store.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiarebalbi.store.core.catalog.Product;
import com.tiarebalbi.store.util.Profile;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import java.math.BigDecimal;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
public class ProductTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    @org.junit.experimental.categories.Category(value = Profile.UnitTest.class)
    public void shouldTestBuildAnProduct() throws Exception {

        Product product = Product.builder()
                .name("Sample 1")
                .description("Sample data to test")
                .price(new BigDecimal("33.20"))
                .picture("image1.jpg").picture("image2.jpg").picture("image3.jpg")
                .build();
        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/products.json"), Product.class));

        assertThat(MAPPER.writeValueAsString(product)).isEqualTo(expected);
    }
}