package dzumi.android.demo.personallibrarymanager.model;

import android.os.Parcel;
import android.os.Parcelable;

import dzumi.android.demo.personallibrarymanager.content_provider.PLMContract;

/**
 * Created by Dzumi on 3/28/2016.
 */
public class Book extends PLMContract.Book implements Parcelable{


    public Book() {
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPaperback() {
        return paperback;
    }

    public void setPaperback(String paperback) {
        this.paperback = paperback;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public long getGenresID() {
        return genresID;
    }

    public void setGenresID(long genresID) {
        this.genresID = genresID;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    long _id;
    long ISBN;
    String name;
    String author;
    String paperback;
    String publisher;
    String language;
    String dimensions;
    long genresID;

    public Book(long _id, long ISBN, String name, String author, String paperback, String publisher, String language, String dimensions, long genresID) {
        this._id = _id;
        this.ISBN = ISBN;
        this.name = name;
        this.author = author;
        this.paperback = paperback;
        this.publisher = publisher;
        this.language = language;
        this.dimensions = dimensions;
        this.genresID = genresID;
    }
    protected Book(Parcel in) {
        _id = in.readLong();
        ISBN = in.readLong();
        name = in.readString();
        author = in.readString();
        paperback = in.readString();
        publisher = in.readString();
        language = in.readString();
        dimensions = in.readString();
        genresID = in.readLong();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeLong(ISBN);
        dest.writeString(name);
        dest.writeString(author);
        dest.writeString(paperback);
        dest.writeString(publisher);
        dest.writeString(language);
        dest.writeString(dimensions);
        dest.writeLong(genresID);
    }
}
