package cn.ltwc.cft.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class TiangouBean implements Parcelable {

    private String img;
    private String title;

    public TiangouBean(String img, String title) {
        super();
        this.img = img;
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TiangouBean [img=" + img + ", title=" + title + "]";
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeString(img);
        dest.writeString(title);

    }

    public static final Creator<TiangouBean> CREATOR = new Creator<TiangouBean>() {

        @Override
        public TiangouBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new TiangouBean(source);
        }

        @Override
        public TiangouBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new TiangouBean[size];
        }

    };

    public TiangouBean(Parcel in) {
        img = in.readString();
        title = in.readString();
    }

}
