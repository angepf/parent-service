package com.cloud.microservices.travelservice.util;

import com.cloud.dependencies.dtoservice.exception.ResourceNotFoundException;

public class ValidateFields {

    public static boolean validateField(String fieldName, String idFind, String idSend) {
        if (idFind != null) {
            return true;
        } else {
            throw new ResourceNotFoundException( fieldName, "id", idSend );
        }
    }
}
