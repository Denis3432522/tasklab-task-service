package com.tasklab.taskservice.exceptionhandler;

import com.tasklab.taskservice.dto.response.error.ErrorResponse;
import com.tasklab.taskservice.dto.response.error.InternalErrorResponse;
import com.tasklab.taskservice.exception.AccessDeniedException;
import com.tasklab.taskservice.exception.BadRequestException;
import com.tasklab.taskservice.exception.InternalServerErrorException;
import com.tasklab.taskservice.exception.ResourceNotFoundException;
import com.tasklab.taskservice.util.LoggerMessagePreparer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class DefaultExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotValidMethodArgument(MethodArgumentNotValidException ex) {
        log.debug(LoggerMessagePreparer.prepareErrorMessage(ex, HttpStatus.BAD_REQUEST));

        Map<String, String> errors = ex.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        for(ObjectError e : ex.getGlobalErrors()) {
            errors.put(e.getObjectName(), e.getDefaultMessage());
        }

        return new ErrorResponse("invalid field values", errors);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(BadRequestException ex) {
        log.debug(LoggerMessagePreparer.prepareDetailedErrorMessage(ex, HttpStatus.BAD_REQUEST));

        return ex.getErrorResponse()
                .orElse(new ErrorResponse("bad request"));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.debug(LoggerMessagePreparer.prepareDetailedErrorMessage(ex, HttpStatus.NOT_FOUND));

        return ex.getErrorResponse()
                .orElse(new ErrorResponse("bad request"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDeniedException(AccessDeniedException ex) {
        log.debug(LoggerMessagePreparer.prepareDetailedErrorMessage(ex, HttpStatus.NOT_FOUND));

        return ex.getErrorResponse()
                .orElse(new ErrorResponse("You are not authorized to perform this operation"));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalServerErrorException(InternalServerErrorException ex) {
        log.error(LoggerMessagePreparer.prepareDetailedErrorMessage(ex, HttpStatus.INTERNAL_SERVER_ERROR));

        return InternalErrorResponse.DEFAULT;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error(LoggerMessagePreparer.prepareErrorMessage(ex, HttpStatus.INTERNAL_SERVER_ERROR));

        return InternalErrorResponse.DEFAULT;
    }
}