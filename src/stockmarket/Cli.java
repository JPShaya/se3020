package stockmarket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Cli { // NOPMD by tom on 7/26/10 9:26 PM

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Cli();

	}

	private Cli() {

		Scanner scanner;
                File inFile = null;
                File outFile = null;
                String inputFile = "";

		try {
			scanner = new Scanner(new File("SystemVars.txt"));

			HashMap<String, String> settings = new HashMap<String, String>();

			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] split = line.split(",");
				settings.put(split[0], split[1]);

			}

			inputFile = settings.get("input_file");
			String inputFileDir = settings.get("input_file_dir");
			String outputFileDir = settings.get("output_file_dir");

			inFile = new File(inputFileDir, inputFile);
                        outFile = new File(outputFileDir, "status.txt");
        

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
                        System.exit(-1);
		}
		DataVerify data = new DataVerify();

                try {
                    data.csvReader(inFile);

                } catch (IOException iOException) {
                    try {
                        BufferedWriter write = new BufferedWriter(new FileWriter(outFile));
                        write.write("ERR\n");
                        write.write("IO File Error");
                        write.close();
                    } catch(Exception e) {
                        e.printStackTrace();
                        System.exit(-1);
                    }

                } catch (TradeDataException tradeDataException) {
                    try {
                        BufferedWriter write = new BufferedWriter(new FileWriter(outFile));
                        write.write("ERR\n");
                        write.write(tradeDataException.getMessage());
                        write.close();
                    } catch(Exception e) {
                        e.printStackTrace();
                        System.exit(-1);
                    }
                }

		try {
                    BufferedWriter write = new BufferedWriter(new FileWriter(outFile));
                    write.write("OK");
                    write.write(inputFile);
                    write.close();
                } catch(Exception e) {
                    e.printStackTrace();
                    System.exit(-1);
                }

	}

}
