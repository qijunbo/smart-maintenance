注意事项
1.com\qq\weixin\mp\aes目录下是用户需要用到的接入企业微信的接口，其中WXBizMsgCrypt.java文件提供的WXBizMsgCrypt类封装了用户接入企业微信的三个接口，其它的类文件用户用于实现加解密，用户无须关心。sample.java文件提供了接口的使用求例。
2.WXBizMsgCrypt封装了VerifyURL, DecryptMsg, EncryptMsg三个接口，分别用于开发者验证回调url，收到用户回复消息的解密以及开发者回复消息的加密过程。使用方法可以参考Sample.java文件。
3.加解密协议请参考企业微信官方文档。
4.请开发者使用jdk1.6以上的版本。针对org.apache.commons.codec.binary.Base64，需要导入架包commons-codec-1.9（或commons-codec-1.8等其他版本），我们有提供，官方下载地址：http://commons.apache.org/proper/commons-codec/download_codec.cgi

****请特别注意******
5.异常java.security.InvalidKeyException:illegal Key Size的解决方案：
在官方网站下载JCE无限制权限策略文件（请到官网下载对应的版本, 例如：JDK7的下载地址：
http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html下载后解压，可以看到local_policy.jar和US_export_policy.jar以及readme.txt。如果安装了JRE，将两个jar文件放到%JRE_HOME%\lib\security目录下覆盖原来的文件，如果安装了JDK，将两个jar文件放到%JDK_HOME%\jre\lib\security目录下覆盖原来文件




注意事项：

1.com\qq\weixin\mp\aes目录下是用户需要用到的接入企业微信的接口，其中WXBizMsgCrypt.java文件提供的WXBizMsgCrypt类封装了用户接入企业微信的三个接口，其它的类文件用户用于实现加解密，用户无须关心。sample.java文件提供了接口的使用示例。

2.WXBizMsgCrypt封装了VerifyURL, DecryptMsg, EncryptMsg三个接口，分别用于开发者验证回调url、接收消息的解密以及开发者回复消息的加密过程。使用方法可以参考Sample.java文件。

3.请开发者使用jdk1.6或以上的版本。针对org.apache.commons.codec.binary.Base64，需要导入jar包commons-codec-1.9（或comm ons-codec-1.8等其他版本），我们有提供，官方下载地址：

http://commons.apache.org/proper/commons-codec/download_codec.cgi

4.异常java.security.InvalidKeyException:illegal Key Size的解决方案：

在官方网站下载JCE无限制权限策略文件（请到官网下载对应的版本， 例如JDK7的下载地址：http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html )：

下载后解压，可以看到local_policy.jar和US_export_policy.jar以及readme.txt。如果安装了JRE，将两个jar文件放到%JRE_HOME% \lib\security目录下覆盖原来的文件，如果安装了JDK，将两个jar文件放到%JDK_HOME%\jre\lib\security目录下覆盖原来文件。

