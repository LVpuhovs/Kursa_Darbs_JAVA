package lv.venta;

import lv.venta.model.Driver;
import lv.venta.model.DriverStandings;
import lv.venta.model.Race;
import lv.venta.model.RaceResult;
import lv.venta.model.Team;
import lv.venta.repo.IDriverRepo;
import lv.venta.repo.IDriverStandingsRepo;
import lv.venta.repo.IRaceRepo;
import lv.venta.repo.IRaceResultRepo;
import lv.venta.repo.ITeamRepo;
import lv.venta.repo.ITeamStandingsRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class F1WebJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(F1WebJavaApplication.class, args);
    }
    @Bean
    public CommandLineRunner testDatabaseLayer(IDriverRepo driverRepo, ITeamRepo teamRepo, IRaceRepo raceRepo, IRaceResultRepo raceResultRepo, 
    		IDriverStandingsRepo driverStandingsRepo, ITeamStandingsRepo teamStandingsRepo) {
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
                
                Race race1 = new Race("Japan");     
                raceRepo.save(race1);
                Race race2 = new Race("Monaco");   
                raceRepo.save(race2);

                List<RaceResult> raceResult1 = List.of(
                		new RaceResult(race1, d1, 1),
                        new RaceResult(race1, d2, 2),
                        new RaceResult(race1, d3, 3),
                        new RaceResult(race1, d4, 4),
                        new RaceResult(race1, d5, 5),
                        new RaceResult(race1, d6, 6),
                        new RaceResult(race1, d7, 7),
                        new RaceResult(race1, d8, 8),
                        new RaceResult(race1, d9, 9),
                        new RaceResult(race1, d10, 10),
                        
                        new RaceResult(race1, d11, 11),
                        new RaceResult(race1, d12, 12),
                        new RaceResult(race1, d13, 13),
                        new RaceResult(race1, d14, 14),
                        new RaceResult(race1, d15, 15),
                        new RaceResult(race1, d16, 16),
                        new RaceResult(race1, d17, 17),
                        new RaceResult(race1, d18, 18),
                        new RaceResult(race1, d19, 19),
                        new RaceResult(race1, d20, 20)
                		);
                raceResultRepo.saveAll(raceResult1);
                
                List<DriverStandings> drivStand1 = new ArrayList<>();
                for(RaceResult raceResult : raceResult1) {
                	DriverStandings drivStand = new DriverStandings(raceResult.getDriver(), raceResult , 1);
                	drivStand.calculatePoints();
                	drivStand1.add(drivStand);                	
                }
                driverStandingsRepo.saveAll(drivStand1);
               
                
                
                
                List<RaceResult> raceResult2 = List.of(
                		new RaceResult(race2, d1, 10),
                        new RaceResult(race2, d2, 1),
                        new RaceResult(race2, d3, 4),
                        new RaceResult(race2, d4, 3),
                        new RaceResult(race2, d5, 6),
                        new RaceResult(race2, d6, 5),
                        new RaceResult(race2, d7, 8),
                        new RaceResult(race2, d8, 7),
                        new RaceResult(race2, d9, 1),
                        new RaceResult(race2, d10, 11),
                        
                        new RaceResult(race2, d11, 20),
                        new RaceResult(race2, d12, 12),
                        new RaceResult(race2, d13, 13),
                        new RaceResult(race2, d14, 14),
                        new RaceResult(race2, d15, 9),
                        new RaceResult(race2, d16, 16),
                        new RaceResult(race2, d17, 17),
                        new RaceResult(race2, d18, 18),
                        new RaceResult(race2, d19, 19),
                        new RaceResult(race2, d20, 15)
                		);
                raceResultRepo.saveAll(raceResult2);
                
                List<DriverStandings> drivStand2 = new ArrayList<>();
                for(RaceResult raceResult : raceResult2) {
                	DriverStandings drivStandings = new DriverStandings(raceResult.getDriver(), raceResult , 2);
                	drivStandings.calculatePoints();
                	drivStand2.add(drivStandings);                	
                }
                driverStandingsRepo.saveAll(drivStand2);
                
                
                
            }
        };
    }
}
