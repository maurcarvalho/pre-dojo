package br.com.dojo.business;

import br.com.dojo.model.GameRanking;
import br.com.dojo.model.Player;

/**
 * @author Mauricio Carvalho
 * @since 12/09/2013
 * 
 */
public interface GameRankBO {

	/**
	 * Retorna e atualiza as informacoes dos Players envolvidos na rodada.
	 * 
	 * @param logLineContent
	 * @param gameRanking
	 * @return rankingInformations
	 */
	public abstract Player getPKInformations(String logLineContent, GameRanking gameRanking);

}
