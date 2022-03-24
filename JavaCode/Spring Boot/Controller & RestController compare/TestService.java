package idv.dennis.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TestService implements Testimpl
{

	@Override
	public String getHelloMessage( String name )
	{
		return "Hello " + Optional.ofNullable(name).orElse("World!");
	}
	
}
