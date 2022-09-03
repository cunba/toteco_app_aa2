package com.svalero.toteco_app_aa2.domain.localdb;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "establishments")
public class EstablishmentLocal implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private double latitude;
    @ColumnInfo
    private double longitude;
    @ColumnInfo
    private boolean open;
    @ColumnInfo
    private float punctuation;

    public EstablishmentLocal(String name, double latitude, double longitude, boolean open, float punctuation) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.open = open;
        this.punctuation = punctuation;
    }

    protected EstablishmentLocal(Parcel in) {
        id = in.readInt();
        name = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        open = in.readByte() != 0;
        punctuation = in.readFloat();
    }

    public EstablishmentLocal() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeByte((byte) (open ? 1 : 0));
        dest.writeFloat(punctuation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EstablishmentLocal> CREATOR = new Creator<EstablishmentLocal>() {
        @Override
        public EstablishmentLocal createFromParcel(Parcel in) {
            return new EstablishmentLocal(in);
        }

        @Override
        public EstablishmentLocal[] newArray(int size) {
            return new EstablishmentLocal[size];
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", open=" + open +
                ", punctuation=" + punctuation +
                '}';
    }

}
