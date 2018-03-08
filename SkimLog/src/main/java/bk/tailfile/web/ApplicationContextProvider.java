package bk.tailfile.web;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContext;

public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext context;
 
    public static ApplicationContext getApplicationContext() {
        return context;
    }
 
    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        context = ctx;
    }
}