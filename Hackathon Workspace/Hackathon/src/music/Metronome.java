package music;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

public class Metronome {
	private Timer myTimer;
	private Clip clip;
	private int tickNumber = 0;
	public Metronome(int bpm)
	{
		try{
			URL url = this.getClass().getClassLoader().getResource("res/Metronome.wav");
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	        clip = AudioSystem.getClip();
	        clip.open(audioIn);
		}catch(Exception e){
			e.printStackTrace();;
		}
		int delay = (int) Math.round(Rhythm.bpmToBeatLength(bpm) * 1000);
		myTimer = new Timer(delay, new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(tickNumber == 0)
					onFirstTick();
				playSound();
				tickNumber++;
			}
		});
	}
	public void start()
	{
		myTimer.start();
	}
	public void stop()
	{
		myTimer.stop();
		tickNumber = 0;
	}
	private void onFirstTick()
	{
		
	}
	private void playSound()
	{
		if (clip.isRunning())
			clip.stop();   // Stop the player if it is still running
		clip.setFramePosition(0); // rewind to the beginning
		clip.start();     // Start playing
	}
}
