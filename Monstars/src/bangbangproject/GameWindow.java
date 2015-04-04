package bangbangproject;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import wiimote.Controller;
import wiimote.ControllerListener;
import wiimote.MovementEvent;
import wiimote.WiimoteController;
import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;
import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;

public class GameWindow extends FenetreAbstraite implements KeyListener, ControllerListener {

	private boolean playerOneReady, playerTwoReady, gameMode;
	private JPanel mainPanel;
	private JPanel gamePanel;
	private JPanel cardLayoutPanel;
	private CowBoySprite leftCowBoy, rightCowBoy;
	private JLabel timerLabel;
	private int playerLeftScore, playerRightScore;
	private JLabel score;
	private JTextArea aide;
	private int timerValue = 3;
	private Timer timer;
	private boolean gameStarted = false;
	private Wiimote[] wiimotes;
	private Controller[] wiimotesControllers;
	private GameEngine engine;
	private static String ressources = "../ressources/sons";
	private static String player_1_name = "billythekid.wav";
	private static String player_2_name = "joeydalton.wav";
	
	
	public GameWindow(String title) {
		this(3,false,0);	
	}
	
	public GameWindow(int roundNumbers, boolean gameType, int difficulty){
		super("Game ON");
		/*wiimotes =  WiiUseApiManager.getWiimotes(2, true);
		wiimotesControllers = new Controller[2];
		wiimotesControllers[0] = new WiimoteController(wiimotes[0]);
		wiimotesControllers[1] = new WiimoteController(wiimotes[1]);
		wiimotesControllers[0].addControllerListener(this);
		wiimotesControllers[1].addControllerListener(this);
		*/engine = new GameEngine(roundNumbers,gameType,this);
		init();
	}
	

	public GameWindow(String string, Object value) {
		this(3,false,0);
	}
	
	public GameWindow(String string, int value, int value2) {
		this(3,false,0);
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		playerOneReady = false;
		playerTwoReady = false;
		gameMode = false;
		playerLeftScore = 0;
		playerRightScore = 0;
		mainPanel = new JPanel();
		aide = new JTextArea();
		aide.setText("\nExplication du jeu\n\nLe principe du jeu est d'appuyer le plus rapidement sur le bouton B de la Wiimote en la levant après que un BONG ait retenti. Veuillez ne pas oublier de mettre la dragonne pour votre sécurité et celle des autres.");
		aide.setLineWrap(true);
		aide.setWrapStyleWord(true);
		aide.setEditable(false);
		aide.setFocusable(false);
		aide.setFont(new Font("Arial",Font.BOLD,Preferences.MEDIUM_SIZE));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(aide);
		cardLayoutPanel = new JPanel();
		cardLayoutPanel.setLayout(new CardLayout());
		initGameMode();
		cardLayoutPanel.add(mainPanel, "test");
		cardLayoutPanel.add(gamePanel, "gamee");
		
		mainPanel.setFocusable(false);
		add(cardLayoutPanel,BorderLayout.CENTER);
		addKeyListener(this);
	}
	
