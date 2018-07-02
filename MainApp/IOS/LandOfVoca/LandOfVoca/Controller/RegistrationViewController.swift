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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let weburl = "http://127.0.0.1:8080/hello"
        Alamofire.request(weburl, method: .get).responseJSON{
            haha in
            if haha.result.isSuccess {
                let responseJson : JSON = JSON(haha.result.value!)
                print(responseJson)
            }else{
                print("connection error \(2)")
            }
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func requestByUrl(url: String, parameters: [String : String]) {
        
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
