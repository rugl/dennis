import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.*;

public class ZipEntry
{
	
	/*
	 * public static void main( String[ ] a )
	 * {
	 * File srcFile = new File( "C:\\Test" );
	 * File targetZip = new File( "C:\\Test\\Daily_2021_08_23.zip" );
	 * File extractDir = new File( "C:\\Test\\new" );
	 * try
	 * {
	 * // Zip
	 * new ZipEntry( ).makeZip( srcFile, targetZip );
	 * // UnZip
	 * new ZipEntry( ).unzipFile( targetZip, extractDir );
	 * } catch ( FileNotFoundException e )
	 * {
	 * // TODO Auto-generated catch block
	 * e.printStackTrace( );
	 * } catch ( IOException e )
	 * {
	 * // TODO Auto-generated catch block
	 * e.printStackTrace( );
	 * }
	 * }
	 */
	
	/**
	 * Unzip
	 * 
	 * @param zipfile    Zip file location
	 * @param extractDir Unzip the folder
	 * @return
	 */
	public boolean unzipFile( File zipfile, File extractDir )
	{
		
		try
		{
			unZip( zipfile, extractDir.getAbsolutePath( ) );
		} catch ( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace( );
			return false;
		}
		
		return true;
	}
	
	/**
	 * Create the folder
	 * 
	 * @param directory
	 * @param subDirectory
	 */
	private void createDirectory( String directory, String subDirectory )
	{
		String dir[];
		File fl = new File( directory );
		try
		{
			if ( subDirectory == "" && fl.exists( ) != true ) fl.mkdir( );
			else if ( subDirectory != "" )
			{
				dir = subDirectory.replace( '\\', '/' ).split( "/" );
				for ( int i = 0; i < dir.length; i++ )
				{
					File subFile = new File( directory + File.separator + dir[ i ] );
					if ( subFile.exists( ) == false ) subFile.mkdir( );
					directory += File.separator + dir[ i ];
				}
			}
		} catch ( Exception ex )
		{
			System.out.println( ex.getMessage( ) );
		}
	}
	
	/**
	 * Unzip the main method
	 * 
	 * @param zipFileName
	 * @param outputDirectory
	 * @throws Exception
	 */
	private void unZip( File ZIPFile, String outputDirectory ) throws Exception
	{
		try
		{
			org.apache.tools.zip.ZipFile zipFile = new ZipFile( ZIPFile );
			if(zipFile.getEntries( ) instanceof org.apache.tools.zip.ZipEntry && zipFile.getEntries( ) != null)
			{
			Enumeration< org.apache.tools.zip.ZipEntry > e = zipFile.getEntries( );
			
			org.apache.tools.zip.ZipEntry zipEntry = null;
			createDirectory( outputDirectory, "" );
			// if(!outputDirectory.exists()) outputDirectory.mkdirs();
			
			while ( e.hasMoreElements( ) )
			{
				zipEntry = e.nextElement( );
				System.out.println( "unziping " + zipEntry.getClass( ).getName( ) );
				if ( zipEntry.isDirectory( ) )
				{
					String name = zipEntry.getName( );
					name = name.substring( 0, name.length( ) - 1 );
					File f = new File( outputDirectory + File.separator + name );
					f.mkdir( );
					System.out.println( "Create the directory¡G" + outputDirectory + File.separator + name );
				} else
				{
					String fileName = zipEntry.getName( );
					fileName = fileName.replace( '\\', '/' );
					
					if ( fileName.indexOf( "/" ) != -1 )
					{
						createDirectory( outputDirectory, fileName.substring( 0, fileName.lastIndexOf( "/" ) ) );
						fileName = fileName.substring( fileName.lastIndexOf( "/" ) + 1, fileName.length( ) );
					}
					
					File f = new File( outputDirectory + File.separator + zipEntry.getName( ) );
					
					f.createNewFile( );
					InputStream in = zipFile.getInputStream( zipEntry );
					FileOutputStream out = new FileOutputStream( f );
					
					byte[ ] by = new byte[ 1024 ];
					int c;
					while ( ( c = in.read( by ) ) != -1 )
					{
						out.write( by, 0, c );
					}
					out.close( );
					in.close( );
				}
			}
			}
		} catch ( Exception ex )
		{
			System.out.println( ex.getMessage( ) );
		}
		
	}
	
	/**
	 * Create the zip file
	 * 
	 * @param srcFile   The file which want to zip
	 * @param targetZip output file name
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	
	public void makeZip( File srcFile, File targetZip ) throws IOException, FileNotFoundException
	{
		ZipOutputStream zos = new ZipOutputStream( new FileOutputStream( targetZip ) );
		String dir = "";
		recurseFiles( srcFile, zos, dir );
		zos.close( );
	}
	
	/**
	 * Zip the main method
	 * 
	 * @param file
	 * @param zos
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	
	private void recurseFiles( File file, ZipOutputStream zos, String dir ) throws IOException, FileNotFoundException
	{
		// directory
		if ( file.isDirectory( ) )
		{
			System.out.println( "Find the folder:" + file.getName( ) );
			dir += file.getName( ) + File.separator;
			String[ ] fileNames = file.list( );
			if ( fileNames != null )
			{
				for ( int i = 0; i < fileNames.length; i++ )
				{
					recurseFiles( new File( file, fileNames[ i ] ), zos, dir );
				}
			}
		}
		// Otherwise, a file so add it as an entry to the Zip file.
		else
		{
			System.out.println( "Compress the file:" + file.getName( ) );
			byte[ ] buf = new byte[ 1024 ];
			int len;
			// Create a new Zip entry with the file's name.
			dir = dir.substring( dir.indexOf( File.separator ) + 1 );
			org.apache.tools.zip.ZipEntry zipEntry = new org.apache.tools.zip.ZipEntry( dir + file.getName( ) );
			// Create a buffered input stream out of the file
			// we're trying to add into the Zip archive.
			FileInputStream fin = new FileInputStream( file );
			BufferedInputStream in = new BufferedInputStream( fin );
			zos.putNextEntry( zipEntry );
			// Read bytes from the file and write into the Zip archive.
			while ( ( len = in.read( buf ) ) >= 0 )
			{
				zos.write( buf, 0, len );
			}
			// Close the input stream.
			in.close( );
			// Close this entry in the Zip stream.
			zos.closeEntry( );
		}
	}
}
