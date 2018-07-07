//
//  Quizz2QuestionBank.swift
//  LandOfVoca
//
//  Created by ChenZengzhan on 2018-06-29.
//  Copyright © 2018 teamtiger. All rights reserved.
//

import Foundation

class Quizz2QuestionBank{
    var list = [Quizz2Question]()
    //this should be on backend
    init(){
        let item = Quizz2Question(text:"我很渴，想喝Water",correctAnswer:true)
        
        list.append(item)
        list.append(Quizz2Question(text: "我在家只穿slipper", correctAnswer: true))
        list.append(Quizz2Question(text: "你的physics真漂亮啊", correctAnswer: false))
        list.append(Quizz2Question(text: "小明想吃shit", correctAnswer: false))
    }
}
