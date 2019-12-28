package com.example.hypertechtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hypertechtest.ui.CustomGifView;
import com.example.hypertechtest.ui.CustomViewHolder;
import com.example.hypertechtest.ui.VideoImage;
import com.example.hypertechtest.ui.VideosAdapter;
import com.example.hypertechtest.util.Utils;
//import com.squareup.picasso.Picasso;

import java.util.List;


public class MyVideosAdapter extends VideosAdapter {

    private final List<MyModel> list;
    private Context context;
    private int lastPosition = -1;

    public class MyViewHolder extends CustomViewHolder {
        final TextView tv;
        final ImageView img_vol, img_playback;
        //to mute/un-mute video (optional)
        boolean isMuted;

        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            tv = view.findViewById(R.id.tv);
            img_vol = view.findViewById(R.id.img_vol);
            img_playback = view.findViewById(R.id.img_playback);
        }

        //override this method to gtet callback when video starts to play
        @Override
        public void videoStarted() {
            super.videoStarted();
            img_playback.setImageResource(R.drawable.ic_pause);
            if (isMuted) {
                muteVideo();
                img_vol.setImageResource(R.drawable.ic_mute);
            } else {
                unmuteVideo();
                img_vol.setImageResource(R.drawable.ic_unmute);
            }
        }

        @Override
        public void pauseVideo() {
            super.pauseVideo();
            img_playback.setImageResource(R.drawable.ic_play);
        }
    }


    public class MyTextViewHolder extends CustomViewHolder {
        final TextView tv;

        public MyTextViewHolder(View x) {
            super(x);
            tv = x.findViewById(R.id.tv);
        }
    }

    public class MyGifViewHolder extends CustomViewHolder {
        final TextView tv;
        final CustomGifView cgfView;

        public MyGifViewHolder(View x) {
            super(x);
            tv = x.findViewById(R.id.tv);
            cgfView = x.findViewById(R.id.gif_view);
        }
    }


    public MyVideosAdapter(List<MyModel> list_urls) {
        this.list = list_urls;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Utils.TYPE_TEXT) {
            View itemView1 = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.single_text, parent, false);
            return new MyTextViewHolder(itemView1);
        } else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.single_card, parent, false);
            return new MyViewHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {

        MyModel myModel = list.get(position);
        byte type = myModel.getType();
        if (type == Utils.TYPE_TEXT) {
            setAnimation((ViewGroup) ((MyTextViewHolder) holder).tv.getParent(), position);
            ((MyTextViewHolder) holder).tv.setText(myModel.getName());
        } else {
            setAnimation((ViewGroup) ((MyViewHolder) holder).itemView, position);
            ((MyViewHolder) holder).tv.setText(myModel.getName());

            //todo
            holder.setImageUrl(myModel.getImage_url());
            if (myModel.getType() == Utils.TYPE_VIDEO)
                holder.setVideoUrl(myModel.getVideo_url());

            //load image into imageview
            if (myModel.getImage_url() != null && !myModel.getImage_url().isEmpty()) {
//                glide.load(holder.getImageUrl()).config(Bitmap.Config.RGB_565).into(holder.getImageView());

                Glide.with(context)
                        .load(myModel.getImage_url())
                        .into(holder.getAAH_ImageView());
            }

            holder.setLooping(true); //optional - true by default

            //to play pause videos manually (optional)
            ((MyViewHolder) holder).img_playback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.isPlaying()) {
                        holder.pauseVideo();
                        holder.setPaused(true);
                    } else {
                        holder.playVideo();
                        holder.setPaused(false);
                    }
                }
            });

            //to mute/un-mute video (optional)
            ((MyViewHolder) holder).img_vol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((MyViewHolder) holder).isMuted) {
                        holder.unmuteVideo();
                        ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_unmute);
                    } else {
                        holder.muteVideo();
                        ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_mute);
                    }
                    ((MyViewHolder) holder).isMuted = !((MyViewHolder) holder).isMuted;
                }
            });

            if (myModel.getVideo_url() == null) {
                ((MyViewHolder) holder).img_vol.setVisibility(View.GONE);
                ((MyViewHolder) holder).img_playback.setVisibility(View.GONE);
            } else {
                ((MyViewHolder) holder).img_vol.setVisibility(View.VISIBLE);
                ((MyViewHolder) holder).img_playback.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            lastPosition = position;
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.anime_content);
            viewToAnimate.startAnimation(animation);
        } else {
            lastPosition = position;
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.anime_down);
            viewToAnimate.startAnimation(animation);
        }
    }
}
