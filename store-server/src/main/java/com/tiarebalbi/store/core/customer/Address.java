package com.tiarebalbi.store.core.customer;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
public class Address {

    private User user;

    private String street;
    private String complement;

    @Min(5)
    @Max(8)
    private int zipcode;

}
