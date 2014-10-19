package projetweb.repository;

import org.springframework.data.repository.CrudRepository;

import projetweb.model.Employes;


public interface EmployesRepository extends CrudRepository<Employes, Long> {

}