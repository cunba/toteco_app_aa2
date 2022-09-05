package com.svalero.toteco_app_aa2.domain.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.svalero.toteco_app_aa2.domain.utils.Location;

public class EstablishmentDTO implements Parcelable {
    private String name;
    private Location location;
    private boolean open;

    public EstablishmentDTO(String name, Location location, boolean open) {
        this.name = name;
        this.location = location;
        this.open = open;
    }

    protected EstablishmentDTO(Parcel in) {
        name = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
        open = in.readByte() != 0;
    }

    public static final Creator<EstablishmentDTO> CREATOR = new Creator<EstablishmentDTO>() {
        @Override
        public EstablishmentDTO createFromParcel(Parcel in) {
            return new EstablishmentDTO(in);
        }

        @Override
        public EstablishmentDTO[] newArray(int size) {
            return new EstablishmentDTO[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeParcelable(location, i);
        parcel.writeByte((byte) (open ? 1 : 0));
    }
}
