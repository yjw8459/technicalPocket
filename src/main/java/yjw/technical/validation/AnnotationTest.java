package yjw.technical.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @Target : 사용자가 만든 어노테이션이 부착될 수 있는 타입을 지정하는 것이다.
 *            ex) 매개변수로 TYPE,CONSTRUCTOR, METHOD, FIELD를 주었는데 CONSTRUCTOR, METHOD, FIELD 3가지는
 *            이름 그대로 생성자와 메소드, 필드에 어노테이션을 붙일 수 있다는 의미이며 TYPE은 클래스, 인터페이스,
 *            열거타입에 어노테이션을 붙일 수 있다는 의미이다.
 * @interface : interface 앞에 간단히 @를 붙여주면 어노테이션 인터페이스가 만들어진다. annotation은 자신의 element를 가질 수 있다.
 *
 *
 *
 * https://seeminglyjs.tistory.com/249
 */
@Target({ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
public @interface AnnotationTest {
    int numEl() default 1;      //int 형 Element를 가지고 default 값은 1
    String strEl();             //String 형 Element를 가진다.
}
