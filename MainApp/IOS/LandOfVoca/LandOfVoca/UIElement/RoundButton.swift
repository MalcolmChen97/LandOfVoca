//
//  RoundButton.swift
//  LandOfVoca
//
//  Created by ChenZengzhan on 2018-07-18.
//  Copyright © 2018 teamtiger. All rights reserved.
//

import UIKit

@IBDesignable
class RoundButton: UIButton {

    @IBInspectable var cornerRadius : CGFloat = 0 {
        didSet{
            self.layer.cornerRadius = cornerRadius
        }
    }
    
    @IBInspectable var boarderWidth : CGFloat = 0 {
        didSet{
            self.layer.borderWidth = boarderWidth
        }
    }
    
    @IBInspectable var boadrdColor : UIColor = UIColor.clear {
        didSet{
            self.layer.borderColor = boadrdColor.cgColor
        }
    }
}
