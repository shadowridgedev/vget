package com.myexperiments.ward;

import java.net.URL;
import java.util.List;

import com.myexperiments.ward.YouTubeInfo.Container;
import com.myexperiments.ward.YouTubeInfo.StreamInfo;

public class YouTubeMPGParser extends YouTubeParser {

    public YouTubeMPGParser() {
    }

    void filter(List<VideoDownload> sNextVideoURL, String itag, URL url) {
        Integer i = Integer.decode(itag);
        StreamInfo vd = itagMap.get(i);

        // get rid of webm
        if (vd.c == Container.WEBM)
            return;

        super.filter(sNextVideoURL, itag, url);
    }
}
