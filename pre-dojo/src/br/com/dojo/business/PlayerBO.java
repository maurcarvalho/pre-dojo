package br.com.dojo.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.dojo.model.Player;

/**
 * @author Mauricio Carvalho
 * @since 12/09/2013
 *
 */
public interface PlayerBO {

	/**
	 * Valida se a Linha que esta sendo feita a leitura contem informacoes de um
	 * jogador.
	 * 
	 * @param logLineContent
	 * @return boolean
	 */
	public abstract Boolean validatePlayer(String logLineContent);

	/**
	 * Retorna um vetor de Strings contendo as informacoes de logLineContent. O
	 * Padrao utilizado para gerar o vetor e separar na string logLineContent
	 * todos as palavras entre espacos em branco.
	 * 
	 * @param logLineContent
	 * @return String[] SplitedLogLine
	 */
	public abstract String[] getSplitedLogLine(String logLineContent);

	/**
	 * Retorna o nome do player matador.
	 * 
	 * @param splitedLogLine
	 * @return String
	 */
	public abstract String getPlayerKillerName(String[] splitedLogLine);

	/**
	 * Retorna a arma utilizada pelo matador.
	 * 
	 * @param splitedLogLine
	 * @return String
	 */
	public abstract String getKillerGun(String[] splitedLogLine);

	/**
	 * Retorna o nome do player morto.
	 * 
	 * @param splitedLogLine
	 * @return String
	 */
	public abstract String getPlayerKilledName(String[] splitedLogLine);

	/**
	 * Retorna a data e hora que o Killer executou seu adversario.
	 * @param splitedLogLine
	 * @return Date 
	 */
	public Date  getPlayerKillTime(String[] splitedLogLine);
	
	/**
	 * Retorna a arma que o vencedor mais matou na rodada.
	 * @param bestRoundPlayer
	 * @return String
	 */
	public abstract String getBestWPlayerGun(Player bestRoundPlayer);
	
	/**
	 * Retorna o player que mais matou na rodada.
	 * @param bestRoundPlayer
	 * @param player
	 * @return Player
	 */
	public abstract Player getBestRoundPlayer (Player bestRoundPlayer, Player player);
	
	/**
	 * Retorna o player que mais matou sem morrer na rodada.
	 * @param bestStreakPlayer
	 * @param player
	 * @return Player
	 */
	public abstract Player getBestStreakPlayer(Player bestStreakPlayer, Player player);
	
	/**
	 * Atualiza a colecao de armas do matador da rodada.
	 * 
	 * @param splitedLogLine
	 * @param pKiller
	 */
	public abstract void updatePlayerGuns(String[] splitedLogLine, Player pKiller);
	
	/**
	 * Atualiza as informacoes do player matador na rodada.
	 * 
	 * @param playerKiller
	 * @param pKillerName
	 * @param gameRanking
	 * @return
	 */
	public abstract Player updatePlayerKiller(Player playerKiller, String pKillerName);
	
	
	/**
	 * Valida se o matador que eliminou 5 oponentes ou mais realizou o feito em
	 * menos de 1 minuto. caso sim, este recebera o premio de time award.
	 * 
	 * @param player
	 * @param killTimes
	 * @return Boolean
	 */
	public abstract Boolean checkTimeAward(Player player, Map<Player, List<Date>> killTimes);

	/**
	 * Valida se nao houve imput incorreto de datas no arquivo log, onde a data
	 * inicial nunca deve ser maior que a data final.
	 * 
	 * @param currentTime
	 * @param lastTime
	 */
	public abstract void validateTime(List<Date>logDates);

}
