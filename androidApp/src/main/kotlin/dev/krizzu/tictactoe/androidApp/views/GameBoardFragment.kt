package dev.krizzu.tictactoe.androidApp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import dev.krizzu.tictactoe.androidApp.R
import dev.krizzu.tictactoe.shared.game.PlayerSign

class GameBoardFragment : Fragment(), View.OnClickListener {

  private lateinit var fieldsContainer: ConstraintLayout

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_game_board, container, false)
  }

  override fun onViewCreated(root: View, savedInstanceState: Bundle?) {
    fieldsContainer = root.findViewById(R.id.container_gameBoard)

    fieldsContainer.children.forEach {
      it.setOnClickListener(this)
    }
  }

  override fun onClick(button: View?) {
    if (button is Button && button.tag != null) {
      val field = button.tag.toString().toByte()
      val playerSign = (activity as MainActivity).onFieldPress(field)
      if (playerSign != null) {
        button.text = playerSign.toString()
      }
    }
  }

  fun clearFields() {
    fieldsContainer.children.forEach {
      if (it is Button) {
        it.text = ""
      }
    }
  }

  fun placeSignAt(sign: PlayerSign, field: Byte) {
    fieldsContainer.children.forEach {
      if (it is Button && it.tag == field.toString()) {
        it.text = sign.toString()
      }
    }
  }
}