/*  @Author-  Revathi Sasi  */
package com.example.group3project1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class CandidateDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate_details)

        val CandidateName = intent.getStringExtra("NAME")
        val CandidateTitle = intent.getStringExtra("TITLE")
        val CandidateCompany = intent.getStringExtra("COMPANY")
        val CandidateImage = intent.getStringExtra("IMAGE")
        val CandidateAddress = intent.getStringExtra("ADDRESS")
        val CandidateAbout = intent.getStringExtra("ABOUT")

        val txtName:TextView = findViewById(R.id.Name)
        val txtTitle:TextView = findViewById(R.id.Title)
        val txtCompany:TextView = findViewById(R.id.Company)
        val txtAbout:TextView = findViewById(R.id.About)
        val Image: ImageView = findViewById(R.id.Image)
        val txtAddress:TextView = findViewById(R.id.Address)
        val btnConnect : Button = findViewById(R.id.btnConnect)
        val btnCancel :Button = findViewById(R.id.btnCancel)
        val btnGoBack : ImageView = findViewById(R.id.btnGoBack)

        txtName.text=CandidateName;
        txtTitle.text = CandidateTitle;
        txtCompany.text=CandidateCompany;
        txtAddress.text=CandidateAddress;
        txtAbout.text = CandidateAbout;
        Glide.with(this).load(CandidateImage).into(Image);

        btnConnect.setOnClickListener {
            btnConnect.text="!Pending";
            btnConnect.setBackgroundColor(Color.GRAY)
            btnCancel.visibility =View.VISIBLE;


        }

        btnCancel.setOnClickListener {
            btnConnect.text="+ Connect";
            btnConnect.setBackgroundColor(Color.BLUE)
            btnCancel.visibility =View.INVISIBLE;

        }

        btnGoBack.setOnClickListener{v->
            val intent = Intent(v.context,CandidateActivity::class.java)

            v.context.startActivity(intent)

        }


    }
}