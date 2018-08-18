package playground.encryptedPropertiesEnhancement;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class AppMain {
	
	public static void main(String args[]) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(DefaultPropertiesTestConfig.class);
        context.close();
    }

}
