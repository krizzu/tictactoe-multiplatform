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
    @IBOutlet weak var vsHumanButton: UIButton!
    @IBOutlet weak var vsCpuButton: UIButton!
    
    @IBOutlet weak var playAsLabel: UILabel!
    @IBOutlet weak var signOButton: UIButton!
    @IBOutlet weak var signXButton: UIButton!
    @IBOutlet weak var startButton: UIButton!
    
    
    var buttonActiveColor = UIColor(hexString: "#ffffff")
    var buttonInactiveColor = UIColor(hexString: "#4b4b4b")
    
    var buttonActiveTextColor = UIColor(hexString: "#282828")
    var buttonInactiveTextColor = UIColor(hexString: "#d7d7d7")
    
    
    let log = Logger.init()
    let ticTacToeGame = TicTacToeGame.init()

    override func viewDidLoad() {
        super.viewDidLoad()
        log.d(message: "Hello world!")
        initButtons()
    }
    
    
    func initButtons() {
        vsHumanButton.addTarget(self, action: #selector(gameTypeSelected), for: .touchUpInside)
        vsCpuButton.addTarget(self, action: #selector(gameTypeSelected), for: .touchUpInside)
        signXButton.addTarget(self, action: #selector(gameTypeSelected), for: .touchUpInside)
        signOButton.addTarget(self, action: #selector(gameTypeSelected), for: .touchUpInside)
        startButton.addTarget(self, action: #selector(startGamePressed), for: .touchUpInside)
    }
    
    
    func togglePlayAsVisiblity(isVisible: Bool) {
        playAsLabel.isHidden = !isVisible
        signXButton.isHidden = !isVisible
        signOButton.isHidden = !isVisible
    }
    
    @objc func startGamePressed() {
        ticTacToeGame.startNewGame()
    }
    
    
    @objc func gameTypeSelected(_ sender: AnyObject?) {
        let activeButton = sender as! UIButton
        var inActiveButton: UIButton
        
        if(sender === vsHumanButton || sender === vsCpuButton) {
            inActiveButton = sender === vsCpuButton ? vsHumanButton : vsCpuButton
            togglePlayAsVisiblity(isVisible: sender === vsHumanButton)
            ticTacToeGame.selectedGameType = sender === vsCpuButton ? GameType.vscomputer : GameType.vshuman
            
        } else {
            inActiveButton = sender === signOButton ? signXButton : signOButton
            ticTacToeGame.selectedPlayerSign = sender === signOButton ? PlayerSign.x : PlayerSign.o
        }
        
        
        activeButton.backgroundColor = buttonActiveColor
        activeButton.setTitleColor(buttonActiveTextColor, for: .normal)
        
        inActiveButton.backgroundColor = buttonInactiveColor
        inActiveButton.setTitleColor(buttonInactiveTextColor, for: .normal)
    }


}


extension UIColor {
    convenience init(hexString: String, alpha: CGFloat = 1.0) {
        var hexInt: UInt32 = 0
        let scanner = Scanner(string: hexString)
        scanner.charactersToBeSkipped = CharacterSet(charactersIn: "#")
        scanner.scanHexInt32(&hexInt)

        let red = CGFloat((hexInt & 0xff0000) >> 16) / 255.0
        let green = CGFloat((hexInt & 0xff00) >> 8) / 255.0
        let blue = CGFloat((hexInt & 0xff) >> 0) / 255.0
        let alpha = alpha

        self.init(red: red, green: green, blue: blue, alpha: alpha)
    }
}
