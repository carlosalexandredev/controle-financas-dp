package com.example.demo.model.util;

import org.modelmapper.ModelMapper;

import java.util.Objects;

/**
 * @TAG SG01
 * Singleton - Design Pattern Criacional
 * @Author Carlos Alexandre Fernandes Batista
 * */

public class ModelMapperUtil {
    private static ModelMapper modelMapper;
    private ModelMapperUtil() {
    }

    public static ModelMapper getInstance() {
        if(Objects.isNull(modelMapper)){
            modelMapper = new ModelMapper();
        }
        return modelMapper;
    }
}
