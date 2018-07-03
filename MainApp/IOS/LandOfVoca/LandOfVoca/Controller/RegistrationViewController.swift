//
//  RegistrationViewController.swift
//  LandOfVoca
//
//  Created by ChenZengzhan on 2018-07-01.
//  Copyright © 2018 teamtiger. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON
class RegistrationViewController: UIViewController {
    
    @IBOutlet weak var userName: UITextField!
    @IBOutlet weak var email: UITextField!
    @IBOutlet weak var nickName: UITextField!
    @IBOutlet weak var password: UITextField!
    
    let weburl = "http://127.0.0.1:8080/user/register"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func requestByUrl(url: String, parameters: [String : String]) {
        
    }
    
    @IBAction func registerPressed(_ sender: Any) {
        let newUser:[String:String] = ["username":userName.text!,"email":email.text!,"password":password.text!,"nickname":nickName.text!]
        Alamofire.request(weburl, method: .post,parameters: newUser,encoding: JSONEncoding.default).responseJSON{
            haha in
            print(haha)
            if haha.result.isSuccess {
//                let responseJson : JSON = JSON(haha.result.value!)
//                print(responseJson)
            }else{
                print("connection error \(2)")
            }
        }
        self.dismiss(animated: true, completion: nil)
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
