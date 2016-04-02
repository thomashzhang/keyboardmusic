package music;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.*;
import javax.sound.midi.Synthesizer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Piano {
	
	Synthesizer synth;
	final MidiChannel channel;
	final int velocity = 64;
	private static Piano piano = new Piano();
	private List<Clip> clips = new ArrayList<Clip>();
	public Piano()
	{
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		channel = synth.getChannels()[1];
	}
	private void noteOn(String key)
	{
		int code = Pitch.nameToCode(Pitch.keyToString(key));
		if(code >= 0)
		{
			channel.noteOn(Pitch.nameToCode(Pitch.keyToString(key)), velocity);
		}
	}
	public static void playNote(String key) throws Exception
	{
		piano.noteOn(key);
	}
}
