//
//  TinderWordViewController.swift
//  LandOfVoca
//
//  Created by ChenZengzhan on 2018-07-20.
//  Copyright © 2018 teamtiger. All rights reserved.
//

import UIKit
import CoreData

class TinderWordViewController: UIViewController {
    // Hard Variables
    let MAX_BUFFER_SIZE = 3
    let SEPERATOR_DISTANCE = 8
    let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    
    // Soft Variables
    var currentIndex = 0
    var currentLoadedCardsArray = [WordCard]()
    var allCardsArray = [WordCard]()
    var allWord = [Word]()
    
    @IBOutlet weak var cardView: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Load Word From Database
        let request : NSFetchRequest<Word> = Word.fetchRequest()
        allWord = loadWords(with:request)
        loadCards()
        addFlip()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
   
    
    func createCard(aWord : Word, imageName : String ) -> WordCard {
        let newCard = WordCard(frame: CGRect(x: 0, y: 0, width: cardView.frame.size.width , height: cardView.frame.size.height - 50) ,aWord:aWord,imageName:imageName)
        newCard.delegate = self
        return newCard
    }
    
    func loadWords(with request : NSFetchRequest<Word>) -> [Word] {
        do{
            return try context.fetch(request)
        }catch{
            print("the error is \(error)")
            return []
        }
    }
    
    func loadCards() {
        
        if allWord.count > 0 {
            let maxCount = (allWord.count > MAX_BUFFER_SIZE) ? MAX_BUFFER_SIZE : allWord.count
            
            for (i,word) in allWord.enumerated() {
                let newCard = createCard(aWord: word, imageName: "word1")
                allCardsArray.append(newCard)
                if i < maxCount {
                    currentLoadedCardsArray.append(newCard)
                    
                }
            }
            
            for (i,_) in currentLoadedCardsArray.enumerated() {
                if i > 0 {
                    cardView.insertSubview(currentLoadedCardsArray[i], belowSubview: currentLoadedCardsArray[i - 1])
                }else {
                    cardView.addSubview(currentLoadedCardsArray[i])
                }
            }
            animateCardAfterSwiping()
            
        }
    }
    
    func animateCardAfterSwiping() {
        
        for (i,card) in currentLoadedCardsArray.enumerated() {
            UIView.animate(withDuration: 0.5, animations: {
                if i == 0 {
                    card.isUserInteractionEnabled = true
                }
                var frame = card.frame
                frame.origin.y = CGFloat(i * self.SEPERATOR_DISTANCE)
                card.frame = frame
            })
        }
    }
    
    
    func removeObjectAndAddNewValues() {
        
        currentLoadedCardsArray.remove(at: 0)
        currentIndex = currentIndex + 1
        
        if (currentIndex + currentLoadedCardsArray.count) < allCardsArray.count {
            let card = allCardsArray[currentIndex + currentLoadedCardsArray.count]
            var frame = card.frame
            frame.origin.y = CGFloat(MAX_BUFFER_SIZE * SEPERATOR_DISTANCE)
            card.frame = frame
            currentLoadedCardsArray.append(card)
            cardView.insertSubview(currentLoadedCardsArray[MAX_BUFFER_SIZE - 1], belowSubview: currentLoadedCardsArray[MAX_BUFFER_SIZE - 2])
        }
        print(currentIndex)
        animateCardAfterSwiping()
    }
    
    func addFlip(){
        
    }
    
}

extension TinderWordViewController : WordCardDelegate{
    
    func cardGoesLeft(card: WordCard) {
        removeObjectAndAddNewValues()
    }
    
    func cardGoesRight(card: WordCard) {
        removeObjectAndAddNewValues()
    }
    
    func currentCardStatus(card: WordCard, distance: CGFloat) {
        
    }
    
    
}
