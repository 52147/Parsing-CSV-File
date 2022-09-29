# Parsing-CSV-File

1. Merge 3 cvs file
2. remove line separator(line break)
use regular expression to split each column
3. found the row that contains these 3 words[robot, fitness, wearable] and print the value of project name, category, funding in that row.
4. create a print function which can calculate the number of search times of each word.

## Input Stream & Output Stream
- Because we need to merge the 3 files in one file and search the keyword in the merged file, so we need to fully understand how `java.io` package work. 

### `void createFile(String pathname) throws IOException` 
- function to merge multiple files in one file
- parameter: directory path ex: D:\\622file
- 1. create a file instance for directory file
- 2. create a PrintWriter intstance for formatted output stream
- 3. Get all the file names in the directory path
- 4. Use a for loop to get the each file name
     - `File(File parent, String child)` create file instance from parent abstract path name for each file name 
     - create a BufferReader to read the text from input stream
     - use printIn() in PrinterWriter to print file name then terminate the line
     - use readLine() in BufferedReader that return the each line of string not containing line-termination and store string in String line
        - while loop iterate line until it is null
              - use printIn() in PrinterWriter to print line then terminate the line
              - then use readLine() in BufferedReader to read the next line
     - use flush() in PrintWriter to written out the output stream that be buffered
 - 5. use getName() in File to print the file name
### `void searchWord(String input) throws IOException`
- function to search input word frequency and print frequency and project name, category, funding in the same row.
- parameter: input word
- 1. use File(String pathname) to create new file instance by converting given pathname string into abstract path name
- 2. Initialize the words array to store each line of row
- 3. use FileReader(File file) create new FileReader given the file to read from
- 4. BufferedReader(FileReader File) creat new BufferedReader to read text from input stream
- 5. Initialize String s to store the each line that read from BufferedReader
- 6. Initialize the count to 0
- 6. while String in bufferedReader in not null 
     - use regular expression to split the word with different column
     - iterate word in each column
       - split each word with white space and stroe it in string[] wo
       - iterate each string w in String[] wo
         - if word equal to our input
           - count++
           - if words length > 1 and >23
             - print the project name which is at words[20]
           - else
             - print the project name which is at words[23] because which means the right index for project name is at index 23
           - print the Category which is at words[2]
           - Initialize string str to store result that use the regular expression to remove non-digit char
           - if((convert str to integer) <= 1)
             - print the funding goal which is at words[16]
           - else
             - print the funding goal which is at words[15] because which means the right index for project name is at index 15
- 7. Check the count: 
     - if the count not 0
       - print the count value
     - else
       - print "word is not present"  
- 8. use close() in FileReader to close and release resources associated with it. Once the stream has been closed, if invoked any method in FileReader will throw IOException.
### `void print(String input)` 
- method to map<input, search times> and print it out
-  if map not contains key input
   - print the input with search 1 time
   - map the input with 1
- else
   - update the map key input with +1 value
   - print the input search time   
## Regular Expression
- Regular Expression is an amazing thing, it can make your life easier or more difficult.
- Use BufferReader to read the input stream of the file, and use `readLine()` can read the text and not include line-termination `\n\r`
- So we do not need to use this Regex to remove the line breaker
``` java
line = line.replace("\r\n", " ").replace("\n", " ");
```
### Remove all non-digits character
``` java
String str = words[15].replaceAll("[^\\d.]", "");
// Explanation:
// [] means one of characters in bracket
// [^] means not one of characters in bracket
// \ is escape character to seperate \d
// \d means digits
```
### split when it matches commas that are followed by an even number of quotes (or no quotes).
- when file contain: book, {a: 123, b: 23445, c: 4555}, book name
- split the col use these Regex
- Output:
```
book
{a: 123, b: 23445, c: 4555}
book name
```
- this works because comma's inside quotes (i.e. the ones we don't want to match/split on) should have an odd number of quotes between them and the end of the line.
``` java
words = s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
// Explanation:
// (?=) via zero-width positive lookahead
// * zero or more times
// $ ignore line terminators, in MULTILINE mode $ matches just before a line terminator or the end of the input sequence.
// ^ ignore line terminators, matches at the beginning of input and after any line terminator except at the end of input. 
```
## Result Output
```
Project Name: "Reynard City Triple Threat Kickstarter"
Category: "{""id"":253,""name"":""Webcomics"",""analytics_name"":""Webcomics"",""slug"":""comics/webcomics"",""position"":5,""parent_id"":3,""parent_name"":""Comics"",""color"":16776056,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/comics/webcomics""}}}"
Funding goal: "600"
Project Name: "Reynard City Triple Threat Kickstarter"
Category: "{""id"":253,""name"":""Webcomics"",""analytics_name"":""Webcomics"",""slug"":""comics/webcomics"",""position"":5,""parent_id"":3,""parent_name"":""Comics"",""color"":16776056,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/comics/webcomics""}}}"
Funding goal: "600"
Project Name: "ROBOT WESTERN: The Animated Comic Book Series"
Category: "{""id"":253,""name"":""Webcomics"",""analytics_name"":""Webcomics"",""slug"":""comics/webcomics"",""position"":5,""parent_id"":3,""parent_name"":""Comics"",""color"":16776056,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/comics/webcomics""}}}"
Funding goal: "4000"
Project Name: "NINJASAUR"
Category: "{""id"":253,""name"":""Webcomics"",""analytics_name"":""Webcomics"",""slug"":""comics/webcomics"",""position"":5,""parent_id"":3,""parent_name"":""Comics"",""color"":16776056,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/comics/webcomics""}}}"
Funding goal: "6500"
The robot is present for 4 Times in the file
```

