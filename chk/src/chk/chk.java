package chk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class chk {
@SuppressWarnings("resource")
public static void main(String args[]){
	
	File f1=new File ("F:/Saran/Work/Java Libraries/Selenium Lib/WedDriver/chromedriver.exe");
	System.setProperty("webdriver.chrome.driver", f1.getAbsolutePath());
	File f2=new File("F:/Saran/Work/Datasheet.xls");
	try {
		FileInputStream FI=new FileInputStream(f2);
		try {
			HSSFWorkbook Workbuk = new HSSFWorkbook(FI);
			HSSFSheet Worksheet = Workbuk.getSheet("Sheet1");
			HSSFRow Row = Worksheet.getRow(1);
			//HSSFCell Username = Row.getCell(0);
			HSSFCell Username = Row.getCell(0);
			HSSFCell Password = Row.getCell(1);
			System.out.println(Username+"  "+ Password);
			Integer Username1 = (int) Double.parseDouble(Username.toString());
			String Password1 = Password.toString();
			WebDriver chromeWD = new ChromeDriver();
			chromeWD.get("https://www.ultimatix.net");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			chromeWD.findElement(By.xpath("//*[@id='USER']")).sendKeys(Username1.toString());
			
			chromeWD.findElement(By.xpath("//*[@id='PASSWORD']")).sendKeys(Password1);
			chromeWD.findElement(By.xpath("//*[@id='login_button']")).click();
			
			String Name = chromeWD.findElement(By.xpath("/html/body/div/div/div[1]/table/tbody/tr[2]/td/table/tbody/tr/td[3]/div/div[1]/div[2]/div[1]")).getText();
			System.out.println("Name Fetched : "+Name);
			if (!Name.equals("Welcome Karthika Selvaraj (351729)")){
				System.out.println("String Match Failed");
			}else{
				System.out.println("String Matched");
				chromeWD.findElement(By.xpath("//*[@id='myFavorites']/div/div/a")).click();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			ArrayList <String> tabs =new ArrayList <String>(chromeWD.getWindowHandles());
			chromeWD.switchTo().window(tabs.get(1));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chromeWD.findElement(By.xpath("//*[@id='frmPortal:PayDataTable:0:JUL']")).click();
			chromeWD.close();
			chromeWD.switchTo().window(tabs.get(0));
			}
			chromeWD.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}	
}
