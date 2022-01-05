package yjw.technical.validation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Target은 어노테이션을 붙일 수 있는 대상을 지정하는 것.
 * 매개변수로 TYPE,CONSTRUCTOR, METHOD, FIELD를 주었는데 CONSTRUCTOR, METHOD, FIELD 3가지는
 * 이름 그대로 생성자와 메소드, 필드에 어노테이션을 붙일 수 있다는 의미이며 TYPE은 클래스, 인터페이스, 열거타입에 어노테이션을 붙일 수 있다는 의미이다.
 */
//@Target({ElementType.METHOD,ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR})
public class CustomConstraint {
}
