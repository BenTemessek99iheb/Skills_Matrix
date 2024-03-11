package com.elyadata.sm.util.exception;

import com.elyadata.sm.util.exception.model.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InternalAppException extends AbstractAppException {

    public InternalAppException(int httpStatus, String mainResource, List<ErrorResponse> errorList) {
        super(httpStatus, mainResource, errorList);
    }

}

