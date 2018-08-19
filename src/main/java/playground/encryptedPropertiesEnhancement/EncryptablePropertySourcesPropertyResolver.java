package playground.encryptedPropertiesEnhancement;

import org.springframework.core.env.PropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;

public class EncryptablePropertySourcesPropertyResolver extends PropertySourcesPropertyResolver
		implements ResolvingEncryptedProperties {

	private final DecryptingPropertyValue decryptingPropertyValue;

	public EncryptablePropertySourcesPropertyResolver(PropertySources propertySources,
			DecryptingPropertyValue decryptingPropertyValue) {
		super(propertySources);
		this.decryptingPropertyValue = decryptingPropertyValue;
	}

	@Override
	public String resolvePlaceholders(String text) {
		String resolvedText = super.resolvePlaceholders(text);
		return decryptIfRequired(resolvedText);
	}

	@Override
	public String resolveRequiredPlaceholders(String text) {
		String resolvedText = super.resolveRequiredPlaceholders(text);
		return decryptIfRequired(resolvedText);
	}

	private String decryptIfRequired(String originalValue) {
		if (!decryptingPropertyValue.shouldBeDecrypted(originalValue)) {
			return originalValue;
		}
		return decryptingPropertyValue.decrypt(originalValue);
	}
}
