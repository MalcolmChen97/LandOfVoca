package Tiger.LOV.Controller;

import Tiger.LOV.Model.User;
import Tiger.LOV.Service.ResponseMsg;
import Tiger.LOV.Service.UserService;
//import com.oracle.tools.packager.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Boolean> createUser(@RequestBody User user){
        try{
            userService.saveUser(user);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Message","Seccuess"+user+"User "+user.getNickname()+" is created");
            return new ResponseEntity<Boolean>(true,httpHeaders,HttpStatus.OK);
        }catch (Exception e){
//            TODO::Need to Handle exception here
//            {
//                "error": {
////                "code": 404,
////                        "message": "ID not found"
////                }
//            }

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Error ","Failed to register");
            return new ResponseEntity<Boolean>(false,httpHeaders,HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping(path = "/user/login")
    public  ResponseEntity<Boolean> login(@RequestBody User user){
        User thisUser = userService.auth(user.getUsername(),user.getPassword());
        System.out.println(user.getPassword());
        if (thisUser != null){

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Message","User "+thisUser.getNickname()+" is logged");
            return new ResponseEntity<Boolean>(true,httpHeaders,HttpStatus.OK);
        }else{
            //TODO::Change to another message
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Error ","Failed to login");
            return new ResponseEntity<Boolean>(false,httpHeaders,HttpStatus.BAD_REQUEST);
        }

    }
}
