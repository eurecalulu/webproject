package com.example.crawler.service.serviceImpl;


import com.example.crawler.pojo.data;
import com.example.crawler.service.CrawlerService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;


@Component
@WebService(name = "CrawlerServiceImpl",targetNamespace = "http://crawlerservice.example.com",endpointInterface = "com.example.crawler.service.CrawlerService")
public class CrawlerServiceImpl implements CrawlerService {

    @Override
    public String getData() {
        return "Doc infos";
    }

}
