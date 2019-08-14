package tty.community.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import tty.community.pages.fragment.ChatFragment
import tty.community.pages.fragment.HomeFragment
import tty.community.pages.fragment.MeFragment

class MainFragmentAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var pages: ArrayList<Fragment> = ArrayList()

    init {
        pages.add(HomeFragment())
        pages.add(ChatFragment())
        pages.add(MeFragment())
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getItem(p0: Int): Fragment {
        return pages[p0]
    }

}