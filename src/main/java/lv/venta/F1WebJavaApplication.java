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
                Driver d1 = new Driver("Lewis", "Hamilton", 44);
                Driver d2 = new Driver("George", "Russell", 63);
                Driver d3 = new Driver("Max", "Verstappen", 1);
                Driver d4 = new Driver("Sergio", "Perez", 11);
                Driver d5 = new Driver("Charles", "Leclerc", 16);
                Driver d6 = new Driver("Carlos", "Sainz", 55);
                Driver d7 = new Driver("Lando", "Norris", 4);
                Driver d8 = new Driver("Oscar", "Piastri", 81);
                Driver d9 = new Driver("Fernando", "Alonso", 14);
                Driver d10 = new Driver("Lance", "Stroll", 18);

                Driver d11 = new Driver("Pierre", "Gasly", 10);
                Driver d12 = new Driver("Esteban", "Ocon", 31);
                Driver d13 = new Driver("Daniel", "Ricciardo", 3);
                Driver d14 = new Driver("Yuki", "Tsunoda", 22);
                Driver d15 = new Driver("Valtteri", "Bottas", 77);
                Driver d16 = new Driver("Guanyu", "Zhou", 24);
                Driver d17 = new Driver("Alex", "Albon", 23);
                Driver d18 = new Driver("Logan", "Saregant", 2);
                Driver d19 = new Driver("Kevin", "Magnussen", 20);
                Driver d20 = new Driver("Nico", "Hulkenberg", 27);

                Team t1 = new Team("Mercedes", d1, d2);
                teamRepo.save(t1);
                driverRepo.save(d1);
                driverRepo.save(d2);

                Team t2 = new Team("Red Bull", d3, d4);
                teamRepo.save(t2);
                driverRepo.save(d3);
                driverRepo.save(d4);
                Team t3 = new Team("Ferrari", d5, d6);
                teamRepo.save(t3);
                driverRepo.save(d5);
                driverRepo.save(d6);
                Team t4 = new Team("McLaren", d7, d8);
                teamRepo.save(t4);
                driverRepo.save(d7);
                driverRepo.save(d8);
                Team t5 = new Team("Aston Martin", d9, d10);
                teamRepo.save(t5);
                driverRepo.save(d9);
                driverRepo.save(d10);
                Team t6 = new Team("Alpine", d11, d12);
                teamRepo.save(t6);
                driverRepo.save(d11);
                driverRepo.save(d12);
                Team t7 = new Team("Alpha Tauri", d13, d14);
                teamRepo.save(t7);
                driverRepo.save(d13);
                driverRepo.save(d14);
                Team t8 = new Team("Sauber", d15, d16);
                teamRepo.save(t8);
                driverRepo.save(d15);
                driverRepo.save(d16);
                Team t9 = new Team("Williams", d17, d18);
                teamRepo.save(t9);
                driverRepo.save(d17);
                driverRepo.save(d18);
                Team t10 = new Team("Haas", d19, d20);
                teamRepo.save(t10);
                driverRepo.save(d19);
                driverRepo.save(d20);
            }
        };
    }

}
