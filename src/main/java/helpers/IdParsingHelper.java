package helpers;


public class IdParsingHelper {

    public static Long parseId(String id) {
        if (id != null) {
            return Long.parseLong(id);
        } else {
            return -1L;
        }
    }
}
