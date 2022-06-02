package sg.edu.np.mad.practical;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class User implements Parcelable {
    public String Name;
    public String Description;
    public int Id;
    public boolean Followed;

    public User(String name, String description, Integer id, Boolean followed) {
        this.Name = name;
        this.Description = description;
        this.Id = id;
        this.Followed = followed;
    }

    public User() {

    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected User(Parcel in) {
        Name = in.readString();
        Description = in.readString();
        Id = in.readInt();
        Followed = in.readBoolean();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeString(Description);
        parcel.writeInt(Id);
        // CAUTION: Doesn't work for API 24
        parcel.writeBoolean(Followed);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
