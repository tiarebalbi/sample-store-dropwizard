package com.tiarebalbi.store.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @NotNull
    @JsonProperty
    private String name;

    @Id
    private Long id;

    @JsonProperty
    private String description;

    @JsonProperty
    @ElementCollection(fetch = FetchType.EAGER)
    @Singular
    private List<String> pictures = new ArrayList<>();

    @Min(0)
    @JsonProperty
    private BigDecimal price;

    @JsonProperty
    private Date publicationDate;

}
