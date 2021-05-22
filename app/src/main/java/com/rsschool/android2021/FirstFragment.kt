package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null

    // My code
    private var listener: MyFragmentListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as MyFragmentListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        // My code
        // _TODO: val min = ...
        val minValue: EditText = view.findViewById(R.id.min_value)
        // _TODO: val max = ...
        val maxValue: EditText = view.findViewById(R.id.max_value)

        generateButton?.setOnClickListener {
            // My code
            // _TODO: send min and max to the SecondFragment
            val min: Int = try {
                minValue.text.toString().toInt()
            } catch (e: Exception) {
                -1
            }
            val max = maxValue.text.toString().toIntOrNull() ?: -1
            if (min < 0 || max < 0 || min > max) {
                Toast.makeText(activity as MainActivity, "Введите корректные значения",
                    Toast.LENGTH_LONG).show()
            } else {
                listener?.doSomething(tag, min, max)
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }




    interface myInterface {
        fun makeSomething()
    }
}