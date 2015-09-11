package com.tiarebalbi.store.core.catalog;

import com.tiarebalbi.store.core.customer.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
@Data
@Entity
@Table(name = "review")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @NotNull
    private User user;

    @NotNull
    private Product product;

    @NotEmpty
    private String title;

    @NotEmpty
    private String resume;

    @Min(0)
    @Max(5)
    private int starts;
}