package com.tiarebalbi.store.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @NotNull
    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @ManyToMany
    @JsonProperty
    private List<Product> products = new ArrayList<>();

}
