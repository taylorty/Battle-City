/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 12, 2016
 * Time: 1:39:29 AM
 *
 * Project: csci205FinalProject
 * Package: GameMain
 * File: Map
 * Description: A class for map in the game
 *
 * ****************************************
 */
package GameMain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class for map in the game
 *
 * @author Tongyu
 */
public class Map {

    /**
     * Hard coded the size of the board
     */
    public static final int BOARD_WIDTH = 16 * 33;
    public static final int BOARD_HEIGHT = 16 * 28;
    private static final int ROW_SHIFT = 1;
    private static final int COL_SHIFT = 2;
    private static final int BASE_POS = 14;

    /**
     * Return the map in 2D array
     *
     * @param stage
     * @return 2D array that represents the map
     */
    public static int[][] getMap(int stage) {
        return createNewStageMap(stage);
    }

    /**
     * Hard-coded and initialize 2D array as map
     */
    public static final int[][] level0 = {
        {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
        {6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 2, 2, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 2, 2, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6},
        {6, 6, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 6, 6, 6, 6},
        {6, 6, 2, 2, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 2, 2, 6, 6, 6, 6},
        {6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6},
        {6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6},
        {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}};

    /**
     * Read map from files
     *
     * @param fileName
     * @return an array list that represents a map
     */
    public static ArrayList<ArrayList<Integer>>
            readFromFile(String fileName) {
        ArrayList<ArrayList<Integer>> tempMap = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (currentLine.isEmpty()) {
                } else {
                    ArrayList<Integer> row = new ArrayList<>();
                    String[] values = currentLine.trim().split("");
                    for (String string : values) {
                        if (!string.isEmpty()) {
                            switch (string) {
                                case "#":
                                    row.add(1);
                                    break;
                                case "@":
                                    row.add(2);
                                    break;
                                case "%":
                                    row.add(5);
                                    break;
                                case "~":
                                    row.add(4);
                                    break;
                                default:
                                    row.add(0);
                                    break;
                            }
                        }
                    }
                    tempMap.add(row);
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return tempMap;
    }

    /**
     * Convert arrayList to array for further operation. This method is based on
     * codes on StackOverflow
     *
     * @see
     * <a href="http://stackoverflow.com/questions/10043209/convert-arraylist-into-2d-array-containing-varying-lengths-of-arrays/34744176">
     * http://stackoverflow.com/questions/10043209/convert-arraylist-into-2d-array-containing-varying-lengths-of-arrays/34744176</a>
     * @param arrayList
     * @return an array converted from arrayList
     */
    public static int[][] arrayListToArray(
            ArrayList<ArrayList<Integer>> arrayList) {
        int[][] intArray = arrayList.stream().map(u -> u.stream().mapToInt(
                i -> i).toArray()).toArray(int[][]::new);
        return intArray;
    }

    /**
     * Create map of the new stage given the stage number
     *
     * @param stage current stage number
     * @return a 2D array that represents the map of the given stage
     */
    public static int[][] createNewStageMap(int stage) {
        int[][] newLevel = level0;
        ArrayList<ArrayList<Integer>> levelReadFromFile = readFromFile(
                "stages/" + String.valueOf(stage));
        int[][] array = arrayListToArray(levelReadFromFile);
        for (int i = ROW_SHIFT; i < array.length + ROW_SHIFT; i++) {
            for (int j = COL_SHIFT; j < array[0].length + COL_SHIFT; j++) {
                newLevel[i][j] = array[i - ROW_SHIFT][j - COL_SHIFT];
            }
        }
        newLevel[array.length - 1][BASE_POS] = BlockType.BASE.getValue();
        return newLevel;
    }
}
