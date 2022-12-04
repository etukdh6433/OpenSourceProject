package function;

public class PlayConfig {
	
	db.Database data = new db.Database();
	
	private int playerNum;
	private int pieceNum;

	public PlayConfig() {
		playerNum = data.loginUserShow().size();
		pieceNum = 2;
	}
	public int getPlayerNum() {
		return playerNum;
	}
//	public void setPlayerNum() {
//		this.playerNum = data.loginUserShow().size();
//	}
	public int getPieceNum() {
		return pieceNum;
	}
	public void setPieceNum(int pieceNum) {
		this.pieceNum = pieceNum;
	}
	
}
