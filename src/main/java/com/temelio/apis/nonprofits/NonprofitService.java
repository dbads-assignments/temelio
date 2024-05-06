package com.temelio.apis.nonprofits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NonprofitService {

    @Autowired
    private NonprofitRepository nonprofitRepository;

    public String sendEmailsToNonprofits(List<String> emails) {
        String emailTemplate = "Sending money to nonprofit %s at address %s";

        List<NonprofitModel> nonprofits = nonprofitRepository.findByEmailIn(emails);

        for (NonprofitModel nonprofit : nonprofits) {
            String email = emailTemplate.formatted(nonprofit.getName(), nonprofit.getAddress());
            System.out.println("Sending email to " + nonprofit.getEmail());
            System.out.println(email);
        }

        return "sending emails ..";
    }

    public List<NonprofitModel> getAllNonprofits() {
        try {
            List<NonprofitModel> nonprofits = new ArrayList<>(nonprofitRepository.findAll());

            if (nonprofits.isEmpty()) {
                return null;
            }

            return nonprofits;
        } catch (Exception exception) {
            // logging for exception into error.logs
            throw new RuntimeException("Something went wrong", exception);
        }
    }

    public Optional<NonprofitModel> getNonprofitById(Long id) {
        return nonprofitRepository.findById(id);
    }

    public NonprofitModel createNonprofit(NonprofitModel newNonprofitData) {
        return nonprofitRepository.save(newNonprofitData);
    }

    public NonprofitModel updateNonprofit(Long id, NonprofitModel newNonprofitData) {
        Optional<NonprofitModel> existingNonprofit = nonprofitRepository.findById(id);

        if (existingNonprofit.isPresent()) {
            NonprofitModel updatedNonprofit = existingNonprofit.get();

            if (newNonprofitData.getName() != null) updatedNonprofit.setName(updatedNonprofit.getName());
            if (newNonprofitData.getEmail() != null) updatedNonprofit.setEmail(updatedNonprofit.getEmail());
            System.out.println(newNonprofitData.getAddress());
            if (newNonprofitData.getAddress() != null) updatedNonprofit.setAddress(updatedNonprofit.getAddress());
//            if (newNonprofitData.getEstablishedDate() != null) updatedNonprofit.setEstablishedDate(updatedNonprofit.getEstablishedDate());

            nonprofitRepository.save(updatedNonprofit);
            return updatedNonprofit;
        }

        return null;
    }

    public void deleteNonprofit(Long id) {
        nonprofitRepository.deleteById(id);
    }
}
