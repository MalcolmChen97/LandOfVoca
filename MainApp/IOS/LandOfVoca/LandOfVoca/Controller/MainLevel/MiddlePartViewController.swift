//
//  MiddlePartViewController.swift
//  LandOfVoca
//
//  Created by ChenZengzhan on 2018-07-11.
//  Copyright © 2018 teamtiger. All rights reserved.
//

import UIKit

class MiddlePartViewController: UIViewController {

    
    @IBOutlet weak var learnButton: UIButton!
    @IBOutlet weak var PkButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        learnButton.layer.cornerRadius = learnButton.frame.width / 2
        learnButton.layer.masksToBounds = true
        PkButton.layer.cornerRadius = learnButton.frame.width / 2
        PkButton.layer.masksToBounds = true
        // Do any additional setup after loading the view.
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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
