package pl.khamul.charity.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "SELECT COUNT(d) FROM Donation d ")
    int countAllDonations();

    @Query("SELECT SUM(d.quantity) FROM Donation d")
    Optional<Integer> sumDonationsQuantity();

}

