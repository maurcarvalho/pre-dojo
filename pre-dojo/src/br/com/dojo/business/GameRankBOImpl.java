package br.com.dojo.business;

import static br.com.dojo.constant.PlayerRankingConstants.NUM_CINCO;
import static br.com.dojo.constant.PlayerRankingConstants.NUM_UM;
import static br.com.dojo.constant.PlayerRankingConstants.NUM_ZERO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.com.dojo.model.GameRanking;
import br.com.dojo.model.Player;

/**
 * @author Mauricio Carvalho
 * @since 12/09/2013
 * 
 */
public class GameRankBOImpl implements GameRankBO {

	private PlayerBO playerBO = new PlayerBOImpl();
    private List<Date> logDates = new ArrayList<Date>();

	public Player getPKInformations(String logLineContent,
			GameRanking gameRanking) {
		String[] splitedLogLine = playerBO.getSplitedLogLine(logLineContent);
		String pKillerName = playerBO.getPlayerKillerName(splitedLogLine);
		String pKilledName = playerBO.getPlayerKilledName(splitedLogLine);
		Player playerKiller = null;
		Player playerKilled = null;

		for (Player playerInGame : gameRanking.getListRanking()) {
			if (playerInGame.getName().equals(pKillerName)) {
				playerKiller = playerInGame;
			}
			if (playerInGame.getName().equals(pKilledName)) {
				playerKilled = playerInGame;
			}
		}

		playerKiller = playerBO.updatePlayerKiller(playerKiller, pKillerName);
		playerBO.updatePlayerGuns(splitedLogLine, playerKiller);
		updatePKRankingTimes(gameRanking, splitedLogLine, playerKiller);
		updateRoundPKilled(playerKilled, pKilledName, gameRanking);
		updateTimeAward(playerKiller, gameRanking);
		updateLogDates(splitedLogLine, logDates);

		return playerKiller;
	}

	/**
	 * Atualiza na Collection de gameRanking o player morto na rodada. Caso o
	 * objeto ainda nao exista na collection, e criado um novo player.
	 * 
	 * @param pKilledName
	 * @param gameRanking
	 */
	private void updateRoundPKilled(Player playerKilled, String pKilledName,
			GameRanking gameRanking) {
		boolean newPlayer = false;

		if (playerKilled == null) {
			playerKilled = new Player();
			playerKilled.setName(pKilledName);
			newPlayer = true;
		}

		playerKilled.setDeathTimes(playerKilled.getDeathTimes() + NUM_UM);
		playerKilled.setStreak(NUM_ZERO);

		if (newPlayer) {
			gameRanking.getListRanking().add(playerKilled);
		}

	}

	/**
	 * Atualiza o ranking com os horarios/player da rodada.
	 * 
	 * @param gameRanking
	 * @param splitedLogLine
	 * @param playerKiller
	 */
	private void updatePKRankingTimes(GameRanking gameRanking, String[] splitedLogLine, Player playerKiller) {
		List<Date> dates = gameRanking.getKillTimes().get(playerKiller);
		if (dates == null) {
			dates = new ArrayList<Date>();
		}
		dates.add(playerBO.getPlayerKillTime(splitedLogLine));
		gameRanking.getKillTimes().put(playerKiller, dates);
	}

	/**
	 * Atualiza o premio de timeAward para o playerKiller no ranking.
	 * 
	 * @param playerKiller
	 * @param gameRanking
	 */
	private void updateTimeAward(Player playerKiller, GameRanking gameRanking) {
		if ((playerKiller.getScore() >= NUM_CINCO)) {
			Boolean timeAward = playerBO.checkTimeAward(playerKiller,
					gameRanking.getKillTimes());
			playerKiller.setTimeAward(timeAward);
		}
	}
    
	/**
	 * Atualiza a lista de horas da partida e valida a sequencia dos eventos
	 * @param splitedLogLine
	 * @param logDates
	 */
	private void updateLogDates(String[]splitedLogLine,List<Date> logDates){
		logDates.add(playerBO.getPlayerKillTime(splitedLogLine));
		playerBO.validateTime(logDates);
	}
}
