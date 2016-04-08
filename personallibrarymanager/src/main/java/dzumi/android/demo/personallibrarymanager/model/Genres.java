package dzumi.android.demo.personallibrarymanager.model;

import android.os.Parcel;
import android.os.Parcelable;

import dzumi.android.demo.personallibrarymanager.content_provider.PLMContract;

/**
 * Created by Dzumi on 3/28/2016.
 */
public class Genres extends PLMContract.Genres implements Parcelable{
    String genresName;
    long _id;

    public String getGenresName() {
        return genresName;
    }

    public void setGenresName(String genresName) {
        this.genresName = genresName;
    }

    public int getGenresID() {
        return genresID;
    }

    public void setGenresID(int genresID) {
        this.genresID = genresID;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    int genresID;
    public Genres() {
    }

    protected Genres(Parcel in) {
        _id = in.readLong();
        genresID = in.readInt();
        genresName = in.readString();
    }

    public static final Creator<Genres> CREATOR = new Creator<Genres>() {
        @Override
        public Genres createFromParcel(Parcel in) {
            return new Genres(in);
        }

        @Override
        public Genres[] newArray(int size) {
            return new Genres[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeInt(genresID);
        dest.writeString(genresName);
    }
}
