package bangbangproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class GameEngine {
	private int roundNumber;
	private int rounds = 0;
	private boolean mode;
	private boolean inGame;
	private boolean canShoot[];
	private boolean misfire[];
	private int playerScore[];
	private GameWindow wind;
	private Timer errorTimer[];
	protected Timer gameTimer;
	private Timer waitEndTimer;
	private Timer readyTimer;
	private boolean shootMode;
	private Random r;
	public GameEngine(int roundNumber, boolean mode,GameWindow wind){
		this.roundNumber = roundNumber;
		this.mode = mode;
		this.wind = wind;
		playerScore = new int[2];
		init();
	}
	private void init(){
		r = new Random();
		inGame = false;
		//rounds = 0;
		canShoot = new boolean[2];
		canShoot[0] = false;
		canShoot[1] = false;
		misfire = new boolean[2];
		misfire[0] = false;
		misfire[1] = false;
		errorTimer = new Timer[2];
		errorTimer[0] = new Timer(1200,new Reload(0));
		errorTimer[1] = new Timer(1200, new Reload(1));
		gameTimer = new Timer(1000,taskPerformer);
		gameTimer.setRepeats(false);
		waitEndTimer = new Timer(350,waitLaunchPerformer);
		waitEndTimer.setRepeats(false);
		readyTimer = new Timer(100,readyListener);
		readyTimer.setRepeats(false);
		shootMode = false;
		
	}
	ActionListener waitLaunchPerformer = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			wind.startGameTimer();
		}
	};
	ActionListener taskPerformer = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			wind.forceVoiceStop();
			String sound = nextSound();
			wind.playSound(sound);
			if(isEndSound(sound)){
				readyTimer.start();
				gameTimer.stop();
			}
		}
	};
	
	ActionListener readyListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!misfire[0])
				canShoot[0] = true;
			if(!misfire[1])
				canShoot[1] = true;
			shootMode = true;
		}
	};
	
	public void reset(){
		inGame = false;
		//rounds = 0;
		canShoot[0] = false;
		canShoot[1] = false;
		shootMode = false;
	}
	/**
	 * The games wait 3 sec minimum for the countdown + a random time
	 */
	public void launchRound(){
		reset();
		rounds++;
		inGame = true;
		wind.setLoadingVisible(false, 0);
		wind.setLoadingVisible(false, 1);
		playStartSound();
		initGameTimer();
	}
	protected void playStartSound(){
		
	}
	protected void initGameTimer(){
		gameTimer.stop();
		gameTimer.setInitialDelay(2500+r.nextInt(8000));
		gameTimer.start();
	}
	public void playerShot(int player){
		if(canShoot[player] && inGame){
			canShoot[0] = false;
			canShoot[1] = false;
			playerScore[player]++;
			wind.playerDead(1-player);
			wind.playerShoot(player);

			wind.playSound("../ressources/sons/coupdefeu.wav");
			wind.startGameTimer();
			errorTimer[0].stop();
			errorTimer[1].stop();
			errorTimer[0].setRepeats(false);
			errorTimer[1].setRepeats(false);
			inGame = false;
			rounds++;
			System.out.println("player "+player+" shoots");
		} else if(misfire[player] && inGame){
			wind.playSound("../ressources/sons/emptychamber.wav");
		}else if(inGame){
			wind.playerShoot(player);
			wind.playSound("../ressources/sons/ricochet.wav");
			errorTimer[player].start();
			canShoot[player] = false;
			misfire[player] = true;
			wind.setLoadingVisible(true, player);
		}
	}
	class Reload implements ActionListener {
	private int player;
		public Reload(int player){
			this.player = player;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(misfire[player]){
				misfire[player] = false;
				if(shootMode)
					canShoot[player] = true;
				wind.playSound("../ressources/sons/reload.wav");
				wind.setLoadingVisible(false, player);
			}
		}
		
	}
	public int getPlayerScore(int player){
		return playerScore[player];
	}
	
	public boolean isInGame(){
		return inGame;
	}
	public int Round(){
		return rounds;
	}
	public int RoundNumber(){
		return roundNumber;
	}
	
	public int getWinner(){
		if(playerScore[0] > playerScore[1])
			return 0;
		return 1;
	}
	public int maxScore(){
		if(playerScore[0] > playerScore[1])
			return playerScore[0];
		return playerScore[1];
	}
	
	protected String nextSound(){
		return "../ressources/sons/Electric_buzzer.wav";
	}
	
	protected boolean isEndSound(String sound){
		return true;
	}
	
	protected GameWindow getWindow(){
		return wind;
	}
}
