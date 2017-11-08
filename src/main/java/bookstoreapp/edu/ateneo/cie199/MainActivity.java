package bookstoreapp.edu.ateneo.cie199;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<Book> mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Get a reference to our Application object and call the
            loadBookData() function to read previously stored data
            from the file
         */
        BookstoreApp app = (BookstoreApp) getApplication();
        app.loadBookData();

        /* To check whether we've successfully loaded our stored data, we
            set up our adapter and give it the current list of books from our
            Application object
          */
        ArrayList<Book> bookList = app.getBookList();

        /* TODO You can switch between the normal ArrayAdapter here OR use the CustomListAdapter
         *      just comment out one of the lines below (and uncomment the other) */
//        mAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, bookList);
        mAdapter = new CustomListAdapter(this, bookList);

        /* Next we connect the adapter to our ListView */
        ListView listBooks = (ListView) findViewById(R.id.list_books);
        listBooks.setAdapter(mAdapter);

        /* And then notify the adapter that the dataset has changed for good measure */
        mAdapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent addBookIntent = new Intent(MainActivity.this,
                                                   AddBookActivity.class);
                startActivity(addBookIntent);

                return;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
