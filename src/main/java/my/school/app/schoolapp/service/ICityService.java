package my.school.app.schoolapp.service;

import my.school.app.schoolapp.model.City;

import java.sql.SQLException;
import java.util.List;

public interface ICityService {
    List<City> getAllCities() throws SQLException;
}
