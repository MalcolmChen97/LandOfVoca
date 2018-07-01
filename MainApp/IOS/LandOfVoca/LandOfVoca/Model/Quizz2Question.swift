//
//  Quizz2Question.swift
//  LandOfVoca
//
//  Created by ChenZengzhan on 2018-06-29.
//  Copyright © 2018 teamtiger. All rights reserved.
//

import Foundation

class Quizz2Question{
    let questionText : String
    let answer : Bool
    init(text:String,correctAnswer:Bool) {
        questionText=text
        answer=correctAnswer
    }
}
