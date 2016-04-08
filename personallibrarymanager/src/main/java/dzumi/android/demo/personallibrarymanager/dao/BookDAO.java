package dzumi.android.demo.personallibrarymanager.dao;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dzumi.android.demo.personallibrarymanager.content_provider.PLMContract;
import dzumi.android.demo.personallibrarymanager.model.Book;
import dzumi.android.demo.personallibrarymanager.model.Genres;

/**
 * Created by Dzumi on 3/28/2016.
 */
public class BookDAO extends BaseDAO {

    public BookDAO(Context context) {
        super(context);
    }

    public void insert(Genres genres, List<Book> books){
        ArrayList<ContentProviderOperation> operations = new ArrayList<>();
        //insert genres truoc
        operations.add(ContentProviderOperation.newInsert(Genres.CONTENT_URI)
        .withValue(Genres.GENRES_ID, genres.getGenresID())
        .withValue(Genres.GENRES_NAME, genres.getGenresName()).build());

        for(Book book : books) {
            operations.add(ContentProviderOperation.newInsert(Book.CONTENT_URI)
                    .withValueBackReference(PLMContract.Book.GENRES_RAW_ID,0)
                    .withValues(getContentValues(book)).build());
        }

        try {
            mContentResolver.
                    applyBatch(PLMContract.AUTHORITY, operations);
        } catch (RemoteException e) {
            // some error handling
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            // some error handling
            e.printStackTrace();
        }
    }

    private ContentValues getContentValues(Book book){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Book.AUTHOR, book.getAuthor());
        contentValues.put(Book.ISBN_BOOK, book.getISBN());
        contentValues.put(Book.NAME, book.getName());
        return contentValues;
    }
}
