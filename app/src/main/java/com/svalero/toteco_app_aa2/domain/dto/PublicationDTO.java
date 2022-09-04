package com.svalero.toteco_app_aa2.domain.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class PublicationDTO implements Parcelable {
    private byte[] photo;
    private int userId;
    private int establishmentId;

    public PublicationDTO(byte[] photo, int userId, int establishmentId) {
        this.photo = photo;
        this.userId = userId;
        this.establishmentId = establishmentId;
    }

    protected PublicationDTO(Parcel in) {
        in.readByteArray(photo);
        userId = in.readInt();
        establishmentId = in.readInt();
    }

    public static final Creator<PublicationDTO> CREATOR = new Creator<PublicationDTO>() {
        @Override
        public PublicationDTO createFromParcel(Parcel in) {
            return new PublicationDTO(in);
        }

        @Override
        public PublicationDTO[] newArray(int size) {
            return new PublicationDTO[size];
        }
    };

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(int establishmentId) {
        this.establishmentId = establishmentId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(photo);
        parcel.writeInt(userId);
        parcel.writeInt(establishmentId);
    }
}
