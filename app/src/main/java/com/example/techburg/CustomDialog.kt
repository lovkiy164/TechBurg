package com.example.techburg

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.techburg.databinding.FragmentDialogBinding

class CustomDialog : DialogFragment(){
    private val args by navArgs<CustomDialogArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val window: Window? = dialog?.window
        window?.setGravity(Gravity.BOTTOM)
        val params: WindowManager.LayoutParams? = window?.attributes
        params?.y = 150
        window?.attributes = params
        val key = args.text
        val textStyle = buildSpannedString {
            inSpans(
                ForegroundColorSpan(Color.rgb(0,0,0)),
                BackgroundColorSpan(Color.rgb(255,255,255))
            ){
                append(key)
            }.setSpan(StyleSpan(Typeface.BOLD),0,this.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return FragmentDialogBinding.inflate(inflater, container, false)
            .also{
                it.text.run {
                    append("$textStyle")
                    append("   ")
                }
                it.btn.setOnClickListener{
                    dismiss()
                }
            }
            .root
    }
}