package com.tiarebalbi.store.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
public class ProductTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void shouldTestBuildAnProduct() throws Exception {

        Product product = Product.builder()
                .name("Sample 1")
                .description("Sample data to test")
                .picture("image1.jpg").picture("image2.jpg").picture("image3.jpg")
                .build();

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/products.json"), Product.class));

        assertThat(MAPPER.writeValueAsString(product)).isEqualTo(expected);
    }
}