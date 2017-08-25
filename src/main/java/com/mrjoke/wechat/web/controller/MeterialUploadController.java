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

    /**
     * 上传文件到服务器(目前只传图片)
     *
     * @param request http请求
     * @param user_id 素材的拥有者id
     * @param file 素材文件
     * @return 重定向至上传微信服务器
     **/
    @PostMapping("/upload")
    public String upload(HttpServletRequest request,String user_id, @RequestParam("filename")MultipartFile file) throws IOException {
        //判断文件是否为空
        if (!file.isEmpty()){
            Material material = new Material();
            //获取文件上传到的目的地的路径
            String realPath = request.getServletContext().getRealPath(Constants.UPLOAD_ROOT_PATH);
            //获取源文件的名字
            String originalFilename = file.getOriginalFilename();
            StringBuffer filename = new StringBuffer();
            //定义上传后的文件名字
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
            //判断上传到的目的地路径是否存在，不存在则创建
            if (!image.getParentFile().exists()){
                image.getParentFile().mkdirs();
            }
            String destPath = realPath + File.separator + filename.toString();
            //上传文件
            file.transferTo(new File(destPath));
            //设置素材的信息
            material.setUser_id(user_id);
            material.setType("image");
            material.setImage_name(filename.toString());
            //保存到数据库中
            materialService.save(material);
            //重定向至上传微信服务器url
            return "redirect:/material/uploadToWechat?user_id=" + user_id;
        }
        request.setAttribute("image_error","请选择图片上传……");
        return "uploadMaterial";
    }

    /**
     * 将素材上传至微信服务器
     *
     * @param user_id 素材拥有者id
     * @param request http请求
     * @return 返回结果，json数据
     **/
    @GetMapping("/uploadToWechat")
    @ResponseBody
    public Object uploadToWechat(String user_id,HttpServletRequest request){
        //将素材信息查询出来
        Material material = materialService.findByUserId(user_id);
        if (material != null){
            //获取access_token
            String accessToken = accessTokenService.token();
            //获取文件所在服务器的本地路径
            String realPath = request.getServletContext().getRealPath(Constants.UPLOAD_ROOT_PATH);
            String filaPath = realPath + File.separator + material.getImage_name();
            //上传素材至微信服务器
            String result = HttpServiceUtil.upload(Constants.TEMP_MATERIAL_UPLOAD,accessToken,filaPath,material);
            //取得返回结果
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
