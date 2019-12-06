# Bilibili-song

##info 中文版

B站点歌姬 是使用谷歌浏览器遥控器 Selenium 做的

SeleniumUtils.java是工具类

BiliBiliMain.java是读取B站弹幕的主线程

PlayWYMusic.java是网易音乐的线程,通过读取主线程里面的musicId属性来切换歌曲,musicId是网易音乐的id,可以通过访问网页版的网易在地址栏拿到音乐id

chromedriver.exe是谷歌浏览器的驱动,在不同系统上是不同的,谷歌浏览器版本也需要对应,
[**驱动下载地址**](http://chromedriver.storage.googleapis.com/index.html)

bilibi.json是配置文件

```json
{
  "name":"bilibiliJsonFile",
  "url":"https://live.bilibili.com/14247985"//直播地址
}
```

B站用户通过发送以下代码就可以播放/切换歌曲
```
音乐123456
```




# Bilibili-song

##info English

My English not good,if you know up,you can read this,my chinese english,sorry.

This is BiliBili Song machine,it use Selenium,it is google chrome Remote control.

SeleniumUtils.java  -> Tool class

BiliBiliMain.java   -> main thread to read bilibili Barrage

PlayWYMusic.java    -> NetEase Music thread,read `musicId` in main thread to play music,Get the music ID in the address bar by visiting Netease on the web

chromedriver.exe    -> It's driven by Google browser. It's different in different systems. The version of Google browser also needs to be corresponding
[**Driver address**](http://chromedriver.storage.googleapis.com/index.html)

bilibi.json -> config file

```json
{
  "name":"bilibiliJsonFile",
  "url":"https://live.bilibili.com/14247985"//Live address
}
```

Bilibili users can play / switch songs by sending the following code
```
音乐123456
```
