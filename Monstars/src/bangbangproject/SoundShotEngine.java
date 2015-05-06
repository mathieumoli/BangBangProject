package bangbangproject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SoundShotEngine  extends GameEngine{
	private int difficulty;
	private List<String> soundFiles;
	private Random random;
	private int chosenSong = -1;
	private String base = "../ressources/sons/difficulte";
	public SoundShotEngine(int roundNumber, boolean mode, GameWindow wind, int diff) {
		super(roundNumber, mode, wind);
		this.difficulty = diff;
		random = new Random();
		System.out.println("difficulty:"+difficulty);
		base = base+difficulty+"/";
	}
	
	@Override
	protected void playStartSound(){
		getWindow().playVoix("tirez au son de", true);
		if(difficulty ==  1){
			getWindow().playSound(base+randomSound());
		} else {
			getWindow().playVoix(randomSound().split(".")[0],true);
		}
	}
	private String randomSound(){
		gameTimer.setDelay(250+random.nextInt(300));
		if(soundFiles   == null){
			File f = new File(base);
			soundFiles = new ArrayList<String>(Arrays.asList(f.list()));
		}
		
		if(chosenSong < 0){
			chosenSong = random.nextInt(soundFiles.size());
			return soundFiles.get(chosenSong);
		} else {
			int rand = chosenSong;
			while(chosenSong == rand){
				rand = random.nextInt(soundFiles.size());
			}
			return soundFiles.get(rand);
		}
	}
	@Override
	protected void initGameTimer(){
		gameTimer.stop();
		gameTimer.setInitialDelay(1500+random.nextInt(600));
		gameTimer.setDelay(250+random.nextInt(300));
		chosenSong = -1;
	}
	@Override
	protected boolean isEndSound(String sound){
		return sound.equals(soundFiles.get(chosenSong));
	}
	@Override
	protected String nextSound(){
		if(random.nextInt(100)>80 && chosenSong >= 0){
			return base+soundFiles.get(chosenSong);
		}
		
		return base+randomSound();
	}
}
