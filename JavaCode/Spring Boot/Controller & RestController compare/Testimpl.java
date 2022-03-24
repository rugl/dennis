package idv.dennis.service;

public interface Testimpl
{
	public String getHelloMessage( String name );
	
	default void doLog( String s )
	{
		System.out.println( s );
	}
}
