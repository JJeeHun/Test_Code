package Annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Annotation_test {
    public static void main(String[] args) {
        Method[] methodList = MyObject.class.getMethods();

        for (Method method : methodList) {
            if(method.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation annotation=method.getDeclaredAnnotation(MyAnnotation.class);
                String value=annotation.value();
                System.out.println(method.getName() + ":" + value);
            }
        }

        MyAnnotation myAnnotation = methodList[0].getAnnotation(MyAnnotation.class);
        System.out.println(myAnnotation);
    }//main
}