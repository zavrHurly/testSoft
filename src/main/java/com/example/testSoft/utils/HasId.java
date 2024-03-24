package com.example.testSoft.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;

public interface HasId {
    Long getId();

    void setId(long id);

    @JsonIgnore
    default boolean isNew() {
        return getId() == null;
    }

    // doesn't work for hibernate lazy proxy
    default long id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}
