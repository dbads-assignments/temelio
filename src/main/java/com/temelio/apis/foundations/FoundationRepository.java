package com.temelio.apis.foundations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FoundationRepository extends JpaRepository<Foundation, Long> {
}
