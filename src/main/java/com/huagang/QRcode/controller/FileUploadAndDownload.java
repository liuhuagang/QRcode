package com.huagang.QRcode.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huagang.QRcode.codegen.qrcode.QrcodeGenerator;
import com.huagang.QRcode.codegen.qrcode.SimpleQrcodeGenerator;

@Controller
public class FileUploadAndDownload {
	private QrcodeGenerator generator = new SimpleQrcodeGenerator();
	private static String commonPath;
	private static String downloadFilePath;
	private static String fileName;
	SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");

	@GetMapping("/upload")
	public String upload() {
		System.out.println("111111111");
		return "uploadA";
	}

	@PostMapping("/uploadFile")
	public String uploadFile(MultipartFile uploadFile, HttpServletRequest req, Model model) {
		String realPath = req.getSession().getServletContext().getRealPath("/uploadFile");
		System.out.println(realPath);
		String format = sd.format(new Date());
		File file = new File(realPath + format);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		String oldName = uploadFile.getOriginalFilename();
		String newName = "123" + oldName.substring(oldName.indexOf("."), oldName.length());
		try {
			uploadFile.transferTo(new File(file, newName));
			String path = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile"
					+ format + "/" + newName;
			commonPath = realPath + format + "/" + newName;
			downloadFilePath = realPath + format + "/";
			fileName = newName;
			model.addAttribute("path", path);
			return "uploadA";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "上传失败";
	}

	@RequestMapping("/download")
	public void Download(HttpServletResponse response, HttpSession session) throws Exception {
		// 设置下载响应头
		response.setHeader("content-disposition", "attachment;fileName" + URLEncoder.encode(fileName, "UTF-8"));
		// 获取文件路径
		boolean success = generator.setLogo(downloadFilePath + fileName).generate("http://www.yxwygs.com/")
				.toFile(downloadFilePath + fileName);
		File file = new File(downloadFilePath, fileName);
		ServletOutputStream outputStream = response.getOutputStream();
		FileUtils.copyFile(file, response.getOutputStream());
	}
}