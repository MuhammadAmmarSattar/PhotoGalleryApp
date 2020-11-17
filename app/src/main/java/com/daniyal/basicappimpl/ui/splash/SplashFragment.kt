package com.daniyal.basicappimpl.ui.splash

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniyal.basicappimpl.data.repository.photo.remote.response.PhotoDTO
import com.daniyal.basicappimpl.databinding.FragmentSplashBinding
import com.daniyal.basicappimpl.ui.base.BaseFragment
import com.daniyal.basicappimpl.ui.callbacks.GroupieInterface
import com.daniyal.basicappimpl.ui.home.adapters.FancyItem
import com.daniyal.basicappimpl.ui.home.adapters.MainExpendableHeaderItem
import com.daniyal.basicappimpl.ui.home.adapters.MainViewItem
import com.daniyal.basicappimpl.ui.home.viewmodels.MainViewModel
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.Section
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_splash.*
import java.util.*

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(), GroupieInterface<PhotoDTO> {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val excitingSection = Section()


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSplashBinding.inflate(inflater, container, false)

    private val mainViewModel: MainViewModel by viewModels()

//        val excitingFancyItems = generateFancyItems(12)

    var photoDTOs: List<PhotoDTO> = listOf(
        PhotoDTO("1", "this is description 1", 21),
        PhotoDTO("2", "this is description 2", 22),
        PhotoDTO("3", "this is description 3", 23),
        PhotoDTO("4", "this is description 4", 24),
    )

    var mainViewItemList:MutableList<MainViewItem> = mutableListOf()
//    var fancyItemList: MutableList<FancyItem> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        subscribeUiEvents(mainViewModel)
        setupRecyclerView(photoDTOs)

    }





    private fun setupRecyclerView(items: List<PhotoDTO>) {
        linearLayoutManager = LinearLayoutManager(activity)
        mainRv.apply {
            layoutManager = GridLayoutManager(activity, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
        }

        items.forEach { photoDTO ->
            mainViewItemList.add(MainViewItem(photoDTO, this))
        }



        ExpandableGroup(MainExpendableHeaderItem("Boring Group"), true).apply {
            add(Section(mainViewItemList))
            groupAdapter.add(this)
        }




        ExpandableGroup(MainExpendableHeaderItem("Exciting Group"), false).apply {
//            excitingSection.addAll(excitingFancyItems)
//            add(excitingSection)
//            groupAdapter.add(this)
            add(Section(mainViewItemList))
            groupAdapter.add(this)
        }


    }



    override fun invokeSingleItemClick(item: PhotoDTO, position: Int) {
        mainViewModel.showToast("${item.desc} - ${position}")
    }

    //
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewLifecycleOwner.lifecycleScope.launch {
//           navigate()
//       }
//
//
//   }
//
//    private suspend fun navigate(){
//        delay(3000)
//        findNavController(this).navigate(R.id.action_splashFragment_to_loginFragment)
//    }


    private fun generateFancyItems(count: Int): MutableList<FancyItem>{
        val rnd = Random()
        return MutableList(count){
            val color = Color.argb(255, rnd.nextInt(256),
                rnd.nextInt(256), rnd.nextInt(256))
            FancyItem(color, rnd.nextInt(100))
        }
    }


}