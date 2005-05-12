
import java.applet.*;
import java.awt.*; 
import java.net.*;
import java.awt.event.*;
import java.util.*;

public class main extends Applet implements  Runnable, MouseListener{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1771548881434019904L;
	
	
	public static Graphics buffer;
	Image offscreen;
	Dimension dim;
	int dimX, dimY;
	
	AudioClip winnerSound; 
	AudioClip looserSound;
	AudioClip beepSound;
	
	Image plansza;
	Image i00;
	Image i01;
	Image i02;
	Image i03;
	Image i04;
	Image i11;
	Image i12;
	Image i13;
	Image i14;
	Image i22;
	Image i23;
	Image i24;
	Image i33;
	Image i34;
	Image i44;
	Image bigBlue;
	Image bigRed;
	Image bigGreen;
	Image bigOrange;
	Image bigViolet;
	Image bigYellow;
	Image smallBlue;
	Image smallRed;
	Image smallGreen;
	Image smallOrange;
	Image smallViolet;
	Image smallYellow;
	Image pinBlue;
	Image pinRed;
	Image pinGreen;
	Image pinOrange;
	Image pinViolet;
	Image pinYellow;
	Image iStart;
	Image iStop;
	Image Selected;
	Image youRock;
	
	URL base;
	MediaTracker mt;
	int xpos;
	int ypos;
	
	boolean blueClicked;
	boolean redClicked;
	boolean greenClicked;
	boolean orangeClicked;
	boolean violetClicked;
	boolean yellowClicked;
	boolean blueSelected;
	boolean redSelected;
	boolean greenSelected;
	boolean orangeSelected;
	boolean violetSelected;
	boolean yellowSelected;
	boolean winner;
	boolean looser;
	
	int licznik;
	int row;
	boolean playing;
	int minColor;
	
	Random generator;
	Vector userColors;
 	Vector tempColors;
 	Vector hiddenColors;
 	Vector colorVector;
 	Vector scoresVector;
 	boolean losuj;
 	int guessedColors;
 	int guessedPlaces;
 	String imageName;
 	private Thread Clock;
 	private int sec;
	
 	int[] coordX = { 359, 388, 418, 449, 
 					 356, 387, 416, 449, 
 					 357, 387, 416, 448, 
 					 355, 385, 416, 446, 
 					 353, 384, 415, 445, 
 					 353, 383, 413, 444, 
 					 352, 382, 413, 444, 
 					 350, 381, 412, 444, };
 	
 	int[] coordY = { 104, 104, 105, 105,
 					 133, 134, 134, 135, 
 					 163, 163, 164, 165, 
 					 193, 193, 195, 196, 
 					 224, 225, 226, 227, 
 					 254, 255, 256, 256, 
 					 285, 286, 287, 289, 
 					 318, 318, 320, 320, };
 	
 	int[] coordR = { 107, 137, 169, 198,
 					 228, 259, 291, 324, };
 	
