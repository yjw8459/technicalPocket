package yjw.technical.annotation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yjw.technical.validation.Insert;
import yjw.technical.validation.ProductConstraint;
import yjw.technical.validation.Update;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @AssertFalse : false 값만 통과 가능
 *
 * @AssertTrue : true 값만 통과 가능
 *
 * @DecimalMax(value=) : 지정된 값 이하의 실수만 통과 가능
 *
 * @DecimalMin(value=) : 지정된 값 이상의 실수만 통과 가능
 *
 * @Digits(integer=,fraction=) : 대상 수가 지정된 정수와 소수 자리수보다 적을 경우 통과 가능
 *
 * @Future : 대상 날짜가 현재보다 미래일 경우만 통과 가능
 *
 * @Past : 대상 날짜가 현재보다 과거일 경우만 통과 가능
 *
 * @Max(value) : 지정된 값보다 아래일 경우만 통과 가능
 *
 * @Min(value) : 지정된 값보다 이상일 경우만 통과 가능
 *
 * @NotNull : null 값이 아닐 경우만 통과 가능
 *
 * @Null : null일 겨우만 통과 가능
 *
 * @Pattern(regex=, flag=) : 해당 정규식을 만족할 경우만 통과 가능
 *
 * @Size(min=, max=) : 문자열 또는 배열이 지정된 값 사이일 경우 통과 가능
 *
 * @Valid : 대상 객체의 확인 조건을 만족할 경우 통과 가능
 *
 * @Email : 이메일 형식
 *
 * @URL : URL 형식
 *
 */
@Getter
@RequiredArgsConstructor
@ProductConstraint
public class Product {

    @Positive//값이 양수인지
    private final int productNo;

    @Size(min = 4, max = 100, message = "{yjw.technical.annotation.Product}")   //사이즈 지정
    private final String name;

    @Min(0)
    @Max(99_999_999)
    private final int price;

    /**
     * Insert 일 경우 Validation
     * Update 일 경우 Validation
     * groups로 구분
     */
    @DecimalMin(value = "0.0", groups = Insert.class)   //지정 값 이상의 실수만
    @DecimalMax(value = "100.0", groups = Update.class)//지정 값 이하의 실수만
    private final BigDecimal discount;

    @PastOrPresent  //현재 혹은 과거의 날짜만
    //@Past         //과거의 날짜만
    private final LocalDateTime saleStartAt;

    @Future             //미래의 날짜만
    //@FutureOrPresent  //현재 혹은 미래의 날짜만
    private final LocalDateTime saleEndAt;

    @Past
    private final LocalDate dateOfManufacture;

}
