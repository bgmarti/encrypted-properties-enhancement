package playground.encryptedPropertiesEnhancement;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.Environment;

public class EncryptablePropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {

    private Environment environment;
     
    private final StringEncryptor stringEncryptor;
    
    public EncryptablePropertySourcesPlaceholderConfigurer(StringEncryptor stringEncryptor) {
    	this.stringEncryptor = stringEncryptor;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                     ConfigurablePropertyResolver propertyResolver) throws BeansException {
        super.processProperties(beanFactoryToProcess,
                new EncryptablePropertySourcesPropertyResolver(((ConfigurableEnvironment) environment).getPropertySources(), stringEncryptor));
    }
}
