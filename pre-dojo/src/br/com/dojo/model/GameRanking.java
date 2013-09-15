package br.com.dojo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameRanking {

	public List<Player> listRanking;
	public Map<Player, List<Date>> killTimes;

	public List<Player> getListRanking() {
		if (listRanking == null) {
			listRanking = new ArrayList<Player>();
		}
		return listRanking;
	}

	public void setListRanking(List<Player> listRanking) {
		this.listRanking = listRanking;
	}

	public Map<Player, List<Date>> getKillTimes() {
		if (killTimes == null) {
			killTimes = new HashMap<Player, List<Date>>();
		}
		return killTimes;
	}

	public void setKillTimes(Map<Player, List<Date>> killTimes) {
		this.killTimes = killTimes;
	}

}
