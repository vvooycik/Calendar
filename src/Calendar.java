import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Calendar {
    LocalTime workStart;
    LocalTime workEnd;
    List<Meeting> meetings;

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

    public boolean tryNewEvent(LocalTime start, Duration duration){
        if(start.isAfter(workStart) && start.plus(duration).isBefore(workEnd)){
            AtomicBoolean isOk = new AtomicBoolean(true);
            meetings.forEach(m -> {
                if((start.isAfter(m.startTime) && start.isBefore(m.startTime.plus(m.duration))) ||
                    start.plus(duration).isAfter(m.startTime) && start.plus(duration).isBefore(m.startTime.plus(m.duration))){
                    isOk.set(false);
                }
            });
            return isOk.get();
        }
        else
            return false;
    }

    public void addEvent(Meeting meeting){
        meetings.add(meeting);
    }

}
