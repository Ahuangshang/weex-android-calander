package cn.ltwc.cft.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * TODO:记事本的JavaBean
 *
 * @author huangshang 2015-11-24 下午5:50:56
 * @Modified_By:
 */
public class NoteBean implements Parcelable {
    private String noteTitle;
    private String noteContent;
    private String completeTime;
    private String currentTime;

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public NoteBean() {
        super();
    }

    public NoteBean(String noteTitle, String noteContent, String completeTime,
                    String currentTime) {
        super();
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.completeTime = completeTime;
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "NoteBean [noteTitle=" + noteTitle + ", noteContent="
                + noteContent + ", completeTime=" + completeTime + "]";
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeString(noteTitle);
        dest.writeString(noteContent);
        dest.writeString(completeTime);
        dest.writeString(currentTime);

    }

    public static final Creator<NoteBean> CREATOR = new Creator<NoteBean>() {

        @Override
        public NoteBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new NoteBean[size];
        }

        @Override
        public NoteBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new NoteBean(source);
        }
    };

    public NoteBean(Parcel source) {
        noteTitle = source.readString();
        noteContent = source.readString();
        completeTime = source.readString();
        currentTime = source.readString();
    }

}
