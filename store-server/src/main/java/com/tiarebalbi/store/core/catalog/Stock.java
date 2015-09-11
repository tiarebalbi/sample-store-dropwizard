package com.tiarebalbi.store.core.catalog;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @ManyToOne
    @JsonProperty
    private Product product;

    @Min(0)
    @JsonProperty
    private int quantily;
}
