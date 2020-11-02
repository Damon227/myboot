package com.example.myboot2.interceptor;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.example.myboot2.aop.FileType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * @author YUANCHENGMAN
 * @date 2020-10-30
 */
@Component
public class FileTypeInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request instanceof MultipartHttpServletRequest) {
            boolean hasAnnotation = handler.getClass().isAssignableFrom(HandlerMethod.class);
            if (!hasAnnotation) {
                return super.preHandle(request, response, handler);
            }

            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                FileType fileType = handlerMethod.getMethodAnnotation(FileType.class);
                if (fileType != null) {
                    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                    Map<String, MultipartFile> files = multipartRequest.getFileMap();
                    Iterator<String> iterator = files.keySet().iterator();
                    while (iterator.hasNext()) {
                        String formKey = iterator.next();
                        MultipartFile multipartFile = multipartRequest.getFile(formKey);
                        File file = this.multipartFileToFile(multipartFile);
                        String type = FileTypeUtil.getType(file);

                        String[] fileTypes = fileType.value();
                        if (Arrays.stream(fileTypes).noneMatch(t -> t.equals(type))) {
                            // 格式错误，直接返回
                            handleJson(response, "文件格式错误");
                            return false;
                        }
                    }
                }
            }

            return true;
        }

        return true;
    }

    private static <T> void handleJson(HttpServletResponse response, Object result) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(JSONUtil.toJsonStr(result));
        writer.close();
        response.flushBuffer();
    }

    private File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    private void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
