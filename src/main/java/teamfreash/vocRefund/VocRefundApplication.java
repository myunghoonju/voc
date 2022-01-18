package teamfreash.vocRefund;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VocRefundApplication {

	public static void main(String[] args) {
		SpringApplication.run(VocRefundApplication.class, args);
	}

}
