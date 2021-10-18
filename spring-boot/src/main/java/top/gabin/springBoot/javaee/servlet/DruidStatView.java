package top.gabin.springBoot.javaee.servlet;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "DruidStatView", urlPatterns = "/druid/*",
        initParams = {@WebInitParam(name = "loginUsername", value = "druid"),
                @WebInitParam(name = "loginPassword", value = "druid")})
public class DruidStatView extends StatViewServlet {
    
}
