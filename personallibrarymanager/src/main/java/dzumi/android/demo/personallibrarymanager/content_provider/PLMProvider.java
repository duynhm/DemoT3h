package dzumi.android.demo.personallibrarymanager.content_provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by duynguyen on 4/1/15.
 */
public class PLMProvider extends ContentProvider {
    private static final String TAG = "database";
    // helper constants for use with the UriMatcher
    private static final int GENRES_LIST = 1;
    private static final int GENRES_ID = 2;


    private static final int BOOK_LIST = 5;
    private static final int BOOK_ID = 6;


    private static final int ALL = 100;
    private static final int GoToFinish = 101;
    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(PLMContract.AUTHORITY, "rawQuery", ALL);

        URI_MATCHER.addURI(PLMContract.AUTHORITY, PLMContract.Genres.TABLE_NAME, GENRES_LIST);
        URI_MATCHER.addURI(PLMContract.AUTHORITY, PLMContract.Genres.TABLE_NAME_ID, GENRES_ID);

        URI_MATCHER.addURI(PLMContract.AUTHORITY, PLMContract.Book.TABLE_NAME, BOOK_LIST);
        URI_MATCHER.addURI(PLMContract.AUTHORITY, PLMContract.Book.TABLE_NAME_ID, BOOK_ID);

    }

    DbContext mDbContext;

    //<editor-fold desc="main method">
    @Override
    public boolean onCreate() {
        Log.i(TAG, "oncreate provider");
        mDbContext = new DbContext(getContext());
        return true;

    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mDbContext.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        boolean useAuthorityUri = false;

        switch (URI_MATCHER.match(uri)) {
            case GENRES_LIST:
                builder.setTables(PLMContract.Genres.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = PLMContract.Genres.SORT_ORDER_DEFAULT;
                }
                break;
            case GENRES_ID:
                builder.setTables(PLMContract.Genres.TABLE_NAME);
                // limit query to one row at most:
                builder.appendWhere(PLMContract.Genres._ID + " = "
                        + uri.getLastPathSegment());
                break;

            case BOOK_LIST:
                builder.setTables(PLMContract.Book.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = PLMContract.Book.SORT_ORDER_DEFAULT;
                }
                break;
            case BOOK_ID:
                builder.setTables(PLMContract.Book.TABLE_NAME);
                // limit query to one row at most:
                builder.appendWhere(PLMContract.Book._ID + " = "
                        + uri.getLastPathSegment());
                break;

            case ALL:
                return rawQuery(selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        Cursor cursor = builder.query(db, projection, selection, selectionArgs,
                null, null, sortOrder);

        // if we want to be notified of any changes:
        if (useAuthorityUri) {
            cursor.setNotificationUri(getContext().getContentResolver(), PLMContract.CONTENT_URI);
        } else {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case GENRES_LIST:
                return PLMContract.Genres.CONTENT_TYPE;
            case GENRES_ID:
                return PLMContract.Genres.CONTENT_ITEM_TYPE;

            case BOOK_LIST:
                return PLMContract.Book.CONTENT_TYPE;
            case BOOK_ID:
                return PLMContract.Book.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

    }

    //<editor-fold desc="private functions">
    protected Uri getUriForId(long id, Uri uri) {
        if (id > 0) {
            Uri itemUri = ContentUris.withAppendedId(uri, id);
            if (!isInBatchMode()) {
                // notify all listeners of changes and return itemUri:
                getContext().
                        getContentResolver().
                        notifyChange(itemUri, null);
            }
            return itemUri;
        }
        // s.th. went wrong:
//        throw new SQLException("Problem while inserting into uri: " + uri);
        return null;
    }

    protected final ThreadLocal<Boolean> mIsInBatchMode = new ThreadLocal<>();

    protected boolean isInBatchMode() {
        return mIsInBatchMode.get() != null && mIsInBatchMode.get();
    }
    //</editor-fold>

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mDbContext.getWritableDatabase();
        long id;

        String table = "";
        switch (URI_MATCHER.match(uri)) {

            case GENRES_LIST:
                table = PLMContract.Genres.TABLE_NAME;
                break;
            case BOOK_LIST:
                table = PLMContract.Book.TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException(
                        "Unsupported URI for insertion: " + uri);
        }

        id = db.insert(table, null, values);
        return getUriForId(id, uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDbContext.getWritableDatabase();
        int delCount = 0;
        String idStr;
        String where = null;
        String table = null;

        //case delete 1 item
        int goTo = URI_MATCHER.match(uri);
        boolean isFinish = false;
        while (!isFinish) {
            switch (goTo) {
                case GoToFinish:
                    if (!TextUtils.isEmpty(selection)) {
                        where += " AND " + selection;
                    }
                    delCount = db.delete(table, where, selectionArgs);
                    isFinish = true;
                    break;

                case GENRES_ID:
                    idStr = uri.getLastPathSegment();
                    where = PLMContract.Genres._ID + " = " + idStr;
                    table = PLMContract.Genres.TABLE_NAME;
                    goTo = GoToFinish;
                    continue;

                case BOOK_ID:
                    idStr = uri.getLastPathSegment();
                    where = PLMContract.Book._ID + " = " + idStr;
                    table = PLMContract.Book.TABLE_NAME;
                    goTo = GoToFinish;
                    continue;

                default:
                    isFinish = true;
                    break;
            }
        }

        switch (URI_MATCHER.match(uri)) {
            case GENRES_LIST:
                delCount = db.delete(PLMContract.Genres.TABLE_NAME, selection, selectionArgs);
                break;
            case BOOK_LIST:
                delCount = db.delete(PLMContract.Book.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                // no support for deleting photos or entities -
                // photos are deleted by a trigger when the item is deleted
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        // notify all listeners of changes:
        try {
            if (delCount > 0 && !isInBatchMode()) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return delCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDbContext.getWritableDatabase();
        int updateCount = 0;

        //case delete 1 item
        String idStr;
        String where = "";
        String table = "";
        int goTo = URI_MATCHER.match(uri);
        boolean isFinish = false;
        while (!isFinish) {
            switch (goTo) {
                case GoToFinish:
                    if (!TextUtils.isEmpty(selection)) {
                        where += " AND " + selection;
                    }
                    updateCount = db.update(table, values, where,selectionArgs);
                    isFinish = true;
                    break;

                case BOOK_ID:
                    idStr = uri.getLastPathSegment();
                    where = PLMContract.Book._ID + " = " + idStr;
                    table = PLMContract.Book.TABLE_NAME;
                    goTo = GoToFinish;
                    continue;

                case GENRES_ID:
                    idStr = uri.getLastPathSegment();
                    where = PLMContract.Genres._ID + " = " + idStr;
                    table = PLMContract.Genres.TABLE_NAME;
                    goTo = GoToFinish;
                    continue;

                default:
                    isFinish = true;
                    break;
            }
        }

        switch (URI_MATCHER.match(uri)) {
            case BOOK_LIST:
                updateCount = db.update(PLMContract.Book.TABLE_NAME, values, selection, selectionArgs);
                break;
            case GENRES_LIST:
                updateCount = db.update(PLMContract.Genres.TABLE_NAME, values,selection, selectionArgs);
                break;
            default:
                // no support for deleting photos or entities -
                // photos are deleted by a trigger when the item is deleted
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        // notify all listeners of changes:
        if (updateCount > 0 && !isInBatchMode()) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updateCount;
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        return super.applyBatch(operations);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        return super.bulkInsert(uri, values);
    }

    //</editor-fold>

    public Cursor rawQuery(String sql, String[] args) {
        SQLiteDatabase db = mDbContext.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, args);

        //?
        cursor.setNotificationUri(getContext().getContentResolver(), PLMContract.CONTENT_URI);

        return cursor;
    }

    //</editor-fold>



}
