package Game;

import java.io.*;
import java.util.*;

public class LeaderBoard {
    private HashMap<String, Integer> leaderBoard;
    private final String FILE_NAME = "leaderboard.txt";
    LeaderBoard() {
        leaderBoard = new HashMap<>();
        loadLeaderBoard();
    }

    public void addPlayer(String name, int score) {
        if (leaderBoard.containsKey(name) && leaderBoard.get(name) > score) return;
        leaderBoard.put(name, score);
    }

    public void loadLeaderBoard() {
        File file = new File(FILE_NAME);
        BufferedReader bf = null;

        try {
            bf = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bf.readLine()) != null) {
                String[] data = line.split(" ");
                leaderBoard.put(data[0], Integer.parseInt(data[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bf != null;
                bf.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveLeaderBoard() {
        // Save hashmap data to file
        File file = new File(FILE_NAME);
        BufferedWriter bf = null;

        try {
            bf = new BufferedWriter(new FileWriter(file));

            for (Map.Entry<String, Integer> entry : leaderBoard.entrySet()) {
                bf.write(entry.getKey() + " " + entry.getValue());
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bf != null;
                bf.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public String getLeaderBoard() {
        StringBuilder result = new StringBuilder();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(leaderBoard.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        int count = 0;
        for (Map.Entry<String, Integer> entry : list) {
            if (count == 10) break;
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append(" USD").append("\n");
            count++;
        }

        return result.toString();
    }
}
