import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


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

    public boolean isEventPossible(Meeting event){
        AtomicBoolean isOk = new AtomicBoolean(true);
        meetings.forEach(m -> {
            if(event.startTime.isAfter(m.startTime) && event.startTime.isBefore(m.endTime) ||
               event.endTime.isAfter(m.startTime) && event.endTime.isBefore(m.endTime)){            //either start of the event is during another event or its end is
                isOk.set(false);
            }
        });
        return isOk.get();
    }
    public boolean addEvent(LocalTime startTime, Duration duration){
        Meeting tmp = new Meeting(startTime, duration);
        if(isEventPossible(tmp))
            return meetings.add(tmp);
        return false;
    }
    public boolean addEvent(LocalTime startTime, LocalTime endTime){
        Meeting tmp = new Meeting(startTime, endTime);
        if(isEventPossible(tmp))
            return meetings.add(tmp);
        return false;
    }
    public boolean addEvent(Meeting meeting){
        if(isEventPossible(meeting))
            return meetings.add(meeting);
        return false;
    }

}
