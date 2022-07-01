package net.clarenceho.prisoners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.IntStream;

@SpringBootApplication
public class App implements CommandLineRunner{
  private static final Logger LOG = LoggerFactory
      .getLogger(App.class);

  private static final int NUM_PRISONER = 100;
  private static final int TRIAL_COUNT = 1_000_000;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Override
  public void run(String... arg0) {
    int winCount = IntStream.rangeClosed(1, TRIAL_COUNT)
        .parallel()
        .map(i -> {
          PrisonerProblem problem = new PrisonerProblem(NUM_PRISONER);
          return problem.prisonersWin()? 1 : 0;
        })
        .reduce(0, Integer::sum);

    LOG.info("Winning percentage = " + ((double)winCount / TRIAL_COUNT));
  }
}