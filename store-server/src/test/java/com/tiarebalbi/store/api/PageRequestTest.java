package com.tiarebalbi.store.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
public class PageRequestTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void shouldBuildPageRequestCorrectly() {
        PageRequest page = PageRequest.builder().page(0).size(25).build();
        assertEquals(25, page.getSize());
        assertEquals(0, page.getPage());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenPassPageInvalid() {
        PageRequest.builder().page(-1).build();
        fail("Should fail because Page index must not be less than zero!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenPassSizeInvalid() {
        PageRequest.builder().page(20).size(0).build();
        fail("Should fail because Page size must not be less than one!");
    }

    @Test
    public void shouldGetOffset() {
        PageRequest page = PageRequest.builder().page(3).size(20).build();
        assertEquals(60, page.getOffset());
    }


    @Test
    public void shouldTestJsonMapper() throws Exception {

        PageRequest page = PageRequest.builder().page(2).size(10).build();

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/pagination.json"), PageRequest.class));

        assertThat(MAPPER.writeValueAsString(page)).isEqualTo(expected);


    }

}