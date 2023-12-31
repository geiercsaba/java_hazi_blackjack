package Game;

import java.io.*;
import java.util.*;

/**
 * LeaderBoard class
 * This class is used to store the previous players top score
 */
public class LeaderBoard {
    private final HashMap<String, Integer> topList;
    private final static String FILE_NAME = "leaderboard.txt";

    /**
     * Constructor
     * Initialize the topList hashmap and load the data from file
     */
    public LeaderBoard() {
        topList = new HashMap<>();
        loadLeaderBoard();
    }

    /**
     * Add player to the topList
     * @param name player name
     * @param score player score
     */
    public void addPlayer(String name, int score) {
        if (topList.containsKey(name) && topList.get(name) > score) return;
        topList.put(name, score);
    }

    /**
     * Load data from file to hashmap
     */
    public void loadLeaderBoard() {
        File file = new File(FILE_NAME);

        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] data = line.split(" ");
                topList.put(data[0], Integer.parseInt(data[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save hashmap data to file
     */
    public void saveLeaderBoard() {
        // Save hashmap data to file
        File file = new File(FILE_NAME);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, Integer> entry : topList.entrySet()) {
                bf.write(entry.getKey() + " " + entry.getValue());
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the top 10 players
     * @return top 10 players as a string
     */
    public String getLeaderBoard() {
        StringBuilder result = new StringBuilder();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(topList.entrySet());
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

    /**
     * Clear the topList(used for testing) not clearing the file
     */
    public void clearLeaderBoard() {
        topList.clear();
    }
}
