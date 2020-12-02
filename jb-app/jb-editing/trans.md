scp -r -P 29086 ripme-1.7.90-jar-with-dependencies.jar  root@184.170.208.122:/root/social/

sHVMPgA4cDE7

scp -r -P 29086 root@184.170.208.122:/root/social/ .

scp -r -P 29086 root@184.170.208.122:/root/download/ .


## YOUTUBE

/usr/local/bin/youtube-dl -F 
/usr/local/bin/youtube-dl -f 140 
/usr/local/bin/youtube-dl --list-thumbnails          
/usr/local/bin/youtube-dl --write-all-thumbnails          
/usr/local/bin/youtube-dl --write-thumbnail 



/usr/local/bin/youtube-dl --merge-output-format 140 299 https://www.youtube.com/watch?v=tDEy4bzRKuQ
/usr/local/bin/youtube-dl --merge-output-format mp4 https://www.youtube.com/watch?v=tDEy4bzRKuQ

youtube-dl -f 'bestvideo[ext=mp4]+bestaudio[ext=m4a]/bestvideo+bestaudio' --merge-output-format mp4 https://www.youtube.com/watch?v=tDEy4bzRKuQ

/usr/local/bin/youtube-dl --yes-playlist -f m4a --audio-format m4a --audio-quality 8

 wget `ytbn eG3k3I3vJ6Q | awk '$4~/jpg/ {print $4}'` -O eG3k3I3vJ6Q.jpg
