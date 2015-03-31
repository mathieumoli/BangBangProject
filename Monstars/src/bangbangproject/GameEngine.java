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
	private Timer gameTimer;
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
		errorTimer[0] = new Timer(800,new Reload(0));
		errorTimer[1] = new Timer(800, new Reload(1));
		gameTimer = new Timer(1000,taskPerformer);
		gameTimer.setRepeats(false);
	}
	ActionListener taskPerformer = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			canShoot[0] = true;
			canShoot[1] = true;

			wind.playSound("../ressources/sons/Electric_buzzer.wav");
			System.out.println("goooooo");
		}
	};
	public void reset(){
		inGame = false;
		//rounds = 0;
		canShoot[0] = false;
		canShoot[1] = false;
	}
	/**
	 * The games wait 3 sec minimum for the countdown + a random time
	 */
	public void launchRound(){
		reset();
		System.out.println("launch round" + inGame);
		rounds++;
		inGame = true;
		gameTimer.stop();
		gameTimer.setDelay(2000+r.nextInt(600));
		gameTimer.start();
	}
	public void playerShot(int player){
		if(canShoot[player] && inGame){
			canShoot[0] = false;
			canShoot[1] = false;
			playerScore[player]++;
			wind.playerDead(1-player);
			wind.playerShoot(player);
			wind.startGameTimer();
			wind.playSound("../ressources/sons/coupdefeu.wav");
			errorTimer[0].stop();
			errorTimer[1].stop();
			errorTimer[0].setRepeats(false);
			errorTimer[1].setRepeats(false);
			inGame = false;
			System.out.println("player "+player+" shoots");
		} else if(misfire[player]){
			wind.playSound("../ressources/sons/emptychamber.wav");
		}else{
			wind.playerShoot(player);
			wind.playSound("../ressources/sons/ricochet.wav");
			errorTimer[player].start();
			canShoot[player] = false;
			misfire[player] = true;
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
				canShoot[player] = true;
				wind.playSound("../ressources/sons/reload.wav");
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
	
}
