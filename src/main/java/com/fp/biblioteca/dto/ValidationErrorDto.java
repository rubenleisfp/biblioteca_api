package com.fp.biblioteca.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidationErrorDto {

    private List<String> errors = new ArrayList<>();
    private String path;
    private Long timestamp;

    public ValidationErrorDto() {

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ValidationErrorDto that = (ValidationErrorDto) o;
        return Objects.equals(errors, that.errors) && Objects.equals(path, that.path) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors, path, timestamp);
    }

    @Override
    public String toString() {
        return "ValidationErrorDto{" +
                "errors=" + errors +
                ", path='" + path + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
