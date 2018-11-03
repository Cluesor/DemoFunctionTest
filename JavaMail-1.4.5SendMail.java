package Cluesor;

import com.sun.mail.util.MailSSLSocketFactory;

import java.io.IOException;
//import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/SendMailServlet")
public class SendMailServlet extends HttpServlet {



    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String usermail = request.getParameter("usermail");

try {
        Properties props = new Properties();
// 开启debug调试，以便在控制台查看
              props.setProperty("mail.debug", "true");
 //设置邮件服务器主机名
              props.setProperty("mail.host", "smtp.qq.com");
// 发送服务器需要身份验证
              props.setProperty("mail.smtp.auth", "true");
// 发送邮件协议名称
              props.setProperty("mail.transport.protocol", "smtp");
// 开启SSL加密，否则会失败
              props.setProperty("mail.smtp.socketFactory.fallback", "false");
              MailSSLSocketFactory sf = new MailSSLSocketFactory();
              sf.setTrustAllHosts(true);
              props.put("mail.smtp.ssl.enable", "true");

              props.put("mail.smtp.ssl.socketFactory", sf);


    // 创建验证器
    Authenticator auth = new Authenticator() {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("2391534083@qq.com", "iikzzkttjdvgdija");//注册邮箱的帐号和授权码
        }
    };


////创建session
    Session session = Session.getInstance(props,auth);

// 2.创建一个Message邮件对象，它相当于是邮件内容
        MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress("2391534083@qq.com"));// 设置发送者

        message.setRecipient(Message.RecipientType.TO, new InternetAddress(usermail)); // 设置发送方式与接收者

        message.setSubject("用户激活","UTF-8");
        message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

        message.setContent("E-Mail From Java", "text/html;charset=utf-8");

// 3.通过session创建 Transport用于将邮件发送
    // 得到transport对象
    Transport ts = session.getTransport();
// 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
    ts.connect("smtp.qq.com","2391534083@qq.com", "iikzzkttjdvgdija");
    ts.sendMessage(message,message.getAllRecipients());
    ts.close();

}catch (GeneralSecurityException e){
    e.printStackTrace();
}catch (MessagingException e){
    e.printStackTrace();
}