 public void run()
 {
		while(true)
		{
			
            try 
            {  
                 Thread.sleep(1000); 
            } 
            catch (InterruptedException e) 
            {  
            }
            sec++;
            if (sec >= 5)
            {
            	beepSound.play();
            	sec = 0;       	
            }       
   		 
		}
}
 	
public String getAppletInfo()
	{
		return "Name: Java Mastermind\r\n" +
			   "Author: Karol Bonenberg, 2005";
	}

 	
 	
public void init()
	{
		Clock = new Thread(this);
		Clock.start();
		mt=new MediaTracker(this);
		dim=getSize();
		/**
		 * Sprawdzamy URL
		 */
		try {
			base = getDocumentBase();
		}
		catch (Exception e) {}
		
		/**
		 * Sciezki do zmiennych
		 */
		plansza = getImage(base,"gfx/plansza2.jpg");
		i00 = getImage(base,"gfx/00.gif");
		i01 = getImage(base,"gfx/01.gif");
		i02 = getImage(base,"gfx/02.gif");
		i03 = getImage(base,"gfx/03.gif");
		i04 = getImage(base,"gfx/04.gif");
		i11 = getImage(base,"gfx/11.gif");
		i12 = getImage(base,"gfx/12.gif");
		i13 = getImage(base,"gfx/13.gif");
		i14 = getImage(base,"gfx/14.gif");
		i22 = getImage(base,"gfx/22.gif");
		i23 = getImage(base,"gfx/23.gif");
		i24 = getImage(base,"gfx/24.gif");
		i33 = getImage(base,"gfx/33.gif");
		i34 = getImage(base,"gfx/34.gif");
		i44 = getImage(base,"gfx/44.gif");
		
		winnerSound = getAudioClip(getDocumentBase(),"gfx/wohoo.au");
		looserSound = getAudioClip(getDocumentBase(),"gfx/ooh.au");
		beepSound = getAudioClip(getDocumentBase(),"gfx/bleep_1.au");
		
		bigBlue = getImage(base,"gfx/big_blue.gif");
		bigRed = getImage(base,"gfx/big_red.gif");
		bigGreen = getImage(base,"gfx/big_green.gif");
		bigOrange = getImage(base,"gfx/big_orange.gif");
		bigViolet = getImage(base,"gfx/big_violet.gif");
		bigYellow = getImage(base,"gfx/big_yellow.gif");
		smallBlue = getImage(base,"gfx/small_blue.gif");
		smallRed = getImage(base,"gfx/small_red.gif");
		smallGreen = getImage(base,"gfx/small_green.gif");
		smallOrange = getImage(base,"gfx/small_orange.gif");
		smallViolet = getImage(base,"gfx/small_violet.gif");
		smallYellow = getImage(base,"gfx/small_yellow.gif");
		pinBlue = getImage(base,"gfx/pin_blue.gif");
		pinRed = getImage(base,"gfx/pin_red.gif");
		pinGreen = getImage(base,"gfx/pin_green.gif");
		pinOrange = getImage(base,"gfx/pin_orange.gif");
		pinViolet = getImage(base,"gfx/pin_violet.gif");
		pinYellow = getImage(base,"gfx/pin_yellow.gif");
		iStart = getImage(base,"gfx/start.gif");
		iStop = getImage(base,"gfx/stop.gif");
		Selected = getImage(base,"gfx/selected.gif");
		youRock = getImage(base,"gfx/rock.gif");
		
		/**
		 * Obrazki do MediaTrackera
		 */
		mt.addImage(plansza,1);
		mt.addImage(i00,2);
		mt.addImage(i01,3);
		mt.addImage(i02,4);
		mt.addImage(i03,5);
		mt.addImage(i04,6);
		mt.addImage(i11,8);
		mt.addImage(i12,9);
		mt.addImage(i13,10);
		mt.addImage(i14,11);
		mt.addImage(i22,14);
		mt.addImage(i23,15);
		mt.addImage(i24,16);
		mt.addImage(i33,20);
		mt.addImage(i34,21);
		mt.addImage(i44,26);
		mt.addImage(bigBlue,27);
		mt.addImage(bigRed,28);
		mt.addImage(bigGreen,29);
		mt.addImage(bigOrange,30);
		mt.addImage(bigViolet,31);
		mt.addImage(bigYellow,32);
		mt.addImage(smallBlue,33);
		mt.addImage(smallRed,34);
		mt.addImage(smallGreen,35);
		mt.addImage(smallOrange,36);
		mt.addImage(smallViolet,37);
		mt.addImage(smallYellow,38);
		mt.addImage(pinBlue,39);
		mt.addImage(pinRed,40);
		mt.addImage(pinGreen,41);
		mt.addImage(pinOrange,42);
		mt.addImage(pinViolet,43);
		mt.addImage(pinYellow,44);
		mt.addImage(iStart,45);
		mt.addImage(iStop,46);
		mt.addImage(Selected,47);
		mt.addImage(youRock,48);
		
		/**
		 * Czekamy az sie grafika zaladuje
		 */
		try {
			mt.waitForAll();
		}
		catch (InterruptedException e) {}
		
		addMouseListener(this);
		
		redSelected = true;
		blueSelected = true;
		greenSelected = true;
		yellowSelected = true;
		playing = false;
		licznik = 4;
		minColor = 3;
		
		offscreen = createImage(dim.width,dim.height);
		buffer = offscreen.getGraphics();
		generator = new Random();
		userColors = new Vector();
		tempColors = new Vector();
		hiddenColors = new Vector();
		colorVector = new Vector();
		scoresVector = new Vector();
		
		losuj=false;
		guessedPlaces=0;
		guessedColors=0;
		sec=0;
	}
	 

public void stop()
	{
		Clock=null;
	}


