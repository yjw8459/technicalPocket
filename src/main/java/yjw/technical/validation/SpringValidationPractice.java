package yjw.technical.validation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 *
 * @ResponseBody : RequestResponseBodyMethodProcessor를 통해서 메서드 파라미터로 바인딩된다.
 * MethodArgumentNotValidException : @ModelAttribute나 @ResponseBody를 데이터 바인딩 할 때 Validation 오류가
 * 있을 경우 발생하는 오류
 * ConstraintViolationException : 메서드 파라미터나 리턴 값에 문제가 있으면 ConstraintViolationException 오류가 발생.
 * Spring에서 기본적으로  HTTP 500 에러로 처리하기 때문에 400(Bad Request)로 변경할 경우 변경처리가 필요하다.
 *
 *
 * https://kapentaz.github.io/spring/Spring-Boo-Bean-Validation-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%95%8C%EA%B3%A0-%EC%93%B0%EC%9E%90/
 */
public class SpringValidationPractice {

    /**
     * @ControllerAdvice를 통해서 ConstraintViolationException이 발생할 경우 400에러로 변경 처리 예제
     * 예제에서 responseBody는 ConstraintViolationException e로 표현했지만 환경에 맞는 Body를 사용한다.
     */
    static class CustomExceptionHandler extends ResponseEntityExceptionHandler {
        @ExceptionHandler(value = {ConstraintViolationException.class})
        protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e, WebRequest request){
            return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        }
    }
}

