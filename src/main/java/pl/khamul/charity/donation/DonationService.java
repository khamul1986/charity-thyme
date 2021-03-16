package pl.khamul.charity.donation;


import org.springframework.stereotype.Service;

@Service
public class DonationService implements DonationServiceInterface {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public int totalDonationsQuantity() {
    return donationRepository.sumDonationsQuantity().orElse(0);
    }


    public int totalDonationsNumber(){

        return donationRepository.countAllDonations();

    }



    public void saveDonation(Donation donation){

        donationRepository.save(donation);
    }
}