```
Project Name: "Wearbuds? | Wireless Earbuds Charged Right on Your Wrist"
Category: "{""id"":339,""name"":""Sound"",""analytics_name"":""Sound"",""slug"":""technology/sound"",""position"":12,""parent_id"":16,""parent_name"":""Technology"",""color"":6526716,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/technology/sound""}}}"
Funding goal: "50000"
Project Name: "Wearbuds? | Wireless Earbuds Charged Right on Your Wrist"
Category: "{""id"":339,""name"":""Sound"",""analytics_name"":""Sound"",""slug"":""technology/sound"",""position"":12,""parent_id"":16,""parent_name"":""Technology"",""color"":6526716,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/technology/sound""}}}"
Funding goal: "50000"
Project Name: "Wearbuds? | Wireless Earbuds Charged Right on Your Wrist"
Category: "{""id"":339,""name"":""Sound"",""analytics_name"":""Sound"",""slug"":""technology/sound"",""position"":12,""parent_id"":16,""parent_name"":""Technology"",""color"":6526716,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/technology/sound""}}}"
Funding goal: "50000"
Project Name: "Wearbuds? | Wireless Earbuds Charged Right on Your Wrist"
Category: "{""id"":339,""name"":""Sound"",""analytics_name"":""Sound"",""slug"":""technology/sound"",""position"":12,""parent_id"":16,""parent_name"":""Technology"",""color"":6526716,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/technology/sound""}}}"
Funding goal: "50000"
The fitness is present for 4 Times in the file
```

```
Project Name: "Hooke Lav: A Sleek Wireless Mic With Pro-Grade Sound"
Category: "{""id"":339,""name"":""Sound"",""analytics_name"":""Sound"",""slug"":""technology/sound"",""position"":12,""parent_id"":16,""parent_name"":""Technology"",""color"":6526716,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/technology/sound""}}}"
Funding goal: "10000"
Project Name: "Hooke Lav: A Sleek Wireless Mic With Pro-Grade Sound"
Category: "{""id"":339,""name"":""Sound"",""analytics_name"":""Sound"",""slug"":""technology/sound"",""position"":12,""parent_id"":16,""parent_name"":""Technology"",""color"":6526716,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/technology/sound""}}}"
Funding goal: "10000"
Project Name: "GripBeats: Turn Your Hands Into A Musical Instrument!"
Category: "{""id"":339,""name"":""Sound"",""analytics_name"":""Sound"",""slug"":""technology/sound"",""position"":12,""parent_id"":16,""parent_name"":""Technology"",""color"":6526716,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/technology/sound""}}}"
Funding goal: "10000"
Project Name: "GripBeats: Turn Your Hands Into A Musical Instrument!"
Category: "{""id"":339,""name"":""Sound"",""analytics_name"":""Sound"",""slug"":""technology/sound"",""position"":12,""parent_id"":16,""parent_name"":""Technology"",""color"":6526716,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/technology/sound""}}}"
Funding goal: "10000"
Project Name: "Hooke Lav: A Sleek Wireless Mic With Pro-Grade Sound"
Category: "{""id"":339,""name"":""Sound"",""analytics_name"":""Sound"",""slug"":""technology/sound"",""position"":12,""parent_id"":16,""parent_name"":""Technology"",""color"":6526716,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/technology/sound""}}}"
Funding goal: "10000"
Project Name: "Hooke Lav: A Sleek Wireless Mic With Pro-Grade Sound"
Category: "{""id"":339,""name"":""Sound"",""analytics_name"":""Sound"",""slug"":""technology/sound"",""position"":12,""parent_id"":16,""parent_name"":""Technology"",""color"":6526716,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/technology/sound""}}}"
Funding goal: "10000"
Project Name: "GripBeats: Turn Your Hands Into A Musical Instrument!"
Category: "{""id"":339,""name"":""Sound"",""analytics_name"":""Sound"",""slug"":""technology/sound"",""position"":12,""parent_id"":16,""parent_name"":""Technology"",""color"":6526716,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/technology/sound""}}}"
Funding goal: "10000"
Project Name: "GripBeats: Turn Your Hands Into A Musical Instrument!"
Category: "{""id"":339,""name"":""Sound"",""analytics_name"":""Sound"",""slug"":""technology/sound"",""position"":12,""parent_id"":16,""parent_name"":""Technology"",""color"":6526716,""urls"":{""web"":{""discover"":""http://www.kickstarter.com/discover/categories/technology/sound""}}}"
Funding goal: "10000"
The wearable is present for 8 Times in the file
```
```
robot
Search robot, 1 times.
robot
Search robot, 2 times.
robot
Search robot, 3 times.
robot
Search robot, 4 times.
fitness
Search fitness, 1 times.
fitness
Search fitness, 2 times.
fitness
Search fitness, 3 times.
wearable
Search wearable, 1 times.
wearable
Search wearable, 2 times.
wearable
Search wearable, 3 times.
```
## Reference
- https://www.geeksforgeeks.org/java-program-to-merge-contents-of-all-the-files-in-a-directory/
- https://www.candidjava.com/tutorial/program-to-search-word-in-a-file/
- https://stackoverflow.com/questions/2163045/how-to-remove-line-breaks-from-a-file-in-java
- https://stackoverflow.com/questions/33053815/what-is-d-how-replaceall-d-is-working
- https://stackoverflow.com/questions/15738918/splitting-a-csv-file-with-quotes-as-text-delimiter-using-string-split/15905916#15905916
