package playground.encryptedPropertiesEnhancement;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.springframework.core.env.PropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;

public class EncryptablePropertySourcesPropertyResolver extends PropertySourcesPropertyResolver {

	private final StringEncryptor stringEncryptor;

    public EncryptablePropertySourcesPropertyResolver(PropertySources propertySources, StringEncryptor stringEncryptor) {
        super(propertySources);
        this.stringEncryptor = stringEncryptor;
    }

    @Override
    public String resolvePlaceholders(String text) {
        String resolvedText = super.resolvePlaceholders(text);
        return tryDecrypt(resolvedText);
    }

    @Override
    public String resolveRequiredPlaceholders(String text) {
        String resolvedText = super.resolveRequiredPlaceholders(text);
        return tryDecrypt(resolvedText);
    }
    
    private String tryDecrypt(String originalValue) {
		if (!PropertyValueEncryptionUtils.isEncryptedValue(originalValue)) {
			return originalValue;
		}
		return PropertyValueEncryptionUtils.decrypt(originalValue,
					this.stringEncryptor);
	}
}
