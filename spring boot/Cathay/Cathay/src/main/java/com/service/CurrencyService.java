package com.service;

import java.util.List;
import java.util.Map;

import com.model.CurrencyDTO;

public interface CurrencyService
{
    String getCurrencyInfoAPI();
    List<CurrencyDTO> transferNewDataFormatAPI(String tag);

    Map<String,String> searchByCode(CurrencyDTO coinDto);
    Map<String,String> addByCode(CurrencyDTO coinDto);
    Map<String,String> updateByCode(CurrencyDTO coinDto);
    Map<String,String> deleteByCode(CurrencyDTO coinDto);

}
