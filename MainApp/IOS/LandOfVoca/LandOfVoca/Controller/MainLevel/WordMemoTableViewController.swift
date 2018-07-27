//
//  WordMemoTableViewController.swift
//  LandOfVoca
//
//  Created by ChenZengzhan on 2018-07-26.
//  Copyright © 2018 teamtiger. All rights reserved.
//

import UIKit
import CoreData

class WordMemoViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var wordTableView: UITableView!
    
    let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    var allMemoWords : [WordForMemo]!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Set up TableView
        configureTableView()
        // Set up array
        loadWords()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return allMemoWords.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "wordMemoCell", for: indexPath) as! WordMemoCell
        let thisWord : WordForMemo = allMemoWords[indexPath.row]
        cell.Chinese.text = thisWord.chinese
        cell.English.text = thisWord.english
        cell.numberOfTimes.text = String(thisWord.numberOfTime)
        return cell
    }
    
    func configureTableView(){
        wordTableView.delegate = self
        wordTableView.dataSource = self
        wordTableView.register(UINib(nibName: "WordMemoCell", bundle: nil), forCellReuseIdentifier: "wordMemoCell")
//       wordTableView.rowHeight = UITableViewAutomaticDimension
//        wordTableView.estimatedRowHeight = 40.0
    }
    
    func loadWords()  {
        let request : NSFetchRequest<WordForMemo> = WordForMemo.fetchRequest()
        
        do{
            allMemoWords = try context.fetch(request)
        }catch{
            print("the error is \(error)")
        }
    }
    @IBAction func backButtonPressed(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
}
