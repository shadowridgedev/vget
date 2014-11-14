package com.github.axet.vget;

import java.net.URL;
import java.util.List;

import com.github.axet.vget.vhs.YouTubeParser;
import com.github.axet.vget.vhs.YouTubeParser.VideoDownload;
import com.github.axet.vget.vhs.YoutubeInfo;

public class ExtractDownloadLinks {

    public static void main(String[] args) {
        try {
            // ex: http://www.youtube.com/watch?v=Nj6PFaDmp6c
            String url = args[0];

            YoutubeInfo info = new YoutubeInfo(new URL(url));

            YouTubeParser parser = new YouTubeParser();

            List<VideoDownload> list = parser.extractLinks(info);

            for (VideoDownload d : list) {
                System.out.println(d.vq + " " + d.url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
