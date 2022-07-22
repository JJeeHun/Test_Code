

import com.sun.xml.internal.ws.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Test00 test00 = new Test00();
        Method method = test00.getClass().getDeclaredMethod("getTestMethod", String.class);
        method.invoke(test00, "sdfdsf");
    }
}

class Test00 {
    private String name = "string method";

    void getMethod() {
        System.out.println(this.name);
    }

    void getTestMethod(String params) {
        System.out.println("test method"+params);
    }
}
