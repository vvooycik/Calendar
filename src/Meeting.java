import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Meeting {
    LocalTime startTime;
    Duration duration;
    LocalTime endTime;

    public Meeting(LocalTime startTime, Duration duration){
        this.startTime = startTime;
        this.duration = duration;
        endTime = startTime.plus(duration);
    }
    public Meeting(LocalTime startTime, LocalTime endTime){
        this.startTime = startTime;
        this.duration = Duration.between(startTime,endTime);
        this.endTime = endTime;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.
                append("start: \"").
                append(DateTimeFormatter.ofPattern("hh:mm").format(startTime)).
                append("\"\n").
                append("end: \"").
                append(DateTimeFormatter.ofPattern("hh:mm").format(startTime.plus(duration)));
        return sb.toString();
    }

}
