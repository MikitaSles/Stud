import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        List<Trial> trials = new ArrayList<>();
        trials.add(new Trial("Trainee1", 80, 90));
        trials.add(new LightTrial("Trainee2", 100, 100));
        trials.add(new StrongTrial("Trainee3", 90, 100));
        trials.add(new ExtraTrial("Trainee4", 70, 80, 60));
        trials.add(new LightTrial("Trainee5", 80, 80));
        trials.add(new Trial("Trainee6", 60, 70));
        trials.add(new StrongTrial("Trainee7", 70, 80));
        trials.add(new ExtraTrial("Trainee8", 80, 90, 40));
        trials.add(new ExtraTrial("Trainee9", 100, 100, 70));

        trials.forEach(System.out::println);

        long passedCount = trials.stream().filter(Trial::isPassed).count();
        System.out.println("Number of passed trials: " + passedCount);

        trials.sort(Comparator.comparingInt((Trial t) -> t.mark1 + t.mark2));

        trials.forEach(t -> System.out.println("Sum of marks: " + (t.mark1 + t.mark2)));

        List<Trial> unpassedTrials = new ArrayList<>();
        for (Trial trial : trials) {
            if (!trial.isPassed()) {
                unpassedTrials.add(trial);
                trial.mark1 = 0;
                trial.mark2 = 0;
            }
        }
        System.out.println("Unpassed trials after clearing marks:");
        unpassedTrials.forEach(System.out::println);

        boolean allFailed = unpassedTrials.size() == trials.size();
        System.out.println("Are all trials failed? " + allFailed);

        int[] sums = trials.stream().mapToInt(t -> t.mark1 + t.mark2).toArray();

        System.out.print("Sums of marks: ");
        for (int i = 0; i < sums.length; i++) {
            System.out.print(sums[i]);
            if (i != sums.length - 1) {
                System.out.print(", ");
            }
        }
    }
}
