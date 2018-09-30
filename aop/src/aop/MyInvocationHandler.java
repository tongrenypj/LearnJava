package aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private Object target;
    /**
     * 构造函数
     * @param target
     */
    MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        // 程序执行前加入逻辑，MethodBeforeAdviceInterceptor
        System.out.println("before-----------------------------");
        // 程序执行
        Object result = method.invoke(target, args);
        // 程序执行后加入逻辑，MethodAfterAdviceInterceptor
        System.out.println("after------------------------------");
        return result;
    }

}

