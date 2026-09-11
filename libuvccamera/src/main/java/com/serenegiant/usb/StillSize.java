package com.serenegiant.usb;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StillSize implements Parcelable, Cloneable {
    /**
     * frame type: mjpeg, uncompressed etc.
     */
    public int type;
    public int width;
    public int height;
    public int stillCaptureMethod;
    public List<Integer> compressionList;

    /**
     * constructor
     */
    public StillSize(final int _type, final int _width, final int _height, final int _stillCaptureMethod, final List<Integer> _compressionList) {
        type = _type;
        width = _width;
        height = _height;
        stillCaptureMethod = _stillCaptureMethod;
        compressionList = _compressionList;
    }

    protected StillSize(Parcel in) {
        type = in.readInt();
        width = in.readInt();
        height = in.readInt();
        stillCaptureMethod = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeInt(stillCaptureMethod);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StillSize> CREATOR = new Creator<StillSize>() {
        @Override
        public StillSize createFromParcel(Parcel in) {
            return new StillSize(in);
        }

        @Override
        public StillSize[] newArray(int size) {
            return new StillSize[size];
        }
    };

    @Override
    public String toString() {
        return String.format(Locale.US, "StillSize(type:%d,width:%d,height:%d,stillCaptureMethod:%d,compressionList:%s)", type, width, height, stillCaptureMethod, compressionList);
    }

    @Override
    public StillSize clone() {
        StillSize size = null;
        try {
            size = (StillSize) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        if (size == null) {
            size = new StillSize(type, width, height, stillCaptureMethod, new ArrayList<>(compressionList));
        } else {
            size.compressionList = new ArrayList<>(compressionList);
        }
        return size;
    }
}
