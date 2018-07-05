//
//  LoginViewController.swift
//  LandOfVoca
//
//  Created by ChenZengzhan on 2018-06-25.
//  Copyright © 2018 teamtiger. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON

class LoginViewController: UIViewController {

    @IBOutlet weak var userName: UITextField!
    @IBOutlet weak var password: UITextField!
    
    let weburl = "http://127.0.0.1:8080/user/login"
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func loginPressed(_ sender: UIButton) {
        let newUser:[String:String] = ["username":userName.text!,"password":password.text!]
        Alamofire.request(weburl, method: .post,parameters: newUser,encoding: JSONEncoding.default).responseJSON{
            haha in
            if haha.result.isSuccess {
                print(haha.result.value!)
                // MARK: -parse json
                //                let responseJson : JSON = JSON(haha.result.value!)
                //                print(responseJson)
            }else{
                print("connection error \(2)")
            }
        }
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
