package com.github.axet.vget.info;

import java.io.File;
import java.net.URL;

import com.github.axet.wget.info.DownloadInfo;
import com.github.axet.wget.info.ProxyInfo;

public class VideoFileInfo extends DownloadInfo {

    public File targetFile;

    public VideoFileInfo(URL source) {
        super(source);
    }

    public VideoFileInfo(URL source, ProxyInfo p) {
        super(source, p);
    }

}
