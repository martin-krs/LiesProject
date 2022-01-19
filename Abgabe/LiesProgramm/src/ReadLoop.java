
public class ReadLoop implements Runnable {
	
	public long lastTime = System.currentTimeMillis();
	public double timeDistance = 450;//550
	public double delta = 0;
	
	private Thread thread;
	private boolean running = false;
	
	public ReadLoop() {
		this.start();
	}
	
	private synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(running) {
			long now = System.currentTimeMillis();
			delta += (now - lastTime);
			lastTime = now;
			if (delta >= timeDistance) {
				AudioPlayer.read();
				delta = 0;
			}
		}
		
	}

}
