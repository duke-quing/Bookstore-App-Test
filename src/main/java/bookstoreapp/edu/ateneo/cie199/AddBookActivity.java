package bookstoreapp.edu.ateneo.cie199;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        Button btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /* Get references to the GUI elements we need data from */
                        EditText edtTitle = (EditText) findViewById(R.id.edt_title);
                        EditText edtAuthor = (EditText) findViewById(R.id.edt_author);
                        Spinner spnIcon = (Spinner) findViewById(R.id.spn_icon);

                        /* Extract data from those GUI elements */
                        String title = edtTitle.getText().toString();
                        String author = edtAuthor.getText().toString();
                        String iconStr = Integer.toString(spnIcon.getSelectedItemPosition() + 1);
                        /* NOTE:
                            You can get the current position of the spinner using the
                            getSelectedItemPosition() function.
                         */

                        /* Get a reference to our Application object and then use it to
                           call the saveBookData() function which writes our new data to
                           the file. */
                        BookstoreApp app = (BookstoreApp) getApplication();
                        boolean result = app.saveBookData(title, author, iconStr);

                        /* If we failed to write to our target file, then the function
                            would have returned a 'false' value to us */
                        if (result == false) {
                            /* We use this to print out a toast indicating our failures */
                            Toast.makeText(AddBookActivity.this,
                                            "Failed to add book!",
                                            Toast.LENGTH_LONG).show();
                            return;
                        }

                        Toast.makeText(AddBookActivity.this,
                                        ("Book Added: " + title + " | " + author + " | " + iconStr),
                                        Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
        );

        return;
    }
}
