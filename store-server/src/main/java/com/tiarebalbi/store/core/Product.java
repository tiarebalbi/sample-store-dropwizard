package com.tiarebalbi.store.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
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
@Table(name = "product")
public class Product {

    @NotNull
    @JsonProperty
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty
    private String description;

    @JsonProperty
    @ElementCollection
    @CollectionTable(name = "product_picture", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "picture")
    @Singular
    private List<String> pictures = new ArrayList<>();

    @Min(0)
    @NotNull
    @JsonProperty
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @JsonProperty
    @Column(name = "publication_date")
    private Date publicationDate;

}
