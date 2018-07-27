//
//  WordMemoCell.swift
//  LandOfVoca
//
//  Created by ChenZengzhan on 2018-07-26.
//  Copyright © 2018 teamtiger. All rights reserved.
//

import UIKit

class WordMemoCell: UITableViewCell {

    @IBOutlet weak var English: UILabel!
    @IBOutlet weak var Chinese: UILabel!
    @IBOutlet weak var numberOfTimes: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
