package helpers;

import java.io.File;
import java.io.InputStream;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

// Class that handles the playing of .wav and .mp3 files for game
// sound effects and music
public class SoundPlayer {
	
	private static Sequencer midiSequence;
	
	private static Clip scareSound = null;
	
	public static void loadScareClip() {
	    try {
	    	File wavFile = new File("res/sounds/Gotcha2.wav");
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(wavFile);
	        SoundPlayer.scareSound = AudioSystem.getClip();
	        SoundPlayer.scareSound.open(audioInputStream);
	    } catch(Exception ex) {
	        System.out.println("Error with loading sound");
	        ex.printStackTrace();
	    }
	}
	
	public static void playScareClip() {
	    try {
	    	if(SoundPlayer.scareSound != null) {
	    		SoundPlayer.scareSound.start();
	    	}
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound");
	        ex.printStackTrace();
	    }
	}
	
	// Play WAV audio file with given gain (increases or decreases volume)
	public static void playWAV(String wavPath, float gain) {
		if((gain > 6.0f) || (gain < -80.0f)) {
			System.out.println("Gain can only be between -80f and 6.0f: Your Gain = "+Float.toString(gain));
			return;
		}
		
	    try {
	    	File wavFile = new File("res/sounds/" + wavPath);
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(wavFile);
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        if(gain != 0.0f) {
				FloatControl gainControl = 
					    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(gain); // Reduce volume by set gain
	        }
	        
	        // Add listener to close the clip when it is done playing
//	        clip.addLineListener(new LineListener() {
//	            public void update(LineEvent myLineEvent) {
//	              if (myLineEvent.getType() == LineEvent.Type.STOP)
//	                clip.close();
//	            }
//	          });
	        
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound: " + wavPath);
	        ex.printStackTrace();
	    }
	}
	
	// Play WAV sound file with default volume
	public static void playWAV(String wavPath) {
		SoundPlayer.playWAV(wavPath, 0);
	}
	
	/// Midi play with set volume for each channel
	public static void playMidi(String midiPath, int volume) {
        // Obtains the default Sequencer connected to a default device.
        Sequencer sequencer;
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
	        
	        // Opens the device, indicating that it should now acquire any
	        // system resources it requires and become operational.
	        sequencer.open();
	        
	        // create a stream from a file
	        InputStream is = SoundPlayer.class.getClassLoader().getResourceAsStream(midiPath);

	        // Sets the current sequence on which the sequencer operates.
	        // The stream must point to MIDI file data.
	        sequencer.setSequence(is);
	        
			Track[] tracks = sequencer.getSequence().getTracks();
			
			for(Track track : tracks) {
				for(int x = 0; x < 16; x++) {
					track.add(new MidiEvent(
							new ShortMessage(ShortMessage.CONTROL_CHANGE, x, 7, volume), 0));
				}
			}
	 
	        // Starts playback of the MIDI data in the currently loaded sequence.
	        sequencer.start();
	        SoundPlayer.midiSequence = sequencer;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Play Midi without volume set
	public static void playMidi(String midiPath) {
        // Obtains the default Sequencer connected to a default device.
        Sequencer sequencer;
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
	        
	        // Opens the device, indicating that it should now acquire any
	        // system resources it requires and become operational.
	        sequencer.open();
	        
	        // create a stream from a file
	        InputStream is = SoundPlayer.class.getClassLoader().getResourceAsStream(midiPath);

	        // Sets the current sequence on which the sequencer operates.
	        // The stream must point to MIDI file data.
	        sequencer.setSequence(is);
	 
	        // Starts playback of the MIDI data in the currently loaded sequence.
	        sequencer.start();
	        SoundPlayer.midiSequence = sequencer;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Stops the current song
	public static void stopMidi() {
		if(SoundPlayer.midiSequence != null) {
			SoundPlayer.midiSequence.stop();
		}
	}
	
	// Stops the current song if playing
	// Resumes the current song if stopped
	public static void togglePauseMidi() {
		if(SoundPlayer.midiSequence == null) {
			return;
		} else if(SoundPlayer.midiSequence.isRunning()) {
			SoundPlayer.midiSequence.stop();
		} else {
			SoundPlayer.midiSequence.start();
		}
	}
	
}
