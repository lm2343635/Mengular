package com.xwkj.mengular.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xwkj.mengular.MengularDocument;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String rootPath=getServletConfig().getServletContext().getRealPath("/");
		MengularDocument document=new MengularDocument(rootPath, 1, "blog.html");
//		document.setValue("blog-date", new Date().toString());
//		document.setValue("blog-readers", "10");
//		document.setValue("blog-title", "Mac版网易云音乐解除海外IP限制");
//		document.setValue("blog-content", "打开Finder，Shift＋Command＋G，输入/etc/resolver, 如果没有该路径请输入/etc然后手动创建resolver文件夹（需要管理员密码），创建完毕以后用Textedit新建文本输入 nameserver 45.32.72.192");
//		String outputFilename=UUID.randomUUID().toString()+".html";
//		document.output("output/"+outputFilename, MengularDocument.DEFAULT_TEMPLATE_CHARACTER_ENCODING);
//		response.sendRedirect("output/"+outputFilename);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
