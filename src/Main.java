import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Calendar calendar1 = new Calendar();
        Calendar calendar2 = new Calendar(LocalTime.of(12, 00), LocalTime.of(20, 00));
        calendar1.addEvent(new Meeting(LocalTime.of(10,00), Duration.ofMinutes(60)));
        calendar1.addEvent(new Meeting(LocalTime.of(10,00), Duration.ofMinutes(60))); // shouldn't be added
        calendar2.addEvent(new Meeting(LocalTime.of(12, 00), Duration.ofMinutes(60)));

        System.out.println(calendar1);
        System.out.println(calendar2);


    }

//    public static List<LocalTime[]> getPossibleMeetingTime(Calendar calendar1, Calendar calendar2){
//
//    }
}
