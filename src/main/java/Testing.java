import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Testing {

    public static void main(String []args) throws ParseException {

        System.out.println("=======================================================");

        String dateTimeStr = "2021-03-01 00:00:01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId toTimeZone = ZoneId.of("America/New_York");

        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, formatter);
        ZonedDateTime nyZonedDateTime = localDateTime.atZone(toTimeZone);

        DateTimeFormatter toFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ");
        System.out.println(toFormatter.format(nyZonedDateTime));

        String simpleDateTimeZoned = "2021-03-01T00:00:01-04:00[America/New_York]";
        SimpleDateFormat sdfTimeZoned = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        System.out.println(sdfTimeZoned.parse(simpleDateTimeZoned));
        System.out.println(sdfTimeZoned.format(new Date()));

    }
}