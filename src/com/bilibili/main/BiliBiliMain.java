package com.bilibili.main;

import com.alibaba.fastjson.JSONObject;
import com.bilibili.utils.JSONFileUtils;
import com.bilibili.utils.SeleniumUtils;
import javazoom.jl.decoder.JavaLayerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BiliBiliMain {
    private static final Logger logger = LogManager.getLogger(BiliBiliMain.class);
    static SeleniumUtils utils = null;
    JSONObject bilibiliConfig;

    volatile static String musicId = "";

    BiliBiliMain() throws IOException, InterruptedException {
        utils = SeleniumUtils.init("webdriver.Chrome.driver", "resources/chromedriver.exe");
        //utils.setMaximize();
        //设置窗口大小
        utils.getDriver().manage().window().setSize(new Dimension(984,904));
        //读取配置文件
        bilibiliConfig = JSONFileUtils.readFile("resources/bilibi.json");

        if (bilibiliConfig == null || bilibiliConfig.size() == 0) {
            logger.error("读取配置文件错误");
            return;
        }
        //打开网站
        utils.goPage(bilibiliConfig.getString("url"));


        Thread.sleep(1000);
        utils.toWindowHandle();
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws IOException, InterruptedException, JavaLayerException {
        BiliBiliMain bm = new BiliBiliMain();

        System.out.println("启动成功");
        String hisD = "";

        //开启另一个线程播放音乐
        Thread musicThread = new Thread(new PlayWYMusic());
        musicThread.start();

        while (true) {

            List<WebElement> search_result = utils.getDriver().findElements(By.cssSelector("#chat-history-list > div"));
            if(search_result.size()==0){
                continue;
            }
            WebElement e = search_result.get(search_result.size() - 1);

            String label = null;
            String lableContent = null;
            String name = null;
            String level=null;
            String context = null;
            boolean isMM = false;

            try {
                label = e.findElement(By.cssSelector(".label")).getText();
            } catch (Exception e1) {

            }
            try {
                lableContent = e.findElement(By.cssSelector(".fans-medal-item-ctnr.dp-i-block.p-relative.v-middle > div > div > a > span")).getText();
            } catch (Exception e1) {

            }
            try {
                name = e.findElement(By.cssSelector(".user-name")).getText();
            } catch (Exception e1) {}
            //String lableContent = e.findElement(By.cssSelector(".fans-medal-item-ctnr.dp-i-block.p-relative.v-middle > div > div > a > span")).getText();

            try {
                level = e.findElement(By.cssSelector(".user-level-icon")).getText();
            } catch (Exception e1) {}

            try {
                context = e.findElement(By.cssSelector(".danmaku-content")).getText();
            } catch (Exception ex) {
                continue;
            }

            try {
                String anchor = e.findElement(By.cssSelector(".anchor-icon")).getText();
                isMM = true;
            } catch (Exception ex) {
                isMM = false;
            }


            if (hisD.equals(context)) {
                continue;
            } else {
                hisD = context;
            }

            System.out.println(isMM + "名片:" + label + ":" + lableContent +
                    "|等级:" + level + ",名字:" + name +
                    ",内容:" + context + ",抓取时间:" + new Date());

            if(context.indexOf("音乐") != -1 && context.indexOf("音") == 0){

                String songId = context.replaceAll("音乐","");

                Pattern pa = Pattern.compile("[0-9]*");
                Matcher isNum = pa.matcher(songId);
                if(!isNum.matches()){
                    continue;
                }

                synchronized (bm){
                    BiliBiliMain.musicId = songId;
                }
            }
        }
    }
}
