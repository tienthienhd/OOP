package utils;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
	public static int loadMapFromFile(String filepath, ArrayList<int[]> matrix) {
//		FileReader fr;
		int tileSet = -1;
		try {
			InputStream in = Utils.class.getResourceAsStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
//			fr = new FileReader(filepath);
			Scanner scanner = new Scanner(br);

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
			scanner.close();
			return tileSet;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static int loadMonsterFromFile(String filepath, ArrayList<Point> points) {
//		FileReader fr;
		int numberOfMonster = -1;
		int typeOfMonster = -1;
		InputStream in = Utils.class.getResourceAsStream(filepath);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
//			fr = new FileReader(filepath);
		Scanner sc = new Scanner(br);
		
		typeOfMonster = sc.nextInt();
		numberOfMonster = sc.nextInt();
		for (int i = 0; i < numberOfMonster; i++) {
			int xMonster = sc.nextInt();
			int yMonster = sc.nextInt();
			Point p = new Point(xMonster, yMonster);
			points.add(p);
		}
		sc.close();
		return typeOfMonster;
	}
}
