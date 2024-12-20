package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Mapper<T> {

    T mapEntity(PreparedStatement statement) throws SQLException;

    T buildEntity(ResultSet resultSet) throws SQLException;

    List<T> mapListEntities(PreparedStatement statement, List<T> list) throws SQLException;
}
