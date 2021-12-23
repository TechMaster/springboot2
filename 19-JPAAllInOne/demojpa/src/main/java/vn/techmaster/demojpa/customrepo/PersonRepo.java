package vn.techmaster.demojpa.customrepo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.techmaster.demojpa.model.Person;


@Repository
public interface PersonRepo extends JpaRepository<Person, Long>, 
PersonRepoCustom {

  @Query("SELECT new vn.techmaster.demojpa.customrepo.JobCount(p.job, COUNT(*)) " + 
  "FROM person AS p GROUP BY p.job ORDER BY 2 DESC")
  List<JobCount> countByJob();

  @Query("SELECT new vn.techmaster.demojpa.customrepo.JobCount(p.job, COUNT(*)) " + 
  "FROM person AS p GROUP BY p.job ORDER BY 2 DESC")
  List<JobCount> findTopJobs(Pageable pageable);

  @Query("SELECT new vn.techmaster.demojpa.customrepo.CityJobCount(p.city, p.job, COUNT(*)) " +
  "FROM person AS p GROUP BY p.city, p.job")
  List<CityJobCount> countJobsInCity();

  @Query("SELECT new vn.techmaster.demojpa.customrepo.JobSalary(p.job, AVG(p.salary)) " + 
  "FROM person AS p GROUP BY p.job ORDER BY 2 DESC")
  List<JobSalary> jobAverageSalary();

  @Query("SELECT new vn.techmaster.demojpa.customrepo.CityAvgSalary(city, AVG(salary)) " +
  "FROM person GROUP BY city ORDER BY 2 DESC")
  List<CityAvgSalary> cityAverageSalary(Pageable pageable);

  @Query("SELECT new vn.techmaster.demojpa.customrepo.CityJobCount(city, job, COUNT(job)) " +
  "FROM person GROUP BY city, job HAVING job = ?1 ORDER BY 3 DESC")
  List<CityJobCount> findCitiesHaveMostSpecificJob(String job, Pageable pageable);
}
