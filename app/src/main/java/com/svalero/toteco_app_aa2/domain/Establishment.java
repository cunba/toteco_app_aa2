package com.svalero.toteco_app_aa2.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.versionedparcelable.ParcelField;

import com.svalero.toteco_app_aa2.domain.utils.Location;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class Establishment implements Parcelable {
    @PrimaryKey(autoGenerate = false)
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private LocalDate creationDate;
    @ColumnInfo
    private Location location;
    @ColumnInfo
    private boolean open;
    @ColumnInfo
    private float punctuation;

    public Establishment(String name, LocalDate creationDate, Location location, boolean open, float punctuation) {
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
        creationDate = Instant.ofEpochMilli(in.readLong()).atZone(ZoneId.systemDefault()).toLocalDate();
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
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
        parcel.writeLong(creationDate.toEpochDay());
        parcel.writeParcelable(location, i);
        parcel.writeBoolean(open);
        parcel.writeFloat(punctuation);
    }
}
