package lv.venta;

import lv.venta.model.*;
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
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
                Race race3 = new Race("Australia");
                raceRepo.save(race3);
                Race race4 = new Race("Italy");
                raceRepo.save(race4);
                Race race5 = new Race("USA");
                raceRepo.save(race5);
                Race race6 = new Race("Germany");
                raceRepo.save(race6);
                Race race7 = new Race("Canada");
                raceRepo.save(race7);
                Race race8 = new Race("Spain");
                raceRepo.save(race8);
                Race race9 = new Race("Romania");
                raceRepo.save(race9);
                Race race10 = new Race("Austria");
                raceRepo.save(race10);

                Random random = new Random();
                List<RaceResult> raceResult1 = generateRaceResults(race1, (List<Driver>) driverRepo.findAll(), random);
                raceResultRepo.saveAll(raceResult1);
                List<DriverStandings> driverStandings1 = generateDriverStandings(raceResult1, 1);
                driverStandingsRepo.saveAll(driverStandings1);
                List<RaceResult> raceResult2 = generateRaceResults(race2, (List<Driver>) driverRepo.findAll(), random);
                raceResultRepo.saveAll(raceResult2);
                List<DriverStandings> driverStandings2 = generateDriverStandings(raceResult2, 2);
                driverStandingsRepo.saveAll(driverStandings2);

                List<RaceResult> raceResult3 = generateRaceResults(race3, (List<Driver>) driverRepo.findAll(), random);
                raceResultRepo.saveAll(raceResult3);
                List<DriverStandings> driverStandings3 = generateDriverStandings(raceResult3, 3);
                driverStandingsRepo.saveAll(driverStandings3);

                List<RaceResult> raceResult4 = generateRaceResults(race4, (List<Driver>) driverRepo.findAll(), random);
                raceResultRepo.saveAll(raceResult4);
                List<DriverStandings> driverStandings4 = generateDriverStandings(raceResult4, 4);
                driverStandingsRepo.saveAll(driverStandings4);

                List<RaceResult> raceResult5 = generateRaceResults(race5, (List<Driver>) driverRepo.findAll(), random);
                raceResultRepo.saveAll(raceResult5);
                List<DriverStandings> driverStandings5 = generateDriverStandings(raceResult5, 5);
                driverStandingsRepo.saveAll(driverStandings5);

                List<RaceResult> raceResult6 = generateRaceResults(race6, (List<Driver>) driverRepo.findAll(), random);
                raceResultRepo.saveAll(raceResult6);
                List<DriverStandings> driverStandings6 = generateDriverStandings(raceResult6, 6);
                driverStandingsRepo.saveAll(driverStandings6);

                List<RaceResult> raceResult7 = generateRaceResults(race7, (List<Driver>) driverRepo.findAll(), random);
                raceResultRepo.saveAll(raceResult7);
                List<DriverStandings> driverStandings7 = generateDriverStandings(raceResult7, 7);
                driverStandingsRepo.saveAll(driverStandings7);

                List<RaceResult> raceResult8 = generateRaceResults(race8, (List<Driver>) driverRepo.findAll(), random);
                raceResultRepo.saveAll(raceResult8);
                List<DriverStandings> driverStandings8 = generateDriverStandings(raceResult8, 8);
                driverStandingsRepo.saveAll(driverStandings8);

                List<RaceResult> raceResult9 = generateRaceResults(race9, (List<Driver>) driverRepo.findAll(), random);
                raceResultRepo.saveAll(raceResult9);
                List<DriverStandings> driverStandings9 = generateDriverStandings(raceResult9, 9);
                driverStandingsRepo.saveAll(driverStandings9);

                List<RaceResult> raceResult10 = generateRaceResults(race10, (List<Driver>) driverRepo.findAll(), random);
                raceResultRepo.saveAll(raceResult10);
                List<DriverStandings> driverStandings10 = generateDriverStandings(raceResult10, 10);
                driverStandingsRepo.saveAll(driverStandings10);
                

                List<Race> races = (List<Race>) raceRepo.findAll();
                List<Team> teams = (List<Team>) teamRepo.findAll();
                for(Race race : races) {
                    for(Team team : teams) {
                        TeamStandings teamStandings = new TeamStandings(team, race);
                        teamStandingsRepo.save(teamStandings);
                    }
                }
            }

            
            
            
            private List<RaceResult> generateRaceResults(Race race, List<Driver> drivers, Random random) {
                List<RaceResult> raceResults = new ArrayList<>();

                List<Integer> positions = new ArrayList<>();
                for (int i = 1; i <= drivers.size(); i++)
                    positions.add(i);
                Collections.shuffle(positions, random);

                for (Driver driver : drivers) {
                    int position = positions.get(driver.getIdD() % positions.size());
                    RaceResult raceResult = new RaceResult(race, driver, position);
                    raceResults.add(raceResult);
                }
                return raceResults;
            }

            private List<DriverStandings> generateDriverStandings(List<RaceResult> raceResults, int raceNumber) {
                List<DriverStandings> driverStandings = new ArrayList<>();
                for (RaceResult raceResult : raceResults) {
                    DriverStandings standing = new DriverStandings(raceResult.getDriver(), raceResult, raceNumber);
                    driverStandings.add(standing);
                }
                return driverStandings;
            }


        };
    }
}
