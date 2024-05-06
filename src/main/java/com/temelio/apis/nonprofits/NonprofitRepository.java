package com.temelio.apis.nonprofits;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NonprofitRepository extends JpaRepository<NonprofitModel, Long> {
    List<NonprofitModel> findByEmailIn(List<String> emails);
}
