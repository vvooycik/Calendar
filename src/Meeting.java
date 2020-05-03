import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Meeting {
    LocalTime startTime;
    Duration duration;

    public Meeting(LocalTime startTime, Duration duration){
        this.startTime = startTime;
        this.duration = duration;
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
