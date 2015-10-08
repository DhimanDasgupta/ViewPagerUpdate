package com.dhimandasgupta.viewpagerupdate;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dhimandasgupta on 09/10/15.
 */
public class Model implements Parcelable {
    public String mText;
    public int mColor;
    public boolean mShowLayout;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mText);
        dest.writeInt(this.mColor);
        dest.writeByte(mShowLayout ? (byte) 1 : (byte) 0);
    }

    public Model() {
    }

    protected Model(Parcel in) {
        this.mText = in.readString();
        this.mColor = in.readInt();
        this.mShowLayout = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Model> CREATOR = new Parcelable.Creator<Model>() {
        public Model createFromParcel(Parcel source) {
            return new Model(source);
        }

        public Model[] newArray(int size) {
            return new Model[size];
        }
    };
}
