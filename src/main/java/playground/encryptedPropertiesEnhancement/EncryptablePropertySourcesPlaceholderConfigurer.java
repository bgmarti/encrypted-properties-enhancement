package playground.encryptedPropertiesEnhancement;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.Environment;

public class EncryptablePropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {

	private final ConfigurableEnvironment environment;

	private final StringEncryptor stringEncryptor;

	public EncryptablePropertySourcesPlaceholderConfigurer(ConfigurableEnvironment environment,
			StringEncryptor stringEncryptor) {
		this.environment = environment;
		this.stringEncryptor = stringEncryptor;
	}

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
			ConfigurablePropertyResolver propertyResolver) throws BeansException {
		super.processProperties(beanFactoryToProcess,
				new EncryptablePropertySourcesPropertyResolver(environment.getPropertySources(), stringEncryptor));
	}
}
