package com.huagang.QRcode.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.huagang.QRcode.codegen.qrcode.QrcodeGenerator;
import com.huagang.QRcode.codegen.qrcode.QreyesFormat;
import com.huagang.QRcode.codegen.qrcode.SimpleQrcodeGenerator;
import com.huagang.QRcode.codegen.utils.IOUtils;

/**
 * TestQrGen2
 *
 * @author Bosco.Liao
 * @since 1.0.0
 */
public class TestQrGen {

	private static String content = "http://www.yxwygs.com/";

	private QrcodeGenerator generator = new SimpleQrcodeGenerator();

	private String localLogoPath;

	@BeforeEach
	public void init() {
		URL url = this.getClass().getClassLoader().getResource("upload/111.jpg");
		System.out.println(url);
		this.localLogoPath = url.getFile();
	}

	@Test
	public void iniTest() {
		URL url = this.getClass().getClassLoader().getResource("upload/111.jpg");
		System.out.println(url);
		this.localLogoPath = url.getFile();
	}

	@Test
	public void testDefault() throws IOException {
		Assert.assertTrue(generator.generate(content).toFile("D:\\LOGO\\0.png"));
		testLocalLogo();
		testRemoteLogo();
		testCustomConfig();
		testCustomCodeEyes();
	}

	/**
	 * 添加本地logo
	 * 
	 * @throws IOException
	 */
	@Test
	public void testLocalLogo() throws IOException {
		System.out.println(this.localLogoPath);
		boolean success = generator.setLogo(this.localLogoPath).generate(content).toFile("src/main/resources/download/1.jpg");
		Assert.assertTrue(success);
	}

	/**
	 * 添加在线logo
	 * 
	 * @throws IOException
	 */
	@Test
	public void testRemoteLogo() throws IOException {
		generator.setRemoteLogo(
				"http://www.demlution.com/site_media/media/photos/2014/11/06/3JmYoueyyxS4q4FcxcavgJ.jpg");
		Assert.assertTrue(generator.generate("https://www.apple.com/cn/").toFile("D:\\LOGO\\2.png"));
	}

	/**
	 * 自定义二维码配置
	 * 
	 * @throws IOException
	 */
	@Test
	public void testCustomConfig() throws IOException {
		generator.getQrcodeConfig().setBorderSize(2).setPadding(10).setMasterColor("#00BFFF")
				.setLogoBorderColor("#B0C4DE");
		Assert.assertTrue(generator.setLogo(this.localLogoPath).generate(content).toFile("D:\\LOGO\\3.png"));
	}

	/**
	 * 自定义二维码码眼颜色
	 * 
	 * @throws IOException
	 */
	@Test
	public void testCustomCodeEyes() throws IOException {
		generator.getQrcodeConfig().setMasterColor("#778899").setLogoBorderColor("#778899")
				.setCodeEyesPointColor("#BC8F8F").setCodeEyesFormat(QreyesFormat.DR2_BORDER_R_POINT);
		Assert.assertTrue(generator.setLogo(this.localLogoPath).generate(content).toFile("D:\\LOGO\\4.png"));
	}

	/**
	 * 写入输出流
	 * 
	 * @throws IOException
	 */
	@Test
	public void testWriteToStream() throws IOException {
		OutputStream ous = null;
		try {
			ous = new FileOutputStream("D:\\LOGO\\5.png");
			Assert.assertTrue(generator.generate(content).toStream(ous));
		} finally {
			IOUtils.closeQuietly(ous);
		}
	}

	@Test
	public void testGetImage() throws IOException {
		BufferedImage image = generator.generate(content).getImage();
		ImageIO.write(image, "png", new File("D:\\LOGO\\6.png"));
	}

}
