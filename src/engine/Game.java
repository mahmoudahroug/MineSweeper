package engine;

import java.awt.Point;
import java.util.Arrays;
import java.util.Random;
public class Game {
    public static int mapSize = 15;
    public static int numMines = 25;
    public static int numVisibles = 0;
    public static int flagCount = numMines;
    public static Cell[][] map = new Cell[mapSize][mapSize];
    public static Point[] mineLocations = new Point[numMines];

    public static void initialize(){
        for(int i = 0; i<mapSize; i++)
            for(int j = 0; j<mapSize; j++)
                map[i][j] = new Cell();
    }
    public static void startGame(Point a){
        spawnMines(a);
        setUpAdjacents();
        reveal(a);
    }


    public static boolean validLoc(Point a){
        return a.x >= 0 && a.x < mapSize && a.y >= 0 && a.y < mapSize;
    }
    public static boolean validLoc(int x, int y){
        return x >= 0 && x < mapSize && y >= 0 && y < mapSize;
    }
    public static boolean validSpawn(Point a, Point exclude){
        // check within borders and no mine placed and isn't point clicked on
        return validLoc(a) && !map[a.x][a.y].isMine() && a.distance(exclude) > 2;
    }
    public static void spawnMines(Point exclude){
        Random r = new Random();
        Random r1 = new Random();
        for (int i = 0; i<numMines; i++) {
            int x = r.nextInt(Game.mapSize);
            int y = r1.nextInt(Game.mapSize);
            Point p = new Point(x, y);
            while (!validSpawn(p, exclude)) {
                x = r1.nextInt(Game.mapSize);
                y = r.nextInt(Game.mapSize);
                p.setLocation(x, y);
            }
            map[x][y].setMine(true);
            mineLocations[i] = p;
        }

    }
    public static void setUpAdjacents(){
        for(int i = 0; i<numMines; i++){
            for(int j = -1; j<=1; j++) {
                for (int k = -1; k <= 1; k++) {
                    int x = mineLocations[i].x + j;
                    int y = mineLocations[i].y + k;
                    if(validLoc(x, y))
                        map[x][y].setAdjacents(map[x][y].getAdjacents()+1);
                }
            }
        }
    }
    public static void reveal(Point a){
        Cell current = map[a.x][a.y];
        if(current.isVisible() || current.isMine())
            return;
        current.setVisible(true);
        current.setFlagged(false);
        numVisibles++;
        if(current.getAdjacents() > 0)
            return;
        for(int i = -1; i<=1; i++)
            for(int j = -1; j<=1;j++)
                if(validLoc(a.x+i, a.y+j))
                    reveal(new Point(a.x+i, a.y+j));
    }
    public static boolean checkWin(){
        return numVisibles + numMines == mapSize*mapSize;
    }



}
