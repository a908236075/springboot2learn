package com.xiaobo.springboot2.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.regex.Matcher;

@RestController
public class PathController {


    public static final Logger log = LoggerFactory.getLogger(PathController.class);

    @GetMapping("/getPath")
    public void dynGetPath() {
        String sysSh = "/topsa/script/sysstatus.sh";
        log.info("=====================获取全网态势报表中cpu使用率!!!!!=============================");
        Process pro = null;
        Runtime r = Runtime.getRuntime();
        //获取项目跟路径
//        String path = System.getProperty("user.dir");
        // 动态的获取路径  sysSh=/topsa/script/sysstatus.sh
        String curPath = System.getProperty("user.dir");
//        String curPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        log.info("获取当前目录 curPath: " + curPath);
        String[] split = curPath.split(Matcher.quoteReplacement(File.separator));
//        String[] split = curPath.split(File.separator);
        String rootPath = "";
        if (split.length > 1) {
            rootPath = File.separator + split[1];
        }
        log.info("rootPath: " + rootPath);
        // find /topsa -path '*/topsa/script/sysstatus.sh'
        String pathCom = "find " + rootPath + " -path '*" + sysSh + "'";
        log.info("拼接的执行命令为: " + pathCom);
        String[] commandArr=new String[]{"sh","-c",pathCom};
        try {
            pro = r.exec(commandArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            sysSh = sb.toString();
            log.info("================result=======动态获取的路劲为: " + sysSh + "=============result==========");
            in.close();
            pro.destroy();
        } catch (Exception e) {
            log.error("动态获取路径信息发生异常: " + e.getMessage());
        }
        Process pro2 = null;
        Runtime r2 = Runtime.getRuntime();
        try {
            pro2 = r2.exec(sysSh);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro2.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            String result = sb.toString();
            log.info("cup使用率相关的result 为: " + result);
            in.close();
            pro2.destroy();
        } catch (Exception e) {
            log.error("获取信息发生Exception: " + e.getMessage());
        }

    }

    @GetMapping("/exeRuntime")
    public String exeRuntime() {
        String pathCom = " find /home -path '*/topsa/script/sysstatus.sh'";
        log.info("拼接的执行命令为: " + pathCom);
        String[] commandArr=new String[]{"sh","-c",pathCom};
        try {
            Process pro = null;
            Runtime r = Runtime.getRuntime();
            pro = r.exec(commandArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            String sysSh = sb.toString();
            log.info("================result=======find 动态获取的路劲为: " + sysSh + "=============result==========");
            in.close();
            pro.destroy();
        } catch (Exception e) {
            log.error("动态获取路径信息发生异常: " + e.getMessage());
        }

        log.info("=============第二条语句=========");

        String pathCom2 = "cd /";
        String pathCom3 = "pwd";
//        String[] commandArr = new String[]{pathCom2, pathCom3};
        String[] commandArr2=new String[]{"sh","-c",pathCom3};
        try {
            Process pro2 = null;
            Runtime r2 = Runtime.getRuntime();
            r2.exec(commandArr2);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro2.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            String sysSh = sb.toString();
            log.info("================第二个语句的结果为: " + sysSh + "=============result==========");
            in.close();
            pro2.destroy();
        } catch (Exception e) {
            log.error("动态获取路径信息发生异常: " + e.getMessage());
        }
        return "OK!";
    }

}
