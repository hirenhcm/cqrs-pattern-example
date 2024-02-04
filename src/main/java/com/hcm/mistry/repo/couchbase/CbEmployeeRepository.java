package com.hcm.mistry.repo.couchbase;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcm.mistry.couchbase.entity.CbEmployee;

@Repository
public interface CbEmployeeRepository extends CrudRepository<CbEmployee, String> {
//    List<CbEmployee> findByFirstName(String firstName);
//
//    List<CbEmployee> findByLastName(String lastName);
}
