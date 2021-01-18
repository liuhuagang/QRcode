package com.huagang.QRcode.test;

import java.io.IOException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import com.huagang.QRcode.codegen.Codectx.LogoShape;
import com.huagang.QRcode.codegen.qrcode.QrcodeGenerator;
import com.huagang.QRcode.codegen.qrcode.SimpleQrcodeGenerator;

/**
 * TestQrGen2
 *
 * @author Bosco.Liao
 * @since 1.3.1
 */
public class TestQrGen2 {
	
	private static String content = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private QrcodeGenerator generator = new SimpleQrcodeGenerator();

	private String localLogoPath;

	@Before
	public void init() {
		URL url = this.getClass().getClassLoader().getResource("mates/pig.png");
		//URL url = this.getClass().getClassLoader().getResource("mates/AodaCat-3.jpeg");
		this.localLogoPath = url.getFile();
	}

	@Test
	public void gen() throws IOException {
		
		generator.getQrcodeConfig().setWidth(350).setHeight(350)
		.setMasterColor("#5F9EA0")
		.setLogoBorderColor("#FFA07A")
		
		.setLogoShape(LogoShape.CIRCLE);
		generator.setLogo(localLogoPath).generate(content).toFile("D:\\LOGO\\qrcode-circle.png");

	}

}
