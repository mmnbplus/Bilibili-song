# Bilibili-song

##info 中文版
B站点歌姬 是使用谷歌浏览器遥控器 Selenium 做的

SeleniumUtils.java是工具类

BiliBiliMain.java是读取B站弹幕的主线程

PlayWYMusic.java是网易音乐的线程,通过读取主线程里面的musicId属性来切换歌曲,musicId是网易音乐的id,可以通过访问网页版的网易在地址栏拿到音乐id

B站用户通过发送
```
音乐123456
```
就可以切换歌曲了

##info English
