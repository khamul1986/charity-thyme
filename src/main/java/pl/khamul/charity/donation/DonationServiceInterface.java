package pl.khamul.charity.donation;

public interface DonationServiceInterface {

    int totalDonationsQuantity();

    int totalDonationsNumber();

    void saveDonation(Donation donation);
}
