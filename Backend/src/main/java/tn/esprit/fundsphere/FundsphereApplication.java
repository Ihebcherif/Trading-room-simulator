package tn.esprit.fundsphere;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.esprit.fundsphere.Entities.TransactionManagement.Portefeuille;
import tn.esprit.fundsphere.Entities.UserManagment.Role;
import tn.esprit.fundsphere.Entities.UserManagment.User;
import tn.esprit.fundsphere.Services.UserService.AuthenticationService;

@EnableScheduling
@SpringBootApplication
public class FundsphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundsphereApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            // Check if admin user already exists
            if (service.isUserExists("admin")) {
                System.out.println("Admin already exists in the database.");
            } else {
                var admin = User.builder()
                        .firstname("Admin")
                        .lastname("Admin")
                        .username("admin")
                        .password("admin")
                        .role(Role.ADMIN)
                        .portefeuille(new Portefeuille())
                        .build();
                System.out.println("Admin token: " + service.register(admin).getAccessToken());
            }
        };
    }
}