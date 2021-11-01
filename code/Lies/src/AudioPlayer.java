import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {

	private Clip clip;

	public AudioPlayer(String path) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
			
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
