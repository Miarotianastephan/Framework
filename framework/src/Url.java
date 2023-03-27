package etu1846.framework.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface Url {
    String url_name();
}
