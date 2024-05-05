package com.temelio.apis.nonprofits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/nonprofit")
public class NonprofitController {

    @Autowired
    private NonprofitService nonProfitService;

    @GetMapping("")
    public ResponseEntity<List<NonprofitModel>> getAllNonprofits() {
        try {
            List<NonprofitModel> Nonprofits = nonProfitService.getAllNonprofits();

            if (Nonprofits == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(Nonprofits, HttpStatus.OK);
        } catch (Exception exception) {
            // logging for exception into error.logs

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<NonprofitModel>> getNonprofitById(@PathVariable Long id) {
        try {
            Optional<NonprofitModel> nonprofit = nonProfitService.getNonprofitById(id);

            if (nonprofit.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(nonprofit, HttpStatus.OK);
        } catch (Exception exception) {
            // logging for exception into error.logs
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<NonprofitModel> createNonprofit(@RequestBody NonprofitModel newNonprofitData) {
        try {
            NonprofitModel nonprofit = nonProfitService.createNonprofit(newNonprofitData);

            return new ResponseEntity<>(nonprofit, HttpStatus.OK);
        } catch (Exception exception) {
            // logging for exception into error.logs

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("{id}")
    public ResponseEntity<NonprofitModel> updateNonprofit(@PathVariable Long id, @RequestBody NonprofitModel newNonprofitData) {
        try {
            NonprofitModel updatedNonprofit = nonProfitService.updateNonprofit(id, newNonprofitData);
            if (updatedNonprofit != null) return new ResponseEntity<>(updatedNonprofit, HttpStatus.OK);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            // logging for exception into error.logs

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteNonprofit(@PathVariable Long id) {
        try {
            nonProfitService.deleteNonprofit(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            // logging for exception into error.logs
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
