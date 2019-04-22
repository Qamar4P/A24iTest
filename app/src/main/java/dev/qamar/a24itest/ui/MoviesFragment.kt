package dev.qamar.a24itest.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.util.DiffUtil
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.squareup.picasso.Picasso
import dev.qamar.a24itest.R
import dev.qamar.a24itest.data.model.Movie
import dev.qamar.a24itest.di.injector
import dev.qamar.a24itest.helpers.ItemViewClickListener
import dev.qamar.a24itest.helpers.LolAdapter
import dev.qamar.a24itest.helpers.LolViewHold
import dev.qamar.a24itest.helpers.ViewHolderCreator
import dev.qamar.a24itest.utils.AppConst
import kotlinx.android.synthetic.main.movies_fragment.*

class MovieGridFragment : BaseFragment() {

    override val layoutId = R.layout.movies_fragment
    private var adapter = LolAdapter<Movie, ItemViewHold>(itemViewClickListener(), ViewHolderCreator<ItemViewHold> { ItemViewHold(it) })

    private val viewModel by lazy {
        ViewModelProviders.of(this,activity?.injector?.moviesViewModelFactory()).get(MoviesViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews();
        observeViewModel()
        //logic for pagination can be easily implemented: Short of time
        viewModel.loadMovies(1)
    }


    private fun setupViews() {
        if (resources.getBoolean(R.bool.isTablet)) {
            recyclerView.layoutManager = GridLayoutManager(context,3);
        }else{
            recyclerView.layoutManager = LinearLayoutManager(context);
        }
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        pullToRefresh.setOnRefreshListener{
            viewModel.loadMovies(1)
            pullToRefresh.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.movies.observe(this, Observer { movies ->
            updateAdapter(movies?: arrayListOf())
        })

        viewModel.loading.observe(this, Observer { loading -> progressBar.visibility = if (loading!!) View.VISIBLE else View.GONE })
    }

    private fun updateAdapter(movies: List<Movie>) {
        adapter.onNewData(MyDiffCallback(adapter.items, movies),movies)

        if (adapter.items.isNotEmpty()) emptyView.visibility = View.GONE else emptyView.visibility = View.VISIBLE
    }

    private fun itemViewClickListener() = ItemViewClickListener<Movie> { _, item, _ ->
        Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
    }

}

class ItemViewHold(parent: ViewGroup) : LolViewHold<Movie>(parent, R.layout.list_item) {
    val textTitle by lazy { itemView.findViewById(R.id.textTitle) as TextView}
    val imageThumbnail by lazy { itemView.findViewById(R.id.imageThumbnail) as ImageView}

    override fun bind() {
        textTitle.text = data.title

        Picasso.with(itemView.context)
            .load(AppConst.IMAGE_BASE_URL+data.poster_path)
            .into(imageThumbnail)
    }
}

class MyDiffCallback(val newList: List<Movie>, var oldList: List<Movie>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id === newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == (newList[newItemPosition]).id
    }
}