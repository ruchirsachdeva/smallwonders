package com.smallwonders.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class Util {

    public static <T> ResponseEntity<T> responseEntity(Optional<T> section) {
        return section
                .map(sec -> new ResponseEntity(sec, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }
}
