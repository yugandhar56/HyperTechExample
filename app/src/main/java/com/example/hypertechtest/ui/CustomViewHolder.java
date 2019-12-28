package com.example.hypertechtest.ui;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.concurrent.Callable;

public class CustomViewHolder  extends RecyclerView.ViewHolder {
    private VideoImage videosImage;
    private String imageUrl;
    private String videoUrl;
    private boolean isLooping = true;
    private boolean isPaused = false;


    public CustomViewHolder(View x) {
        super(x);
        videosImage = (VideoImage) x.findViewWithTag("aah_vi");
    }

    public void playVideo() {
        this.videosImage.getCustomVideoView().setPaused(false);
        this.videosImage.getCustomVideoView().startVideo();
    }

    public void videoStarted() {
        this.videosImage.getImageView().setVisibility(View.GONE);
    }
    public void showThumb() {
        this.videosImage.getImageView().setVisibility(View.VISIBLE);
    }

    public void initVideoView(String url, Activity _act) {
        this.videosImage.getCustomVideoView().setVisibility(View.VISIBLE);
        Uri uri = Uri.parse(url);
        this.videosImage.getCustomVideoView().setSource(uri);
        this.videosImage.getCustomVideoView().setLooping(isLooping);
        this.videosImage.getCustomVideoView().set_act(_act);
        this.videosImage.getCustomVideoView().setMyFuncIn(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                videoStarted();
                return null;
            }
        });

        this.videosImage.getCustomVideoView().setShowThumb(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                showThumb();
                return null;
            }
        });
    }

    public void setLooping(boolean looping) {
        isLooping = looping;
    }

    public void pauseVideo() {
        this.videosImage.getCustomVideoView().pauseVideo();
        this.videosImage.getCustomVideoView().setPaused(true);
    }

    public void muteVideo() {
        this.videosImage.getCustomVideoView().muteVideo();
    }

    public void unmuteVideo() {
        this.videosImage.getCustomVideoView().unmuteVideo();
    }

    public VideoImage getVideoImage() {
        return videosImage;
    }

    public ImageView getAAH_ImageView() {
        return videosImage.getImageView();
    }

    public String getImageUrl() {
        return imageUrl + "";
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        this.videosImage.getImageView().setVisibility(View.VISIBLE);
        this.videosImage.getCustomVideoView().setVisibility(View.GONE);
    }

    public void setAah_vi(VideoImage videosImage) {
        this.videosImage = videosImage;
    }

    public String getVideoUrl() {
        return videoUrl + "";
    }

    public boolean isPlaying() {
        return this.videosImage.getCustomVideoView().isPlaying();
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isLooping() {
        return isLooping;
    }
}