 public void paint(Graphics g)
	{
	 	

	 	buffer.clearRect(0,0,dim.width,dim.height);
	 	
	 	buffer.drawImage(plansza,0,0,this);
	 	buffer.drawImage(bigRed,534,149,this);
	 	buffer.drawImage(bigBlue,532,178,this);
	 	buffer.drawImage(bigGreen,533,208,this);
	 	buffer.drawImage(bigYellow,534,240,this);
	 	buffer.drawImage(bigOrange,532,300,this);
	 	buffer.drawImage(bigViolet,533,269,this);
	 	
	 	/*
	 	 *  Tu wchodzimy jezeli START nie zostal nacisniety
	 	 */
	 	
	 	if (winner) {
	 		playing=false;
	 		winner^=true;
	 	}
	 	
	 	if(looser) {
	 		playing=false;
	 		looser^=true;
	 	}
	 	
	 	if (!playing) {
	 		tempColors.clear();
	 		userColors.clear();
	 		hiddenColors.clear();
	 		scoresVector.clear();
	 		//wybor ustawien
	 		if (redClicked) {
	 			
	 			if (!redSelected || licznik>3) { //przynajmniej 3 kolory
	 				if (redSelected) licznik--; else licznik++; //zabawa licznikiem
	 				redSelected^=true; //zmiana stanu przycisku
	 			}
	 			redClicked=false;
	 		
	 		}
	 		if (blueClicked) {
	 			
	 			if (!blueSelected || licznik>minColor) {
	 				if (blueSelected) licznik--; else licznik++;
	 				blueSelected^=true;
	 			}
	 			blueClicked=false;
	 		
	 		}
	 		if (greenClicked) {
	 			
	 			if (!greenSelected || licznik>minColor) {
	 				if (greenSelected) licznik--; else licznik++;
	 				greenSelected^=true;
	 			}
	 			greenClicked=false;
	 		
	 		}
	 		if (orangeClicked) {
	 			
	 			if (!orangeSelected || licznik>minColor) {
	 				if (orangeSelected) licznik--; else licznik++;
	 				orangeSelected^=true;
	 			}
	 			orangeClicked=false;
	 		
	 		}
	 		if (violetClicked) {
	 			
	 			if (!violetSelected || licznik>minColor) {
	 				if (violetSelected) licznik--; else licznik++;
	 				violetSelected^=true;
	 			}
	 			violetClicked=false;
	 		
	 		}
	 		if (yellowClicked) {
	 			
	 			if (!yellowSelected || licznik>minColor) {
	 				if (yellowSelected) licznik--; else licznik++;
	 				yellowSelected^=true;
	 			}
	 			yellowClicked=false;
	 		
	 		}
	 	
	 		if (playing) buffer.drawImage(iStop,518,336,this); 
	 		else buffer.drawImage(iStart,514,334,this);
	 		if (redSelected) buffer.drawImage(Selected,561,157,this);
	 		if (blueSelected) buffer.drawImage(Selected,561,186,this);
	 		if (greenSelected) buffer.drawImage(Selected,560,216,this);
	 		if (yellowSelected) buffer.drawImage(Selected,560,247,this);
	 		if (violetSelected) buffer.drawImage(Selected,560,277,this);
	 		if (orangeSelected) buffer.drawImage(Selected,558,307,this);
	 	}
	 	
	 	/*
	 	 * GRA ROZPOCZETA
	 	 */
	 	
	 	else {	
	 		/*
	 		 *  losujemy kolorki!
	 		 */
			if (losuj){
				addColors();
		 		for(int i=0;i<4;i++) hiddenColors.add(userColors.get(generator.nextInt(userColors.size())));				
		 		losuj=false;
		 		row=0;
			}

			
	 		if (redClicked) {	 
	 			if(redSelected) {
	 				buffer.drawImage(pinRed,525,105,this);
	 				tempColors.add("1");
	 			}	
	 			redClicked=false; 		
	 		}
	 		if (blueClicked) {
	 			if (blueSelected) {
	 				buffer.drawImage(pinBlue,525,105,this);
	 				tempColors.add("2");
	 			}		
	 			blueClicked=false;
	 		
	 		}
	 		if (greenClicked) {
	 			if(greenSelected) {
	 				buffer.drawImage(pinGreen,525,105,this);
	 				tempColors.add("3");
	 			}
	 			greenClicked=false;
	 		
	 		}
	 		if (yellowClicked) {
	 			if(yellowSelected) {
	 				buffer.drawImage(pinYellow,525,105,this);
	 				tempColors.add("4");
	 			}
	 			yellowClicked=false;
	 		
	 		}
	 		if (violetClicked) {
	 			if(violetSelected) {
	 				buffer.drawImage(pinViolet,525,105,this);
	 				tempColors.add("5");
	 			} 			
	 			violetClicked=false;
	 		
	 		}
	 		if (orangeClicked) {
	 			if(orangeSelected) {
	 				buffer.drawImage(pinOrange,525,105,this);
	 				tempColors.add("6");
	 			}
	 			orangeClicked=false;
	 		
	 		}
 	/*
 	 * Rysowanie wcisnietych kolorkow
 	 */
			if (tempColors.size()>0) {
				for(int i=0;i<tempColors.size();i++) {				
			
					if (tempColors.get(i)=="1") {
						buffer.drawImage(smallRed,coordX[i],coordY[i],this);
					}				
					if (tempColors.get(i)=="2") {
						buffer.drawImage(smallBlue,coordX[i],coordY[i],this);
					}
					if (tempColors.get(i)=="3") {
						buffer.drawImage(smallGreen,coordX[i],coordY[i],this);
					}
					if (tempColors.get(i)=="4") {
						buffer.drawImage(smallYellow,coordX[i],coordY[i],this);
					}
					if (tempColors.get(i)=="5") {
						buffer.drawImage(smallViolet,coordX[i],coordY[i],this);
					}
					if (tempColors.get(i)=="6") {
						buffer.drawImage(smallOrange,coordX[i],coordY[i],this);
					}			
				}
			}
			/*
			 *  Sprawdzamy trafienia!
			 */
			
			checkGuesses();
			
			/*
			 *  Rysujemy informacje o zgadnieciu koloru/miejsca
			 */
			
			for(int i=0; i<scoresVector.size(); i++) {
				if(scoresVector.get(i)=="0") buffer.drawImage(i00,475,coordR[i],this);
				if(scoresVector.get(i)=="1") buffer.drawImage(i01,475,coordR[i],this);
				if(scoresVector.get(i)=="2") buffer.drawImage(i02,475,coordR[i],this);
				if(scoresVector.get(i)=="3") buffer.drawImage(i03,475,coordR[i],this);
				if(scoresVector.get(i)=="4") buffer.drawImage(i04,475,coordR[i],this);
				if(scoresVector.get(i)=="6") buffer.drawImage(i11,475,coordR[i],this);
				if(scoresVector.get(i)=="7") buffer.drawImage(i12,475,coordR[i],this);
				if(scoresVector.get(i)=="8") buffer.drawImage(i13,475,coordR[i],this);
				if(scoresVector.get(i)=="9") buffer.drawImage(i14,475,coordR[i],this);
				if(scoresVector.get(i)=="12") buffer.drawImage(i22,475,coordR[i],this);
				if(scoresVector.get(i)=="13") buffer.drawImage(i23,475,coordR[i],this);
				if(scoresVector.get(i)=="14") buffer.drawImage(i24,475,coordR[i],this);
				if(scoresVector.get(i)=="18") buffer.drawImage(i33,475,coordR[i],this);
				if(scoresVector.get(i)=="19") buffer.drawImage(i34,475,coordR[i],this);
				if(scoresVector.get(i)=="24") {
					buffer.drawImage(i44,475,coordR[i],this);
					buffer.drawImage(youRock,304,330,this);
					winnerSound.play();
					winner=true;
				}
				
			}
			
	 		if (playing) buffer.drawImage(iStop,518,336,this); 
	 		else buffer.drawImage(iStart,514,334,this);
	 		if (redSelected) buffer.drawImage(Selected,561,157,this);
	 		if (blueSelected) buffer.drawImage(Selected,561,186,this);
	 		if (greenSelected) buffer.drawImage(Selected,560,216,this);
	 		if (yellowSelected) buffer.drawImage(Selected,560,247,this);
	 		if (violetSelected) buffer.drawImage(Selected,560,277,this);
	 		if (orangeSelected) buffer.drawImage(Selected,558,307,this);
	 		
	 	};
	 	buffer.drawString("("+hiddenColors.toString()+")",10,510);
	 	//buffer.drawString("("+xpos+","+ypos+")",10,520);
	 	/*
	 	 * Rysujemy bufor na ekreanie
	 	 */
	 	
	 	g.drawImage(offscreen,0,0,this);	 	 	
	}
 
/*
 * Metoda do sprawdzania trafien
 */
 
