> 协议，网络协议的简称，网络协议是通信计算机双方必须共同遵从的一组约定。如怎么样建立连接、怎么样互相识别等。只有遵守这个约定，计算机之间才能相互通信交流。它的三要素是：语法、语义、时序。
为了使数据在网络上从源到达目的，网络通信的参与方必须遵循相同的规则，这套规则称为协议（protocol），它最终体现为在网络上传输的数据包的格式。
协议往往分成几个层次进行定义，分层定义是为了使某一层协议的改变不影响其他层次的协议。

# 应用层  该层包括所有和应用程序协同工作，利用基础网络交换应用程序专用的数据的协议。
-  HTTP(Hypertext Transfer Protocol),超文本传输协议。
消息体数据的格式一般有xml，json。
  
- TELNET (Teletype over the Network, 网络电传) ，通过一个终端(terminal)登陆到网络(运行在TCP协议上)。   
- FTP (File Transfer Protocol, 文件传输协议) ，由名知义(运行在TCP协议上) 。   
- SMTP (Simple Mail Transfer Protocol，简单邮件传输协议) ，用来发送电子邮件(运行在TCP协议上) 。   
- DNS (Domain Name Service，域名服务) ，用于完成地址查找，邮件转发等工作(运行在TCP和UDP协议上) 。   
- NTP (Network Time Protocol，网络时间协议) ，用于网络同步(运行在UDP协议上) 。  
- SNMP (Simple Network Management Protocol, 简单网络管理协议) ，用于网络信息的收集和网络管理。    

# 传输层  该层提供端对端的通信。
最重要的传输层协议是传输控制协议TCP。   
传输控制协议TCP (Transport Control Protocol) - 数据流传输（面向连接，可靠）

[详见](./interview-TCP&UDP.md)
  
用户数据报文协议UDP (User Datagram Protocol) - 数据报文传输(无连接不可靠)    

# 网络层  该层负责数据转发和路由。从该层上面往下看，可以认为底下存在的是一个不可靠无连接的端对端的数据通路。
最核心的协议当然是IP协议。
此外还有ICMP，RIP,OSPF,IS-IS,BGP,ARP,RARP等。    

# 链路层  TCP/IP参考模型定义了链路层，但该层不属于TCP/IP协议栈的范围。
常用的链路层技术有以太网(Ethernet)，令牌环(Token Ring)，光纤数据分布接口(FDDI)，端对端协议( PPP)，X.25，帧中继(Frame Relay)，ATM，Sonet, SDH等