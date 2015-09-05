package com.tiarebalbi.store.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * Pagination Request Model
 *
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
@Builder
public class PageRequest {

    @Getter
    @NonNull
    private int size;

    @Getter
    private int page;

    @JsonCreator
    public PageRequest(@JsonProperty("size") int size, @JsonProperty("page") int page) {

        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }

        if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        this.size = size;
        this.page = page;
    }

    public int getOffset() {
        return page * size;
    }

    public boolean hasPrevious() {
        return page > 0;
    }

}
