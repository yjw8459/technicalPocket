package yjw.technical.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public String yyyymmddHH24Miss(){
        return DateTimeFormatter.ofPattern("yyyyMMddkkhhss").format(LocalDateTime.now());
    }

}
