package com.github.axet.vget.info;

import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.github.axet.vget.info.VideoInfo.VideoQuality;
import com.github.axet.wget.info.DownloadInfo;
import com.github.axet.wget.info.ex.DownloadError;
import com.github.axet.wget.info.ex.DownloadRetry;

public abstract class VGetParser {

    static public final VideoQuality[] VIDEOQUALITYS = new VideoQuality[] { VideoQuality.p2304, VideoQuality.p1080,
            VideoQuality.p720, VideoQuality.p480, VideoQuality.p360, VideoQuality.p270, VideoQuality.p224 };

    abstract public void extract(VideoInfo info, AtomicBoolean stop, Runnable notify);

    public void getVideo(VideoInfo vvi, Map<VideoQuality, URL> sNextVideoURL) {
        if (sNextVideoURL.size() == 0) {
            // rare error:
            //
            // The live recording you're trying to play is still being processed
            // and will be available soon. Sorry, please try again later.
            //
            // retry. since youtube may already rendrered propertly quality.
            throw new DownloadRetry("no video with required quality found,"
                    + " wait until youtube will process the video");
        }

        VideoQuality maxQuality = vvi.getVq();

        int i = 0;

        if (maxQuality != null) {
            i = Arrays.binarySearch(VIDEOQUALITYS, maxQuality);

            if (i == -1)
                i = VIDEOQUALITYS.length;
        }

        for (; i < VIDEOQUALITYS.length; i++) {
            if (sNextVideoURL.containsKey(VIDEOQUALITYS[i])) {
                vvi.setVq(VIDEOQUALITYS[i]);
                DownloadInfo info = new DownloadInfo(sNextVideoURL.get(VIDEOQUALITYS[i]));
                vvi.setInfo(info);
                return;
            }
        }

        // throw download stop if user choice not maximum quality and we have no
        // video rendered by youtube
        throw new DownloadError("no video with required quality found,"
                + " increace VideoInfo.setVq to the maximum and retry download");
    }
}
