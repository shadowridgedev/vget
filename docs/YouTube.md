# YouTube

This document explain how vget download video from youtube servers. If you'd to improve, fix bugs or create new parser class this document would help.

This is very simple. All work done in two steps.

1) vget open html video page https://www.youtube.com/watch?v=sPKtTIckdkc and extract download links

2) then vget pass url to wget library which does actual download.

YouTube have simple server structure. Main http server, which handles http requests and how you html pages. And CDN servers, which handles video files.

After you request html video page html server generate a page with randomly selected CDN servers and return it to the user. Depend on CDN server it may have different software installed, and handle video urls different.

Some CDN server may have encruption installed, some may have new urls and additional security parameters. Who know what would be next.

YouTube may generate page with proper url secret parameters which you have to parse, if they change the scheme - vget stop to download video. Go and find out why.

Video URL may be few types. First type - combined video stream, video and sound comes togeter. This stream have quality cap - combined stream have maximum 720p. And second type of the stream split stream: video and sound streams provided as separate URLS. This split streams have no quality limitations, so you may download 4k videos. Here a lot of other types of streams like 3d streams. You can find out what types of stream youtube used to be on Wikipedia: http://en.wikipedia.org/wiki/YouTube#Quality_and_codecs

Currently I see no correct way of handling split streams, (two separate files or merge two streams on the fly) vget works only with 720p.

[YouTubeParser.java](/src/main/java/com/github/axet/vget/vhs/YouTubeParser.java)
