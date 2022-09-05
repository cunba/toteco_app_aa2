package com.svalero.toteco_app_aa2.domain.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class PublicationDTO implements Parcelable {
    private byte[] photo;
    private int userId;
    private int establishmentId;
    private float totalPunctuation;
    private float totalPrice;

    public PublicationDTO(byte[] photo, int userId, int establishmentId, float totalPunctuation, float totalPrice) {
        this.photo = photo;
        this.userId = userId;
        this.establishmentId = establishmentId;
        this.totalPunctuation = totalPunctuation;
        this.totalPrice = totalPrice;
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

    public float getTotalPunctuation() {
        return totalPunctuation;
    }

    public void setTotalPunctuation(float totalPunctuation) {
        this.totalPunctuation = totalPunctuation;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
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
