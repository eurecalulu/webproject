package com.example.crawler.service;


import com.example.crawler.pojo.data;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService(name = "CrawlerService",targetNamespace = "http://crawlerservice.example.com")
public interface CrawlerService {
    @WebMethod
    String getData();

}
