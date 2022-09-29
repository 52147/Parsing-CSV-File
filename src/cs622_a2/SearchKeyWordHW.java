package cs622_a2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class SearchKeyWordHW {
	static HashMap<String, Integer> map = new HashMap<>();
	/**
	 * function to merge multiple files in one file
	 * parameter:  directory path
	 */
	public static void createFile(String path) throws IOException {

		// create a file instance for directory file
		File dir = new File(path);

		// create a PrintWriter intstance for formatted output stream
		PrintWriter pw = new PrintWriter("output.txt");

		// Get all the file names in the directory path
		String[] fileNames = dir.list();

		// Use a for loop to get the each file name
		for (String fileName : fileNames) {
			System.out.println("Reading from " + fileName);

			// create file instance from parent abstract path name for each file name
			File f = new File(dir, fileName);

			// create a BufferReader to read the text from input stream
			BufferedReader br = new BufferedReader(new FileReader(f));
			// use printIn() in PrinterWriter to print file name then terminate the line
			pw.println("Contents of file " + fileName);

			// use readLine() in BufferedReader that return the each line of string not
			// containing line-termination and store string in String line
			String line = br.readLine();
			// while loop iterate line until it is null
			while (line != null) {
				// line = line.replace("\r\n", " ").replace("\n", " "); // remove line break
				// write to the output file
				pw.println(line); // use printIn() in PrinterWriter to print line then terminate the line
				line = br.readLine(); // then use readLine() in BufferedReader to read the next line
			}
			// use flush() in PrintWriter to written out the output stream that be buffered
			pw.flush();
		}
		// use getName() in File to print the file name
		System.out.println("Reading from all files " + " in directory " + dir.getName() + " Completed");

	}
	
	/**
	 * function to search input word frequency and print frequency and project name, category, funding in the same row.
	 * @param input
	 * @throws IOException
	 */
	public static void searchWord(String input) throws IOException {

		// use File(String pathname) to create new file instance by converting given
		// pathname string into abstract path name
		File file = new File("output.txt");

		// Initialize the words array to store each line of row
		String[] words = null;
		// use FileReader(File file) create new FileReader given the file to read from
		FileReader fr = new FileReader(file);
		// BufferedReader(FileReader File) creat new BufferedReader to read text from
		// input stream
		BufferedReader br = new BufferedReader(fr);

		// Initialize String s to store the each line that read from BufferedReader
		String s;
		// Initialize the count to 0
		int count = 0;
		// while String in bufferedReader in not null
		while ((s = br.readLine()) != null) {
			// use regular expression to split the word with different column
			words = s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			// iterate word in each column
			for (String word : words) {
				// split each word with white space and stroe it in string[] wo
				String[] wo = word.split(" ");
				// iterate each string w in String[] wo
				for (String w : wo) {
					// if word equal to our input
					if (w.equals(input)) {
						count++;

						if (words.length > 1 && words.length > 23) {
							if (words[20] != "") {
								System.out.println("Project Name: " + words[20]);
							} else {
								// print the project name which is at words[23] because which means the right
								// index for project name is at index 23
								System.out.println("Project Name: " + words[23]);
							}
							System.out.println("Category: " + words[2]);
							// Initialize string str to store result that use the regular expression to
							// remove non-digit char
							String str = words[15].replaceAll("[^\\d.]", "");

//							if(str.equals("")) {
//								System.out.println("Funding goal: " + 0);
//							}

							if (Integer.parseInt(str) <= 1) {

								System.out.println("Funding goal: " + words[16]);
							} else {
								// print the funding goal which is at words[15] because which means the right
								// index for project name is at index 15
								System.out.println("Funding goal: " + words[15]);
							}
						}

					}
				}

			}

		}

		if (count != 0) {
			System.out.println("The " + input + " is present for " + count + " Times in the file");
		} else {
			System.out.println("The given word is not present in the file");
		}
		// use close() in FileReader to close and release resources associated with it.
		// Once the stream has been closed, if invoked any method in FileReader will
		// throw IOException.
		fr.close();
	}

	// method to map<input, search times> and print it out
	public static void print(String input) {

		if (!map.containsKey(input)) {
			System.out.println("Search " + input + ", 1 times.");
			map.put(input, map.getOrDefault(input, 0) + 1);
		} else {
			map.put(input, map.get(input) + 1);
			System.out.println("Search " + input + ", " + (map.get(input)) + " times.");
		}

	}

	public static void main(String[] args) throws IOException {

//		createFile("D:\\622file");
		searchWord("robot");
		searchWord("fitness");
		searchWord("wearable");

		Scanner sc = new Scanner(System.in);
		// test print method when user can input time times
		for (int i = 0; i < 10; i++) {
			String input = sc.next();
			print(input);
		}

	}

}
