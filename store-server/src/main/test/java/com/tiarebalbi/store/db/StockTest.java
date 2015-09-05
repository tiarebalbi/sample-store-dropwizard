package com.tiarebalbi.store.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiarebalbi.store.core.Product;
import com.tiarebalbi.store.core.Stock;
import com.tiarebalbi.store.support.Profile;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import java.math.BigDecimal;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
public class StockTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    @org.junit.experimental.categories.Category(value = Profile.UnitTest.class)
    public void shouldSetAStockToTheProduct() throws Exception {

        Product product = Product.builder()
                .name("Sample 1")
                .description("Sample data to test")
                .price(new BigDecimal("33.20"))
                .picture("image1.jpg").picture("image2.jpg").picture("image3.jpg")
                .build();

        Stock stock = new Stock(product, 20);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/stock.json"), Stock.class));

        assertThat(MAPPER.writeValueAsString(stock)).isEqualTo(expected);
    }

}