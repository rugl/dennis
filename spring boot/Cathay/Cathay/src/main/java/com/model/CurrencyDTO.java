package com.model;

public class CurrencyDTO
{
	private String	updated;
	private String	updatedISO;
	private String	updateduk;
	
	private String	disclaimer;
	private String	chartName;

	private String	code;
	private String	symbol;
	private String	rate;
	private String	description;
	private Float	rateFloat;

	private String	chineseCodeName;

	public CurrencyDTO()
	{
	}

	public CurrencyDTO( String updated, String updatedISO, String updateduk, String disclaimer, String chartName, String code,
			String symbol, String rate, String description, Float rateFloat,
			String chineseCodeName )
	{
		this.updated = updated;
		this.updatedISO = updatedISO;
		this.updateduk = updateduk;
		this.disclaimer = disclaimer;
		this.chartName = chartName;
		this.code = code;
		this.symbol = symbol;
		this.rate = rate;
		this.description = description;
		this.rateFloat = rateFloat;
		this.chineseCodeName = chineseCodeName;
	}

	public String getDisclaimer()
	{
		return disclaimer;
	}

	public void setDisclaimer( String disclaimer )
	{
		this.disclaimer = disclaimer;
	}

	public String getChartName()
	{
		return chartName;
	}

	public void setChartName( String chartName )
	{
		this.chartName = chartName;
	}

	public String getUpdated()
	{
		return updated;
	}

	public void setUpdated( String updated )
	{
		this.updated = updated;
	}

	public String getUpdatedISO()
	{
		return updatedISO;
	}

	public void setUpdatedISO( String updatedISO )
	{
		this.updatedISO = updatedISO;
	}

	public String getUpdateduk()
	{
		return updateduk;
	}

	public void setUpdateduk( String updateduk )
	{
		this.updateduk = updateduk;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode( String code )
	{
		this.code = code;
	}

	public String getSymbol()
	{
		return symbol;
	}

	public void setSymbol( String symbol )
	{
		this.symbol = symbol;
	}

	public String getRate()
	{
		return rate;
	}

	public void setRate( String rate )
	{
		this.rate = rate;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public Float getRateFloat()
	{
		return rateFloat;
	}

	public void setRateFloat( Float rateFloat )
	{
		this.rateFloat = rateFloat;
	}

	public String getChineseCodeName()
	{
		return chineseCodeName;
	}

	public void setChineseCodeName( String chineseCodeName )
	{
		this.chineseCodeName = chineseCodeName;
	}

}
