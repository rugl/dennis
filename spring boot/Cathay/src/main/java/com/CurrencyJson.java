package com;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.model.CurrencyDTO;

@Component
public class CurrencyJson
{
    private Logger log = LogManager.getLogger(CurrencyJson.class);
    
	@SuppressWarnings( { "deprecation", "rawtypes", "unchecked" } )
	public List< CurrencyDTO > parse( String inputJsonString , String tag)
	{
		List< CurrencyDTO > list = new ArrayList<>();
		JSONObject jsonO = new JSONObject( inputJsonString );
		HashMap map = new HashMap<String, String>();
		
		map.put( "updated", jsonO.getJSONObject( "time" ).getString( "updated" ));
		map.put( "updatedISO", jsonO.getJSONObject( "time" ).getString( "updatedISO" ));
		map.put( "updateduk", jsonO.getJSONObject( "time" ).getString( "updateduk" ));
		
		ZonedDateTime updatedz = ZonedDateTime.parse( map.get( "updatedISO" ).toString(), DateTimeFormatter.ISO_DATE_TIME );
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy/MM/dd HH:mm:ss" );
		log.info( "updatedzISON = " + updatedz.toLocalDateTime( ).format( formatter ) );
		map.put( "updatedISON", updatedz.toLocalDateTime( ).format( formatter ) );
		
		DateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
		TimeZone utcT = TimeZone.getTimeZone( "UTC" );
		sdf.setTimeZone( utcT );
		Date date = new Date(updatedz.toLocalDateTime( ).format( formatter ));
		log.info( sdf.format( date ) );
		map.put( "updatedN", sdf.format( date ) );

		TimeZone bst = TimeZone.getTimeZone( "Europe/London" );
		sdf.setTimeZone( bst );
		date = new Date(updatedz.toLocalDateTime( ).format( formatter ));
		log.info( sdf.format( date ) );
		map.put( "updatedukN",  sdf.format( date ) );

		map.put( "disclaimer", jsonO.getString( "disclaimer" ) );
		map.put( "chartName", jsonO.getString( "chartName" ) );
		
		JSONObject jsonO1 = jsonO.getJSONObject( "bpi" ) ;
		String sa[] = {"USD", "GBP", "EUR"};
		
		for ( int i = 0; i < sa.length; i++ )
		{
			JSONObject jsonO2 = jsonO1.getJSONObject( sa[i] ) ;

			String code = jsonO2.getString( "code" ) ;
			String symbol =  jsonO2.getString( "symbol" ) ;
			String rate =  jsonO2.getString( "rate" ) ;
			String description =  jsonO2.getString( "description" ) ;
			Float rateFloat = BigDecimal.valueOf(jsonO2.getDouble("rate_float")).floatValue() ;
			
			if ("new".equals( tag ))
			{
				CurrencyDTO coin =  new CurrencyDTO( map.get( "updatedN" ).toString(), map.get( "updatedISON" ).toString(), map.get( "updatedukN" ).toString(), map.get( "disclaimer" ).toString(), map.get( "chartName" ).toString(), code,
						symbol, rate, description, rateFloat,"null" );
				list.add( coin );
			}else
			{
				CurrencyDTO coin =  new CurrencyDTO( map.get( "updated" ).toString(), map.get( "updatedISO" ).toString(), map.get( "updateduk" ).toString(), map.get( "disclaimer" ).toString(), map.get( "chartName" ).toString(), code,
						symbol, rate, description, rateFloat,"null" );
				list.add( coin );
			}
			log.info( "new".equals( tag )?"out put new date":"out put original data" );
			
		}
		return list;
	}
	
}
