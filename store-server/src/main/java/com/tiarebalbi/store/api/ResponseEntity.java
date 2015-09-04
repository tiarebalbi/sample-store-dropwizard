package com.tiarebalbi.store.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.jetty.http.HttpStatus;

/**
 * Representation of a response entity in the API context.
 *
 * @author Tiarê Balbi Bonamini
 * @version 1.5.0
 */
public class ResponseEntity<T> {

    private HttpStatus httpStatus;

    private T response;

    @JsonCreator
    public ResponseEntity(@JsonProperty T response, @JsonProperty("status") HttpStatus httpStatus) {
        this.response = response;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public T getResponse() {
        return response;
    }
}
