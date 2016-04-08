package dzumi.android.demo.personallibrarymanager.content_provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import dzumi.android.demo.personallibrarymanager.BuildConfig;

/**
 * Created by duynguyen on 4/1/15.
 */
public class PLMContract {

    /**
     * The authority of the mPDS provider.
     */
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider.plm";
    /**
     * The content URI for the top-level
     * mpds authority.
     * Using for raw query
     */


    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY);

    public static final Uri CONTENT_URI_RAW_QUERY =
            Uri.withAppendedPath(CONTENT_URI, "rawQuery");

    public static interface CommonColumns
            extends BaseColumns {
    }

    public static class Genres implements CommonColumns{

        public static final String GENRES_NAME = "Name";
        public static final String GENRES_ID = "GenresID";

        public static final String TABLE_NAME = "Genres";
        public static final String TABLE_NAME_ID = "Genres/#";
        public static final String CONTENT_TYPE_VAlUE = "/vnd.dzumi.android.demo.plm_genres";

        /**
         * The content URI for this table.
         */
        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(PLMContract.CONTENT_URI, TABLE_NAME);
        /**
         * The mime type of a directory of items.
         */
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + CONTENT_TYPE_VAlUE;
        /**
         * The mime type of a single item.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + CONTENT_TYPE_VAlUE;
        /**
         * A projection of all columns
         * in the items table.
         */
        public static final String[] PROJECTION_ALL =
                {_ID, GENRES_ID, GENRES_NAME};

        /**
         * The default sort order for
         * queries containing NAME fields.
         */
        public static final String SORT_ORDER_DEFAULT = GENRES_NAME + " ASC";
    }

    public static class Book implements CommonColumns {

        public static final String PAPER_BACK = "Paperback";
        public static final String PUBLISHER = "Publisher";
        public static final String NAME = "Name";
        public static final String LANGUAGE = "Language";
        public static final String ISBN_BOOK = "ISBN";
        public static final String DIMENSIONS = "Dimensions";
        public static final String AUTHOR = "Author";
        public static final String GENRES_RAW_ID = "GenresID";

        public static final String TABLE_NAME = "Books";
        public static final String TABLE_NAME_ID = "Books/#";

        public static final String[] PROJECTION_ALL = {_ID, ISBN_BOOK, NAME, PUBLISHER, AUTHOR, GENRES_RAW_ID, DIMENSIONS, PAPER_BACK, LANGUAGE};

        public static final String CONTENT_TYPE_VAlUE = "/vnd.dzumi.android.demo.plm_books";

        /**
         * The content URI for this table.
         */
        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(PLMContract.CONTENT_URI, TABLE_NAME);
        /**
         * The mime type of a directory of items.
         */
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + CONTENT_TYPE_VAlUE;
        /**
         * The mime type of a single item.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + CONTENT_TYPE_VAlUE;

        /**
         * The default sort order for
         * queries containing NAME fields.
         */
        public static final String SORT_ORDER_DEFAULT = NAME + " ASC";

    }
}
