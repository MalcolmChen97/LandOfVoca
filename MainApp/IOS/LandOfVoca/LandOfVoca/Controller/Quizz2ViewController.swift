//
//  Quizz2ViewController.swift
//  LandOfVoca
//
//  Created by ChenZengzhan on 2018-06-29.
//  Copyright © 2018 teamtiger. All rights reserved.
//

import UIKit

class Quizz2ViewController: UIViewController {
    let allQuestions = Quizz2QuestionBank()
    var pickedAnswer : Bool = false
    var questionNumber : Int = 0
    var score = 0
    @IBOutlet weak var questionLabel: UILabel!
    @IBOutlet weak var progressLabel: UILabel!
    @IBOutlet weak var scoreLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        updateUI()
    }

    @IBAction func answerPressed(_ sender: UIButton) {
        if sender.tag == 1{
            pickedAnswer = true
        }else if sender.tag == 2{
            pickedAnswer = false
        }
        if checkAnswer(){
            score += 1000
        }
        nextQuestion()
    }
    
    func checkAnswer() -> Bool {
        return pickedAnswer == allQuestions.list[questionNumber].answer
    }
    
    func startOver()  {
        questionNumber = 0
        updateUI()
    }
    
    func updateUI()  {
        questionLabel.text = allQuestions.list[questionNumber].questionText
        scoreLabel.text = "Score: " + String(score)
        progressLabel.text = String(questionNumber+1) + "/" + String(allQuestions.list.count)
    }
    
    func nextQuestion() {
        
        
        if questionNumber+1<allQuestions.list.count {
            questionNumber+=1
            updateUI()
        }else{
            updateUI()
            let alert = UIAlertController(title: "Awesome", message: "You'va finished all", preferredStyle: .alert)
            let restartAction = UIAlertAction(title: "再来一把！", style: .default) { (UIAlertAction) in
                self.startOver()
            }
            alert.addAction(restartAction)
            present(alert,animated: true,completion: nil)
        }
       
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
