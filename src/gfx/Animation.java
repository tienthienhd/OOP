package gfx;

import java.awt.image.BufferedImage;

public class Animation {
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;

	public Animation(BufferedImage[] frames, int speed) {
		this.frames = frames;
		this.speed = speed;
		timer = 0;
		index = 0;
		lastTime = System.currentTimeMillis();
	}

	public void update() {
		long currTime = System.currentTimeMillis();
		timer += currTime - lastTime;
		lastTime = currTime;
		if (timer > speed) {
			index++;
			timer = 0;
			if (index >= frames.length) {
				index = 0;
			}
		}
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
}
