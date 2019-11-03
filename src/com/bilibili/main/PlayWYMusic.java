package com.bilibili.main;

import org.openqa.selenium.Dimension;
import com.bilibili.utils.SeleniumUtils;
import org.openqa.selenium.interactions.Actions;

public class PlayWYMusic implements Runnable{
    SeleniumUtils utils;

    PlayWYMusic(){
        utils = SeleniumUtils.init("webdriver.Chrome.driver", "resources/chromedriver.exe");
        utils.getDriver().manage().window().setSize(new Dimension(984,904));
    }

    static String musci = "";

    @Override
    public void run() {
        System.out.println("音乐线程启动成功");
        while(true) {
            if (!musci.equals(BiliBiliMain.musicId)) {
                System.out.println("更换音乐");


                utils.getDriver().quit();

                utils = SeleniumUtils.init("webdriver.Chrome.driver", "resources/chromedriver.exe");
                utils.getDriver().manage().window().setSize(new Dimension(984,904));

                utils.goPage("https://music.163.com/#/song?id=" + BiliBiliMain.musicId);
                utils.toWindowHandle();
                musci = BiliBiliMain.musicId;

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(utils.getDriver().getTitle());

                Actions action = new Actions(utils.getDriver());
                action.moveByOffset(276, 245).perform();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                action.click().perform();
            }
        }
    }
}
