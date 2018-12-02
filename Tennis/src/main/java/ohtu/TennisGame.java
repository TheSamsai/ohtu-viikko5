package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        if (m_score1==m_score2) {
            return scoresEqual(m_score1);
        } else if (m_score1>=4 || m_score2>=4) {
            int scoreDelta = m_score1 - m_score2;
            
            if (scoreDelta == 1) return "Advantage player1";
            else if (scoreDelta == -1) return "Advantage player2";
            else if (scoreDelta >= 2) return "Win for player1";
            else return "Win for player2";
        } else return scoreToString(m_score1) + "-" + scoreToString(m_score2); 
    }

    public String scoresEqual(int score) {
        if (score <= 3) return scoreToString(score) + "-All";
        else return "Deuce";
    }

    public String scoreToString(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "";
        }
    }
}
