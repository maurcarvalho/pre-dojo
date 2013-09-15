package br.com.dojo.view;

import br.com.dojo.model.GameRanking;

public interface RankingView {

	/**
	 * Metodo responsavel por montar o StringBuilder de saida e
	 * chamar a impressao do mesmo.
	 * @param gameRanking
	 */
	public void printReport(GameRanking gameRanking);
}
