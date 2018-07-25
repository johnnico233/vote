package vote.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vote.domain.DeleteFollowInfo;
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
    public String getUserOverview(HttpSession httpSession, Model model, @PathVariable int id) {
        User user = userService.getUserOverviewInfo(id);
        List<FollowUser> followUsers = userService.getFollowList(id, 0, 5);
        if (user != null) {
            model.addAttribute("userInfo", user);
            model.addAttribute("infoType", "overview");
            model.addAttribute("followList", followUsers);
            model.addAttribute("title", "总览");
            return "user";
        }
        return "home";
    }

    @RequestMapping("/{id}/userInfo")
    public String getUserConcreteInfo(HttpSession httpSession, Model model, @PathVariable int id) {
        User user = userService.getUserOverviewInfo(id);
        if (user != null) {
            model.addAttribute("userInfo", user);
            model.addAttribute("infoType", "userInfo");
            model.addAttribute("title", "我的信息");
            return "user";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "/{id}/uploadFile", method = RequestMethod.POST)
    public @ResponseBody
    Result uploadPhoto(@PathVariable int id, @RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile != null) {
            String path = multipartFile.getOriginalFilename();
            if (!path.endsWith(".png") && !path.endsWith(".jpg") && !path.endsWith(".jpeg"))
                return new Result(ResultCode.FAILED, "文件不符,只能上传png,jpg,jpeg格式");
            else {
                File file = new File(avatarPathTmp + new Date().getTime() + id + path.substring(path.lastIndexOf(".")));
                try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                    InputStream inputStream = multipartFile.getInputStream();
                    byte[] bytes = new byte[1024];
                    while (inputStream.read(bytes) != -1) {
                        outputStream.write(bytes);
                    }
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return new Result(ResultCode.SUCCESS, "上传成功", file.getName());
            }
        } else {
            return new Result(ResultCode.FAILED, "文件不存在");
        }
    }

    @RequestMapping(value = "/{id}/updateUserInfo", method = RequestMethod.POST)
    public @ResponseBody
    Result updateUserInfo(@PathVariable int id, @RequestBody User user) {
        //wait for modify~
        ResultCode resultCode = userService.updateUserInfo(user);
        return new Result(resultCode, resultCode == ResultCode.SUCCESS ? "修改成功" :
                (resultCode == ResultCode.UPDATE_USER_INFO_FAILED) ? "更新用户信息失败" : "更新用户头像失败");
    }

    @RequestMapping("/**")
    public String getUrl(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("userID") != null) {
            return getUserOverview(httpSession, model, (int) httpSession.getAttribute("userID"));
        }
        return "home";
    }

    @RequestMapping(value = "/checkNameExistExceptMe", method = RequestMethod.POST)
    public @ResponseBody
    Result checkNameExistExceptMe(@RequestBody User user) {
        ResultCode code = userService.checkUsernameNotExistExceptMe(user) ? ResultCode.SUCCESS : ResultCode.USERNAME_EXIST;
        return new Result(code, code == ResultCode.SUCCESS ? "" : "用户名已存在");
    }

    @RequestMapping(value = "/checkPhoneExistExceptMe", method = RequestMethod.POST)
    public @ResponseBody
    Result checkPhoneExistExceptMe(@RequestBody User user) {
        System.out.println(user);
        ResultCode code = userService.checkPhoneNotExistExceptMe(user) ? ResultCode.SUCCESS : ResultCode.PHONE_EXIST;
        return new Result(code, code == ResultCode.SUCCESS ? "" : "电话号码已存在");
    }

    @RequestMapping(value = "/checkEmailExistExceptMe", method = RequestMethod.POST)
    public @ResponseBody
    Result checkEmailExistExceptMe(@RequestBody User user) {
        System.out.println(user);
        ResultCode code = userService.checkEmailNotExistExceptMe(user) ? ResultCode.SUCCESS : ResultCode.EMAIL_EXIST;
        return new Result(code, code == ResultCode.SUCCESS ? "" : "Email已存在");
    }

    @RequestMapping(value = "/checkInfoExceptMe", method = RequestMethod.POST)
    public @ResponseBody
    Result checkInfoExceptMe(@RequestBody User user) {
        ResultCode code = userService.checkUserInfoNotExistExceptMe(user);
        String errorWord = "";
        if (code == ResultCode.EMAIL_EXIST)
            errorWord = "Email已经存在";
        else if (code == ResultCode.PHONE_EXIST)
            errorWord = "电话号码已经存在";
        else if (code == ResultCode.USERNAME_EXIST)
            errorWord = "用户名已存在";
        return new Result(code, errorWord);
    }

    @RequestMapping(value = "/{id}/followList")
    public String getFollowList(HttpSession httpSession, Model model, @PathVariable int id,
                                @RequestParam(required = false) String idx) {
        int index = 1;
        if (idx != null)
            index = Integer.valueOf(idx);
        List<FollowUser> followUsers = userService.getFollowList(id, (index - 1) * 5, 5);
        model.addAttribute("infoType", "followList");
        model.addAttribute("followList", followUsers);
        model.addAttribute("title", "关注列表");
        model.addAttribute("index", index);
        int total = userService.getFollowUserCount(id);
        total = Integer.valueOf(((total - 1) / 5)) + 1;
        model.addAttribute("total", total);
        return "user";
    }

    @RequestMapping(value = "/follow/delete", method = RequestMethod.POST)
    public @ResponseBody
    Result deleteFollowUser(@RequestBody DeleteFollowInfo info) {
        int result = userService.deleteFollowUser(info);
        return new Result(result == 1 ? ResultCode.SUCCESS : ResultCode.DELETE_FAILED, result == 1 ? "取消关注成功" : "取消关注失败");
    }
}
