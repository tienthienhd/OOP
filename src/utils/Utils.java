package utils;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
	public static int loadMapFromFile(String filepath, ArrayList<int[]> matrix) {
		FileReader fr;
		int tileSet = -1;
		try {
			fr = new FileReader(filepath);
			Scanner scanner = new Scanner(fr);

			tileSet = scanner.nextInt();
			int width = scanner.nextInt();
			int height = scanner.nextInt();

			for (int i = 0; i < height; i++) {
				int[] row = new int[width];
				for (int j = 0; j < width; j++) {
					row[j] = scanner.nextInt();
				}
				matrix.add(row);
			}
			return tileSet;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static int loadMonsterFromFile(String filepath, ArrayList<Point> points) {
		FileReader fr;
		int numberOfMonster = -1;
		int typeOfMonster = -1;
		try {
			fr = new FileReader(filepath);
			Scanner sc = new Scanner(fr);
			
			typeOfMonster = sc.nextInt();
			numberOfMonster = sc.nextInt();
			for (int i = 0; i < numberOfMonster; i++) {
				int xMonster = sc.nextInt();
				int yMonster = sc.nextInt();
				Point p = new Point(xMonster, yMonster);
				points.add(p);
			}
			return typeOfMonster;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
