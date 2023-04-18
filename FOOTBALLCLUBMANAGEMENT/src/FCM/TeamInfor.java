package FCM;

import java.util.Objects;

public class TeamInfor {
	
	private String TeamID;
	private String TeamName;
	private String Coach;
	private String Quantity;
	public TeamInfor() {
	
	}
	public TeamInfor(String teamID, String teamName, String coach, String quantity) {
		
		TeamID = teamID;
		TeamName = teamName;
		Coach = coach;
		Quantity = quantity;
	}
	public String getTeamID() {
		return TeamID;
	}
	public void setTeamID(String teamID) {
		TeamID = teamID;
	}
	public String getTeamName() {
		return TeamName;
	}
	public void setTeamName(String teamName) {
		TeamName = teamName;
	}
	public String getCoach() {
		return Coach;
	}
	public void setCoach(String coach) {
		Coach = coach;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	
	public String toString() {
		return "TeamInfor [TeamID=" + TeamID + ", TeamName=" + TeamName + ", Coach=" + Coach + ", Quantity=" + Quantity
				+ "]";
	}
	
	public int hashCode() {
		return Objects.hash(Coach, Quantity, TeamID, TeamName);
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamInfor other = (TeamInfor) obj;
		return Objects.equals(Coach, other.Coach) && Quantity == other.Quantity && Objects.equals(TeamID, other.TeamID)
				&& Objects.equals(TeamName, other.TeamName);
	}
	
	

}
