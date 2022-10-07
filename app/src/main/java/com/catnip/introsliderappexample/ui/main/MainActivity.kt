package com.catnip.introsliderappexample.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.viewpager2.widget.ViewPager2
import com.catnip.introsliderappexample.databinding.ActivityMainBinding
import com.catnip.introsliderappexample.model.SliderData
import com.catnip.introsliderappexample.ui.form.FormFragment
import com.catnip.introsliderappexample.ui.form.OnNameSubmittedListener
import com.catnip.introsliderappexample.ui.slider.SliderFragment
import com.catnip.introsliderappexample.utils.ViewPagerAdapter
import com.catnip.introsliderappexample.utils.getNextIndex
import com.catnip.introsliderappexample.utils.getPreviousIndex

class MainActivity : AppCompatActivity(), OnNameSubmittedListener {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val pagerAdapter: ViewPagerAdapter by lazy {
        ViewPagerAdapter(supportFragmentManager, lifecycle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        initFragmentViewPager()
        setOnClickListeners()
    }

    private fun initAdapter() {
        pagerAdapter.apply {
            addFragment(
                SliderFragment.newInstance(
                    SliderData(
                        title = "This game is awesome",
                        desc = "Look at this Bulbasaur",
                        imgSlider = "https://archives.bulbagarden.net/media/upload/thumb/2/21/001Bulbasaur.png/250px-001Bulbasaur.png"
                    )
                )
            )
            addFragment(
                SliderFragment.newInstance(
                    SliderData(
                        title = "Banyak orang tidak tahu kalau saya bermain binomo",
                        desc = "Ayo join binomo",
                        imgSlider = "https://awsimages.detik.net.id/visual/2019/11/29/0de6b77d-48e4-46c5-bbb7-6d20d4603689_169.png?w=715&q=90"
                    )
                )
            )
            addFragment(
                SliderFragment.newInstance(
                    SliderData(
                        title = "Begitu Syulit lupakan Bahrun",
                        desc = "Apalagi bahrun baik",
                        imgSlider = "https://thumb.viva.co.id/media/frontend/thumbs3/2022/09/22/632bda78adfcb-intan-penyanyi-begitu-sulit-lupakan-rehan_1265_711.jpg"
                    )
                )
            )
            addFragment(FormFragment.newInstance().apply {
                setNameSubmittedListener(this@MainActivity)
            })
        }
    }

    private fun setupViewPager() {
        binding.vpIntro.apply {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when {
                        position == 0 -> {
                            binding.tvNext.isInvisible = false
                            binding.tvNext.isEnabled = true
                            binding.tvPrevious.isInvisible = true
                            binding.tvPrevious.isEnabled = false
                        }
                        position < pagerAdapter.getMaxIndex() -> {
                            binding.tvNext.isInvisible = false
                            binding.tvNext.isEnabled = true
                            binding.tvPrevious.isInvisible = false
                            binding.tvPrevious.isEnabled = true
                        }
                        position == pagerAdapter.getMaxIndex() -> {
                            binding.tvNext.isInvisible = true
                            binding.tvNext.isEnabled = false
                            binding.tvPrevious.isInvisible = false
                            binding.tvPrevious.isEnabled = true
                        }
                    }
                }
            })
        }
        binding.dotsIndicator.attachTo(binding.vpIntro)
    }

    private fun initFragmentViewPager() {
        initAdapter()
        setupViewPager()
    }

    private fun setOnClickListeners() {
        binding.tvNext.setOnClickListener {
            navigateToNextFragment()
        }
        binding.tvPrevious.setOnClickListener {
            navigateToPreviousFragment()
        }
    }

    private fun navigateToPreviousFragment() {
        val nextIndex = binding.vpIntro.getPreviousIndex()
        if (nextIndex != -1) {
            binding.vpIntro.setCurrentItem(nextIndex, true)
        }
    }

    private fun navigateToNextFragment() {
        val nextIndex = binding.vpIntro.getNextIndex()
        if (nextIndex != -1) {
            binding.vpIntro.setCurrentItem(nextIndex, true)
        }
    }

    override fun onNameSubmitted(name: String) {
        Log.d(MainActivity::class.java.simpleName, "onNameSubmitted: $name")
    }
}