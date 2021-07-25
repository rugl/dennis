package com.controllers;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.CurrencyDTO;
import com.service.CurrencyService;

@RestController
public class CurrencyController
{
    @Autowired
    CurrencyService currencyService;
    
    private Logger log = LogManager.getLogger(CurrencyController.class);
    
    @RequestMapping(value ="/loadData",produces = "application/json", method = RequestMethod.GET)
    public List<CurrencyDTO> loadData(){
        List<CurrencyDTO> list = currencyService.transferNewDataFormatAPI("null");
        return list;
    }
    
    @RequestMapping(value ="/loadNewTime",produces = "application/json", method = RequestMethod.GET)
    public List<CurrencyDTO> loadNewTime(){
    	List<CurrencyDTO> list = currencyService.transferNewDataFormatAPI("new");
    	return list;
    }

    @RequestMapping(value ="/search" ,consumes = "application/json",produces = "application/json", method = RequestMethod.GET)
    public Map<String, String> searchByCode(@RequestBody CurrencyDTO coinDto){
        log.info("CurrencyController search , code:"+ coinDto.getCode());
        Map<String, String> map = currencyService.searchByCode(coinDto);
        return map;
    }

    @RequestMapping(value ="/add" ,consumes = "application/json",produces = "application/json", method = RequestMethod.POST)
    public Map<String, String> addByCode(@RequestBody CurrencyDTO coinDto){
    	log.info("CurrencyController add , code:"+ coinDto.getCode());
        Map<String, String> map = currencyService.addByCode(coinDto);
        return map;
    }

    @RequestMapping(value ="/update" ,consumes = "application/json",produces = "application/json", method = RequestMethod.POST)
    public Map<String, String> updateByCode(@RequestBody CurrencyDTO coinDto){
        log.info("CurrencyController update , code:"+ coinDto.getCode());
        Map<String, String> map = currencyService.updateByCode(coinDto);
        return map;
    }

    @RequestMapping(value ="/delete" ,consumes = "application/json",produces = "application/json", method = RequestMethod.POST)
    public Map<String, String> deleteByCode(@RequestBody CurrencyDTO coinDto){
        log.info("CurrencyController delete , code:"+ coinDto.getCode());
        Map<String, String> map = currencyService.deleteByCode(coinDto);
        return map;
    }
}
