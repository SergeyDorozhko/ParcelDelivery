package org.darozhka.parceldelivery.delivery.config;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.omg.CORBA.portable.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author S.Darozhka
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    private static final String INTERNAL_ISSUE_MESSAGE =
            "Some server side issues. Please retry again later or contact support team.";
    private static final String DEFAULT_ISSUE_MESSAGE =
            "Something went wrong. Please retry again later or contact support team.";

    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<Object> handleDataAccess(
            DataAccessException dae,
            WebRequest request) {
        LOGGER.error("", dae);
        return handleExceptionInternal(
                dae,
                ErrorResponseBody.with(dae.getMessage()),
                HttpHeaders.EMPTY,
                HttpStatus.CONFLICT,
                request);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(EntityNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                ErrorResponseBody.with(latestNotBlankMessageOrDefault(ex)),
                HttpHeaders.EMPTY,
                HttpStatus.NOT_FOUND,
                request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                ex.getConstraintViolations().stream().
                        map(ConstraintViolationDto::from).
                        collect(Collectors.toList()),
                HttpHeaders.EMPTY,
                HttpStatus.UNPROCESSABLE_ENTITY,
                request);
    }

    @ExceptionHandler({AuthenticationException.class})
    protected ResponseEntity<Object> handleAuthentication(AuthenticationException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                ErrorResponseBody.with(latestNotBlankMessageOrDefault(ex)),
                HttpHeaders.EMPTY,
                HttpStatus.UNAUTHORIZED,
                request);
    }

    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                ErrorResponseBody.with(latestNotBlankMessageOrDefault(ex)),
                HttpHeaders.EMPTY,
                HttpStatus.FORBIDDEN,
                request);
    }

    @ExceptionHandler({
            NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class,
            ConversionFailedException.class, ApplicationException.class})
    protected ResponseEntity<Object> handleMisc(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                ErrorResponseBody.with(latestNotBlankMessageOrDefault(ex)),
                HttpHeaders.EMPTY,
                HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<Object> handleRuntime(RuntimeException ex, WebRequest request) {
        LOGGER.error("", ex);
        return handleExceptionInternal(
                ex,
                ErrorResponseBody.with(INTERNAL_ISSUE_MESSAGE),
                HttpHeaders.EMPTY,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

    private String latestNotBlankMessageOrDefault(Throwable ex) {
        return getLatestNotBlankMessage(ex).orElse(DEFAULT_ISSUE_MESSAGE);
    }

    private Optional<String> getLatestNotBlankMessage(Throwable ex) {
        Validate.notNull(ex, "Exception is null");

        Set<Throwable> cycleBreaker = SetUtils.newIdentityHashSet();
        Throwable current = ex;

        while(cycleBreaker.add(current)) {
            String message = StringUtils.stripToNull(current.getMessage());
            if (message != null) {
                return Optional.of(message);
            }

            current = current.getCause();
            if (current == null) {
                break;
            }
        }

        return Optional.empty();
    }

    public static class ErrorResponseBody {
        private final String message;

        public ErrorResponseBody(@JsonProperty("message") String message) {
            Validate.notNull(message, "Message can't be null");

            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            } else if (obj != null && this.getClass() == obj.getClass()) {
                ErrorResponseBody that = (ErrorResponseBody)obj;
                return Objects.equals(this.message, that.message);
            } else {
                return false;
            }
        }

        public int hashCode() {
            return Objects.hash(message);
        }

        public String toString() {
            return "ErrorResponseBody{message='" + this.message + '\'' + '}';
        }

        public static ErrorResponseBody with(String message) {
            return new ErrorResponseBody(message);
        }
    }

    public static class ConstraintViolationDto {
        private String propertyPath;
        private String message;

        public ConstraintViolationDto() {
        }

        public ConstraintViolationDto(String propertyPath, String message) {
            this.propertyPath = propertyPath;
            this.message = message;
        }

        public String getPropertyPath() {
            return this.propertyPath;
        }

        public void setPropertyPath(String propertyPath) {
            this.propertyPath = propertyPath;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            } else if (obj != null && this.getClass() == obj.getClass()) {
                ConstraintViolationDto that = (ConstraintViolationDto)obj;
                return Objects.equals(this.propertyPath, that.propertyPath) && Objects.equals(this.message, that.message);
            } else {
                return false;
            }
        }

        public int hashCode() {
            return Objects.hash(this.propertyPath, this.message);
        }

        public String toString() {
            return "ConstraintViolationDto{propertyPath='" + this.propertyPath + '\'' + ", message='" + this.message + '\'' + '}';
        }

        public static ConstraintViolationDto from(ConstraintViolation<?> violation) {
            return violation == null ? null : new ConstraintViolationDto(violation.getPropertyPath().toString(), violation.getMessage());
        }
    }
}
