package com.mrjoke.wechat.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mrjoke.wechat.domain.Material;
import com.mrjoke.wechat.service.IAccessTokenService;
import com.mrjoke.wechat.service.IMaterialService;
import com.mrjoke.wechat.utils.Constants;
import com.mrjoke.wechat.utils.HttpServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/material")
public class MeterialUploadController {
    @Autowired
    @Qualifier("materialService")
    private IMaterialService materialService;
    @Autowired
    @Qualifier("accessTokenService")
    private IAccessTokenService accessTokenService;

    @PostMapping("/upload")
    public String upload(HttpServletRequest request,String user_id, @RequestParam("filename")MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            Material material = new Material();
            String realPath = request.getServletContext().getRealPath(Constants.UPLOAD_ROOT_PATH);
            String originalFilename = file.getOriginalFilename();
            StringBuffer filename = new StringBuffer();
            filename.append(user_id);
            if (originalFilename.endsWith(".jpg") || originalFilename.endsWith(".JPG")){
                filename.append(".jpg");
            }else if (originalFilename.endsWith(".jpeg") || originalFilename.endsWith(".JPEG")){
                filename.append(".jpeg");
            }else if (originalFilename.endsWith(".png") || originalFilename.endsWith(".PNG")){
                filename.append(".png");
            }else{
                request.setAttribute("image_error","图片格式错误……");
                return "uploadMaterial";
            }
            File image = new File(realPath, filename.toString());
            if (!image.getParentFile().exists()){
                image.getParentFile().mkdirs();
            }
            String destPath = realPath + File.separator + filename.toString();
            file.transferTo(new File(destPath));
            material.setUser_id(user_id);
            material.setType("image");
            material.setImage_name(filename.toString());
            materialService.save(material);
            return "redirect:/material/uploadToWechat?user_id=" + user_id;
        }
        request.setAttribute("image_error","请选择图片上传……");
        return "uploadMaterial";
    }

    @GetMapping("/uploadToWechat")
    @ResponseBody
    public Object uploadToWechat(String user_id,HttpServletRequest request){
        System.out.println(user_id);
        Material material = materialService.findByUserId(user_id);
        if (material != null){
            String accessToken = accessTokenService.token();
            String realPath = request.getServletContext().getRealPath(Constants.UPLOAD_ROOT_PATH);
            String filaPath = realPath + File.separator + material.getImage_name();
            String result = HttpServiceUtil.upload(Constants.TEMP_MATERIAL_UPLOAD,accessToken,filaPath,material);
            System.out.println(result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.get("errcode") == null){
                material.setMedia_id((String) jsonObject.get("media_id"));
                material.setCreated_at((String.valueOf(jsonObject.get("created_at"))));
                materialService.update(material);
                return jsonObject;
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("error_upload","上传出错……");
        return map;
    }
}
