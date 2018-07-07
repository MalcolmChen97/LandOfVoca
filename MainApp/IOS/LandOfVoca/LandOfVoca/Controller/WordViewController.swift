//
//  WordViewController.swift
//  LandOfVoca
//
//  Created by ChenZengzhan on 2018-07-05.
//  Copyright © 2018 teamtiger. All rights reserved.
//

import UIKit
import CoreData

class WordViewController: UIViewController {

    @IBOutlet weak var cLabel: UILabel!
    @IBOutlet weak var eLabel: UILabel!
    @IBOutlet weak var Card: UIView!
    let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    var wordArray : [Word] = []
    var wordNumber : Int = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let request : NSFetchRequest<Word> = Word.fetchRequest()
        wordArray = loadWords(with: request)
        cLabel!.text = wordArray[wordNumber].chinese
        eLabel!.text = wordArray[wordNumber].english
        //huge bug here because it just load words over and over agagin
        print(wordArray.count)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    @IBAction func panCard(_ sender: UIPanGestureRecognizer) {
        let card = sender.view!
        let point = sender.translation(in: view)
        card.center = CGPoint(x: view.center.x + point.x , y: view.center.y + point.y)
        
        if sender.state == UIGestureRecognizerState.ended{
            
            if card.center.x < 75 {
                //move off to left side
                UIView.animate(withDuration: 0.3, animations: {
                    card.center = CGPoint(x: card.center.x - 200, y: card.center.y + 75)
                    card.alpha = 0
                },completion: {finish in
                    self.setNextWord()
                    card.center = self.view.center
                    card.alpha = 0.85
                })
                
                return
            }else if card.center.x > view.frame.width - 75{
                //move off to right side
                UIView.animate(withDuration: 0.3, animations: {
                    card.center = CGPoint(x: card.center.x + 200, y: card.center.y + 75)
                    card.alpha = 0
                },completion: {finish in
                    self.setNextWord()
                    card.center = self.view.center
                    card.alpha = 0.85
                })
            
                return
            }
            
            //reset to the center
            UIView.animate(withDuration: 0.2, animations: {
                card.center = self.view.center
            })
        }
        
    }
    
    func loadWords(with request : NSFetchRequest<Word>) -> [Word] {
        do{
            return try context.fetch(request)
        }catch{
            print("the error is \(error)")
            return []
        }
    }
    
    func setNextWord() {
        wordNumber += 1
        cLabel!.text = wordArray[wordNumber].chinese
        eLabel!.text = wordArray[wordNumber].english
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
