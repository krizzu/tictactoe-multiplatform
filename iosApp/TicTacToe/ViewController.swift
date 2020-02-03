//
//  ViewController.swift
//  TicTacToe
//
//  Created by Krzysztof Borowy on 03/02/2020.
//  Copyright © 2020 KrizzuDev. All rights reserved.
//

import UIKit
import shared

class ViewController: UIViewController {
    let log = Logger.init()

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        log.d(message: "Hello world!")
    }


}

