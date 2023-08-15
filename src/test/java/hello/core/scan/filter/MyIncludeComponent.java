package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // 얘가  중요
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
