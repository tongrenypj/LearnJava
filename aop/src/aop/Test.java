package aop;

import java.lang.reflect.Proxy;
public class Test {
    public static void main(String[] args) {
        Service aService = new ServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(aService);
        // Proxy为InvocationHandler实现类动态创建一个符合某一接口的代理实例
        Service aServiceProxy = (Service) Proxy.newProxyInstance(aService
                .getClass().getClassLoader(), aService.getClass()
                .getInterfaces(), handler);
        // 由动态生成的代理对象来aServiceProxy 代理执行程序，其中aServiceProxy 符合Service接口
        aServiceProxy.add();
        System.out.println();
        aServiceProxy.update();

    }
}

