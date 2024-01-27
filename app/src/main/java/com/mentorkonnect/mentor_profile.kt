package com.mentorkonnect

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.findViewTreeViewModelStoreOwner


class mentor_profile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_mentor_profile, container, false)

        val logout_btn=view.findViewById<Button>(R.id.logout_btn)
        val edit_profile=view.findViewById<Button>(R.id.edit_profile_btn)

        logout_btn.setOnClickListener {
            MainActivity.auth.signOut()
            startActivity(Intent(context, LoginActivity::class.java))
            activity?.finish()

        }

        edit_profile.setOnClickListener {
            startActivity(Intent(context, Edit_Profile::class.java))
        }







        return view
    }


}