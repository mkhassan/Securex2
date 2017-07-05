package byAJ.Securex.configs;



import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;



public class CommonBeanConfig {
	
	@Bean
	
	public StrongPasswordEncryptor strongEncryptor() {
		
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor;
	}

}
