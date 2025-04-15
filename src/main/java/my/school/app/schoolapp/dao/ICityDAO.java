package my.school.app.schoolapp.dao;

import my.school.app.schoolapp.model.City;

import java.sql.SQLException;
import java.util.List;

public interface ICityDAO {
    List<City> getAll() throws SQLException;
}
