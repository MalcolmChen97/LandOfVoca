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
    
    //whether flipped
    var flip = false
    var flipView : WordCard?
    
    //UI
    @IBOutlet weak var cardView: UIView!
    @IBOutlet weak var refreshButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Load Word From Database
        let request : NSFetchRequest<Word> = Word.fetchRequest()
        allWord = loadWords(with:request)
        loadCards()
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
        self.refreshButton.alpha = 1
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
        flip = false
        
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
    
    func addToMemo(card : WordCard) {
        let thisWord = card.representedWord!
        let requestForMemo : NSFetchRequest<WordForMemo> = WordForMemo.fetchRequest()
        let predicate = NSPredicate(format: "english CONTAINS[cd] %@",thisWord.english!)
        requestForMemo.predicate = predicate
        var wordArray : [WordForMemo]
        do {
            wordArray = try context.fetch(requestForMemo)
            if (wordArray.count == 0){
                let newMemoWord : WordForMemo = WordForMemo(context: context)
                newMemoWord.chinese = thisWord.chinese!
                newMemoWord.english = thisWord.english!
            }else{
                wordArray[0].numberOfTime += 1
            }
            saveContext()
            
        }catch{
            print("error happens while fetching for wordmemo")
        }
        
    }
    
    func saveContext () {
        if context.hasChanges {
            do {
                try context.save()
            } catch {
                // Replace this implementation with code to handle the error appropriately.
                // fatalError() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development.
                let nserror = error as NSError
                fatalError("Unresolved error \(nserror), \(nserror.userInfo)")
            }
        }
    }
    
    @IBAction func leftButtonPressed(_ sender: Any) {
        if(flip == true){
            flipView?.leftAction()
        }else{
            currentLoadedCardsArray[0].leftAction()
        }
        
    }
    
    @IBAction func middleButtonPressed(_ sender: Any) {
        currentLoadedCardsArray[0].tapAction()
        UIView.animate(withDuration: 0.5) {
            self.refreshButton.alpha = 0
        }
    }
    
    @IBAction func rightButtonPressed(_ sender: Any) {
        if(flip == true){
            flipView?.rightAction()
        }else{
            currentLoadedCardsArray[0].rightAction()
        }
    }
    
}

extension TinderWordViewController : WordCardDelegate{
    
    func beingTapped(card : WordCard) {
        flip = true
        flipView = card
    }
    
    func cardGoesLeft(card : WordCard) {
        removeObjectAndAddNewValues()
        addToMemo(card: card)
    }
    
    func cardGoesRight(card : WordCard) {
        removeObjectAndAddNewValues()
    }
    
    func currentCardStatus(card : WordCard, distance: CGFloat) {
        
    }
    
    
}
