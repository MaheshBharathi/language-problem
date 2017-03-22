package au.com.lf.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by mahesh on 22/03/17.
 */
public class LanguageFinderServiceImpl implements LanguageFinderService {

    private static final String  WORD_NOT_FOUND = "Sorry! The System could not find language of the word you entered";
    private static final String  FILE_NOT_FOUND = "Something went wrong at server, Please try again later";


    /**
     * Returns the language of a word.
     * If the input word is not found in any of language files, returns a friendly message.
     * First match wins, ignores case
     * Language is derived from file name.
     * All exceptions are captured and returns a friendly message
     * @param input - user input
     * @param fileLocation - file system location to scan files
     * @return
     */

    @Override
    public String findLanguage(String input, String fileLocation) {
        File folder = new File(fileLocation);
        Scanner scanner = null;

        if(folder.listFiles() == null) {
            return FILE_NOT_FOUND;
        }

        for(File file : folder.listFiles()) {
            String fileName = file.getName();
            String language = fileName.substring(0, fileName.indexOf("."));

            try {
                Pattern pattern = Pattern.compile("[.,:;\\s]");
                scanner = new Scanner(file).useDelimiter(pattern);

                while (scanner.hasNextLine()) {

                    if(!scanner.hasNext()) {
                        break;
                    } else {
                        String nextWord = scanner.next();
                        if(input.equalsIgnoreCase(nextWord)) {
                            return language;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return FILE_NOT_FOUND;
            } finally {
                if (scanner != null) {
                    scanner.close();
                }
            }
        }

        return WORD_NOT_FOUND;
    }
}
