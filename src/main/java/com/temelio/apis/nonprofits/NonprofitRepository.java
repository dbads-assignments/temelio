package com.temelio.apis.nonprofits;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NonprofitRepository extends JpaRepository<NonprofitModel, Long> {
}
