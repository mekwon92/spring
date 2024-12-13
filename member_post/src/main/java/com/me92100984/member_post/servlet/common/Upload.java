package com.me92100984.member_post.servlet.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.me92100984.member_post.utils.Commons;
import com.me92100984.member_post.vo.Attach;

@WebServlet("/upload")
public class Upload extends HttpServlet{

	// @Override
	// protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	// 	DiskFileItemFactory factory = new DiskFileItemFactory();
	// 	factory.setSizeThreshold(1024*1024); //인메모리 크기? 버퍼 크기가 1MB
	// 	factory.setRepository(new File(Commons.UPLOAD_PATH, "tmp"));
	// 	//부모 자식 (/는 os마다 달라서 이렇게 하는거 좋음) new File(Commons.UPLOAD_PATH + File.separator + "tmp") 이라고 써야함
	// 	//seperate, out 등은 비초기화 final
	// 	ServletFileUpload upload = new ServletFileUpload(factory);
	// 	List<Attach> attachs = new ArrayList<>();
	// 	try {
	// 		List<FileItem> items = upload.parseRequest(req); 
	// 		for(FileItem item : items) {
	// 			System.out.println(item.getName());
	// 			System.out.println(item.getSize());
				
	// 			String origin = item.getName();
	// 			int dotIdx = origin.lastIndexOf(".");
	// 			String ext = "";
	// 			if(dotIdx != -1) {
	// 				ext = origin.substring(dotIdx);
	// 			}
	// 			String uuid = UUID.randomUUID().toString();
	// 			String realName = uuid + ext;
	// 			String path = getTodayStr();
	// 			File parentPath = new File(Commons.UPLOAD_PATH,path);
	// 			if(!parentPath.exists()) {
	// 				parentPath.mkdirs();
	// 			}
	// 			item.write(new File(parentPath, realName));	
	// 			attachs.add(Attach.builder().uuid(realName).path(path).origin(origin).build());
	// 		}
	// 		System.out.println(attachs);
			
	// 		resp.setContentType("application/json; charset=utf-8");
	// 		resp.getWriter().print(new Gson().toJson(attachs));
			
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}	
	// }
	// public String getTodayStr() {
	// 	return new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
	// }
}
