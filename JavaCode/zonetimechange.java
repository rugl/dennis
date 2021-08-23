	public void zonetimechange()
	{
		String ISOTime = "2021-07-24T17:21:00+00:00" //this is ISO time format
		
		// use the java api to dericet covert to zone DateTime
		ZonedDateTime zonetime = ZonedDateTime.parse( ISOTime, DateTimeFormatter.ISO_DATE_TIME );
		// set will date time format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy/MM/dd HH:mm:ss" );
		// use zonedDateTime call format input value is the Date format
		Date dateISO = zonetime.toLocalDateTime( ).format( formatter )
		
		// ================= here will use ISO date cover to the other zone time ======================
		// set the DateFormat instantiate the SimpleDateFormat
		DateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
		// set the zone here is UTC +0
		TimeZone utcT = TimeZone.getTimeZone( "UTC" );
		// the same set the format to dateformat
		sdf.setTimeZone( utcT );
		// Instantiate the dateISO which from ISO date format
		Date date = new Date(dateISO);
		// here to use the dateISO to calculate the UTC zone time
		sdf.format( date );

		// set the zone here is BSP, so we use the "Europe/London" as same as you can use the Asia/taipei ....
		TimeZone bst = TimeZone.getTimeZone( "Europe/London" );
		// set the zone
		sdf.setTimeZone( bst );
		// Instantiate the dateISO which from ISO date format
		date = new Date(dateISO);
		// here to use the dateISO to calculate the London zone time the same BSP
		sdf.format( date );
	}
