package com.example.springopenapirest.web;

import com.example.web.dto.ErrorDescription;
import com.example.web.dto.ErrorResponse;
import com.example.web.dto.ValidationErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Hidden
@Slf4j
@RestControllerAdvice(annotations = Controller.class)
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorResponse badRequest(MethodArgumentNotValidException exception) {
        final BindingResult bindingResult = exception.getBindingResult();
        log.error("", exception);
        return createValidationResponse(buildMessage(bindingResult), buildErrors(bindingResult));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse error(EntityNotFoundException exception) {
        log.error("", exception);
        return getErrorResponse(exception);
    }

    private ErrorDescription createErrorDescription(String node, String errorMessage) {
        ErrorDescription errorDescription = new ErrorDescription();
        errorDescription.setField(node);
        errorDescription.setError(errorMessage);
        return errorDescription;
    }

    private String buildMessage(BindingResult bindingResult) {
        return String.format("Error on %s, rejected errors [%s]", bindingResult.getTarget(), bindingResult
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(joining(";")));
    }

    private List<ErrorDescription> buildErrors(BindingResult bindingResult) {
        return bindingResult
                .getFieldErrors()
                .stream()
                .map(this::createErrorDescription)
                .collect(toList());
    }

    private ErrorDescription createErrorDescription(FieldError e) {
        return createErrorDescription(e.getField(), e.getDefaultMessage());
    }

    private ErrorResponse getErrorResponse(Throwable throwable) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(throwable.getMessage());
        return response;
    }

    private ValidationErrorResponse createValidationResponse(String message, List<ErrorDescription> errors) {
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setMessage(message);
        response.setErrors(errors);
        return response;
    }
}

