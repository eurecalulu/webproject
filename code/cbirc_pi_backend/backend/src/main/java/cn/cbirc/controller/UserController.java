package cn.cbirc.controller;

import cn.cbirc.config.security.JwtTokenUtils;
import cn.cbirc.model.vo.ResponseVO;
import cn.cbirc.model.vo.UserInfoVO;
import cn.cbirc.model.vo.UserLoginVO;
import cn.cbirc.service.UserService;
import cn.cbirc.util.ResponseUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 用户注册控制类
 */
@Api(tags = "用户模块接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    private static final String NO_LOGIN="未登录";

    @ApiOperation("获取用户数量")
    @GetMapping("/count")
    public ResponseEntity<Integer> getUserCount(){
        return ResponseUtils.success(userService.getUserCount());
    }


    @GetMapping("/info")
    @ApiOperation("查看当前用户信息")
    @ApiResponses({
            @ApiResponse(code = 200,message = "成功",response = UserInfoVO.class),
            @ApiResponse(code = 500,message = "错误·",response = ResponseVO.class)
    })
    public ResponseEntity userInfo(){
        UserInfoVO userInfo = userService.userInfo();
        if(userInfo!=null){
            return ResponseUtils.success(userService.userInfo());
        }else {
            return ResponseUtils.failure(NO_LOGIN);
        }
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public ResponseEntity<ResponseVO> register(@RequestBody UserLoginVO user){
        ResponseVO res=userService.registerUser(user.getName(),user.getPassword());
        if(res.isSuccess()){
            return ResponseUtils.success(res);
        }
        else{
            return ResponseUtils.failure(res);
        }
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseEntity<ResponseVO> login(@RequestBody UserLoginVO user){
        ResponseVO res=userService.login(user.getName(),user.getPassword());
        if(res.isSuccess()){
            String token = res.getMessage();
            HttpHeaders headers = new HttpHeaders();
            headers.add(JwtTokenUtils.TOKEN_NAME,token);
            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,JwtTokenUtils.TOKEN_NAME);
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(ResponseVO.buildSuccess("登陆成功"));
        }else{
            return ResponseUtils.failure(res);
        }
    }


}
