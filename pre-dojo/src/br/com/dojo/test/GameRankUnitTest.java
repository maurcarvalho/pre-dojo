package br.com.dojo.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.dojo.business.GameRankBO;
import br.com.dojo.business.GameRankBOImpl;
import br.com.dojo.model.GameRanking;
import br.com.dojo.model.Gun;
import br.com.dojo.model.Player;

public class GameRankUnitTest {

	@Test
	public void testGetPKInformations() {
		GameRankBO gameRank = new GameRankBOImpl();
		GameRanking gameRanking = new GameRanking();
		String logLine  = "23/04/2013 15:36:04 - Zack killed Roman using GLOCK";
		
		Player expectedResult = new Player();
		expectedResult.setName("Zack");
		expectedResult.setScore(1);
		expectedResult.setStreak(1);
		List<Gun>playerGuns = new ArrayList<Gun>();
		Gun gun = new Gun("GLOCK");
		gun.setUsedTimes(1);
		playerGuns.add(gun);
		expectedResult.setPlayerGuns(playerGuns);
		Player realResult = gameRank.getPKInformations(logLine, gameRanking);
		boolean equals = expectedResult.equals(realResult);
		assertTrue(equals);
		
		//Verifica se o player morto entrou na lista de do ranking
		List<Player> playersOnGame = gameRanking.getListRanking();
		Player playerKilled = new Player();
		playerKilled.setDeathTimes(1);
		playerKilled.setStreak(0);
		playerKilled.setName("Roman");
		
		boolean contains = playersOnGame.contains(playerKilled);
		assertTrue(contains);
		
		
		
	}

}
