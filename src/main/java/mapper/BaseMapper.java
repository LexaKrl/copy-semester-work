package mapper;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseMapper<T> implements Mapper<T> {

    @Override
    public T mapEntity(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return buildEntity(resultSet);
        }
        return null;
    }

    public List<T> mapListEntities(PreparedStatement statement, List<T> list) throws SQLException {
        ResultSet resultSet = statement.executeQuery();

        if (resultSet != null) {
            while (resultSet.next()) {
                list.add(buildEntity(resultSet));
            }
        }
        return list;
    }
}
