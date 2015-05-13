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
	private String shootSound;
	private String base = "../ressources/sons/difficulte";
	private boolean end = false;
	public SoundShotEngine(int roundNumber, boolean mode, GameWindow wind, int diff) {
		super(roundNumber, mode, wind);
		this.difficulty = diff;
		random = new Random();
		System.out.println("difficulty:"+difficulty);
		if(difficulty ==  3)
			base = base+3+"/";
		else
			base =  base+1+"/";
		//System.out.println(randomSound());
		
	}
	
	@Override
	protected void playStartSound(){
		System.out.println("playstartsound");
		if(difficulty ==  1){

			getWindow().playVoix("tirez au son de",true);
			getWindow().playSound(base+shootSound);
		} else {
			getWindow().playVoix("tirez au son de",true);
			getWindow().playVoix(shootSound.split("\\.")[0],true);
		}
	}
	private String randomSound(){
		//gameTimer.setDelay(1500+random.nextInt(1000));
		if(soundFiles   == null){
			File f = new File(base);
			soundFiles = new ArrayList<String>(Arrays.asList(f.list()));
		}
		
		if(chosenSong < 0){
			chosenSong = random.nextInt(soundFiles.size());
			shootSound = soundFiles.get(chosenSong);
			soundFiles.remove(chosenSong);
			return shootSound;
		} else {
			int rand = chosenSong;
			rand = random.nextInt(soundFiles.size());
			return soundFiles.get(rand);
		}
	}
	@Override
	protected void initGameTimer(){
		System.out.println("initgametimer");
		gameTimer.stop();
		gameTimer.setInitialDelay(9000+random.nextInt(1500));
		gameTimer.setDelay(2500+random.nextInt(300));
		gameTimer.setRepeats(true);
		chosenSong = -1;
		gameTimer.start();
		soundFiles = null;
		end = false;
		randomSound();
	}
	@Override
	protected boolean isEndSound(String sound){
		return end;
	}
	@Override
	protected String nextSound(){
		
		if(random.nextInt(100)>80 && chosenSong >= 0){
			end = true;
			System.out.println("endsong");
			return base+shootSound;
		}
		String soundToPlay = base+randomSound();
		System.out.println(soundToPlay);
		return soundToPlay;
	}
}
