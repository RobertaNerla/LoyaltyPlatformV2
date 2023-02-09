package it.unicam.cs.ids.loyaltyplatform.dao;

import it.unicam.cs.ids.loyaltyplatform.tracker.ProgrammaAPuntiTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgrammaAPuntiTrackerRepository extends JpaRepository<ProgrammaAPuntiTracker, Long> {
    Optional<ProgrammaAPuntiTracker> findProgrammaAPuntiTrackerBy
}
