package com.serenegiant.usb;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StillFormat implements Parcelable, Cloneable {

    public int index;
    /**
     * format type: mjpeg, uncompressed etc.
     */
    public int type;
    public List<Descriptor> frameDescriptors;

    public StillFormat(int index, int type, List<Descriptor> frameDescriptors) {
        this.index = index;
        this.type = type;
        this.frameDescriptors = frameDescriptors;
    }

    protected StillFormat(Parcel in) {
        index = in.readInt();
        type = in.readInt();
        frameDescriptors = in.createTypedArrayList(Descriptor.CREATOR);
    }

    public static final Creator<StillFormat> CREATOR = new Creator<StillFormat>() {
        @Override
        public StillFormat createFromParcel(Parcel in) {
            return new StillFormat(in);
        }

        @Override
        public StillFormat[] newArray(int size) {
            return new StillFormat[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
        dest.writeInt(type);
        dest.writeTypedList(frameDescriptors);
    }

    @Override
    public StillFormat clone() {
        StillFormat format = null;

        try {
            format = (StillFormat) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        if (format == null) {
            format = new StillFormat(index, type, new ArrayList<>());
        }

        List<Descriptor> descriptorList = new ArrayList<>();
        if (frameDescriptors != null) {
            for (Descriptor descriptor : frameDescriptors) {
                descriptorList.add(descriptor.clone());
            }
        }
        format.frameDescriptors = descriptorList;

        return format;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "StillFormat(index:%d,type:%d,frameDescriptors:%s)", index, type, frameDescriptors);
    }

    public static class Descriptor implements Parcelable, Cloneable {

        public int index;
        /**
         * frame type: mjpeg, uncompressed etc.
         */
        public int type;
        public int width;
        public int height;
        public int resolutionIndex;
        public int endPointAddress;
        public int numCompressionPattern;
        public List<Integer> compressions;
        public int stillCaptureMethod;

        /**
         * constructor
         */
        public Descriptor(final int _index, final int _type, final int _width, final int _height,
                          final int _resolutionIndex, final int _endPointAddress, final int _numCompressionPattern,
                          final List<Integer> _compressions, final int _stillCaptureMethod) {
            index = _index;
            type = _type;
            width = _width;
            height = _height;
            resolutionIndex = _resolutionIndex;
            endPointAddress = _endPointAddress;
            numCompressionPattern = _numCompressionPattern;
            compressions = _compressions;
            stillCaptureMethod = _stillCaptureMethod;
        }

        protected Descriptor(Parcel in) {
            index = in.readInt();
            type = in.readInt();
            width = in.readInt();
            height = in.readInt();
            resolutionIndex = in.readInt();
            endPointAddress = in.readInt();
            numCompressionPattern = in.readInt();
            final int size = in.readInt();
            compressions = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                compressions.add(in.readInt());
            }
            stillCaptureMethod = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(index);
            dest.writeInt(type);
            dest.writeInt(width);
            dest.writeInt(height);
            dest.writeInt(resolutionIndex);
            dest.writeInt(endPointAddress);
            dest.writeInt(numCompressionPattern);
            final int size = compressions != null ? compressions.size() : 0;
            dest.writeInt(size);
            for (int i = 0; i < size; i++) {
                dest.writeInt(compressions.get(i));
            }
            dest.writeInt(stillCaptureMethod);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Descriptor> CREATOR = new Creator<Descriptor>() {
            @Override
            public Descriptor createFromParcel(Parcel in) {
                return new Descriptor(in);
            }

            @Override
            public Descriptor[] newArray(int size) {
                return new Descriptor[size];
            }
        };

        @Override
        public String toString() {
            return String.format(Locale.US, "Descriptor(index:%d,type:%d,width:%d,height:%d,resolutionIndex:%d,endPointAddress:%d,numCompressionPattern:%d,compressions:%s,stillCaptureMethod:%d)", index, type, width, height, resolutionIndex, endPointAddress, numCompressionPattern, compressions, stillCaptureMethod);
        }

        @NonNull
        @Override
        public Descriptor clone() {
            Descriptor descriptor = null;

            try {
                descriptor = (Descriptor) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            if (descriptor == null) {
                descriptor = new Descriptor(index, type, width, height, resolutionIndex, endPointAddress, numCompressionPattern, new ArrayList<>(), stillCaptureMethod);
            }
            descriptor.compressions = compressions != null ? new ArrayList<>(compressions) : new ArrayList<>();

            return descriptor;
        }
    }
}
