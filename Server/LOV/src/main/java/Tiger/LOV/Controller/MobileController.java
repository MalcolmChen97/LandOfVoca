package Tiger.LOV.Controller;

import Tiger.LOV.Model.User;
import Tiger.LOV.Service.ResponseMsg;
import Tiger.LOV.Service.UserService;
import com.oracle.tools.packager.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class MobileController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/user/login")
    public String haha(){
        return "Greeting to IOS from Spring";
    }

    @PostMapping(path = "/user/register")
    public ResponseMsg createUser(@RequestBody User user){
        try{
            userService.saveUser(user);
            return new ResponseMsg("Sucuess",user,"User "+user.getNickname()+" is created");
        }catch (Exception e){
//            TODO::Need to Handle exception here
//            {
//                "error": {
//                "code": 404,
//                        "message": "ID not found"
//                }
//            }
            return new ResponseMsg("Failed",user,e.getMessage());
        }
    }

    @PostMapping(path = "/user/login")
    public ResponseMsg login(@RequestBody User user){
        User thisUser = userService.auth(user.getUsername(),user.getPassword());
        System.out.println(user.getPassword());
        if (thisUser != null){
            return new ResponseMsg("Sucuess",thisUser,"User "+thisUser.getNickname()+" is logged");
        }else{
            //TODO::Change to another message
            return new ResponseMsg("Failed",thisUser,"no such user");
        }

    }
}