 private void checkGuesses()
 {
		if (tempColors.size()>0 && tempColors.size()%4==0 ) {
			checkColors();
			checkPlaces();
			if (guessedColors==0 && guessedPlaces==0) scoresVector.add("0");
			if (guessedColors==1 && guessedPlaces==0) scoresVector.add("1");
			if (guessedColors==2 && guessedPlaces==0) scoresVector.add("2");
			if (guessedColors==3 && guessedPlaces==0) scoresVector.add("3");
			if (guessedColors==4 && guessedPlaces==0) scoresVector.add("4");
			if (guessedColors==0 && guessedPlaces==1) scoresVector.add("5");
			if (guessedColors==1 && guessedPlaces==1) scoresVector.add("6");
			if (guessedColors==2 && guessedPlaces==1) scoresVector.add("7");
			if (guessedColors==3 && guessedPlaces==1) scoresVector.add("8");
			if (guessedColors==4 && guessedPlaces==1) scoresVector.add("9");
			if (guessedColors==0 && guessedPlaces==2) scoresVector.add("10");
			if (guessedColors==1 && guessedPlaces==2) scoresVector.add("11");
			if (guessedColors==2 && guessedPlaces==2) scoresVector.add("12");
			if (guessedColors==3 && guessedPlaces==2) scoresVector.add("13");
			if (guessedColors==4 && guessedPlaces==2) scoresVector.add("14");
			if (guessedColors==0 && guessedPlaces==3) scoresVector.add("15");
			if (guessedColors==1 && guessedPlaces==3) scoresVector.add("16");
			if (guessedColors==2 && guessedPlaces==3) scoresVector.add("17");
			if (guessedColors==3 && guessedPlaces==3) scoresVector.add("18");
			if (guessedColors==4 && guessedPlaces==3) scoresVector.add("19");
			if (guessedColors==0 && guessedPlaces==4) scoresVector.add("20");
			if (guessedColors==1 && guessedPlaces==4) scoresVector.add("21");
			if (guessedColors==2 && guessedPlaces==4) scoresVector.add("22");
			if (guessedColors==3 && guessedPlaces==4) scoresVector.add("23");
			if (guessedColors==4 && guessedPlaces==4) scoresVector.add("24");
			row++;
			if (row>7) {
		 		looserSound.play();
				looser=true;
			}
		}
 }
 
/*
 * Metoda sprawdzajaca liczbe trafionych miejsc
 */
 private void checkPlaces() 
 { 
	 /*
	  * Sprawdzamy MIEJSCA poszczegolnych kolorow
	  */
	 int elements=tempColors.size()-4;
	 guessedPlaces=0;	
	 
	 for(int i=elements; i<tempColors.size(); i++) {		 
		 if (hiddenColors.get(i%4)==tempColors.get(i)) guessedPlaces++;
	 }
 }
 
