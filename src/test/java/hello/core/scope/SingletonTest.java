package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBeen.class);

        SingletonBeen singletonBeen1 = ac.getBean(SingletonBeen.class);
        SingletonBeen singletonBeen2 = ac.getBean(SingletonBeen.class);
        System.out.println("singletonBeen1 = " + singletonBeen1);
        System.out.println("singletonBeen2 = " + singletonBeen2);

        assertThat(singletonBeen1).isSameAs(singletonBeen2);

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBeen {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBeen.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBeen.destroy");
        }
    }
}
