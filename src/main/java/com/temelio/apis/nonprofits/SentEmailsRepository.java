package com.temelio.apis.nonprofits;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SentEmailsRepository extends JpaRepository<SentEmailsModel, Long> {
}
