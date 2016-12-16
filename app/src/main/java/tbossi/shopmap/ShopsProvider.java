package tbossi.shopmap;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class ShopsProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI("tbossi.shopmap.provider", "shop", 1);
        sUriMatcher.addURI("tbossi.shopmap.provider", "shop/#", 2);
    }
    String[] columns = new String[] { "_id", "name", "lat", "lon" };

    private List<Shop> shopList;

    public ShopsProvider() {
        shopList = new ArrayList<Shop>();

        shopList.add(new Shop("Shop1", 10, 30));
        shopList.add(new Shop("Shop2", 12, 32));
        shopList.add(new Shop("Shop3", 13, 27));
        shopList.add(new Shop("Shop4", 15, 18));
        shopList.add(new Shop("Shop5", 30, 10));
        shopList.add(new Shop("Shop6", 28, 30));
        shopList.add(new Shop("Shop7", 20, 30));
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // call the code to actually do the query

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        int counter = 1;
        for (Shop s: shopList)
            matrixCursor.addRow(new Object[] { counter, s.getName(), s.getLat(), s.getLon() });

        return matrixCursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
