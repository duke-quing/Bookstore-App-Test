package bookstoreapp.edu.ateneo.cie199;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by francis on 11/4/17.
 */

public class CustomListAdapter extends ArrayAdapter<Book> {
    private static int DEFAULT_LAYOUT_RESOURCE = R.layout.layout_custom_list_item;
    private static int ICON_RESOURCE_ARRAY[] = {
            R.drawable.icon_main,
            R.drawable.icon_a,
            R.drawable.icon_b,
            R.drawable.icon_c,
            R.drawable.icon_d,
            R.drawable.icon_e,
            R.drawable.icon_f,
            R.drawable.icon_g,
            R.drawable.icon_h
    };

    private Context mContext = null;

    public CustomListAdapter(@NonNull Context context, ArrayList<Book> list) {
        super(context, DEFAULT_LAYOUT_RESOURCE, list);
        mContext = context;
        return;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /* If the convertView passed is null (i.e. this is a totally new view being created)
            then it is up to us to set it. We do this using something we call an inflater. */
        if (convertView == null) {
            /* We must get the System's Layout Inflater first */
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            /* Then "inflate" our custom resource layout on the "parent" container
                (i.e. your ListView) to create the View for our list item. This one
                becomes our convertView (previously null) */
            convertView = inflater.inflate(DEFAULT_LAYOUT_RESOURCE, parent, false);

            /* We can then manipulate the contents of our list item view from here */
        }

        /* Get the Book object whose properties will be used to populate the list item */
        Book bookItem = getItem(position);

        /* Get references to the TextViews in our custom list item layout */
        TextView txvAuthor = (TextView) convertView.findViewById(R.id.txv_author);
        TextView txvTitle = (TextView) convertView.findViewById(R.id.txv_title);

        /* And populate them with the appropriate data */
        txvAuthor.setText(bookItem.getAuthor());
        txvTitle.setText(bookItem.getTitle());

        /* To set the icon, we must get the ImageView in our custom list item layout */
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.img_icon);

        /* Then we retrieve the correct resource ID drawable to use */
        int iconRscId = ICON_RESOURCE_ARRAY[bookItem.getIcon()];

        /* And set the ImageView's resource to it */
        imgIcon.setImageResource( iconRscId );

        return convertView;
    }
}
