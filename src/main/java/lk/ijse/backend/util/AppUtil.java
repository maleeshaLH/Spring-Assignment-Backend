package lk.ijse.backend.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class AppUtil {

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    public static String createOrderId(){
        return "Order-"+ UUID.randomUUID();
    }
}
