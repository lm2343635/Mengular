package org.fczm.mengular.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fczm.mengular.MengularDocument;

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
		
		List<Map<String, String>> comments=new ArrayList<>();
		Map<String, String> comment=new HashMap<>();
		comment.put("cid", "5b0a0bf2541345d4015437e3c2f40021");
		comment.put("name", "李大爷");
		comment.put("date", "2016-05-01 16:22");
		comment.put("content", "非常好的一篇文章！！！");
		comments.add(comment);
		comments.add(comment);
		comments.add(comment);
		document.setLoop("comment-list", comments);
		
		List<Map<String, String>> friends=new ArrayList<>();
		Map<String, String> friend=new HashMap<>();
		friend.put("fid", "297e9e79546a454d01546b2b86430214");
		friend.put("name", "张三");
		friend.put("age", "20");
		friends.add(friend);
		friends.add(friend);
		friends.add(friend);
		document.setLoop("friend-list", friends);
		
		document.setValue("blog-date", new Date().toString());
		document.setValue("blog-readers", "10");
		document.setValue("blog-title", "Mac版网易云音乐解除海外IP限制");
		document.setValue("blog-content", "打开Finder，Shift＋Command＋G，输入/etc/resolver, 如果没有该路径请输入/etc然后手动创建resolver文件夹（需要管理员密码），创建完毕以后用Textedit新建文本输入 nameserver 45.32.72.192");
		String outputFilename=UUID.randomUUID().toString()+".html";
		document.output("output/"+outputFilename, MengularDocument.DEFAULT_TEMPLATE_CHARACTER_ENCODING);
		response.getWriter().println("<a href='output/"+outputFilename+"' target='_blank'>Open Blog</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
