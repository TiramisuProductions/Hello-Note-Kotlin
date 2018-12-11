package hellonote.hellonotekotlin.fragment


import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orm.SugarRecord

import hellonote.hellonotekotlin.R
import hellonote.hellonotekotlin.adapter.BankAccountAdapter
import hellonote.hellonotekotlin.adapter.EmailAdapter
import hellonote.hellonotekotlin.bus.RxBus
import hellonote.hellonotekotlin.bus.RxEvent
import hellonote.hellonotekotlin.database.BankAccount
import hellonote.hellonotekotlin.database.Email
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_list.*

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BankAccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class BankAccountFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var personDisposable: Disposable



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyler()
        personDisposable = RxBus.listen(RxEvent.EventAddPerson::class.java).subscribe {

            initRecyler()
        }

    }

    fun initRecyler(){


        recycler_list.setHasFixedSize(true)
        recycler_list.layoutManager = LinearLayoutManager(activity)

        doAsync {
            var bankAccounts :  List<BankAccount>? = null;
            bankAccounts = SugarRecord.listAll(BankAccount::class.java)
            val adapter = BankAccountAdapter(this@BankAccountFragment.requireContext())
            Collections.reverse(bankAccounts)
            adapter.items = bankAccounts
            uiThread {
                recycler_list.adapter = adapter
            }
        }



    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BankAccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BankAccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
