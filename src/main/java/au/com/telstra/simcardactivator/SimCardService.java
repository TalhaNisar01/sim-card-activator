package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.model.SimCard;
import au.com.telstra.simcardactivator.repository.SimCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SimCardService {

    @Autowired
    private SimCardRepository simCardRepository;

    private static final String ACTUATOR_URL = "http://localhost:8444/actuate";

    public boolean activateSimCard(SimCard simCard) {
        RestTemplate restTemplate = new RestTemplate();
        String url = ACTUATOR_URL;
        SimCard requestBody = new SimCard();
        requestBody.setIccid(simCard.getIccid());

        Boolean isSuccess = restTemplate.postForObject(url, requestBody, Boolean.class);

        simCard.setActive(isSuccess);
        simCardRepository.save(simCard);

        return isSuccess;
    }

    public SimCard getSimCardStatus(long simCardId) {
        return simCardRepository.findById(simCardId).orElse(null);
    }
}
