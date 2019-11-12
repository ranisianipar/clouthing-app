package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Laundries")
public class Laundry implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String title;

    @NonNull
    private int amount;

    private String pic;

    @NonNull
    private int status;

    @NonNull
    private long pickUpDate;

    @NonNull
    private long createdAt;

    // images
    private String image;

    // location

    @NonNull
    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NonNull long createdAt) {
        this.createdAt = createdAt;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @NonNull
    public int getStatus() {
        return status;
    }

    public void setStatus(@NonNull int status) {
        this.status = status;
    }

    public void setStatus(@NonNull LaundryStatus status) {
        if (status == LaundryStatus.CANCELED)
            setStatus(2);
        else if (status == LaundryStatus.DONE)
            setStatus(1);
        else if (status == LaundryStatus.PENDING)
            setStatus(0);
    }

    @NonNull
    public long getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(@NonNull long pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    protected Laundry(Parcel parcel) {
        this.title = parcel.readString();
        this.amount = parcel.readInt();
    }

    public static final Creator<Laundry> CREATOR = new Creator<Laundry>() {
        @Override
        public Laundry createFromParcel(Parcel parcel) {
            return new Laundry(parcel);
        }

        @Override
        public Laundry[] newArray(int i) {
            return new Laundry[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeInt(amount);
    }


    public enum LaundryStatus {
        PENDING, // 0
        DONE, // 1
        CANCELED // 2
    }
}
