package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.model.SimCard;
import au.com.telstra.simcardactivator.service.SimCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sim-cards")
public class SimCardController {

    @Autowired
    private SimCardService simCardService;

    @PostMapping("/activate")
    public String activateSimCard(@RequestBody SimCard simCard) {
        boolean isSuccess = simCardService.activateSimCard(simCard);
        return isSuccess ? "Activation successful" : "Activation failed";
    }

    @GetMapping("/status")
    public SimCard getSimCardStatus(@RequestParam long simCardId) {
        return simCardService.getSimCardStatus(simCardId);
    }
}
