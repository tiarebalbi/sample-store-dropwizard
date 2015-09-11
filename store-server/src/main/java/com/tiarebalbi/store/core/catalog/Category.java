package com.tiarebalbi.store.core.catalog;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
@JsonInclude(content = JsonInclude.Include.NON_NULL)
public class Category {

    @NotNull
    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @Singular
    @ManyToMany
    @JsonProperty
    private List<Product> products = new ArrayList<>();

    @Singular
    @ManyToMany
    @JsonProperty("children")
    private List<Category> children = new ArrayList<>();

}
