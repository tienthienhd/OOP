package utils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
	public static boolean loadMapFromFile(String filepath, ArrayList<int[]> matrix) {
		FileReader fr;
		try {
			fr = new FileReader(filepath);
			Scanner scanner = new Scanner(fr);
			
			int width = scanner.nextInt();
			int height = scanner.nextInt();
			
			for(int i = 0; i < height; i++) {
				int[] row = new int [width];
				for(int j = 0; j < width; j++) {
					row[j] = scanner.nextInt();
				}
				matrix.add(row);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
