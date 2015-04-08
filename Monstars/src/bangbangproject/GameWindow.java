package bangbangproject;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
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
	private JLabel[] loadingLabels;
	private GameEngine engine;
	private static String ressources = "../ressources/sons";
	private static String player_1_name = "billythekid.wav";
	private static String player_2_name = "joeydalton.wav";
	
	private boolean end = false;
	public GameWindow(String title) {
		this(3,false,0);	
	}
	
	public GameWindow(int roundNumbers, boolean gameType, int difficulty){
		super("Game ON");
		wiimotes =  WiiUseApiManager.getWiimotes(2, true);
		wiimotesControllers = new Controller[2];
		wiimotesControllers[0] = new WiimoteController(wiimotes[0]);
		wiimotesControllers[1] = new WiimoteController(wiimotes[1]);
		wiimotesControllers[0].addControllerListener(this);
		wiimotesControllers[1].addControllerListener(this);
		engine = new GameEngine(roundNumbers,gameType,this);
		System.out.println("Round numbers:"+roundNumbers);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		JScrollPane scrollPane;
		aide = new JTextArea();
		aide.setText("\nExplication du jeu\n\nLe principe du jeu est d'appuyer le plus rapidement sur le bouton B de la Wiimote en la levant après que le son ait retenti."
				+ " Veuillez ne pas oublier de mettre la dragonne pour votre sécurité et celle des autres.\n\n"
				+ "Mode Duel : Le son retentissant est un BONG classique.\n\n"
				+ "Si vous tirez avant le son, vous devrez attendre un certains temps , un vrai cowboy doit recharger son revolver. La partie s'arrête après que le nombre de round choisi soit effectué. "
				+ "Puis le vainqueur, celui qui aura atteint le nombre de round correspondant, sera annoncé."
				+ "Lorsque vous êtes prêt à jouer, appuyez sur le bouton A de la Wiimote"
				+ "La prochaine fois pour ne pas écouter ce message et passer directement au jeu appuyez sur le bouton A");
		aide.setLineWrap(true);
		aide.setWrapStyleWord(true);
		aide.setEditable(false);
		aide.setFocusable(false);
		aide.setFont(new Font("Arial",Font.BOLD,Preferences.MEDIUM_SIZE));
		scrollPane = new JScrollPane(aide);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(scrollPane);
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
		gamePanel.setLayout(new BorderLayout());
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1,3));
		JPanel leftPane = new JPanel();
		leftPane.setLayout(new BorderLayout());
		JPanel rightPane = new JPanel();
		rightPane.setLayout(new BorderLayout());
		leftCowBoy = new CowBoySprite(CowBoySprite.LEFT,gamePanel);
		rightCowBoy = new CowBoySprite(CowBoySprite.RIGHT,gamePanel);
		leftPane.add(leftCowBoy);
		rightPane.add(rightCowBoy);
		score = new JLabel(playerLeftScore + "-" + playerRightScore);
		score.setHorizontalAlignment(JLabel.CENTER);
		score.setFont(new Font("Arial",Font.BOLD,Preferences.LARGE_SIZE));
		centerPanel.add(leftPane);
		centerPanel.add(score);
		centerPanel.add(rightPane);
		timerLabel = new JLabel();
		timerLabel.setFont(new Font("Arial",Font.BOLD,Preferences.LARGE_SIZE));
		loadingLabels = new JLabel[2];
		loadingLabels[0] = new JLabel("chargement");
		loadingLabels[1] = new JLabel("chargement");
		loadingLabels[0].setVisible(false);
		loadingLabels[1].setVisible(false);
		loadingLabels[0].setFont(new Font("Arial",Font.BOLD,Preferences.LARGE_SIZE));

		loadingLabels[1].setFont(new Font("Arial",Font.BOLD,Preferences.MEDIUM_SIZE));
		leftPane.add(loadingLabels[0],BorderLayout.SOUTH);
		rightPane.add(loadingLabels[1], BorderLayout.SOUTH);
		timerLabel.setHorizontalAlignment(JLabel.CENTER);
		gamePanel.add(centerPanel,BorderLayout.CENTER);
		gamePanel.add(timerLabel,BorderLayout.NORTH);
	}
	
	public void setLoadingVisible(boolean visible,int player){
		loadingLabels[player].setVisible(visible);
		repaint();
	}
	
	private void initEndGame(int playerNumber){
		if(!end){
			end = true;
			JPanel endPanel = new JPanel();
			endPanel.setLayout(new GridLayout(1,3));
			CowBoySprite c = getPlayerSprite(playerNumber);
			c.resetState();
			endPanel.add(c);
			String winGame =getPlayerString(playerNumber) + " a gagné";
			JLabel winLabel = new JLabel(winGame);
			voix.playWav(getPlayerSound(playerNumber),true);
			voix.playShortText(" a gagné",true);
			winLabel.setFont(new Font("Arial",Font.BOLD,Preferences.LARGE_SIZE));
			endPanel.add(winLabel);
			cardLayoutPanel.add(endPanel, "end");
			CardLayout cl = (CardLayout) cardLayoutPanel.getLayout();
			cl.show(cardLayoutPanel,"end");
		}
		
	}
	public String getPlayerString(int playerNumber){
		if(playerNumber == 0){
			return "Billy the kid";
		} else {
			return "Joey Dalton";
		}
	}
	public String getPlayerSound(int playerNumber){
		String base = ressources;
		if(playerNumber == 0){
			return base+"/billythekid.wav";
		} else {
			return base + "/joeydalton.wav";
		}
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
		//if(timer == null  || !timer.isRunning()){
		boolean launch = false;
		if(engine.Round() > 0 && engine.maxScore() < engine.RoundNumber()){
			voix.playWav(getPlayerSound(0),true);
			voix.playShortText(engine.getPlayerScore(0) +"",true);
			voix.playWav(getPlayerSound(1),true);
			voix.playShortText(engine.getPlayerScore(1) +"",true);
			launch = true;
		}
		if(engine.maxScore() >=  engine.RoundNumber() ){
			initEndGame(engine.getWinner());
		}
		
		gameStarted = false;
		timerValue = 3;
		if(engine == null)
			engine = new GameEngine(3,true,this);
		timerLabel.setText("be ready !");
		ActionListener taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!end)
					engine.launchRound();
				
				/*		if(timerValue <= 1){

					engine.launchRound();
					System.out.println("round launched");
					timer.stop();
					timerLabel.setText("be ready !");
				}
				 else {
					timerLabel.setText(--timerValue + "");
					voix.playShortText(timerValue + "");
				 }
			}*/
			timer.stop();
		}};
		int time = 0;
		if(launch)
			time = 10000;
		
			timer = new Timer(time,taskPerformer);
			timer.setRepeats(false);
		
			timer.start();
		//}
	}
	
	public void resetState(){
		gameMode = true;
		leftCowBoy.resetState();
		rightCowBoy.resetState();
	}
	
	public  void changeColor() {
    	// on récupère les couleurs de base dans la classe Preferences 
		Preferences pref = Preferences.getData();
		aide.setBackground(pref.getCurrentBackgroundColor());
		aide.setForeground(pref.getCurrentForegroundColor());
	}
	
	/**
	 * Pour modifier la police des textes à chaque fois que l'on presse F4 
	 */
	public void changeSize(){
		Font f = Preferences.getData().getCurrentFont();
		aide.setFont(f);
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
	
	/**
	 * Return the direction of the wiimotes
	 */
	@Override
	public void movementConfirmed(MovementEvent event) {

		if (event.getSource() == wiimotesControllers[0]) {
			// System.out.println("Wiimote 1 : " + event.getDirection());
		}

		if (event.getSource() == wiimotesControllers[1]) {
			// System.out.println("Wiimote 2 : " + event.getDirection());
		}
	}
	
	/**
	 * Return the shoot event of the wiimotes
	 */
	@Override
	public void getShootEvent(MovementEvent event) {
		
		if(engine != null && engine.isInGame()) {
			int orig;
			
			if(event.getSource() == wiimotesControllers[0]) {
				orig = 0;
			} else {
				orig = 1;
			}
			
			engine.playerShot(orig);
		}
	}
	
	/**
	 * Return the ready event of the wiimotes
	 */
	@Override
	public void buttonAEvent(MovementEvent event) {
		System.out.println("button a event");
		if(!gameMode) {
			if(event.getSource() == wiimotesControllers[0]){
				playerOneReady = true;
			} else {
				playerTwoReady = true;
			}
			
			if(playerOneReady && playerTwoReady) {
				setGameMode();
				this.voix.forceStop();
				playerOneReady = false;
				playerTwoReady = false;
				startGameTimer();
				this.voix.forceStop();
				//removeKeyListener(this);
			}
		}
	}

	/**
	 * Return the up event of the wiimotes
	 */
	@Override
	public void buttonUpEvent(MovementEvent event) {
		
		if (event.getSource() == wiimotesControllers[0]) {
			System.out.println("Wiimote 1 : up event !");
		}
		
		if (event.getSource() == wiimotesControllers[1]) {
			System.out.println("Wiimote : up event !");
		}
	}
	
	/**
	 * Return the down event of the wiimotes
	 */
	@Override
	public void buttonDownEvent(MovementEvent event) {
		
		if (event.getSource() == wiimotesControllers[0]) {
			System.out.println("Wiimote 1 : down event !");
		}
		
		if (event.getSource() == wiimotesControllers[1]) {
			System.out.println("Wiimote : down event !");
		}
	}

	/**
	 * Return the plus event of the wiimotes
	 */
	@Override
	public void buttonPlusEvent(MovementEvent event) {

		if (event.getSource() == wiimotesControllers[0]) {
			System.out.println("Wiimote 1 : plus event !");
		}
		
		if (event.getSource() == wiimotesControllers[1]) {
			System.out.println("Wiimote : plus event !");
		}
	}

	/**
	 * Return the minus event of the wiimotes
	 */
	@Override
	public void buttonMinusEvent(MovementEvent event) {

		if (event.getSource() == wiimotesControllers[0]) {
			System.out.println("Wiimote 1 : minus event !");
		}
		
		if (event.getSource() == wiimotesControllers[1]) {
			System.out.println("Wiimote : minus event !");
		}
	}
	
	// renvoie le fichier wave contenant le message d'accueil
	protected String wavAccueil() {
				return "../ressources/sons/explicationDuel.wav";
	}

	// renvoie le fichier wave contenant la règle du jeu
	protected String wavRegleJeu() {
				return "../ressources/sons/explicationDuel.wav";
	}

	// renvoie le fichier wave contenant la règle du jeu
	protected String wavAide() {
				return "../ressources/sons/explicationDuel.wav";
	}
}
