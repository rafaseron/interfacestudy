package br.com.rafaseron.interfacestudy.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.rafaseron.interfacestudy.R
import br.com.rafaseron.interfacestudy.adapter.CarAdapter
import br.com.rafaseron.interfacestudy.adapter.TabAdapter
import br.com.rafaseron.interfacestudy.data.CarFactory
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.Tab

class MainActivity : AppCompatActivity() {
    //inicializamos as variveis e typamos
    lateinit var tabMenu: TabLayout
    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun setupViews(){
            tabMenu = findViewById(R.id.tabMenu)
            viewPager = findViewById(R.id.vpModelos)
        }
        setupViews()


        fun setupTabAdapter(){
            /* TabAdapter (FragmentAdapter) */
            //val adaptador = TabAdapter(this)
            viewPager.adapter = TabAdapter(this) //aqui estamos falando pro ViewPager que o adaptador que o controlará é o adaptador personalizado que criamos, trabalhando neste contexto (Nesta Activity)

        }
        setupTabAdapter()

        fun tabListener(){
            tabMenu.addOnTabSelectedListener(object:OnTabSelectedListener{
                override fun onTabSelected(tab: Tab?) {
                    Log.d("ABRIMOS O TAB ITEM", "Tab selected: ${tab?.position}")

                    if (tab != null) { viewPager.currentItem = tab.position }

                    /*tab?.let {
                        viewPager.currentItem = it.position
                        Log.d("DEBUG", "Dentro da verificacao After setting current item")
                    }*/
                    Log.d("DEBUG", "Apos verificacao After setting current item")
                }
                override fun onTabUnselected(tab: Tab?) { }
                override fun onTabReselected(tab: Tab?) { }
            })

            viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    tabMenu.getTabAt(position)?.select()

                }
            })

        }
        tabListener()

}



}