package ajou.artifact.arti_fact;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArtiFactApplication {

	public static void main(String[] args) {
		
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing()
				.load();
		
		// 환경 변수를 System Property로 설정
		dotenv.entries().forEach(entry -> {
			System.setProperty(entry.getKey(), entry.getValue());
		});
		
		SpringApplication.run(ArtiFactApplication.class, args);
	}

}
