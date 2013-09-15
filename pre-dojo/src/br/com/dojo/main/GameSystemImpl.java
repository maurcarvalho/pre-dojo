package br.com.dojo.main;

import static br.com.dojo.constant.ResourceConstants.FILE_PATH;
import static br.com.dojo.constant.ResourceConstants.USER_DIR;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import br.com.dojo.business.GameRankBOImpl;
import br.com.dojo.business.PlayerBOImpl;
import br.com.dojo.model.GameRanking;
import br.com.dojo.model.Player;
import br.com.dojo.view.RankingViewImpl;
/**
 * @author Mauricio Carvalho
 * @since 12/09/2013
 *
 */
public class GameSystemImpl implements GameSystem {

	public FileInputStream getResourceFile() throws FileNotFoundException{
		return new FileInputStream(USER_DIR + FILE_PATH);
	}
	
	public GameRanking readFile() throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getResourceFile()));
		String logLineContent = new String();
		
		PlayerBOImpl playerBO = new PlayerBOImpl();
		GameRankBOImpl gameRankBO = new GameRankBOImpl();
		GameRanking gameRanking = new GameRanking();
		
		Player pKiller;
		
		while((logLineContent = bufferedReader.readLine()) != null){
			if(playerBO.validatePlayer(logLineContent)){
                
				pKiller = gameRankBO.getPKInformations(logLineContent,gameRanking);
				if(!gameRanking.getListRanking().contains(pKiller)){
					gameRanking.getListRanking().add(pKiller);
				}
			}
		}
		
	    return gameRanking;
	}
	
	/**
	 * Metodo de execucao do sistema
	 * @param args
	 */
	public static void main(String[] args) {
		GameSystemImpl system = new GameSystemImpl();
		GameRanking ranking;
		RankingViewImpl rankingView;
		
		try{
		      ranking = system.readFile();
		      rankingView = new RankingViewImpl();
		      rankingView.printReport(ranking);
		     
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
