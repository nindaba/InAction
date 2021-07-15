package com.yadlings.inactionproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Producer")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @GetMapping
    private void produce(){
        producerService.send();
    }
}
