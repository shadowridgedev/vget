# YouTube

This document explain how vget download video from youtube servers. If you'd to improve, fix bugs or create new parser class this document would help.

This is very simple. All work done in two steps.

1) vget open html video page https://www.youtube.com/watch?v=sPKtTIckdkc and extract download links

2) then vget pass url to wget library which does actual download.

