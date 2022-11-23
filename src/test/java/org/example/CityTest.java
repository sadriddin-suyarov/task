package org.example;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class CityTest {
    private static Map<String, List<Integer>> loadedData;
    private static List<City> citiesFromFile;
    private static List<City> citiesFromDB;

    @BeforeClass
    public static void setup() {
        loadedData = CityUtil.loadCitiesFromFile();
        citiesFromFile = CityUtil.sortLoadedCitiesToList(loadedData);
        citiesFromDB = CityUtil.loadCitiesFromDB();
    }

    @Test
    public void printCitiesLoadedFromFile() {
        CityUtil.printCitiesSorted(loadedData);
    }

    @Test
    public void printCitiesFromCombiningDataStructure() {
        CityUtil.printCitiesSorted(citiesFromFile);
    }

    @Test
    public void printCitiesLoadedFromDB() {
        CityUtil.printCitiesSorted(citiesFromDB);
    }
}
