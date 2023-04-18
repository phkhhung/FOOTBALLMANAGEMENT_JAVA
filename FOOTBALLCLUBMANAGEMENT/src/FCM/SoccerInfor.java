package FCM;

import java.util.Objects;

public class SoccerInfor {
	private String soccerID;
	private String soccerName;
	private String position;
	private String nationality;
	private String teamID;
	public SoccerInfor() {
		
	}
	public SoccerInfor(String soccerID, String soccerName, String position, String nationality, String teamID) {
		this.soccerID = soccerID;
		this.soccerName = soccerName;
		this.position = position;
		this.nationality = nationality;
		this.teamID = teamID;
	}
	public String getSoccerID() {
		return soccerID;
	}
	public void setSoccerID(String soccerID) {
		this.soccerID = soccerID;
	}
	public String getSoccerName() {
		return soccerName;
	}
	public void setSoccerName(String soccerName) {
		this.soccerName = soccerName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getTeamID() {
		return teamID;
	}
	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}
	@Override
	public String toString() {
		return "SoccerInfor [soccerID=" + soccerID + ", soccerName=" + soccerName + ", position=" + position
				+ ", nationality=" + nationality + ", teamID=" + teamID + "]";
	}
	
	public int hashCode() {
		return Objects.hash(nationality, position, soccerID, soccerName, teamID);
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SoccerInfor other = (SoccerInfor) obj;
		return Objects.equals(nationality, other.nationality) && Objects.equals(position, other.position)
				&& Objects.equals(soccerID, other.soccerID) && Objects.equals(soccerName, other.soccerName)
				&& Objects.equals(teamID, other.teamID);
	}
	
}