	private void setGameMode(){
		CardLayout cl = (CardLayout)(cardLayoutPanel.getLayout());
		cl.show(cardLayoutPanel, "gamee");
		gameMode = true;
		leftCowBoy.repaint();
		rightCowBoy.repaint();
	}
	private void initGameMode(){

		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(1,3));
		leftCowBoy = new CowBoySprite(CowBoySprite.LEFT,gamePanel);
		rightCowBoy = new CowBoySprite(CowBoySprite.RIGHT,gamePanel);
		score = new JLabel(playerLeftScore + "-" + playerRightScore);
		score.setHorizontalAlignment(JLabel.CENTER);
		score.setFont(new Font("Arial",Font.BOLD,Preferences.LARGE_SIZE));
		gamePanel.add(leftCowBoy);
		gamePanel.add(score);
		gamePanel.add(rightCowBoy);
		timerLabel = new JLabel();
		timerLabel.setFont(new Font("Arial",Font.BOLD,Preferences.LARGE_SIZE));
		timerLabel.setHorizontalAlignment(JLabel.CENTER);
		add(timerLabel,BorderLayout.NORTH);
	}
	
	private CowBoySprite getPlayerSprite(int playerNumber){
		CowBoySprite sprite;
		if(playerNumber == 0){
			sprite = leftCowBoy;
		} else
			sprite = rightCowBoy;
		return sprite;
	}
	
	public void playerShoot(int playerNumber){
		CowBoySprite sprite = getPlayerSprite(playerNumber);
		sprite.setShootState();
	}
	
	public void playerDead(int playerNumber){
		//if(gameMode){
			getPlayerSprite(playerNumber).setDeadState();
			score.setText(engine.getPlayerScore(0)+"-"+engine.getPlayerScore(1));
			startResetTimer();

		//}
	}
	
	public void startResetTimer(){
		//gameMode = false;
		ActionListener taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				resetState();
				
			}
		};
		Timer t = new Timer(1000,taskPerformer);
		t.setRepeats(false);
		t.start();
	}

	public void startGameTimer() {
		if(timer == null  || !timer.isRunning()){
		if(engine.Round() > 0){
			voix.playShortText("Joueur 1 "+engine.getPlayerScore(0)+ " joueur 2 " + engine.getPlayerScore(1));
		}
		gameStarted = false;
		timerValue = 3;
		timerLabel.setText(timerValue + "");
		voix.playShortText(timerValue + "");
		if(engine == null)
			engine = new GameEngine(3,true,this);
		ActionListener taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(timerValue <= 1){

					engine.launchRound();
					System.out.println("round launched");
					timer.stop();
					timerLabel.setText("be ready !");
				}
				 else {
					timerLabel.setText(--timerValue + "");
					voix.playShortText(timerValue + "");
				 }
			}
		};
		timer = new Timer(1000,taskPerformer);
		
		timer.start();
		}
	}
	
	public void resetState(){
		gameMode = true;
		leftCowBoy.resetState();
		rightCowBoy.resetState();
	}
	
	@Override
	public void changeColor() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void changeSize() {
		// TODO Auto-generated method stub
		
	}
	
	// renvoie le fichier wave contenant le message d'accueil
	protected String wavAccueil() {
		return "../ressources/sons/accueil.wav";
	}

	// renvoie le fichier wave contenant la règle du jeu
	protected String wavRegleJeu() {
		return "../ressources/sons/aideF1.wav";
	}

	// renvoie le fichier wave contenant la règle du jeu
	protected String wavAide() {
		return "../ressources/sons/aide.wav";
	}
	
	public void keyReleased(KeyEvent e){
		if(!gameMode){
			switch(e.getKeyChar()){
			case 'a':
				playerOneReady = true;
				break;
			case 'e':
				playerTwoReady = true;
				break;
			}
			if(playerOneReady && playerTwoReady){
				setGameMode();
				playerOneReady = false;
				playerTwoReady = false;
				startGameTimer();
				//removeKeyListener(this);
			}
		} else if(engine.isInGame()){
			switch(e.getKeyChar()){
			case 'a':
				engine.playerShot(0);
				break;
			case 'e':
				engine.playerShot(1);
				break;
			}
		}
	}
	
	public void playSound(String wavSound){
		voix.playWav(wavSound);
	}
	
	@Override
	public void movementConfirmed(MovementEvent event) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getShootEvent(MovementEvent event) {
		if(engine !=null && engine.isInGame()){
			int orig,cible;
			if(event.getSource() == wiimotesControllers[0]){
				orig = 0;
			} else {
				orig = 1;
			}
			engine.playerShot(orig);
		} else {
		}
	}
	
	@Override
	public void buttonAEvent(MovementEvent event) {
		if(!gameMode){
			if(event.getSource() == wiimotesControllers[0]){
				playerOneReady = true;
			} else {
				playerTwoReady = true;
			}
			if(playerOneReady && playerTwoReady){
				setGameMode();
				playerOneReady = false;
				playerTwoReady = false;
				startGameTimer();
				//removeKeyListener(this);
			}
		}
	}
}
