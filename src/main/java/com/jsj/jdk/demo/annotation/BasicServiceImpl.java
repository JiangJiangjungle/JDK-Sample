package com.jsj.jdk.demo.annotation;

/**
 * 模拟的service bean
 *
 * @author jiangshenjie
 */
@Service
public class BasicServiceImpl implements BasicService {
    @Override
    @Transactional(id = 1)
    public void doService() {
        System.out.println("Provide basic service.");
    }
}
