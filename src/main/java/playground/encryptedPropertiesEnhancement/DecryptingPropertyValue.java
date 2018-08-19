package playground.encryptedPropertiesEnhancement;

public interface DecryptingPropertyValue {

	boolean shouldBeDecrypted(String propertyValue);
	
	String decrypt(String encryptedPropertyValue);
}
