package com.todomgt.app.model;

import com.todomgt.app.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ItemCreateRequest implements Serializable {

    public static final long serialVersionUID = 1l;

    private String title;
    private String description;
    private Status status;

}
