package lv.venta;

import lv.venta.model.Driver;
import lv.venta.model.Team;
import lv.venta.repo.IDriverRepo;
import lv.venta.repo.ITeamRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

@SpringBootApplication
public class F1WebJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(F1WebJavaApplication.class, args);
    }
    @Bean
    public CommandLineRunner testDatabaseLayer(IDriverRepo driverRepo, ITeamRepo teamRepo) {
        return new CommandLineRunner() {

            @Override
            public void run(String... args) throws Exception {
               Driver d1 =  new Driver("Lewis", "Hamilton", 44);
               Driver d2 = new Driver("George", "Russell", 63);

                Team t1 = new Team("Mercedes", d1, d2);
                teamRepo.save(t1);
                driverRepo.save(d1);
                driverRepo.save(d2);

            }
        };
    }

}
