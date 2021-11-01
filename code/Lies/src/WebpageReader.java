import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;

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
		      text = text.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");  //"<[{^>]}*>", ""
		}
		catch (Exception e) {
			
		}
		return text;
	}
	
	public static LinkedList<String> divString(String s) {
		LinkedList<String> result = new LinkedList<String>();
		String[] split = s.split(" ", 0);
		// noch löschen
		for  (int i = 0; i < split.length; i++) {
			System.out.println(split[i]);
		}
		return result;
	}
	
	
	/**		URL url = null;
	URLConnection con = null;
	
	try {
		url = new URL("https://Google.com");
		con = url.openConnection();
		InputStreamReader reader = new InputStreamReader(con.getInputStream(), "UTF8");
		BufferedReader buf = new BufferedReader(reader);
		
		String line = "";
		while (true) {
			line = buf.readLine();
			if (line != null) {
				line = line.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
				System.out.println(line);
			} else {
				break;
			}
		}
		
	} catch (IOException e) {
		e.printStackTrace();
	}*/
	
	
/**		String text = "";
    Connection conn = Jsoup.connect(url);
    Document doc;
	try {
		doc = conn.get();
	    text = doc.body().text();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return text;*/

}
