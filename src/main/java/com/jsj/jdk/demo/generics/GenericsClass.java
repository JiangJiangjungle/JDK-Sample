package com.jsj.jdk.demo.generics;

/**
 * 泛型类和泛型方法的使用;
 * 泛型接口的用法与泛型类相似。
 *
 * @author jiangshenjie
 */
public class GenericsClass<T extends UpperBound> {
    private T field;

    public GenericsClass(T field) {
        this.field = field;
    }

    /**
     * 非泛型方法，只是形参使用了指定的泛型类
     *
     * @param value
     * @return
     */
    public UpperBound getAnotherValue(GenericsClass<?> value) {
        return value.getField();
    }

    /**
     * 非泛型方法，返回值是泛型类的泛型成员类型
     *
     * @return
     */
    public T getField() {
        return field;
    }

    /**
     * 非泛型方法，传入参数是泛型类的泛型成员类型
     *
     * @param field
     */
    public void setField(T field) {
        this.field = field;
    }
}
