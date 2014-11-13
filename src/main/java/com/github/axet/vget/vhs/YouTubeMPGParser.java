package com.github.axet.vget.vhs;

import java.net.URL;
import java.util.List;

import com.github.axet.vget.info.VideoInfo.VideoQuality;

public class YouTubeMPGParser extends YouTubeParser {

    public YouTubeMPGParser(URL input, VideoQuality q) {
        super(input);
    }

    void addVideo(List<VideoDownload> sNextVideoURL, String itag, URL url) {
        Integer i = Integer.parseInt(itag);

        // get rid of webm
        switch (i) {
        case 102:
        case 101:
        case 100:
        case 46:
        case 45:
        case 44:
        case 43:
            return;
        }

        super.addVideo(sNextVideoURL, itag, url);
    }

}
