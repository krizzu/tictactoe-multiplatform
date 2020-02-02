package dev.krizzu.tictactoe.androidApp.views

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import dev.krizzu.tictactoe.androidApp.R
import dev.krizzu.tictactoe.shared.game.GameType

class GameTypeSelectFragment : Fragment() {

  private lateinit var vsHumanButton: Button
  private lateinit var vsCpuButton: Button
  private var act: MainActivity? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_game_type_select, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    vsHumanButton = view.findViewById(R.id.button_vsHuman)
    vsCpuButton = view.findViewById(R.id.button_vsCpu)

    act = activity as MainActivity

    initButtons()
  }

  private fun initButtons() {

    vsHumanButton.setOnClickListener {
      updateGameTypeButton(GameType.vsHuman)
    }

    vsCpuButton.setOnClickListener {
      updateGameTypeButton(GameType.vsComputer)
    }

    updateGameTypeButton(act?.gameController?.selectedGameType!!)
  }

  private fun updateGameTypeButton(selectedGameType: GameType) {
    val colorsInactive = ColorStateList.valueOf(resources.getColor(R.color.colorDarkGrey))
    val colorsActive = ColorStateList.valueOf(resources.getColor(R.color.colorBackgroundLight))

    when (selectedGameType) {
      GameType.vsComputer -> {
        vsHumanButton.backgroundTintList = colorsInactive
        vsCpuButton.backgroundTintList = colorsActive
      }
      GameType.vsHuman -> {
        vsCpuButton.backgroundTintList = colorsInactive
        vsHumanButton.backgroundTintList = colorsActive
      }
    }
    act?.onGameTypeChange(selectedGameType)
  }
}

