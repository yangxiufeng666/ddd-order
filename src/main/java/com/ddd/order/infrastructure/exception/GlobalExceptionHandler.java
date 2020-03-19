package com.ddd.order.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 15:57
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ResponseEntity<ErrorRepresentation> handleInvalidRequest(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String path = request.getRequestURI();

        Map<String, Object> error = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, fieldError -> {
                    String message = fieldError.getDefaultMessage();
                    return StringUtils.isEmpty(message) ? "无错误提示" : message;
                }));

        log.error("Validation error for [{}]:{}", ex.getParameter().getParameterType().getName(), error);
        ErrorRepresentation representation = ErrorRepresentation.from(ErrorDetail.from(new RequestValidationException(error), path));
        return new ResponseEntity<>(representation, new HttpHeaders(), HttpStatus.valueOf(representation.status()));
    }
    @ExceptionHandler(AppException.class)
    @ResponseBody
    public ResponseEntity<?> handleAppException(AppException ex, HttpServletRequest request) {
        log.error("App error:", ex);
        ErrorRepresentation representation = ErrorRepresentation.from(ErrorDetail.from(ex, request.getRequestURI()));
        return new ResponseEntity<>(representation, new HttpHeaders(), HttpStatus.valueOf(representation.status()));
    }
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<?> handleGeneralException(Throwable ex, HttpServletRequest request) {
        String path = request.getRequestURI();
        log.error("Error occurred while access[{}]:", path, ex);
        ErrorRepresentation representation = ErrorRepresentation.from(ErrorDetail.from(new SystemException(ex), path));
        return new ResponseEntity<>(representation, new HttpHeaders(), HttpStatus.valueOf(representation.status()));
    }
}
