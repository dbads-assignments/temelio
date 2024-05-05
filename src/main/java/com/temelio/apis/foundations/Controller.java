package com.temelio.apis.foundations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/foundation")
public class Controller {

    @Autowired
    private FoundationService foundationService;

    @GetMapping("")
    public ResponseEntity<List<Foundation>> getAllFoundations() {
        try {
            List<Foundation> foundations = foundationService.getAllFoundations();

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
    public ResponseEntity<Optional<Foundation>> getFoundationById(@PathVariable Long id) {
        try {
            Optional<Foundation> foundation = foundationService.getFoundationById(id);

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
    public ResponseEntity<Foundation> createFoundation(@RequestBody Foundation newFoundationData) {
        try {
            Foundation foundation = foundationService.createFoundation(newFoundationData);

            return new ResponseEntity<>(foundation, HttpStatus.OK);
        } catch (Exception exception) {
            // logging for exception into error.logs

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("{id}")
    public ResponseEntity<Foundation> updateFoundation(@PathVariable Long id, @RequestBody Foundation newFoundationData) {
        try {
            Foundation updatedFoundation = foundationService.updateFoundation(id, newFoundationData);
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
