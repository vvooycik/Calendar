import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Calendar calendar1 = new Calendar();
        Calendar calendar2 = new Calendar(LocalTime.of(12, 00), LocalTime.of(20, 00));
        calendar1.addEvent(new Meeting(LocalTime.of(9,15), Duration.ofMinutes(60)));
        calendar1.addEvent(new Meeting(LocalTime.of(10,00), Duration.ofMinutes(60))); // shouldn't be added
        calendar1.addEvent(new Meeting(LocalTime.of(10,10), Duration.ofMinutes(65))); // shouldn't be added
        calendar2.addEvent(new Meeting(LocalTime.of(12, 00), Duration.ofMinutes(60)));
        calendar2.addEvent(new Meeting(LocalTime.of(15, 00), Duration.ofMinutes(60)));

        System.out.println("Calendar 1");
        System.out.println(calendar1);
        System.out.println("Calendar 2");
        System.out.println(calendar2);
        System.out.println("Possible meeting hours in Calendar 1");
        calendar1.getPossibleMeetingTime(Duration.ofMinutes(30)).forEach(m ->
                System.out.println("{"+ m[0] + " - " + m[1] + "}")
        );
        System.out.println("Possible meeting hours in Calendar 2");
        calendar2.getPossibleMeetingTime(Duration.ofMinutes(30)).forEach(m ->
                System.out.println("{"+ m[0] + " - " + m[1] + "}")
        );
        System.out.println("\n\n\n");
        System.out.println("When they can have a meeting?");
        getMeetingTime(calendar1,calendar2, Duration.ofMinutes(30)).forEach(m ->
                System.out.println("{"+ m[0] + " - " + m[1] + "}"));
    }

    public static List<LocalTime[]> getMeetingTime(Calendar calendar1, Calendar calendar2, Duration duration){
        List<LocalTime[]> result = new ArrayList<>();
        LocalTime possibleStart = calendar1.workStart.compareTo(calendar2.workStart) > 0 ? calendar2.workStart : calendar1.workStart;
        LocalTime possibleEnd = calendar1.workEnd.compareTo(calendar2.workEnd) > 0 ? calendar1.workEnd : calendar2.workEnd;
        // above variables are time in either is at work

        int[] time1 = new int[(int)Duration.between(possibleStart,possibleEnd).toMinutes()];
        int[] time2 = new int[time1.length];
        int[] common = new int[time1.length];


        List<LocalTime[]> freeTime1 = calendar1.getPossibleMeetingTime(duration);
        List<LocalTime[]> freeTime2 = calendar2.getPossibleMeetingTime(duration);

        for(int i = 0; i<freeTime1.size(); i++){
            LocalTime newStart = freeTime1.get(i)[0].minus(Duration.ofMinutes(possibleStart.getHour()*60+possibleStart.getMinute()));
            LocalTime newEnd = freeTime1.get(i)[1].minus(Duration.ofMinutes(possibleStart.getHour()*60+possibleStart.getMinute()));
            int start = newStart.getHour()*60 + newStart.getMinute();
            int end = newEnd.getHour()*60 + newEnd.getMinute();
            for(int j = start; j<end; j++){
                time1[j] = 1;
            }
        }
        for(int i = 0; i<freeTime2.size(); i++){
            LocalTime newStart = freeTime2.get(i)[0].minus(Duration.ofMinutes(possibleStart.getHour()*60+possibleStart.getMinute()));
            LocalTime newEnd = freeTime2.get(i)[1].minus(Duration.ofMinutes(possibleStart.getHour()*60+possibleStart.getMinute()));
            int start = newStart.getHour()*60 + newStart.getMinute();
            int end = newEnd.getHour()*60 + newEnd.getMinute();
            for(int j = start; j<end; j++){
                time2[j] = 1;
                if(time1[j] == 1)
                    common[j] = 1;
            }
        }
        int counter=0, start = 0;
        for(int i =0; i<common.length; i++){
            if(common[i] == 1){
                start = i;
                while(common[i] == 1){
                    i++;
                    counter++;
                }
                if(counter >= duration.toMinutes()){
                    result.add(new LocalTime[]{possibleStart.plusMinutes(start), possibleStart.plusMinutes(start+counter)});
                    counter = 0;
                }
            }
        }

        return result;

    }
}
