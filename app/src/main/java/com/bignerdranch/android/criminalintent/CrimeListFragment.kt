package com.bignerdranch.android.criminalintent

import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.util.UUID

private const val TAG = "CrimeListFragment"

class CrimeListFragment: Fragment() {

    /**
     * 호스팅 액티비티에서 구현할 인터페이스
     */
    interface Callbacks {
        fun onCrimeSelected(crimeId: UUID)
    }

    private var callbacks: Callbacks? = null
    private lateinit var crimeRecyclerView : RecyclerView
    private var adapter: CrimeAdapter? = CrimeAdapter(emptyList())

    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this).get(CrimeListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("DEPRECATION")
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)

        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)
        crimeRecyclerView.adapter = adapter // 초기 어뎁터 설정
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeListViewModel.crimeListLiveData.observe(
            viewLifecycleOwner,
            Observer { crimes ->
                crimes?.let {
                    Log.i(TAG, "Got crimes ${crimes.size}")
                    updateUI(crimes)
                }
            }
        )
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }
    @Suppress("DEPRECATION")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_crime_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.new_crime -> {
                val crime = Crime()
                crimeListViewModel.addCrime(crime)
                callbacks?.onCrimeSelected(crime.id)
                true
            }
            else -> {
                @Suppress("DEPRECATION")
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun updateUI(crimes: List<Crime>){
        adapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = adapter // 업데이트 어뎁터 설정
    }

    private inner class CrimeHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener{

        private lateinit var crime: Crime

        private val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
        private val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)
        // private val policeButton: Button? = itemView.findViewById(R.id.crime_police)

        init {
            itemView.setOnClickListener(this)

            /*
            policeButton?.setOnClickListener {
                Toast.makeText(context, "경찰에 연락합니다!", Toast.LENGTH_SHORT).show()
            }*/
        }

        override fun onClick(p0: View?) {
            callbacks?.onCrimeSelected(crime.id)
        }

        fun bind(crime:Crime){
            this.crime = crime
            titleTextView.text = this.crime.title
            dateTextView.text = DateFormat.format("EEEE, MMM d, yyyy", this.crime.date)
            solvedImageView.visibility = if(crime.isSolved){
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }

    }

    private inner class CrimeAdapter(var crimes: List<Crime>) : RecyclerView.Adapter<CrimeHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            /*
            val layoutId = when(viewType){
                1 -> R.layout.list_item_crime_police
                else -> R.layout.list_item_crime
            }
            */

            val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
            return CrimeHolder(view)
        }

        /*override fun getItemViewType(position: Int): Int {
            return if(crimes[position].requiresPolice) {
                1
            } else {
                0
            }
        }*/

        override fun getItemCount(): Int {
            return crimes.size
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]
            holder.bind(crime)
        }

    }

    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }
    }


}