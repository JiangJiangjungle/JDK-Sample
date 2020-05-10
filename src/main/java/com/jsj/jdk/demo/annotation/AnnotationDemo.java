package com.jsj.jdk.demo.annotation;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 利用自定义注解，简单模仿一个Spring IOC容器的bean创建和注册的过程
 *
 * @author jiangshenjie
 */
public class AnnotationDemo {
    private static Map<Class<?>, Object> applicationContext = new HashMap<>(8);

    public AnnotationDemo() {
    }

    public static void main(String[] args) {
        String rootPath = AnnotationDemo.class.getResource("").getPath();
        String packageName = AnnotationDemo.class.getPackage().getName();
        //扫描并创建bean
        autoWired(rootPath, packageName);
        //查询对应类型的bean
        BasicService service = getBean(BasicService.class);
        //调用方法
        service.doService();
    }

    /**
     * 获取已注册的bean
     *
     * @param clz
     * @param <T>
     * @return
     */
    private static <T> T getBean(Class<T> clz) {
        return (T) applicationContext.get(clz);
    }

    /**
     * 扫描包，并根据@Service注解创建和注册对应bean
     */
    private static void autoWired(String rootPath, String scanPackageName) {
        File file = new File(rootPath);
        String[] paths = file.list();
        if (paths == null) {
            return;
        }
        Arrays.stream(paths)
                .filter(path -> path.endsWith(".class"))
                .forEach((path) -> {
                    String className = String.format("%s.%s", scanPackageName, path.substring(0, path.indexOf(".class")));
                    try {
                        Class<?> clz = Class.forName(className);
                        Service serviceAnnotation = clz.getAnnotation(Service.class);
                        if (serviceAnnotation != null) {
                            //生成动态代理类并注册
                            applicationContext.put(clz.getInterfaces()[0], proxy(clz.newInstance()));
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                });
    }

    public static <T> T proxy(T object) {
        return TransactionProxy.bind(object);
    }
}
