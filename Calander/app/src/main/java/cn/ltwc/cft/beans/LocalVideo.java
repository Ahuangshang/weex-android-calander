package cn.ltwc.cft.beans;

import android.graphics.Bitmap;

/**
 * video
 * Created by LZL on 16/5/5.
 */
public class LocalVideo {
    private int id;//ID：MediaStore.Audio.Media._ID
    private String title;//名称：MediaStore.Audio.Media.TITLE
    private String album;//专辑名：MediaStore.Audio.Media.ALBUM
    private String artist;//歌手名：MediaStore.Audio.Media.ARTIST
    private String displayName;
    private String mimeType;//视频类型：MediaStore.Video.Media.MIME_TYPE
    private String path;//路径：MediaStore.Audio.Media.DATA
    private long size;//大小：MediaStore.Audio.Media.SIZE
    private long duration;//总播放时长：MediaStore.Audio.Media.DURATION
    private Bitmap image;

    public LocalVideo() {
    }

    public LocalVideo(int id, String title, String album, String artist, String displayName, String mimeType, String path, long size, long duration, Bitmap image) {
        this.id = id;
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.displayName = displayName;
        this.mimeType = mimeType;
        this.path = path;
        this.size = size;
        this.duration = duration;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
