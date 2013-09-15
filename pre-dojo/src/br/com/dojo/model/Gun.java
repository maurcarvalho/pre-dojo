package br.com.dojo.model;

import static br.com.dojo.constant.PlayerRankingConstants.NUM_ZERO;

public class Gun {

	private String name;
	private Integer usedTimes;

	public Integer getUsedTimes() {
		if (usedTimes == null) {
			usedTimes = new Integer(NUM_ZERO);
		}
		return usedTimes;
	}

	public void setUsedTimes(Integer usedTimes) {
		this.usedTimes = usedTimes;
	}

	public Gun(String gunName) {
		this.name = gunName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		Gun other = (Gun) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
