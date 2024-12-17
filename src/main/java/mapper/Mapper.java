package mapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Mapper<T> {

    T mapEntity(PreparedStatement statement) throws SQLException;
}
