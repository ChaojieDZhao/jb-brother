```cmd
lsblk    #看一下移动硬盘的磁盘标示符 
fdisk -l    #查看disk
ls -l /dev/disk/by-uuid    #查看UUID

df -h    #显示已经挂载的分区列表 
ls -lSr |more    #以尺寸大小排列文件和目录 
du -sh dir1    #估算目录 'dir1' 已经使用的磁盘空间' 
du -sh * | sort -rn    #以容量大小为依据依次显示文件和目录的大小 
du -Sh或du -Ssh    #查看一个文件夹中所有文件的大小（不含子目录中的文件）
du -h或者du -sh    #查看一个文件夹中所有文件的大小（包含子目录中的文件）
du -ah 或者ls -lRh    #查看文件夹中每一个文件的大小
``` 