package dev.krizzu.tictactoe.androidApp.views

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import dev.krizzu.tictactoe.androidApp.R
import dev.krizzu.tictactoe.shared.game.PlayerSign

class SignSelectFragment : Fragment() {

  private lateinit var signXButton: Button
  private lateinit var signOButton: Button
  private lateinit var gamePlayAsText: TextView

  private lateinit var act: MainActivity

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_sign_select, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    act = activity as MainActivity
    initButtons(view)
  }

  private fun initButtons(root: View) {
    signXButton = root.findViewById(R.id.button_signX)
    signOButton = root.findViewById(R.id.button_signO)
    gamePlayAsText = root.findViewById(R.id.text_gamePlayAs)

    signXButton.setOnClickListener {
      act.onSignChange(PlayerSign.X)
      updateGameTypeButton()
    }

    signOButton.setOnClickListener {
      act.onSignChange(PlayerSign.O)
      updateGameTypeButton()
    }

    updateGameTypeButton()
  }

  private fun updateGameTypeButton() {
    val colorsInactive = ColorStateList.valueOf(resources.getColor(R.color.colorDarkGrey))
    val colorsActive = ColorStateList.valueOf(resources.getColor(R.color.colorBackgroundLight))

    when (act.gameController.selectedPlayerSign) {
      PlayerSign.X -> {
        signOButton.backgroundTintList = colorsInactive
        signXButton.backgroundTintList = colorsActive
      }
      PlayerSign.O -> {
        signXButton.backgroundTintList = colorsInactive
        signOButton.backgroundTintList = colorsActive
      }
    }
  }

  fun onGameTypeChange(isVsCpu: Boolean) {
    val visibilityFlag = if (isVsCpu) View.VISIBLE else View.INVISIBLE

    signXButton.visibility = visibilityFlag
    signOButton.visibility = visibilityFlag
    gamePlayAsText.visibility = visibilityFlag
  }
}
