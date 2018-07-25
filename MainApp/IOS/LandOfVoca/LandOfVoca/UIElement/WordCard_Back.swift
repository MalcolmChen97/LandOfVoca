//
//  WordCard_Back.swift
//  LandOfVoca
//
//  Created by ChenZengzhan on 2018-07-24.
//  Copyright © 2018 teamtiger. All rights reserved.
//
import UIKit

class WordCard_Back: WordCard {
    
    public override init(frame: CGRect, aWord : Word, imageName : String) {
        super.init(frame : frame, aWord : aWord, imageName : imageName)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func setUpView(){
        // Set up shapes layer
        layer.cornerRadius = 20
        layer.shadowRadius = 3
        layer.shadowOpacity = 0.4
        layer.shadowOffset = CGSize(width: 0.5, height: 3)
        layer.shadowColor = UIColor.darkGray.cgColor
        clipsToBounds = true
        isUserInteractionEnabled = true
        
        originalPoint = center
        
        // Add panGestureRecongizer
        let panGestureRecognizer = UIPanGestureRecognizer(target: self, action: #selector(self.beingDragged))
        addGestureRecognizer(panGestureRecognizer)
        
        // Set up Background Image
        let backGroundImageView = UIImageView(frame:bounds)
        backGroundImageView.image = UIImage(named:imageName)
        backGroundImageView.contentMode = .scaleAspectFill
        backGroundImageView.clipsToBounds = true;
        addSubview(backGroundImageView)
        
        
        // Set up Word Text Labels
        let labelText = UILabel(frame:CGRect(x: 50, y: 50, width: frame.size.width - 80 , height: 130))
        let attributedText = NSMutableAttributedString(string: representedWord.english!, attributes: [.foregroundColor: UIColor.white,.font:UIFont.boldSystemFont(ofSize: 35)])
        attributedText.append(NSAttributedString(string: "\n"+representedWord.chinese!, attributes: [.foregroundColor: UIColor.white,.font:UIFont.systemFont(ofSize: 23)]))
        labelText.attributedText = attributedText
        labelText.numberOfLines = 2
        labelText.adjustsFontSizeToFitWidth = true
        labelText.minimumScaleFactor = 0.8
        labelText.textAlignment = .center
        addSubview(labelText)
        
        imageViewStatus = UIImageView(frame: CGRect(x: (frame.size.width / 2) - 37.5, y: 25, width: 75, height: 75))
        imageViewStatus.alpha = 0
        addSubview(imageViewStatus)
        
        overLayImage = UIImageView(frame:bounds)
        overLayImage.alpha = 0
        addSubview(overLayImage)
        
    }
    
    
    
}
