package bangbangproject;

import java.awt.GridLayout;

import javax.swing.JPanel;

import devintAPI.FenetreAbstraite;

public class GameWindow extends FenetreAbstraite {
	private boolean playerOneReady,playerTwoReady;
	private JPanel mainPanel;
	private CowBoySprite leftCowBoy, rightCowBoy;
	
	public GameWindow(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	
	}
	public GameWindow(int roundNumbers, boolean gameType, int difficulty){
		super("Game ON");
		init();
	}
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		playerOneReady = false;
		playerTwoReady = false;
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3,1));
		leftCowBoy = new CowBoySprite(CowBoySprite.LEFT);
		rightCowBoy = new CowBoySprite(CowBoySprite.RIGHT);
		mainPanel.add(leftCowBoy,0);
		mainPanel.add(rightCowBoy,2);
		
	}
	@Override
	protected String wavAide() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void changeColor() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void changeSize() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected String wavAccueil() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String wavRegleJeu() {
		// TODO Auto-generated method stub
		return null;
	}

}
