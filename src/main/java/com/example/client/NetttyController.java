package com.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by win7 on 2017/4/12.
 */
@RestController
@RequestMapping(value = "netty")
public class NetttyController {
    @Autowired
    FileTransferClient fileTransferClient;
    @GetMapping(value = "go")
    public  void  go(){
        System.err.println("sdff");
        System.err.println("sdff");
        System.err.println("sdff");
        System.err.println("sdff");
          fileTransferClient.go();
    }
}
