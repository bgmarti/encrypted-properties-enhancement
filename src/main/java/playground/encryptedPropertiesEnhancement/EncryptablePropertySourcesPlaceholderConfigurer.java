package playground.encryptedPropertiesEnhancement;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;

public class EncryptablePropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {

	private final ResolvingEncryptedProperties resolvingEncryptedProperties;

	public EncryptablePropertySourcesPlaceholderConfigurer(ResolvingEncryptedProperties resolvingEncryptedProperties) {
		this.resolvingEncryptedProperties = resolvingEncryptedProperties;
	}

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
			ConfigurablePropertyResolver propertyResolver) throws BeansException {
		super.processProperties(beanFactoryToProcess, resolvingEncryptedProperties);
	}
}
