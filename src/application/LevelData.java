package application;

public class LevelData {
	private int[][] topData;
	private int[][] botData;
	public LevelData(int[][] topData, int[][] botData) {
		this.topData = topData;
		this.botData = botData;
	}
	public int[][] getTopData() {
		return topData;
	}
	public void setTopData(int[][] topData) {
		this.topData = topData;
	}
	public int[][] getBotData() {
		return botData;
	}
	public void setBotData(int[][] botData) {
		this.botData = botData;
	}

}
