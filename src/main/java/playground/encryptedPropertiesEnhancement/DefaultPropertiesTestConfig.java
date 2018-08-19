package playground.encryptedPropertiesEnhancement;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySources;

@Configuration
@org.springframework.context.annotation.PropertySources({ @PropertySource("classpath:test.properties") })
public class DefaultPropertiesTestConfig {

	@Bean
	static StringEncryptor stringEncryptor() {
		return new StringEncryptor() {

			@Override
			public String encrypt(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String decrypt(String arg0) {
				System.out.println("decrypting:" + arg0);
				return "decrypted " + arg0;
			}
		};
	}

	@Bean
	static DecryptingPropertyValue decryptingPropertyValue() {
		return new JasyptPropertyValueDecryptor(stringEncryptor());
	}

	@Bean
	static EncryptablePropertySourcesPlaceholderConfigurer encryptablePropertySourcesPlaceholderConfigurer(
			ConfigurableEnvironment env) {

		EncryptablePropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new EncryptablePropertySourcesPlaceholderConfigurer(
				new EncryptablePropertySourcesPropertyResolver(env.getPropertySources(), decryptingPropertyValue()));
		return propertySourcesPlaceholderConfigurer;
	}

	@Bean
	public DefaultPropertiesTest propertiesTest(@Value("${some.property}") String someProperty,
			@Value("${some.property2}") String someProperty2, @Value("${some.property3}") String someProperty3) {
		System.out.println("System.getenv:" + System.getenv("some.property"));
		System.out.println("${some.property}:" + someProperty);
		System.out.println("${some.property2}:" + someProperty2);
		return new DefaultPropertiesTest();
	}

}
