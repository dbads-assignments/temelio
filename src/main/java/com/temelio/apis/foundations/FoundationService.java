package com.temelio.apis.foundations;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class FoundationService {

    @Autowired
    private FoundationRepository foundationRepository;

    public List<Foundation> getAllFoundations() {
        try {
            List<Foundation> foundations = new ArrayList<>(foundationRepository.findAll());

            if (foundations.isEmpty()) {
                return null;
            }

            return foundations;
        } catch (Exception exception) {
            // logging for exception into error.logs
            throw new RuntimeException("Something went wrong", exception);
        }
    }

    public Optional<Foundation> getFoundationById(Long id) {
        return foundationRepository.findById(id);
    }

    public Foundation createFoundation(Foundation newFoundationData) {
        return foundationRepository.save(newFoundationData);
    }

    public Foundation updateFoundation(Long id, Foundation newFoundationData) {
        Optional<Foundation> existingFoundation = foundationRepository.findById(id);

        if (existingFoundation.isPresent()) {
            Foundation updatedFoundation = existingFoundation.get();

            if (newFoundationData.getName() != null) updatedFoundation.setName(updatedFoundation.getName());
            if (newFoundationData.getEmail() != null) updatedFoundation.setEmail(updatedFoundation.getEmail());
            if (newFoundationData.getAddress() != null) updatedFoundation.setAddress(updatedFoundation.getAddress());
//            if (newFoundationData.getEstablishedDate() != null) updatedFoundation.setEstablishedDate(updatedFoundation.getEstablishedDate());

            return updatedFoundation;
        }

        return null;
    }

    public void deleteFoundation(Long id) {
        foundationRepository.deleteById(id);
    }
}
