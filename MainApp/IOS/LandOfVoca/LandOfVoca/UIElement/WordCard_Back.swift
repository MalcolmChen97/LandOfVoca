//
//  WordCard_Back.swift
//  LandOfVoca
//
//  Created by ChenZengzhan on 2018-07-24.
//  Copyright © 2018 teamtiger. All rights reserved.
//
import UIKit

class WordCard_Back: UIView {
    // Hard Variables
    let THERESOLD_MARGIN = (UIScreen.main.bounds.size.width/2) * 0.75
    let SCALE_STRENGTH : CGFloat = 4
    let SCALE_RANGE : CGFloat = 0.90
    
    // Soft Variables
    var xCenter: CGFloat = 0.0
    var yCenter: CGFloat = 0.0
    var originalPoint = CGPoint.zero
    var imageViewStatus = UIImageView()
    var overLayImage = UIImageView()
    var isRemembered = false
    
    // Word Variables
    var imageName : String!
    var representedWord : Word!
    
    weak var delegate: WordCardDelegate?
    
    
    public init(frame: CGRect, aWord : Word, imageName : String) {
        super.init(frame: frame)
        self.representedWord = aWord
        self.imageName = imageName
        setUpView()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func setUpView(){
        // Set up shapes layer
        layer.cornerRadius = 20
        layer.shadowRadius = 3
        layer.shadowOpacity = 0.4
        layer.shadowOffset = CGSize(width: 0.5, height: 3)
        layer.shadowColor = UIColor.darkGray.cgColor
        clipsToBounds = true
        isUserInteractionEnabled = false
        
        originalPoint = center
        
        // Add panGestureRecongizer
        let panGestureRecognizer = UIPanGestureRecognizer(target: self, action: #selector(self.beingDragged))
        // MARK::Add later in view controller
        //isUserInteractionEnabled = true
        addGestureRecognizer(panGestureRecognizer)
        
        // Set up Background Image
        let backGroundImageView = UIImageView(frame:bounds)
        backGroundImageView.image = UIImage(named:imageName)
        backGroundImageView.contentMode = .scaleAspectFill
        backGroundImageView.clipsToBounds = true;
        addSubview(backGroundImageView)
        
        
        // Set up Word Text Labels
        let labelText = UILabel(frame:CGRect(x: 0, y: 0, width: frame.size.width - 100, height: 80))
        let attributedText = NSMutableAttributedString(string: representedWord.english!, attributes: [.foregroundColor: UIColor.white,.font:UIFont.boldSystemFont(ofSize: 35)])
        attributedText.append(NSAttributedString(string: "\n"+representedWord.chinese!, attributes: [.foregroundColor: UIColor.white,.font:UIFont.systemFont(ofSize: 35)]))
        labelText.attributedText = attributedText
        labelText.numberOfLines = 2
        addSubview(labelText)
        
        imageViewStatus = UIImageView(frame: CGRect(x: (frame.size.width / 2) - 37.5, y: 25, width: 75, height: 75))
        imageViewStatus.alpha = 0
        addSubview(imageViewStatus)
        
        overLayImage = UIImageView(frame:bounds)
        overLayImage.alpha = 0
        addSubview(overLayImage)
        
    }
    
    @objc func beingDragged(_ gestureRecognizer: UIPanGestureRecognizer) {
        // Reminder: gestureRecognizer takes a view and gives the point of finger
        xCenter = gestureRecognizer.translation(in: self).x
        yCenter = gestureRecognizer.translation(in: self).y
        switch gestureRecognizer.state {
            // Keep swiping
        // I dont think this one is needed, lets see
        case .began:
            originalPoint = self.center;
            break
        // in the middle of a swipe
        case .changed:
            let rotationStrength = min(xCenter / UIScreen.main.bounds.size.width, 1)
            let rotationAngel = .pi/8 * rotationStrength
            let scale = max(1 - fabs(rotationStrength) / SCALE_STRENGTH, SCALE_RANGE)
            center = CGPoint(x: originalPoint.x + xCenter, y: originalPoint.y + yCenter)
            let transforms = CGAffineTransform(rotationAngle: rotationAngel)
            let scaleTransform: CGAffineTransform = transforms.scaledBy(x: scale, y: scale)
            self.transform = scaleTransform
            updateOverlay(xCenter)
            break
        // swipe ended
        case .ended:
            afterSwipeAction()
            break
        default:
            break
        }
    }
    
    func updateOverlay(_ distance: CGFloat) {
        
        imageViewStatus.image = distance > 0 ? #imageLiteral(resourceName: "btn_like_pressed") : #imageLiteral(resourceName: "btn_skip_pressed")
        overLayImage.image = distance > 0 ? #imageLiteral(resourceName: "overlay_like") : #imageLiteral(resourceName: "overlay_skip")
        imageViewStatus.alpha = min(fabs(distance) / 100, 0.5)
        overLayImage.alpha = min(fabs(distance) / 100, 0.5)
        delegate?.currentCardStatus(card: self, distance: distance)
    }
    
    func afterSwipeAction() {
        
        if xCenter > THERESOLD_MARGIN {
            rightAction()
        }
        else if xCenter < -THERESOLD_MARGIN {
            leftAction()
        }
        else {
            //reseting image
            UIView.animate(withDuration: 0.5, delay: 0.0, usingSpringWithDamping: 0.5, initialSpringVelocity: 1.0, options: [], animations: {
                self.center = self.originalPoint
                //stop here and need to keep working
                
                self.transform = CGAffineTransform(rotationAngle: 0)
                self.imageViewStatus.alpha = 0
                self.overLayImage.alpha = 0
                self.delegate?.currentCardStatus(card: self, distance:0)
            })
        }
    }
    
    func rightAction() {
        
        let finishPoint = CGPoint(x: frame.size.width*2, y: 2 * yCenter + originalPoint.y)
        UIView.animate(withDuration: 0.5, animations: {
            self.center = finishPoint
        }, completion: {(_) in
            self.removeFromSuperview()
        })
        isRemembered = true
        delegate?.cardGoesRight(card: self)
        print("Remembered")
    }
    
    func leftAction() {
        
        let finishPoint = CGPoint(x: -frame.size.width*2, y: 2 * yCenter + originalPoint.y)
        UIView.animate(withDuration: 0.5, animations: {
            self.center = finishPoint
        }, completion: {(_) in
            self.removeFromSuperview()
        })
        isRemembered = false
        delegate?.cardGoesLeft(card: self)
        print("Saved For Later Use")
    }
    
}
