package br.com.rafaseron.interfacestudy.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.rafaseron.interfacestudy.UI.CarFragmentFavorits
import br.com.rafaseron.interfacestudy.UI.CarFragmentModels

class TabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    //precisa de init de: Fragment, FragmentActivity, FragmentManager, Lifecycle

    override fun createFragment(position: Int): Fragment {

        when (position){
            0 -> return CarFragmentModels()
            1 -> return CarFragmentFavorits()
            else -> return CarFragmentModels()
        }

    }
    override fun getItemCount(): Int {
        return 2
    }


}