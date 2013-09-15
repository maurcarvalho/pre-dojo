package br.com.dojo.constant;

/**
 * @author Mauricio Carvalho
 * @since 12/09/2013
 *
 */
public final class PlayerRankingConstants {
	// Constantes de validacao/obtencao de dados do arquivo de log
	public static final String MASTER_PLAYER = "<WORLD>"; 
	public static final String KILLED = "killed";
	public static final Integer INDEX_KILLER = 3;
	public static final Integer INDEX_KILLED = 5;
	public static final Integer INDEX_GUN = 7;
	
	//Constantes para evitar numeros magicos
	public static final Integer NUM_ZERO = 0;
	public static final Integer NUM_UM = 1;
	public static final Integer NUM_CINCO = 5;
	
	//Constantes para conversao e manipulacao de hora/data
	public static final Long T_ZERO = new Long(NUM_ZERO);
	public static final Long T_UM = new Long(NUM_UM);
	
	//Constantes utilizadas na view
	public static final String LINE_SEPARATOR = "------------------------------------------------------------------------------------------------------------------------\n";
	public static final String AWARD_ATTENTION = "************************************************************************************************\n";
	public static final String PLAYER_NAME = " Player - ";
	public static final String BEST_GUN = " Arma mais utilizada: ";
    
	public static final String NUM_DEAD = " Morreu: ";
	public static final String SCORE = " Matou: ";
	public static final String PLAYER_WIN = " Vencedor: ";
	public static final String AWARD_DEAD = " Award por não morrer em nenhuma rodada para : ";
	public static final String AWARD_STREAK = " Award por streak para o player: ";
	public static final String NUM_STREAK = " rodadas sem morrer.";
	public static final String AWARD_TIME = "Award por matar 5 oponentes em ate um minuto para: ";
    
    //Constantes comuns
	public static final String SPACE = " ";
	public static final String HYPHEN = " - ";
	public static final String NEXT_LINE = "\n";
	    
}
