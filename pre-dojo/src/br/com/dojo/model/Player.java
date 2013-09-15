package br.com.dojo.model;

import java.util.ArrayList;
import java.util.List;
import static br.com.dojo.constant.PlayerRankingConstants.NUM_ZERO;
/**
 * @author Mauricio Carvalho
 * @since 12/09/2013
 *
 */
public class Player {
	private String name;
	private List<Gun> playerGuns;
    private Integer deathTimes;
    private Integer score;
    private Integer streak;
    private Boolean timeAward;

   public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Gun> getPlayerGuns() {
		if (playerGuns == null) {
			playerGuns = new ArrayList<Gun>();
		}
		return playerGuns;
	}

	public void setPlayerGuns(List<Gun> playerGuns) {
		this.playerGuns = playerGuns;
	}

	public Integer getDeathTimes() {
		if(deathTimes == null){
			deathTimes = new Integer(NUM_ZERO);
		}
		return deathTimes;
	}

	public void setDeathTimes(Integer deathTimes) {
		this.deathTimes = deathTimes;
	}

	public Integer getScore() {
		if(score == null){
			score = new Integer(NUM_ZERO);
		}
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getStreak() {
		if(streak == null){
			streak = new Integer(NUM_ZERO);
		}
		return streak;
	}

	public void setStreak(Integer streak) {
		this.streak = streak;
	}

	public Boolean getTimeAward() {
		if(timeAward == null){
			timeAward = new Boolean(false);
		}
		return timeAward;
	}

	public void setTimeAward(Boolean timeAward) {
		this.timeAward = timeAward;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (deathTimes == null) {
			if (other.deathTimes != null)
				return false;
		} else if (!deathTimes.equals(other.deathTimes))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (playerGuns == null) {
			if (other.playerGuns != null)
				return false;
		} else if (!playerGuns.equals(other.playerGuns))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (streak == null) {
			if (other.streak != null)
				return false;
		} else if (!streak.equals(other.streak))
			return false;
		if (timeAward == null) {
			if (other.timeAward != null)
				return false;
		} else if (!timeAward.equals(other.timeAward))
			return false;
		return true;
	}

}
