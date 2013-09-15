package br.com.dojo.business;

import static br.com.dojo.constant.PlayerRankingConstants.*;
import static br.com.dojo.constant.ResourceConstants.LOG_DATA_EXCEPTION;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import br.com.dojo.model.Gun;
import br.com.dojo.model.Player;

public class PlayerBOImpl implements PlayerBO {

	@Override
	public Boolean validatePlayer(String logLineContent) {
		return ((!logLineContent.contains(MASTER_PLAYER)) && logLineContent
				.contains(KILLED));
	}

	@Override
	public String[] getSplitedLogLine(String logLineContent) {
		return logLineContent.split(SPACE);
	}

	@Override
	public String getPlayerKillerName(String[] splitedLogLine) {
		return splitedLogLine[INDEX_KILLER];
	}

	@Override
	public String getKillerGun(String[] splitedLogLine) {
		return splitedLogLine[INDEX_GUN];
	}

	@Override
	public String getPlayerKilledName(String[] splitedLogLine) {
		return splitedLogLine[INDEX_KILLED];
	}

	@Override
	public Date getPlayerKillTime(String[] splitedLogLine) {
		Date date = null;
		SimpleDateFormat spf = new SimpleDateFormat(DATE_PATTERN);

		try {
			StringBuilder dateAndHour = new StringBuilder(splitedLogLine[NUM_ZERO]);
			dateAndHour.append(SPACE);
			dateAndHour.append(splitedLogLine[NUM_UM]);
			date = spf.parse(dateAndHour.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public String getBestWPlayerGun(Player bestRoundPlayer) {
		int aux = NUM_ZERO;
		String bestGunName = SPACE;
		for (Gun gun : bestRoundPlayer.getPlayerGuns()) {
			if (gun.getUsedTimes() > aux) {
				aux = gun.getUsedTimes();
				bestGunName = gun.getName();
			}
		}
		return bestGunName;
	}
	@Override
	public Player getBestRoundPlayer(Player bestRoundPlayer, Player player) {
		if (player.getScore().equals(bestRoundPlayer.getScore())) {
			if (player.getDeathTimes() < bestRoundPlayer.getDeathTimes()) {
				bestRoundPlayer = player;
			}
		} else if (player.getScore() > bestRoundPlayer.getScore()) {
			bestRoundPlayer = player;
		}

		return bestRoundPlayer;
	}

	@Override
	public Player getBestStreakPlayer(Player bestStreakPlayer, Player player) {
		if (player.getStreak() > bestStreakPlayer.getStreak()) {
			bestStreakPlayer = player;
		}
		return bestStreakPlayer;
	}

	@Override
	public void updatePlayerGuns(String[] splitedLogLine, Player pKiller) {
		Gun gun = null;
		boolean existsGun = false;
		String gunName = getKillerGun(splitedLogLine);

		for (Gun playerGun : pKiller.getPlayerGuns()) {
			if (playerGun.getName().equals(gunName)) {
				gun = playerGun;
				existsGun = true;
			}
		}

		if (!existsGun) {
			gun = new Gun(getKillerGun(splitedLogLine));
		}

		gun.setUsedTimes(gun.getUsedTimes() + NUM_UM);
		pKiller.getPlayerGuns().add(gun);

	}

@Override
	public Player updatePlayerKiller(Player playerKiller, String pKillerName) {
		if (playerKiller == null) {
			playerKiller = new Player();
			playerKiller.setName(pKillerName);
		}
		playerKiller.setScore(playerKiller.getScore() + NUM_UM);
		playerKiller.setStreak(playerKiller.getStreak() + NUM_UM);
		return playerKiller;
	}

	@Override
	public Boolean checkTimeAward(Player player, Map<Player, List<Date>> killTimes) {
		boolean TimeAward = false;
		List<Date> listOfDates = new LinkedList<Date>();
		Set<Player> set = killTimes.keySet();

		for (Player playerKey : set) {
			if (player.equals(playerKey)) {
				listOfDates.addAll(killTimes.get(playerKey));
			}
		}
        
		int currentIndex = (listOfDates.size() - NUM_UM) ;
		Date currentTime = listOfDates.get(currentIndex);
		
		int lastValidIndex = (listOfDates.size() - NUM_CINCO); 
		Date lastTime = listOfDates.get(lastValidIndex);
		
		Long time = currentTime.getTime() - lastTime.getTime();
		Long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
		Long hours = TimeUnit.MILLISECONDS.toHours(time);

		if ((minutes <= T_UM) && (hours.equals(T_ZERO))) {
			TimeAward = true;
		}

		return TimeAward;
	}
	
	@Override
	public void validateTime(List<Date> listOfDates) {
		Date currentTime = listOfDates.get(listOfDates.size() - NUM_UM);
		Date lastTime = null;
		for (Date date : listOfDates) {
			lastTime = date;
			if (lastTime.after(currentTime)) {
				throw new IllegalArgumentException(LOG_DATA_EXCEPTION);
			}
		}
		
	}

}
