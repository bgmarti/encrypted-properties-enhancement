package playground.encryptedPropertiesEnhancement;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;

public class JasyptPropertyValueDecryptor implements DecryptingPropertyValue {

	private final StringEncryptor stringEncryptor;

	public JasyptPropertyValueDecryptor(StringEncryptor stringEncryptor) {
		this.stringEncryptor = stringEncryptor;
	}

	@Override
	public boolean shouldBeDecrypted(String propertyValue) {
		return PropertyValueEncryptionUtils.isEncryptedValue(propertyValue);
	}

	@Override
	public String decrypt(String encryptedPropertyValue) {
		return PropertyValueEncryptionUtils.decrypt(encryptedPropertyValue, stringEncryptor);
	}

}
