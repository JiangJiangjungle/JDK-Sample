package com.jsj.jdk.demo.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jiangshenjie
 * "@Transactional" 的动态代理类
 */
public class TransactionProxy<T> implements InvocationHandler {
    private final T object;

    public TransactionProxy(T object) {
        this.object = object;
    }

    /**
     * 创建动态代理类
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T bind(T object) {
        Class<?> clazz = object.getClass();
        return (T) Proxy.newProxyInstance(clazz.getClassLoader()
                , clazz.getInterfaces(), new TransactionProxy<>(object));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> clazz = this.object.getClass();
        Class<?>[] argClasses = null;
        if (args != null) {
            argClasses = new Class[args.length];
            for (int i = 0; i <= args.length; i++) {
                argClasses[i] = args[i].getClass();
            }
        }
        Method implementClassMethod = clazz.getMethod(method.getName(), argClasses);
        Transactional transactional = implementClassMethod.getAnnotation(Transactional.class);
        Object result;
        //若未修饰@Transactional注解，则执行原来的调用逻辑
        if (transactional == null) {
            result = method.invoke(object, args);
            return result;
        }
        //执行代理逻辑
        System.out.printf("Transaction id:[%d] start.\n", transactional.id());
        result = method.invoke(object, args);
        System.out.printf("Transaction id:[%d] end.\n", transactional.id());
        return result;
    }
}
