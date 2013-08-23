# vget

Video Download Library.

Support for Youtube, Vimeo. Multithread downloads. Based on multithread download library https://github.com/axet/wget.

Pure java!

Inspired by http://sourceforge.net/projects/ytd2/.

Code taken from ytd2 and completely rewritten to support more web sites.

Good examples here:
  https://github.com/pculture/miro/blob/master/tv/lib/flashscraper.py

## Exceptions

Here are a two kind of exceptions.

1) Fatal exception. all RuntimeException's
  We shall stop application

2) DownloadError (extends RuntimeException)
  We unable to process following url and shall stop to download it

## Example Direct Download

    package com.github.axet.vget;
    
    import java.io.File;
    import java.net.URL;
    
    public class Example {
    
        public static void main(String[] args) {
            try {
                VGet v = new VGet(new URL("http://vimeo.com/52716355"), new File("/Users/axet/Downloads"));
                v.download();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    
    }

## Example Application Managed Download

    package com.github.axet.vget;
    
    import java.io.File;
    import java.net.URL;
    import java.util.concurrent.atomic.AtomicBoolean;
    
    import com.github.axet.vget.info.VideoInfo;
    import com.github.axet.wget.info.DownloadInfo;
    import com.github.axet.wget.info.DownloadInfo.Part;
    import com.github.axet.wget.info.DownloadInfo.Part.States;
    
    public class Example {
    
        VideoInfo info;
        long last;
    
        public void run() {
            try {
                AtomicBoolean stop = new AtomicBoolean(false);
                Runnable notify = new Runnable() {
                    @Override
                    public void run() {
                        VideoInfo i1 = info;
                        DownloadInfo i2 = i1.getInfo();
    
                        // notify app or save download state
                        // you can extract information from DownloadInfo info;
                        switch (i1.getState()) {
                        case EXTRACTING:
                        case EXTRACTING_DONE:
                        case DONE:
                            System.out.println(i1.getState());
                            break;
                        case RETRYING:
                            System.out.println(i1.getState() + " " + i1.getDelay());
                            break;
                        case DOWNLOADING:
                            long now = System.currentTimeMillis();
                            if (now - 1000 > last) {
                                last = now;
    
                                String parts = "";
    
                                for (Part p : i2.getParts()) {
                                    if (p.getState().equals(States.DOWNLOADING)) {
                                        parts += String.format("Part#%d(%.2f) ", p.getNumber(),
                                                p.getCount() / (float) p.getLength());
                                    }
                                }
    
                                System.out.println(String.format("%s %.2f %s", i1.getState(),
                                        i2.getCount() / (float) i2.getLength(), parts));
                            }
                            break;
                        default:
                            break;
                        }
                    }
                };
    
                info = new VideoInfo(new URL("http://vimeo.com/52716355"));

                // [OPTIONAL] limit maximum quality, or do not call this function if
                // you wish
                // maximum quality available.
                //
                // if youtube does not have video with requested quality, programm
                // will raise an exception
    
                info.setUserQuality(VideoQuality.p480);

                VGet v = new VGet(info, new File("/Users/axet/Downloads"));
    
                // [OPTIONAL] call v.extract() only if you d like to get video title before start
                // download. or just skip it.
                v.extract(stop, notify);
                System.out.println(info.getTitle());
    
                v.download(stop, notify);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    
        public static void main(String[] args) {
            Example e = new Example();
            e.run();
        }
    }

## Central Maven Repo

    <dependency>
      <groupId>com.github.axet</groupId>
      <artifactId>vget</artifactId>
      <version>1.1.2</version>
    </dependency>
