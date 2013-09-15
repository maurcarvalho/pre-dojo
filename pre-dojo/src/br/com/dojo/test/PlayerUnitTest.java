package br.com.dojo.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.dojo.business.PlayerBO;
import br.com.dojo.business.PlayerBOImpl;
import br.com.dojo.model.Gun;
import br.com.dojo.model.Player;

public class PlayerUnitTest {

	@Test
	public void testValidatePlayer() {
		String fisrtErrorScene  = "23/04/2013 15:36:33 - <WORLD> killed Ralph by DROWN";
		String secondErrorScene = "23/04/2013 15:34:22 - New match 11348965 has started";
		String thirdSErrorcene  = "23/04/2013 15:39:22 - Match 11348965 has ended";
		
		PlayerBO playerBo = new PlayerBOImpl();
        assertFalse(playerBo.validatePlayer(fisrtErrorScene));
        assertFalse(playerBo.validatePlayer(secondErrorScene));
        assertFalse(playerBo.validatePlayer(thirdSErrorcene));
        
        //Sucess Result
        String firstSucessScene = "23/04/2013 15:36:04 - Roman killed Marck using AK47";
        assertTrue(playerBo.validatePlayer(firstSucessScene));

	}

	@Test
	public void testGetSplitedLogLine() {
		PlayerBO playerBO = new PlayerBOImpl();
		String logLineContent = "23/04/2013 15:36:04 - Nick killed Roman using WS";
		String[] expectedReturn = {"23/04/2013","15:36:04","-","Nick","killed","Roman","using","WS"};
		String[] realReturn = playerBO.getSplitedLogLine(logLineContent);
		assertArrayEquals(expectedReturn, realReturn);
	}

	@Test
	public void testGetPlayerKillTime() throws ParseException{
		PlayerBO playerBO = new PlayerBOImpl();
		String[] splitedLogLine = {"23/04/2013","15:36:04","-","Nick","killed","Roman","using","WS"};
		Date data = playerBO.getPlayerKillTime(splitedLogLine);
		
		SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date expectedDate = spf.parse("23/04/2013 15:36:04");
		assertEquals(expectedDate, data);
	}
	@Test
	public void testGetPKillerName() {
		PlayerBO playerBO = new PlayerBOImpl();
		String[] splitedLogLine = {"23/04/2013","15:36:04","-","Nick","killed","Roman","using","WS"};
		String expectedReturn = "Nick";
		String realReturn = playerBO.getPlayerKillerName(splitedLogLine);
		assertEquals(expectedReturn,realReturn);
	}
	
	@Test
	public void testGetKillerGun() {
		PlayerBO playerBO = new PlayerBOImpl();
		String[] splitedLogLine = {"23/04/2013","15:36:04","-","Nick","killed","Roman","using","AK47"};
		String expectedReturn = "AK47";
		String realReturn = playerBO.getKillerGun(splitedLogLine);
		assertEquals(expectedReturn,realReturn);
	}

	@Test
	public void testGetPlayerKilledName() {
		PlayerBO playerBO = new PlayerBOImpl();
		String[] splitedLogLine = {"23/04/2013","15:36:04","-","Marck Zum","killed","Roman","using","AK47"};
		String expectedReturn = "Roman";
		String realReturn = playerBO.getPlayerKilledName(splitedLogLine);
		assertEquals(expectedReturn,realReturn);
	}
   
	@Test
	public void testGetBestWinnerPlayerGun(){
		Player player = new Player();
		player.setName("BestPlayer");
	    Gun glock = new Gun("GLOCK");
	    glock.setUsedTimes(1);
	    Gun AK47 = new Gun("AK47");
	    AK47.setUsedTimes(10);
	    
	    List<Gun> listOfGuns = new ArrayList<Gun>();
	    listOfGuns.add(glock);
	    listOfGuns.add(AK47);
	    
	    PlayerBO playerBO = new PlayerBOImpl();
	    
	    player.setPlayerGuns(listOfGuns);
	    String expectedGunName = "AK47";
	    String realGunName = playerBO.getBestWPlayerGun(player);
	    assertEquals(expectedGunName,realGunName);
	    
	}

	@Test
	public void testGetBestRoundPlayer(){
		Player playerOne = new Player();
		playerOne.setName("BestPlayer");
		playerOne.setDeathTimes(10);
		playerOne.setScore(113);
	    
	    Player playerTwo = new Player();
	    playerTwo.setScore(90);
	    playerTwo.setDeathTimes(1);
	
	    PlayerBO playerBO = new PlayerBOImpl();
	    //Lider da ultima rodada continua sendo o lider da partida
	    Player realPlayer = playerBO.getBestRoundPlayer(playerOne, playerTwo);
	    assertEquals(playerOne,realPlayer);
	    
	    //Novo Lider nesta rodada
	    playerTwo.setScore(200);
	    playerTwo.setDeathTimes(10);
	    realPlayer = playerBO.getBestRoundPlayer(playerOne, playerTwo);
	    assertEquals(playerTwo,realPlayer);

	    //Score empatadado. Lider decidido pelo numero de mortes
	    playerTwo.setScore(200);
	    playerTwo.setDeathTimes(2);

	    playerOne.setScore(200);
	    playerOne.setDeathTimes(1);
	    //Neste momento PlayerTwo e o lider por isso foi o primeiro param
	    realPlayer = playerBO.getBestRoundPlayer(playerTwo, playerOne);
	    assertEquals(playerOne,realPlayer);

	} 
	
	@Test
	public void testGetBestStreakPlayer(){
		Player playerOne = new Player();
		playerOne.setStreak(10);
		
		Player playerTwo = new Player();
		playerTwo.setStreak(11);
		
	    PlayerBO playerBO = new PlayerBOImpl();
	    Player realPlayer = playerBO.getBestStreakPlayer(playerOne, playerTwo);
		assertEquals(playerTwo, realPlayer);
	}

}
