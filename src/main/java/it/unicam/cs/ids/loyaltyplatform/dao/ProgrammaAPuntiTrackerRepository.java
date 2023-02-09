package it.unicam.cs.ids.loyaltyplatform.dao;

import it.unicam.cs.ids.loyaltyplatform.tracker.ProgrammaAPuntiTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammaAPuntiTrackerRepository extends JpaRepository<ProgrammaAPuntiTracker, Long> {
}
