package com.example.supabasetestproject.presentation.screen.main

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.supabasetestproject.data.local_data_source.LocalRepositoryImpl

class SelectLanguageDialog(private val repository: LocalRepositoryImpl) : DialogFragment() {
    val listOf = arrayOf<String>("ru", "sg", "en")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireContext())
//            .setItems(
//                listOf,
//                DialogInterface.OnClickListener { dialogInterface, i ->
//                    repository.setLanguage(listOf[i])
//                    dialogInterface.cancel()
//                    requireActivity().recreate()
//                },
//            )
            .setSingleChoiceItems(
                listOf,
                -1,
            ) { dialog, item ->
                repository.setLanguage(listOf[item])
                dialog.cancel()
                requireActivity().recreate()
            }
            .setTitle("Select language")
        return dialog.create()
    }
}
