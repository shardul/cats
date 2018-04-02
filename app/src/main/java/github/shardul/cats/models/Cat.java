package github.shardul.cats.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by Shardul on 30/03/18.
 */
@Entity
public class Cat implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "_id")
    private @NonNull String id;

    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "source_url")
    private String sourceUrl;

    public Cat(String id, String url, String sourceUrl) {
        this.id = id;
        this.url = url;
        this.sourceUrl = sourceUrl;
    }


    Cat(Parcel in) {
        id = in.readString();
        url = in.readString();
        sourceUrl = in.readString();
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cat cat = (Cat) o;

        if (!id.equals(cat.id)) return false;
        if (url != null ? !url.equals(cat.url) : cat.url != null) return false;
        return sourceUrl != null ? sourceUrl.equals(cat.sourceUrl) : cat.sourceUrl == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (sourceUrl != null ? sourceUrl.hashCode() : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public static final Creator<Cat> CREATOR = new Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel in) {
            return new Cat(in);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(url);
        dest.writeString(sourceUrl);
    }
}
