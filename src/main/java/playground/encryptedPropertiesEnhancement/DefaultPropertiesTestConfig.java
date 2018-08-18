package playground.encryptedPropertiesEnhancement;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@PropertySources({ @PropertySource("classpath:test.properties") })
public class DefaultPropertiesTestConfig {

	@Bean
	static EncryptablePropertySourcesPlaceholderConfigurer encryptablePropertySourcesPlaceholderConfigurer(
			ConfigurableEnvironment env) {
		StringEncryptor stringEncryptor = new StringEncryptor() {

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

		EncryptablePropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new EncryptablePropertySourcesPlaceholderConfigurer(
				env, stringEncryptor);
		return propertySourcesPlaceholderConfigurer;
	}

	@Bean
	public DefaultPropertiesTest propertiesTest(@Value("${some.property}") String someProperty,
			@Value("${some.property2}") String someProperty2) {
		System.out.println("System.getenv:" + System.getenv("some.property"));
		System.out.println("${some.property}:" + someProperty);
		System.out.println("${some.property2}:" + someProperty2);
		return new DefaultPropertiesTest();
	}

}
