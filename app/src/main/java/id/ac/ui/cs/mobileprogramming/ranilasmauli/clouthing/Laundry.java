package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Laundry {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String title;

    @NonNull
    private int amount;

    private String pic;

    @NonNull
    private LaundryStatus status;

    @NonNull
    private Date pickUpTime;

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
    public LaundryStatus getStatus() {
        return status;
    }

    public void setStatus(@NonNull LaundryStatus status) {
        this.status = status;
    }

    @NonNull
    public Date getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(@NonNull Date pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    // images
    // location


    public enum LaundryStatus {
        PENDING,
        DONE,
        CANCELED
    }
}
