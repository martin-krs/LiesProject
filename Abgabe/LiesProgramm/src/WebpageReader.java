import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class WebpageReader {

	public static String getTextFromURL(String url) {
		String text = "";
		try {
			//Instantiating the URL class
		      URL url_ = new URL(url);
		      //Retrieving the contents of the specified page
		      Scanner sc = new Scanner(url_.openStream());
		      //Instantiating the StringBuffer class to hold the result
		      StringBuffer sb = new StringBuffer();
		      while(sc.hasNext()) {
		         sb.append(sc.next());
		         //System.out.println(sc.next());
		      }
		      //Retrieving the String from the String Buffer object
		      text = sb.toString();
		      //Removing the HTML tags
		      text = text.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
		}
		catch (Exception e) {
			
		}
		return text;
	}
	
	/**
	public static String getTextFromURL1(String url) {
		String line = "";
		
		try {
			URL url1 = new URL(url);
			URLConnection con = url1.openConnection();
			InputStreamReader reader = new InputStreamReader(con.getInputStream(), "UTF8");
			BufferedReader buf = new BufferedReader(reader);
			
			while (true) {
				line = buf.readLine();
				if (line != null) {
					line = line.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
					System.out.println(line);
				} else {
					break;
				}
			return line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	*/
}
