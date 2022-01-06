package yjw.technical.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * Custom Validator(사용자 정의 검증자)
 * https://meetup.toast.com/posts/223
 */
@Documented
@Constraint(validatedBy = ProductConstraintValidator.class)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductConstraint {
    /**
     *  default {} : []등 Object일 경우
     *  groups는 상황별 validation 제어를 위해서 사용할 수 있다.
     *  예를 들어 Insert 할 때와 Update할 때 Validation을 구분해서
     *  실행하고 싶을 때 사용한다.
     *
     *  payload는 심각도를 나타낸다고 한다, 더욱 디테일하게 나눌 경우 사용한다.
     * @return
     */
    String message() default "{yjw.technical.annotation.Product}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