 /*
  * Metoda sprawdzajaca liczbe zgodnych kolorow
  */
 private void checkColors()
 {
	 int elements=tempColors.size()-4;
	
	 guessedColors=0;
	 /*
	  * Kopiujemy wylosowane kolorki do tymczasowego wektora
	  */
	 Vector tmpV = new Vector();
	 for (int i=0; i<hiddenColors.size(); i++) {
		 tmpV.add(i,hiddenColors.get(i));
	 }
	 for (int i=elements;i<tempColors.size();i++) {
		 if (tmpV.contains(tempColors.get(i))) {
			 guessedColors++;
			 tmpV.set(tmpV.indexOf(tempColors.get(i)),"0");
		 }
	 }
 }

 /*
  * Metoda do wybierania zestawu kolorow uzytego w grze
  */
 private void addColors()
 {	
	 userColors.clear();
	 if (redSelected) userColors.add("1");
	 if (blueSelected) userColors.add("2");
	 if (greenSelected) userColors.add("3");
	 if (yellowSelected) userColors.add("4");
	 if (violetSelected) userColors.add("5");
	 if (orangeSelected) userColors.add("6");	 
 }
 
 /*
  * Metoda do buforowania
  */
 public void update(Graphics g)
 {
      paint(g);
 } 

public void mouseClicked(MouseEvent me) {
	xpos=me.getX();
	ypos=me.getY();
	/*
	 * Poszczegolne przyciski - kolorki,start,stop
	 */
	if (xpos>534 && xpos<534+bigRed.getWidth(null) && ypos>149 && ypos<149+bigRed.getHeight(null))
		redClicked=true;
	if (xpos>532 && xpos<532+bigBlue.getWidth(null) && ypos>178 && ypos<178+bigBlue.getHeight(null))
		blueClicked=true;
	if (xpos>533 && xpos<533+bigGreen.getWidth(null) && ypos>208 && ypos<208+bigGreen.getHeight(null))
		greenClicked=true;
	if (xpos>534 && xpos<534+bigYellow.getWidth(null) && ypos>240 && ypos<240+bigYellow.getHeight(null))
		yellowClicked=true;
	if (xpos>533 && xpos<533+bigViolet.getWidth(null) && ypos>269 && ypos<269+bigViolet.getHeight(null))
		violetClicked=true;
	if (xpos>532 && xpos<532+bigOrange.getWidth(null) && ypos>300 && ypos<300+bigOrange.getHeight(null))
		orangeClicked=true;
	if (!playing) {
		if (xpos>514 && xpos<514+iStart.getWidth(null) && ypos>334 && ypos<334+iStart.getHeight(null))
			{ playing=true; losuj=true;}
	} else {
		if (xpos>518 && xpos<518+iStop.getWidth(null) && ypos>336 && ypos<336+iStop.getHeight(null))
			playing=false;
	}
	repaint();
}


public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}


public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}


public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}


public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
	
}
