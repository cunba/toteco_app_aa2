package com.svalero.toteco_app_aa2.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.svalero.toteco_app_aa2.domain.utils.Location;

public class Establishment implements Parcelable {
    private int id;
    private String name;
    private String creationDate;
    private Location location;
    private boolean open;
    private float punctuation;

    public Establishment(String name, String creationDate, Location location, boolean open, float punctuation) {
        this.name = name;
        this.creationDate = creationDate;
        this.location = location;
        this.open = open;
        this.punctuation = punctuation;
    }

    public Establishment() {
    }

    protected Establishment(Parcel in) {
        id = in.readInt();
        name = in.readString();
        creationDate = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
        open = in.readByte() != 0;
        punctuation = in.readFloat();
    }

    public static final Creator<Establishment> CREATOR = new Creator<Establishment>() {
        @Override
        public Establishment createFromParcel(Parcel in) {
            return new Establishment(in);
        }

        @Override
        public Establishment[] newArray(int size) {
            return new Establishment[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public float getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(float punctuation) {
        this.punctuation = punctuation;
    }

    @Override
    public String toString() {
        return "Establishment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", location=" + location +
                ", open=" + open +
                ", punctuation=" + punctuation +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(creationDate);
        parcel.writeParcelable(location, i);
        parcel.writeBoolean(open);
        parcel.writeFloat(punctuation);
    }
}
