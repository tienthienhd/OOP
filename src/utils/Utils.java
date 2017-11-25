package utils;

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
			
			for(int i = 0; i < height; i++) {
				int[] row = new int [width];
				for(int j = 0; j < width; j++) {
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
}
