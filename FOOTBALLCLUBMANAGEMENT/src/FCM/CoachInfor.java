package FCM;

import java.util.Objects;

public class CoachInfor {
	private String CoachID;
	private String CoachName;
	private String Nationality;
	private String TeamID;
	public CoachInfor(String coachID, String coachName, String nationality, String teamid) {
		
		CoachID = coachID;
		CoachName = coachName;
		Nationality = nationality;
		TeamID = teamid;
	}
	public CoachInfor() {
		
	}
	public String getCoachID() {
		return CoachID;
	}
	public void setCoachID(String coachID) {
		CoachID = coachID;
	}
	public String getCoachName() {
		return CoachName;
	}
	public void setCoachName(String coachName) {
		CoachName = coachName;
	}
	@Override
	public int hashCode() {
		return Objects.hash(CoachID, CoachName, Nationality, TeamID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoachInfor other = (CoachInfor) obj;
		return Objects.equals(CoachID, other.CoachID) && Objects.equals(CoachName, other.CoachName)
				&& Objects.equals(Nationality, other.Nationality) && Objects.equals(TeamID, other.TeamID);
	}
	public String getNationality() {
		return Nationality;
	}
	public void setNationality(String nationality) {
		Nationality = nationality;
	}
	public String getTeamID() {
		return TeamID;
	}
	public void setTeamID(String teamid) {
		TeamID = teamid;
	}
	@Override
	public String toString() {
		return "CoachInfor [CoachID=" + CoachID + ", CoachName=" + CoachName + ", Nationality=" + Nationality
				+ ", TeamID=" + TeamID + "]";
	}
	
}
