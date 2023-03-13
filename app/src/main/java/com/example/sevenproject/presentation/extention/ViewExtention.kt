package com.example.sevenproject.presentation.extention

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(msg:String){
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(msg:Int){
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}