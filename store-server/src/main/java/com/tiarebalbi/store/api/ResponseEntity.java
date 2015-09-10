package com.tiarebalbi.store.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.jetty.http.HttpStatus;

/**
 * Representation of a response entity in the API context.
 *
 * @author Tiarê Balbi Bonamini
 * @version 1.5.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ResponseEntity<T> {

    private int httpStatus;

    private T response;

    @JsonCreator
    public ResponseEntity(@JsonProperty("response") T response, @JsonProperty("status") int httpStatus) {
        this.response = response;
        this.httpStatus = httpStatus;
    }

    public ResponseEntity(T response) {
        this.response = response;
        this.httpStatus = HttpStatus.OK_200;
    }

    public ResponseEntity(int status) {
        this.httpStatus = status;
    }

    @JsonProperty("status")
    public int getHttpStatus() {
        return httpStatus;
    }

    @JsonProperty
    public T getResponse() {
        return response;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseEntity{");
        sb.append("status=").append(httpStatus);
        sb.append(", response=").append(response);
        sb.append('}');
        return sb.toString();
    }
}
