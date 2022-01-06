package yjw.technical.validation;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.xml.validation.Validator;

@ControllerAdvice
public class GlobalValidationExceptionHandler
        extends ResponseEntityExceptionHandler {

    static class Response{

    }
    /**
     * 유효성 검사 실패 시 ViolationException이 발생하는데
     * @ExceptionHandler(value = ConstraintViolationException.class)은
     * 유효성 검사 실패 시 발생하는 예외를 처리한다고 선언한다.
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    protected Response handleException(ConstraintViolationException exception){
        return null;
    }
}
