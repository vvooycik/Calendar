import java.time.Duration;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args){
        Calendar person1 = new Calendar();
        Calendar person2 = new Calendar(LocalTime.of(12, 00), LocalTime.of(20, 00));
        person1.addEvent(new Meeting(LocalTime.of(10,00), Duration.ofMinutes(60)));
        person2.addEvent(new Meeting(LocalTime.of(12, 00), Duration.ofMinutes(60)));

        Meeting test1 =
    }
}
