package com.cx.chenxing.utils;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;

public class PicUploadUtil {

    public static String uploadPic(MultipartHttpServletRequest request) throws IOException {
        MultipartFile multipartFile = request.getFile("picurl");
        String serverFilePatn = "";
        if (null != multipartFile) {
            /**
             * 项目服务器地址路径
             */
            String projectServerPath = request.getScheme() + "://"+request.getServerName()+":" +
                    request.getServerPort() + request.getContextPath() + "/upload/";
            /**
             * 上传文件绝对路径
             */
            String path = request.getSession().getServletContext().getRealPath("/upload/");
            /**
             * 上传文件名
             */
            String fileName = System.currentTimeMillis()+".jpg";

            File file = new File(path + fileName);
            /**
             * 判断路径是否存在，如果不存在就创建一个
             */
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            /**
             * 创建文件
             */
            multipartFile.transferTo(new File(new File(path).getAbsolutePath() + File.separator + fileName));
            /**
             * 返回服务器文件地址
             */
            serverFilePatn = projectServerPath + fileName;

        }
        return serverFilePatn;
    }
}
