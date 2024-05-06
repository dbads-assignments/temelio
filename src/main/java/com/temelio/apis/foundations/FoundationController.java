package com.temelio.apis.foundations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/foundation")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class FoundationController {

    @Autowired
    private FoundationService foundationService;

    @GetMapping("")
    public ResponseEntity<List<FoundationModel>> getAllFoundations() {
        try {
            List<FoundationModel> foundations = foundationService.getAllFoundations();

            if (foundations == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(foundations, HttpStatus.OK);
        } catch (Exception exception) {
            // logging for exception into error.logs

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<FoundationModel>> getFoundationById(@PathVariable Long id) {
        try {
            Optional<FoundationModel> foundation = foundationService.getFoundationById(id);

            if (foundation.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(foundation, HttpStatus.OK);
        } catch (Exception exception) {
            // logging for exception into error.logs

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<FoundationModel> createFoundation(@RequestBody FoundationModel newFoundationData) {
        try {
            FoundationModel foundation = foundationService.createFoundation(newFoundationData);

            return new ResponseEntity<>(foundation, HttpStatus.OK);
        } catch (Exception exception) {
            // logging for exception into error.logs

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("{id}")
    public ResponseEntity<FoundationModel> updateFoundation(@PathVariable Long id, @RequestBody FoundationModel newFoundationData) {
        try {
            FoundationModel updatedFoundation = foundationService.updateFoundation(id, newFoundationData);
            if (updatedFoundation != null) return new ResponseEntity<>(updatedFoundation, HttpStatus.OK);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            // logging for exception into error.logs

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteFoundation(@PathVariable Long id) {
        try {
            foundationService.deleteFoundation(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            // logging for exception into error.logs
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
