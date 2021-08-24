import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CoverUtf8Big5
{
	public CoverUtf8Big5() {
	String file_path = "C:\\utf8.txt";
	try
	{
		String line = "";
		File file = new File( file_path );
		
		BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream( file ), "utf-8" ) );
		System.out.println( file );
		File wfile = new File( "C:\\big5.txt" );
		System.out.println( wfile );
		BufferedWriter fw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( wfile ), "big5" ) );
		
		while ( ( line = br.readLine( ) ) != null )
		{
			fw.write( line + "\n" );
			System.out.println( line );
		}
		
		// System.out.println(System.getProperty("file.encoding"));
		fw.flush( );
		fw.close( );
		br.close( );
	} catch ( Exception e )
	{
		System.err.println( e );
	}
	}
}
