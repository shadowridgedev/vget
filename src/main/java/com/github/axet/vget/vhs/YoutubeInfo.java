package com.github.axet.vget.vhs;

import java.net.URL;

import com.github.axet.vget.info.VideoInfo;

public class YoutubeInfo extends VideoInfo {

    // keep it in order hi->lo
    public enum YoutubeQuality {
        p3072, p2304, p1080, p720, p520, p480, p360, p270, p240, p224, p144
    }

    private YoutubeQuality vq;

    public YoutubeInfo(URL web) {
        super(web);
    }

    public YoutubeQuality getVideoQuality() {
        return vq;
    }

    public void setVideoQuality(YoutubeQuality vq) {
        this.vq = vq;
    }

}