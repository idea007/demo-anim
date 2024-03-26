package com.dafay.demo.anim.ui.anim.scene

import android.transition.*
import android.view.ViewGroup
import android.widget.RadioGroup
import com.dafay.demo.anim.R
import com.dafay.demo.anim.ui.frg.BaseFragment
import kotlinx.android.synthetic.main.frg_scene_1.view.*


/**
 * Created by  idea on 2019-11-23$ 10:52$
 * des: scene 切换
 */
class Scene1Fragment : BaseFragment() {

    private lateinit var scene1: Scene
    private lateinit var scene2: Scene
    private lateinit var scene3: Scene

    override fun getLayoutId(): Int {
        return R.layout.frg_scene_1
    }

    override fun onInitViews() {
        initView()
        bindListener()
    }


    private fun initView() {
        // 不能被复用
        scene1 = Scene(
            rootView.rl_root_scene,
            rootView.rl_root_scene.findViewById(R.id.rl_container) as ViewGroup
        )
        scene2 = Scene.getSceneForLayout(
            rootView.rl_root_scene,
            R.layout.layout_section_scene_2,
            context
        )
        scene3 = Scene.getSceneForLayout(
            rootView.rl_root_scene,
            R.layout.layout_section_scene_3,
            context
        )
    }

    private fun bindListener() {

        rootView.rg_select_scene.setOnCheckedChangeListener(object :
            RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.select_scene_1 -> {
                        TransitionManager.go(scene1)
                    }

                    R.id.select_scene_2 -> {
                        var transition = TransitionSet()
                            .addTransition(ChangeBounds())
                            .addTransition(Fade(Fade.IN))
                            .addTransition(Fade(Fade.OUT))
                        transition.excludeTarget(R.id.transition_oval, true)
                        TransitionManager.go(scene2, transition)
                    }

                    R.id.select_scene_3 -> {
                        TransitionManager.go(scene3)
                    }
                }
            }
        })
    }


}