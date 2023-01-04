package com.example.demo.fortune.entity;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@NoArgsConstructor
@ToString
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
}


