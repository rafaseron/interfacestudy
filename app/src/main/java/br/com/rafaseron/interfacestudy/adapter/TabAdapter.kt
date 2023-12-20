package br.com.rafaseron.interfacestudy.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.rafaseron.interfacestudy.UI.CarFragment

class TabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    //precisa de init de: Fragment, FragmentActivity, FragmentManager, Lifecycle

    override fun createFragment(position: Int): Fragment {
       var posicao =  when (position){
            1 -> CarFragment()
            2 -> CarFragment()
           else -> CarFragment()
        }
        return posicao
    }
    override fun getItemCount(): Int {
        return 2
    }


}