package yjw.technical.validation;


import yjw.technical.annotation.Product;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * ConstraintValidator<ProductConstraint(제약자), Product(검증대상)>
 * ProductConstraintValidator(검증자, Validator)
 *
 * ProductConstraint 어노테이션이 Product 클래스에 적용된 경우 실행하게 된다.
 */
public class ProductConstraintValidator
        implements ConstraintValidator<ProductConstraint, Product> {


    @Override
    public void initialize(ProductConstraint constraintAnnotation) {
        //annotation에 있는 정보를 멤버변수로 저장해서 isValid()에서 사용할 수 있다.
        System.out.println(constraintAnnotation);
    }

    /**
     * isValid() 메서드에서는 파라미터를 검증 대상 객체로 받기 때문에 추가 유효성 검사를 시도할 수 있다.
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Product value, ConstraintValidatorContext context) {
        //initialize가 실행된 후 isValid가 실행되게 된다.
        return false;
    }
}
