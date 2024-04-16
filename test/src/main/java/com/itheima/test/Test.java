package com.itheima.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.env.StandardEnvironment;
//
public class Test {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("bean1",
                BeanDefinitionBuilder.genericBeanDefinition(Bean1.class).getBeanDefinition());
//        factory.registerSingleton("bean1", new Bean1());
        factory.registerSingleton("bean2", new Bean2());
        factory.registerSingleton("bean3", new Bean3());
        factory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        factory.addEmbeddedValueResolver(new StandardEnvironment()::resolvePlaceholders);
        AutowiredAnnotationBeanPostProcessor postProcessor = new AutowiredAnnotationBeanPostProcessor();
        postProcessor.setBeanFactory(factory);

        Bean1 bean1 = factory.getBean("bean1", Bean1.class);
        postProcessor.postProcessProperties(null, bean1, "bean1");
        System.out.println(bean1);
        System.out.println(bean1.getBean2());
        System.out.println(bean1.getVersion());
    }

    static class Bean1 {
        @Value("${java.specification.version}")
        private String version;

        public String getVersion() {
            return version;
        }

        @Autowired
        private Bean2 bean2;

        public Bean2 getBean2() {
            return bean2;
        }
    }

    static class Bean2 {
    }

    static class Bean3 {
    }
}
