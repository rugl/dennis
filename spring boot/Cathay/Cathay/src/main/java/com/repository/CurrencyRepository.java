package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.entity.Currency;

public interface CurrencyRepository extends CrudRepository< Currency, String >
{
	List< Currency > findByCode( String code );

}
