package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class CityUtil {

    public static List<City> loadCitiesFromDB() {
        List<City> cities = new ArrayList<>();
        String jdbcURL = "jdbc:h2:./test";
        String username = "sa";
        String password = "1234";
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             Statement statement = connection.createStatement()){
            String sql = "SELECT CITY_CODE, CITY_NAME FROM TEST.PUBLIC.CITY ORDER BY CITY_NAME, CITY_CODE";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int cityCode = resultSet.getInt("CITY_CODE");
                String cityName = resultSet.getString("CITY_NAME");
                cities.add(new City(cityCode,cityName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    public static Map<String, List<Integer>> loadCitiesFromFile() {
        Map<String, List<Integer>> cities = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/myFile0.csv"))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.startsWith("city_code")) {
                    line = reader.readLine();
                    continue;
                }
                String[] split = line.split(",");
                if (cities.containsKey(split[1])) {
                    List<Integer> values = cities.get(split[1]);
                    List<Integer> newValues = new ArrayList<>(values);
                    newValues.add(Integer.valueOf(split[0]));
                    cities.put(split[1], newValues);
                } else {
                    cities.put(split[1], List.of(Integer.valueOf(split[0])));
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    public static void printCitiesSorted(Map<String, List<Integer>> citiesAsMap) {
        Set<String> cityNames = citiesAsMap.keySet();
        cityNames.stream()
                .sorted(Comparator.naturalOrder())
                .forEach(name -> citiesAsMap.get(name)
                        .stream()
                        .sorted(Comparator.naturalOrder())
                        .forEach(code -> System.out.println(code + "," + name)));
    }

    public static List<City> sortLoadedCitiesToList(Map<String, List<Integer>> citiesAsMap) {
        List<City> cities = new ArrayList<>();
        Set<String> cityNames = citiesAsMap.keySet();
        cityNames
                .forEach(name -> citiesAsMap.get(name)
                        .forEach(code -> cities.add(new City(code, name))));
        return cities;
    }

    public static void printCitiesSorted(List<City> cities) {
        cities.stream()
                .sorted((first, second) -> {
                    if (second.getCityName().compareTo(first.getCityName()) == 0) {
                        return first.getCityCode().compareTo(second.getCityCode());
                    } else {
                        return first.getCityName().compareTo(second.getCityName());
                    }
                }).forEach(city -> System.out.println(city.getCityCode() + "," + city.getCityName()));
    }
}