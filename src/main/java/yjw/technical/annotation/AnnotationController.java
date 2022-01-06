package yjw.technical.annotation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/annotation")
@RequiredArgsConstructor
public class AnnotationController {

    private final AnnotationService annotationService;

    @GetMapping
    public void test1(){
        annotationService.test1();
    }

}
