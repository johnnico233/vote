package vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vote.domain.FollowUser;
import vote.domain.User;
import vote.result.Result;
import vote.result.ResultCode;
import vote.service.UserService;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Value("${avatar_path_tmp}")
    private String avatarPathTmp;
    @Autowired
    private UserService userService;
    @RequestMapping("/{id}")
    public String getUserOverview(HttpSession httpSession, Model model,@PathVariable int id){
        User user=userService.getUserOverviewInfo(id);
        List<FollowUser> followUsers=userService.getFollowList(id,0,5);
        if(user!=null){
            model.addAttribute("userInfo",user);
            model.addAttribute("infoType","overview");
            model.addAttribute("followList",followUsers);
            model.addAttribute("title","总览");
            return "user";
        }
        return "home";
    }
    @RequestMapping("/{id}/userInfo")
    public String getUserConcreteInfo(HttpSession httpSession,Model model,@PathVariable int id){
        User user=userService.getUserOverviewInfo(id);
        if(user!=null){
            model.addAttribute("userInfo",user);
            model.addAttribute("infoType","userInfo");
            model.addAttribute("title","我的信息");
            return "user";
        }else{
            return "home";
        }
    }
    @RequestMapping(value = "/{id}/uploadFile",method = RequestMethod.POST)
    public @ResponseBody Result uploadPhoto(@PathVariable int id,@RequestParam("file") MultipartFile multipartFile){
        if(multipartFile!=null){
            String path=multipartFile.getOriginalFilename();
            if(!path.endsWith(".png")&&!path.endsWith(".jpg")&&!path.endsWith(".jpeg"))
                return new Result(ResultCode.FAILED,"文件不符,只能上传png,jpg,jpeg格式");
            else{
                File file=new File(avatarPathTmp+new Date().getTime()+id+path.substring(path.lastIndexOf(".")));
                try(BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(file))){
                    InputStream inputStream=multipartFile.getInputStream();
                    byte[] bytes=new byte[1024];
                    while(inputStream.read(bytes)!=-1){
                        outputStream.write(bytes);
                    }
                    inputStream.close();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
                return new Result(ResultCode.SUCCESS,"上传成功",file.getName());
            }
        }else{
            return new Result(ResultCode.FAILED,"文件不存在");
        }
    }
    @RequestMapping(value = "/{id}/updateUserInfo",method = RequestMethod.POST)
    public @ResponseBody Result updateUserInfo(@PathVariable int id,@RequestBody User user){
        //wait for modify~
        return new Result(ResultCode.SUCCESS,"");
    }

    @RequestMapping("/**")
    public String getUrl(HttpSession httpSession,Model model){
        if(httpSession.getAttribute("userID")!=null){
            return getUserOverview(httpSession,model,(int)httpSession.getAttribute("userID"));
        }
        return "home";
    }
}
