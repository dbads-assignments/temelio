package com.temelio.apis.foundations;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class FoundationService {

    @Autowired
    private FoundationRepository foundationRepository;

    public List<FoundationModel> getAllFoundations() {
        try {
            List<FoundationModel> foundations = new ArrayList<>(foundationRepository.findAll());

            if (foundations.isEmpty()) {
                return null;
            }

            return foundations;
        } catch (Exception exception) {
            // logging for exception into error.logs
            throw new RuntimeException("Something went wrong", exception);
        }
    }

    public Optional<FoundationModel> getFoundationById(Long id) {
        return foundationRepository.findById(id);
    }

    public FoundationModel createFoundation(FoundationModel newFoundationData) {
        return foundationRepository.save(newFoundationData);
    }

    public FoundationModel updateFoundation(Long id, FoundationModel newFoundationData) {
        Optional<FoundationModel> existingFoundation = foundationRepository.findById(id);

        if (existingFoundation.isPresent()) {
            FoundationModel updatedFoundation = existingFoundation.get();

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
