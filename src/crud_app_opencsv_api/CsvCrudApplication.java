package crud_app_opencsv_api;

import java.util.List;
import java.util.Scanner;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;


public class CsvCrudApplication {
	
	private static final String CSV_FILE_PATH = "data.csv";
    private static final char CSV_SEPARATOR = ',';

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Record");
            System.out.println("2. View Records");
            System.out.println("3. Update Record");
            System.out.println("4. Delete Record");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addRecord();
                    break;
                case 2:
                    viewRecords();
                    break;
                case 3:
                    updateRecord();
                    break;
                case 4:
                    deleteRecord();
                    break;
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
	}
	
	private static void addRecord() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter data in the following format: Name, Age, Email");
        System.out.print("Enter data: ");
        String data = scanner.nextLine();

        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH, true), CSV_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {

            String[] record = data.split(String.valueOf(CSV_SEPARATOR));
            writer.writeNext(record);
            System.out.println("Record added successfully.");

        } catch (IOException e) {
            System.err.println("Error adding record: " + e.getMessage());
        }
    }
	
	private static void viewRecords() {
		try (CSVReader reader = new CSVReaderBuilder(new FileReader(CSV_FILE_PATH))
                .withCSVParser(new com.opencsv.CSVParserBuilder().withSeparator(CSV_SEPARATOR).build())
                .build()) {

            List<String[]> records = reader.readAll();
            System.out.println("\nRecords:");

            for (String[] fields : records) {
                System.out.println("Name: " + fields[0] + ", Age: " + fields[1] + ", Email: " + fields[2]);
            }

        } catch (IOException | CsvException e) {
            System.err.println("Error viewing records: " + e.getMessage());
        }
	}
	
	private static void updateRecord() {
		 
	}
	
	private static void deleteRecord() {
		 
	}
	 
}
