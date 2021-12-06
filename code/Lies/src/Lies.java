import java.util.LinkedList;
import java.util.Scanner;

public class Lies {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static LinkedList<String> text = new LinkedList<String>();
	
	public static void startProcess(String url) {
		// Filtert den Text der website und spielt ihn ab. Man hat die M�glichkeit zu pausieren,
		// wieder zu starten, oder das Programm zu beenden.
		String opt = "";
		String pagetext = WebpageReader.getTextFromURL(url);
		text = WebpageReader.divString(pagetext);
		//AudioPlayer ap = new AudioPlayer("/soundtrack.wav");
		//ap.play();
		
		AudioPlayer.silben[0] = "ha";
		AudioPlayer.silben[1] = "lo";
		AudioPlayer.silben[2] = "au";
		AudioPlayer.silben[3] = "to";
		AudioPlayer.silben[4] = "mat";
		
		// Startet einen separaten eigenen Thread, der sich um das vorlesen k�mmert.
		// Dadurch blockiert die Eingabe f�r Pause und exit nicht das Vorlesen.
		ReadLoop rl = new ReadLoop();
		
		boolean loop = true;
		boolean shouldStop = true;
		while (loop) {
			opt = sc.nextLine();
			switch (opt) {
			case "s":
				if (shouldStop) {
					AudioPlayer.play = false;
					shouldStop = !shouldStop;
				} else if (!shouldStop) {
					AudioPlayer.play = true;
					shouldStop = !shouldStop;
				}
				break;
			case "e":
				System.exit(0);
			}
		}
	}
	
	public static void lies(String url) {
		// Beendet auf wunsch das Programm, oder startet einen Lies-Process
		String opt = "";
		System.out.println("Willkommen bei Lies!\nUm den Text vorzulesen, dr�cke 'l',\num zu pausieren oder fortzusetzen, dr�cke 's',\num zu beenden, dr�cke 'e'.\n");
		opt = sc.nextLine();
		switch (opt) {
		case "l":
			startProcess(url);
			break;
		case "s":
			System.out.println("Sie m�ssen erst starten.\n");
			break;
		case "e":
			System.out.println("Bis zum n�chsten Mal!\n");
			break;
		default:
			System.out.println("Keine Antwortm�glichkeit\n");
		}
	}

	public static void main (String[] args) {
		// Pr�ft, ob eine URL am Anfang �bergeben wurde
		try {
			//String url = "https://" + args[0];
			String url = "https://www.ostfalia.de/";
			lies(url);
		} catch (Exception e) {
			System.out.println("Es muss eine URL �bergeben werden.");
			System.exit(0);
		}
	}
}
