package com.tiarebalbi.store.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
public class AssertTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = IllegalArgumentException.class)
    public void shouldTestNotNullAssert() {
        Object a = null;
        Assert.notNull(a);
        fail("Should have throw an exception");
    }

    @Test
    public void shouldTestNotNullAssertAndThrowExceptionWithMessage() {
        Object a = null;
        String message = "Should not be null";

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo(message));


        Assert.notNull(a, message);
    }

    @Test
    public void shouldHappenNothingWhenPassAnObject() {
        Assert.notNull("2");
    }


    @Test
    public void shouldTestTrueExpression() {
        Assert.isTrue(1 + 1 == 2);
    }

    @Test
    public void shouldThrowExceptionOnFalse() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo("This expression must be true"));

        Assert.isTrue(1 + 1 == 3);
    }
    @Test
    public void shouldThrowExceptionOnFalseWithCustomMessage() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo("Ops!! Error"));

        Assert.isTrue(1 + 1 == 3, "Ops!! Error");
    }

}