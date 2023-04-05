package com.user.userapp.web.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.util.HashMap;

public class BadRequestAlertException extends AbstractThrowableProblem {

    public BadRequestAlertException(
            String defaultMessage, String entityName, String errorKey
    ){
        super(DEFAULT_TYPE, defaultMessage, Status.BAD_REQUEST,
                getAlertParameters(entityName, errorKey));
    }

    private static String getAlertParameters(String entityName, String errorKey) {
        HashMap<String, String> params = new HashMap<>();
        params.put("message", "error."+errorKey);
        params.put("params", entityName);
        return params.toString();
    }

}
