package stockmarket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import com.csvreader.CsvReader;

public class Cli { // NOPMD by tom on 7/26/10 9:26 PM

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Cli();

	}

	private Cli() {

		Scanner scanner;
		try {
			scanner = new Scanner(new File("SystemVars.txt"));

			HashMap<String, String> settings = new HashMap<String, String>();

			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] split = line.split(",");
				settings.put(split[0], split[1]);

			}

			String inputFile = settings.get("input_file");
			String inputFileDir = settings.get("input_file_dir");
			String outputFileDir = settings.get("output_file_dir");

			//System.out.println(inputFile + inputFileDir + outputFileDir);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// verfiy

		// make output

	}

}
