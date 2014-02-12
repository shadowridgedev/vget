package com.github.axet.vget;

import java.net.URL;

import com.github.axet.vget.info.VGetParser.VideoDownload;

public class ExtractDownloadLinks {

    public static void main(String[] args) {
        try {
            // ex: http://www.youtube.com/watch?v=Nj6PFaDmp6c
            String url = args[0];
            VGet v = new VGet(new URL(url));

            v.extract();

            for (VideoDownload d : v.getVideo().getVideoDownloads()) {
                System.out.println(d.vq + " " + d.url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
