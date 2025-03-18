package com.app.controller;

import com.app.entity.Evalution.Agent;
import com.app.entity.Evalution.Area;
import com.app.entity.Evalution.CustomerVisit;
import com.app.repository.AgentRepository;
import com.app.repository.AreaRepository;
import com.app.repository.CustomerVisitRepository;
import com.app.service.SmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/crm")
public class CrmController {
   private  AreaRepository areaRepository;
   private AgentRepository agentRepository;
   private CustomerVisitRepository customerVisitRepository;
   private SmsService smsService;

   public CrmController(AreaRepository areaRepository, AgentRepository agentRepository, CustomerVisitRepository customerVisitRepository, SmsService smsService) {
        this.areaRepository = areaRepository;
       this.agentRepository = agentRepository;
       this.customerVisitRepository = customerVisitRepository;
       this.smsService = smsService;
   }
   // http://localhost:8080/api/v1/crm?pinCode=560076
    @GetMapping
    public ResponseEntity<List<Area>>searchAgent(
    @RequestParam String pinCode
    ){
        List<Area> agents = areaRepository.findByPinCode(pinCode);
        return new ResponseEntity<>(agents, HttpStatus.OK);
    }
//    http://localhost:8080/api/v1/crm?customerId=1&agentId=1
    @PutMapping
    public String allocateAgent(
            @RequestParam long customerId,
            @RequestParam long agentId
    ){
        Agent agent = null;
        Optional<Agent> opAgent = agentRepository.findById(agentId);
        if(opAgent.isPresent()){
             agent = opAgent.get();
        }
        CustomerVisit customerVisit = customerVisitRepository.findById(customerId).get();
        customerVisit.setAgent(agent);

        customerVisitRepository.save(customerVisit);

        smsService.sendSms("+919359318161","agent is now Allocateds-9359318151");
        return "Agent is now allocated";
    }

}
