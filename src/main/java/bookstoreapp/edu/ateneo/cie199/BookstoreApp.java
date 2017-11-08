package bookstoreapp.edu.ateneo.cie199;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by francis on 10/26/17.
 */

public class BookstoreApp extends Application {
    /* TODO Don't forget to declare this Application in your Manifest! */
    private String mDefaultCsvFileName = "bookstore_data.csv";
    private ArrayList<Book> mBookList = new ArrayList<>();

    public ArrayList<Book> getBookList() {
        return mBookList;
    }

    /*************************************/
    /** BookstoreApp File I/O Functions **/
    /*************************************/
    public boolean saveBookData(String title,
                             String author,
                             String icon) {
        File targetDirectory = getFilesDir();

        if (targetDirectory.exists() == false) {
            Log.w("Warning", "Directory does not exist. Creating the directory...");
            targetDirectory.mkdirs();
        }

        File targetFile = new File(targetDirectory,
                                    mDefaultCsvFileName);
        try {
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(targetFile, true);

            String dataStr = encodeDataToCSV(title, author, icon);
            fos.write(dataStr.getBytes());
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void loadBookData() {
        File targetDirectory = getFilesDir();

        if (targetDirectory.exists() == false) {
            Log.e("Error", "Directory does not exist");
            return;
        }

        File targetFile = new File(targetDirectory,
                                    mDefaultCsvFileName);

        if (targetFile.exists() == false) {
            Log.e("Error", "File does not exist");
            return;
        }

        int nBytesRead = 0;
        byte buf[] = new byte[32];
        String contentStr = "";

        try {
            InputStream is = new FileInputStream(targetFile);

            while (is.available() > 0) {
                nBytesRead = is.read(buf, 0, 32);
                contentStr += new String(buf, 0, nBytesRead);
            }

            is.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Clear the contents of our old array list */
        mBookList.clear();

        /* Parse the content string from CSV */
        ArrayList<Book> parsedBookList = decodeDataFromCSV(contentStr);

        /* Finally, we add everything to our actual array list of books */
        mBookList.addAll( parsedBookList );

        return;
    }

    /****************************************************/
    /** BookstoreApp CSV Encoding / Decoding Functions **/
    /****************************************************/
    public String encodeDataToCSV(String title,
                                  String author,
                                  String icon) {
        String csvStr = "";

        /*
            Our CSV format for this bookstore app file:

            <title>, <author>, <icon>
            <title>, <author>, <icon>
            <title>, <author>, <icon>
            <title>, <author>, <icon>
            ...
         */

        csvStr += title + ", ";
        csvStr += author + ", ";
        csvStr += icon;
        csvStr += "\n";

        return csvStr;
    }

    public ArrayList<Book> decodeDataFromCSV(String csvStr) {
        ArrayList<Book> parsedBookList = new ArrayList<>();

        /*
            RECALL Our CSV format for this bookstore app file:

            <title>, <author>, <icon>
            <title>, <author>, <icon>
            <title>, <author>, <icon>
            <title>, <author>, <icon>
            ...
         */

        /* First we have to split the entire CSV string (which contains
            the whole file) and treat each line as a separate Book record.

            This is why we set our split criteria to "\n" or the newline
            character. The end result is a string array containing each
            line in the file.
         */
        String bookRecords[] = csvStr.split("\n");

        /*
            Next, we look at each individual line / book record and then
            split it by commas (,) since we know it is a CSV file.
         */
        for (String rec : bookRecords) {
            String bookInfo[] = rec.split(",");

            /*
                We know that the first index is the title of the book,
                the second is the author of the book, and so on.
             */

            // Note: Use trim() to remove extra spaces from Strings
            String title = bookInfo[0].trim();
            String author = bookInfo[1].trim();
            String icon = bookInfo[2].trim();

            /* Finally add an element in our ArrayList for the parsed record */
            parsedBookList.add( new Book(title, author, Integer.parseInt(icon)) );
        }

        return parsedBookList;
    }
}
