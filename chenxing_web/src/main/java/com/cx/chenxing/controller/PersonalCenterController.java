package com.cx.chenxing.controller;

import com.cx.chenxing.user.UserService;
import com.cx.chenxing.user.result.UserBean;
import com.cx.chenxing.utils.NormalUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * 个人中心相关
 */
@Controller
@RequestMapping("/personalcenter")
public class PersonalCenterController {

    @Resource
    private UserService userService;

    /**
     * 个人档案首页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        UserBean user= (UserBean) session.getAttribute("user");
        if(user != null){
            UserBean users = userService.selectByPrimaryKey(user.getId());
            model.addAttribute("user", users);
        }else{
            model.addAttribute("messge", "登录超时，请重新登陆！");
            return "redirect:/index";
        }
        return "frontpages/personalcenter";
    }


    /**
     * 修改头像
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/modifypic")
    public String modifyPic(HttpServletRequest request, Model model){
        MultipartHttpServletRequest reques = (MultipartHttpServletRequest) request;
        HttpSession session = request.getSession();
        UserBean user= (UserBean) session.getAttribute("user");
        if(user != null){
            UserBean u = userService.selectByPrimaryKey(user.getId());
            String[] types = {"JPG", "PNG", "JPEG", "GIF", "JEPG"};
            try {
                String[] newfileName = NormalUtils.syqpicdownUrl(reques, "picurl",u.getImg());
                if (newfileName != null) {
                    //判断图片类型
                    if (!Arrays.asList(types).contains(newfileName[0].substring(newfileName[0].lastIndexOf(".")+1).toUpperCase())) {
                        model.addAttribute("messge", "照片格式只支持png、jpg、jpeg、gif！");
                        return "redirect:/personalcenter/index";
                    }
                    u.setImg(newfileName[1]);
                } else {
                    model.addAttribute("messge", "发布信息失败，请上传您的照片！");
                    return "redirect:/personalcenter/index";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            userService.updateSelective(u);
        }else{
            model.addAttribute("messge", "登录超时，请重新登陆！");
            return "redirect:/index";
        }

        return "redirect:/personalcenter/index";
    }
}
