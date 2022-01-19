import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	
	public static int counter = 0;
	public static boolean play = true;
	public static String[] syls;

	private Clip clip;

	public AudioPlayer(String path) {
		File audioFile = new File(path);
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
			
			AudioFormat baseFormat = audioInputStream.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
					baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
			
			AudioInputStream dAudioInputStream = AudioSystem.getAudioInputStream(decodeFormat, audioInputStream);
			
			clip = AudioSystem.getClip();
			clip.open(dAudioInputStream);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void read() {
		if (play) {
			if (counter < syls.length) {
				if (syls[counter].equals("LEER")) {
					// make nothing 
				} else {
					AudioPlayer aupl = new AudioPlayer("..//res/" + syls[counter] + ".wav");
					aupl.play();
				}
				counter++;
			} else {
				System.exit(0);
			}
		}
	}
	
	public void play() {
		if (clip == null) {
			return;
		}
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void close() {
		stop();
		clip.stop();
	}
	
	public void stop() {
		if (clip.isRunning()) {
			clip.stop();
		}
	}

}
