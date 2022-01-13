package yjw.technical.request.handler;


import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RequestHandler {

    /**
     * 참조 : https://offbyone.tistory.com/144
     * 스프링 프레임워크를 사용하여 개발할 때 서비스나 DAO 객체에 bean을 얻기 위해서는 @Autowired 또는 @Resource를 사용하여
     * 빈을 얻는다.
     * HttpServletRequest, HttpServletResponse, HttpSession과 같은 서블릿(Servlet) 객체를 얻기 위해서는
     * Controller 메서드의 인자로 지정해서 값을 얻고 필요하면 서비스 객체로 보내야한다.
     *
     * 하지만, 이외의 유틸리티성 객체에서 데이터베이스에 접근하고자 할 때, Controller나 서비스 객체가 아닌
     * 리스너, AOP등에서 서비스 객체 또는 DAO 객체를 사용해야할 경우도 있다.
     * 이럴 경우 필요한 빈(Bean) 또는 서블릿(Servlet)객체에 직접 접근하는 방법이다.
     *
     * 빈을 직접 얻기 위한 방법
     * ContextLoader로부터 현재의 WebApplicationContext를 얻은 후 getBean() 메서드로 빈을 얻을 수 있다.
     * ContextLoader -> WebApplicationContext
     *
     * 서블릿 객체를 얻기 위한 방법
     * RequestContextHolder로부터 현재의 ServletRequestAttributes 객체를 얻은 후, 필요한 객체를 얻을 수 있다.
     * RequestContextHolder -> ServletRequestAttributes
     */
    public static void contextTest(){
        //빈을 직접 얻기 위한 방법
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        //서블릿 객체를 얻기위한 방법
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    }


    //Request 객체 얻기
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest();
    }

    //Set Request Attribute
    public static void setRequestAttributes(String key, Object obj){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.setAttribute(key, obj, attr.SCOPE_REQUEST);
    }
    //Get Request Attribute
    public static void getRequestAttributes(String key){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.getAttribute(key, attr.SCOPE_REQUEST);
    }

    //Session 객체 얻기
    public static HttpSession getSession(){
        return getRequest().getSession();
    }
    //Set Session Attributes
    public static void setSessionAttributes(String key, Object obj){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.setAttribute(key, obj, attr.SCOPE_SESSION);
    }

    //Get Session Attributes
    public static void getSessionAttributes(String key){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.getAttribute(key, attr.SCOPE_SESSION);
    }

    //HttpServletResponse 객체 얻기
    public static HttpServletResponse getResponse(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getResponse();
    }

    //beanName을 통해서 Bean을 얻을 수 있다.
    public static Object getBean(String beanName){
        return ContextLoader.getCurrentWebApplicationContext().getBean(beanName);
    }



    public static void test(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        //ServletRequestAttributes의 getAttribute를 통한 값 설정
        //scope를 통해서 request, session 구분 SCOPE_REQUEST = 0, SCOPE_SESSION = 1

        //get
        requestAttributes.getAttribute("key", requestAttributes.SCOPE_REQUEST);
        requestAttributes.getAttribute("key", requestAttributes.SCOPE_SESSION);

        //set
        requestAttributes.setAttribute("key", "value", requestAttributes.SCOPE_REQUEST);
        requestAttributes.setAttribute("key", "value", requestAttributes.SCOPE_SESSION);


        requestAttributes.getRequest().getSession().getAttribute("test");
        requestAttributes.getRequest().getSession().setAttribute("test", "value");
    }
}
