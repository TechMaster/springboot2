package vn.techmaster.demojpa.customrepo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import vn.techmaster.demojpa.model.Person;



public interface PersonRepoCustom {
  Map<String, List<Person>> groupPeopleByCity();
  TreeMap<String, List<Person>> groupPeopleByOrderCity();

  Map<String, CityJobCount> topJobInCity();

  TreeMap<String, List<JobCount>> countAllTopJobsInCity();

  Map<String, Double> averageJobAge();

  LinkedHashMap<String, Double> topAverageJobAge(int top);
}
