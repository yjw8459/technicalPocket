package yjw.technical.annotation;

import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Set;

@Service
public class AnnotationService {

    /**
     * Factory로 Validator를 생성하고 검증 대상을 validate() 메서드로 실행하면
     * 그 결과를 얻을 수 있다.
     * Validation을 통과하지 않는다고 해서 바로 Exception을 발생하지는 않는다.
     */
    public void test1(){
        /**
         * Locale 설정을 통해서 다국어 메시지 처리도 가능하다.
         * Locale 객체에 따라서 메시지 다국어 지원
         */
        //Locale.setDefault(Locale.US);
        Locale.setDefault(Locale.KOREA);
        //Product 객체 생성
        Product product = new Product(0, "화장품", 13000, BigDecimal.valueOf(5.5),
                LocalDateTime.now().plusDays(5),
                LocalDateTime.now().plusDays(5),
                LocalDate.now().minusMonths(3));

        //Validator 생성
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        //Validation 및 출력
        Set<ConstraintViolation<Product>> validate = validator.validate(product);
        validate.forEach(System.out::println);

    }

}
