package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.CurrencyJson;
import com.entity.Currency;
import com.model.CurrencyDTO;
import com.repository.CurrencyRepository;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    private Logger log = LogManager.getLogger(CurrencyServiceImpl.class);
    
    @Value("${coinDesk.path}")
    private String coinDeskPath;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CurrencyJson jsonParser;

    @Autowired
    CurrencyRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String getCurrencyInfoAPI() {
        log.info("start getCurrencyInfoAPI() coinDeskPath = " + coinDeskPath);
        String result = restTemplate.getForObject(coinDeskPath, String.class);
        return result;
    }

    @Override
    public List<CurrencyDTO> transferNewDataFormatAPI(String tag) {
        log.info("start transferNewDataFormatAPI ");
        try {
            String coinResult = getCurrencyInfoAPI();

            List<CurrencyDTO> coins;
            if("new".equals( tag ))
            	coins = jsonParser.parse(coinResult, "new");
            else
            	coins = jsonParser.parse(coinResult, "null");

            Map<String, String> map = getCodeMapChineseCurrencyName("all");

            coins.forEach(coin -> {
                String code = coin.getCode();
                
                if(map.containsKey(code)){
                    String chineseCodeName = map.get(code);
                    coin.setChineseCodeName(chineseCodeName);
                }
            });

            return coins;
        } catch (Exception e) {
//            log.error("loadNewCoinData err " + e);
            
        }
        return new ArrayList<>();
    }



    @Override
    public Map<String,String> searchByCode(CurrencyDTO coinDto) {
        log.info("start searchChineseCurrencyNameConversionalTableByCode ");

        Currency currency = convertToEntity(coinDto);
        Map<String, String> map = getCodeMapChineseCurrencyName(currency.getCode());
        return map;
    }

    @Override
    public Map<String,String> addByCode(CurrencyDTO coinDto) {
        log.info("start addChineseCurrencyNameConversionalTableByCode ");

        Currency currency = convertToEntity(coinDto);
        Map<String, String> map = getCodeMapChineseCurrencyName(currency.getCode());

        if(map.containsKey(currency.getCode())){
            updateByCode(coinDto);
        }else{
            repository.save(currency);
        }
        Map<String, String> resultMap = getCodeMapChineseCurrencyName(currency.getCode());

        return resultMap;
    }

    @Override
    public Map<String,String> updateByCode(CurrencyDTO coinDto) {
        log.info("start updateChineseCurrencyNameConversionalTableByCode ");

        Currency currency = convertToEntity(coinDto);

        Map<String, String> map = getCodeMapChineseCurrencyName(currency.getCode());

        if(map.containsKey(currency.getCode())){
            repository.findByCode(currency.getCode()).forEach(currencyData -> {
                //Get currency info by id
                Optional<Currency> currencyOptional = repository.findById( currencyData.getId());
                currencyOptional.get().setChineseCodeName(coinDto.getChineseCodeName());
                repository.save(currencyOptional.get());
            });
        }else{
            repository.save(currency);
        }
        Map<String, String> resultMap = getCodeMapChineseCurrencyName(currency.getCode());

        return resultMap;
    }

    @Override
    public Map<String,String> deleteByCode(CurrencyDTO coinDto) {
        log.info("start deleteChineseCurrencyNameConversionalTableByCode ");

        Currency currency = convertToEntity(coinDto);

        repository.findByCode(currency.getCode()).forEach(currencyData -> {
            repository.deleteById(currencyData.getId());
        });

        Map<String, String> map  =  getCodeMapChineseCurrencyName(currency.getCode());

        return map;
    }


    private Map<String, String> getCodeMapChineseCurrencyName(String code) {

        Map<String, String> codeMap = new HashMap<String, String>();

        if(code.equals("all")){
            repository.findAll().forEach(currency ->{
                codeMap.put(currency.getCode(), currency.getChineseCodeName());
            });
        }else{
            repository.findByCode(code).forEach(currency -> {
                codeMap.put(currency.getCode(), currency.getChineseCodeName());
            });
        }
        
        return codeMap;
    }

    private Currency convertToEntity(CurrencyDTO coinDto) throws ParseException {
        Currency coin = modelMapper.map(coinDto, Currency.class);
        return coin;
    }

    // private CurrencyDTO convertToDto(Currency coin) {
    //     CurrencyDTO postDto = modelMapper.map(coin, CurrencyDTO.class);
    //     return postDto;
    // }


}