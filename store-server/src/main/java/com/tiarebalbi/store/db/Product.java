package com.tiarebalbi.store.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

}
