package com.web.hevepratas.exceptions.configs;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
public class StandardError implements Serializable {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;

    public StandardError(){
    }

}
