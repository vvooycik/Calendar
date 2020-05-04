import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Calendar {
    LocalTime workStart;
    LocalTime workEnd;
    private List<Meeting> meetings;

    public Calendar(){
        workStart = LocalTime.of(9,0);
        workEnd = LocalTime.of(17,0);
        meetings = new ArrayList<>();
    }
    public Calendar(LocalTime workStart, LocalTime workEnd){
        this.workStart = workStart;
        this.workEnd = workEnd;
        meetings = new ArrayList<>();
    }

    public List<LocalTime[]> getPossibleStartTimes(Duration duration){
        List<LocalTime[]> result = new ArrayList<>();
        meetings.sort(Comparator.comparing(m -> m.startTime));
        for(int i = 0; i<meetings.size()-1; i++){
            if(Duration.between(meetings.get(i).endTime, meetings.get(i+1).startTime).compareTo(duration)>0){
                result.add(new LocalTime[]{meetings.get(i).endTime, meetings.get(i + 1).startTime});
            }
        }
        if(meetings.get(meetings.size()-1).endTime.isBefore(workEnd))       //if the last meeting ends before workEnd
            result.add(new LocalTime[]{meetings.get(meetings.size()-1).endTime, workEnd});
        return result;
    }

    public boolean addEvent(LocalTime startTime, Duration duration){

        return true;
    }
    public boolean addEvent(LocalTime startTime, LocalTime endTime){

        return true;
    }
    public boolean addEvent(Meeting meeting){

        return meetings.add(meeting);
    }

}
