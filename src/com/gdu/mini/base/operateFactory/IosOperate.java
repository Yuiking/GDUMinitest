package com.gdu.mini.base.operateFactory;

import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gdu.mini.utils.Log;
import com.gdu.mini.utils.Sleep;



public class IosOperate extends AppOperate{
	
	private static IOSDriver driver;
	
	public IosOperate(IOSDriver driver) {
		super(driver);
		this.driver=driver;
	}

	
	/**
	 * 模拟向左滑动
	 */
	@Override
	public boolean swipeToLeft() {
		Log.logInfo("模拟向左滑动");
		int width=((AppiumDriver)driver).manage().window().getSize().width;
		int height=((AppiumDriver)driver).manage().window().getSize().height;
		try{
		((AppiumDriver)driver).swipe(width * 8 / 10, height* 5/ 10, width * 2/ 10, height *5/ 10, 1000);
		}catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * 模拟向右滑动
	 */
	@Override
	public boolean swipeToRight() {
		Log.logInfo("模拟向右滑动");
		int width=((AppiumDriver)driver).manage().window().getSize().width;
		int height=((AppiumDriver)driver).manage().window().getSize().height;
		try{
		((AppiumDriver)driver).swipe(width * 2 / 10, height* 5/ 10, width * 8/ 10, height *5/ 10, 1000);
		}catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	
	
	@Override
	public void acceptAlert() {
		Log.logInfo("accept alerts");
		
	}
	/**
     * Wait for element display
     * 模拟等待目标显示,在原生方法执行时添加日志。
     *
     * @param TargetText input log text.
     * @param element    the element wait for display.
     * @return boolean
     */
	@Override
	public Boolean IdentifyIsDisplay(String[] TargetText, WebElement element) {
		Log.logInfo("等待某个场景显示");
		return element.isDisplayed();
	}

	@Override
	public void backToHomePage() {
		// TODO Auto-generated method stub
			while(true)
				{
				closeH5();
		//			Log.logInfo(elementExists(3, By.name("新个人中心")));
//					if(elementExists(3, By.name("新个人中心")))
//					{
//						Log.logInfo("找到［个人中心］，屏幕向左滑动回到主屏");
//						swipeToLeft();
//					}
					List<WebElement> listelements = driver.findElements(By.xpath("//UIAButton[contains(@name,'PA')]"));
					Log.logInfo("当前页面插件个数为："+listelements.size());
					if(listelements.size()>5)
					{
						Log.logInfo("在右屏，屏幕向右滑动回到主屏");
						swipeToRight();
					}else if(listelements.size()==0)
					{
						Log.logInfo("在左屏，屏幕向左滑动回到主屏");
						swipeToLeft();
					}
					
//					if(elementExists(3, By.name("exchangeButton")))
//					{
//						Log.logInfo("找到［切换模式图标］，屏幕向右滑动回到主屏");
//						swipeToRight();
//					}
					if(elementExists(3, By.name("回到主屏")))
					{
						Log.logInfo("找到［回到主屏］，点击回到主屏");
						driver.findElementByName("回到主屏").click();
						
					}	
					if(elementExists(5, By.name("无用户体系")))
					{
							Log.logInfo("点击无用户体系，恢复用户体系状态");
							driver.findElementByName("无用户体系").click();
							break;
					}
				}			
		}
	/**
	 * 模拟向上滑屏
	 */
	@Override
	public void scrollToUp(String TargetText) {
		// TODO Auto-generated method stub
		int width=driver.manage().window().getSize().width;
		int height=driver.manage().window().getSize().height;
		Log.logStep("向上滑动屏幕以发现［"+TargetText+"]");
		while(true)
		{
			if(waitForText(5, new String[]{TargetText}))
			{
				break;
			}
			driver.swipe(width / 2, height * 8 / 10, width / 2, height * 9 / 10, 1000);
		}
	}

	@Override
	public void scrollToDown(String TargetText) {
		int width=driver.manage().window().getSize().width;
		int height=driver.manage().window().getSize().height;
		Log.logStep("向下滑动屏幕以发现［"+TargetText+"]");
		while(true)
		{
			if(waitForText(5, new String[]{TargetText}))
			{
				break;
			}
			driver.swipe(width / 2, height * 9 / 10, width / 2, height * 8 / 10, 1000);
		}
		
	}

	@Override
	public void hideKeyboard()
	{
		((AppiumDriver) driver).findElementByName("完成").click();
	}


	@Override
	public void closeH5() {
		// TODO Auto-generated method stub
		if (waitForText(2, "htmlbackhome")) {
			Sleep.sleep(2);
			Log.logInfo("进入点击htmlbackhome状态...");
			try {
				click(driver.findElement(By.name("htmlbackhome")), "点击关闭");
			} catch (Exception e) {
				// TODO: handle exception
				Log.logError(e.getMessage());
			}
			Sleep.sleep(1);
			if (waitForText(2, "htmlbackhome")) {
				click(driver.findElement(By.name("htmlbackhome")), "点击关闭");
			}
		}
		if (waitForText(2, "closeButton")) {
			click(driver.findElement(By.name("closeButton")), "点击关闭");
//			Sleep.sleep(1);
//			if (appOperate.waitForText(2, "closeButton")) {
//				appOperate.click(driver.findElement(By.name("closeButton")), "点击关闭");
//				}
		}
		Sleep.sleep(2);
	}


	@Override
	public void checkH5() {
		// TODO Auto-generated method stub
		while(true)
		{
			if (waitForText(3, "loading")) {
				Sleep.sleep(10);
				if (waitForText(3, "网络无法连接")) {
					click(driver.findElement(By.name("网络无法连接")), "点击尝试重连网络");
					Sleep.sleep(10);
				}else
				{
					break;
				}
			}
		}
	}
	
	
}
