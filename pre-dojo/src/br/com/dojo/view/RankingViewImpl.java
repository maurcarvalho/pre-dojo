package br.com.dojo.view;

import static br.com.dojo.constant.PlayerRankingConstants.AWARD_ATTENTION;
import static br.com.dojo.constant.PlayerRankingConstants.AWARD_DEAD;
import static br.com.dojo.constant.PlayerRankingConstants.AWARD_TIME;
import static br.com.dojo.constant.PlayerRankingConstants.AWARD_STREAK;
import static br.com.dojo.constant.PlayerRankingConstants.BEST_GUN;
import static br.com.dojo.constant.PlayerRankingConstants.HYPHEN;
import static br.com.dojo.constant.PlayerRankingConstants.LINE_SEPARATOR;
import static br.com.dojo.constant.PlayerRankingConstants.NEXT_LINE;
import static br.com.dojo.constant.PlayerRankingConstants.NUM_DEAD;
import static br.com.dojo.constant.PlayerRankingConstants.NUM_STREAK;
import static br.com.dojo.constant.PlayerRankingConstants.NUM_ZERO;
import static br.com.dojo.constant.PlayerRankingConstants.PLAYER_NAME;
import static br.com.dojo.constant.PlayerRankingConstants.PLAYER_WIN;
import static br.com.dojo.constant.PlayerRankingConstants.SCORE;
import static br.com.dojo.constant.PlayerRankingConstants.SPACE;

import javax.swing.JOptionPane;

import br.com.dojo.business.PlayerBO;
import br.com.dojo.business.PlayerBOImpl;
import br.com.dojo.model.GameRanking;
import br.com.dojo.model.Player;

public class RankingViewImpl {
    
	public void printReport(GameRanking gameRanking){
		Player bestRoundPlayer = new Player();
		Player bestStreakPlayer = new Player();
		StringBuilder out = new StringBuilder();
		PlayerBO playerBO = new PlayerBOImpl();
		if(!gameRanking.getListRanking().isEmpty()){
			
			for (Player player : gameRanking.getListRanking()) {
				appendPlayersInformation(player, out);
				bestRoundPlayer = playerBO.getBestRoundPlayer(bestRoundPlayer, player);
				bestStreakPlayer = playerBO.getBestStreakPlayer(bestStreakPlayer, player);
				if(player.getTimeAward()){
					appendPlayerTimeAward(player,out);
				}
			}
		}
		    appendBestStreakAward(bestStreakPlayer,out);
		    appendBestRoundPlayer(bestRoundPlayer,out);
			String bestGunName = playerBO.getBestWPlayerGun(bestRoundPlayer);
			appendBestWinnerPlayerGun(out, bestGunName);	
			
			
			if(bestRoundPlayer.getDeathTimes() == NUM_ZERO){
				appendDeathAward(bestRoundPlayer, out);
			}
			
			
			printInformation(out);
		}
	
	/**
	 * Executa comando de saida gráfica 
	 * @param out
	 */
	private void printInformation(StringBuilder out){
		JOptionPane.showMessageDialog(null,out);
	}
	
	/**
	 * Realiza append com o award de mortes do jogador
	 * @param bestRoundPlayer
	 * @param out
	 */
	private void appendDeathAward(Player bestRoundPlayer, StringBuilder out) {
		out.append(AWARD_DEAD);
		out.append(bestRoundPlayer.getName());
		out.append(NEXT_LINE);
	}

	/**
	 * Realiza append com a melhor arma do Vencedor
	 * @param out
	 * @param bestGunName
	 */
	private void appendBestWinnerPlayerGun(StringBuilder out, String bestGunName) {
		out.append(BEST_GUN);
		out.append(bestGunName);
		out.append(NEXT_LINE);
	    out.append(LINE_SEPARATOR);
	}

	/**
	 * Realiza append com as informacoes do Player que mais matou sem morrer na rodada.
	 * @param bestStreakPlayer
	 * @param out
	 */
	private void appendBestStreakAward(Player bestStreakPlayer,StringBuilder out) {
		out.append(AWARD_ATTENTION);
		out.append(AWARD_STREAK);
		out.append(bestStreakPlayer.getName());
		out.append(HYPHEN);
		out.append(SPACE);
		out.append(bestStreakPlayer.getStreak());
		out.append(NUM_STREAK);
		out.append(NEXT_LINE);
		out.append(AWARD_ATTENTION);
	}

	/**
	 * Realiza append com as informacoes do vencedor da rodada.
	 * @param bestRoundPlayer
	 * @param out
	 */
	private void appendBestRoundPlayer(Player bestRoundPlayer, StringBuilder out) {
		out.append(PLAYER_WIN);
		out.append(bestRoundPlayer.getName());
		out.append(NEXT_LINE);
	}
	
	/**
	 * Realiza append com as informacoes do Player.
	 * @param player
	 * @param out
	 */
	private void appendPlayersInformation(Player player, StringBuilder out) {
	   
	   out.append(PLAYER_NAME);
	   out.append(player.getName());	
	   out.append(NEXT_LINE);	 
		
	   out.append(NUM_DEAD);
		out.append(player.getDeathTimes());
		

		out.append(SCORE);
		out.append(player.getScore());
		out.append(NEXT_LINE);
		out.append(LINE_SEPARATOR);
	}

	
	/**
	 * Adiciona a String de saida time award 
	 * para os players que ganharam o premio na partida.
	 * @param player
	 * @param out
	 */
	private void appendPlayerTimeAward(Player player, StringBuilder out){
		out.append(AWARD_ATTENTION);
		out.append(AWARD_TIME);
		out.append(player.getName());
		out.append(NEXT_LINE);
		out.append(AWARD_ATTENTION);
	}
}
	
