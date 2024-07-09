package com.example.restexemplo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestexemploApplication  {

	// inserindo valores iniciais
	public static void main(String[] args) {
		SpringApplication.run(RestexemploApplication.class, args);

		System.out.println(" \n \n  Starting ... is server \n \n");
	}
}
/***
public class RestexemploApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestexemploApplication.class, args);
	}

	// inserindo valores iniciais
	public void run(String... args) throws Exception {
		System.out.println(" \n ");
		// System.out.println("Instancia: " + clientRepository);
		System.out.println("\n \n *************  A Aplicação iniciou ************* \n\n");

		var client = new Client("raphael", "m", 38);
		clientRepository.save(client);

		var client1 = new Client("maria sousa", "f", 38);
		clientRepository.save(client1);

		var client2 = new Client("alisson siqueira", "m", 38);
		clientRepository.save(client2);	
	}
}

 */
