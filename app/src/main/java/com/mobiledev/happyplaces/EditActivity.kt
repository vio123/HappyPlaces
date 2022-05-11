package com.mobiledev.happyplaces

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.location.Geocoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.mobiledev.happyplaces.databinding.ActivityEditBinding
import com.mobiledev.happyplaces.fragments.ui.home.HomeViewModel
import com.mobiledev.happyplaces.model.Place
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class EditActivity : AppCompatActivity() {
    private val actType:Array<String> = arrayOf("Select Activity Type","hiking","walking","running")
    private val placeType:Array<String> = arrayOf("Select Place Type","munte","mare")
    private lateinit var geocoder: Geocoder
    // This property is only valid between onCreateView and
    // onDestroyView.
    private  var img: Uri? = null
    private var nr = 0
    private lateinit var list:List<String>
    private var longitude by Delegates.notNull<Double>()
    private var latitude by Delegates.notNull<Double>()
    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val plc:Place = intent.getSerializableExtra("list") as Place
        val homeViewModel =
            ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application))[HomeViewModel::class.java]
        binding.chooseFile.setOnClickListener {
            pickImage()
        }
        val actAdapter = ArrayAdapter(applicationContext,android.R.layout.simple_spinner_dropdown_item,actType)
        binding.activityType.adapter = actAdapter
        binding.activityType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        geocoder = Geocoder(this, Locale.getDefault())
        val placeAdapter = ArrayAdapter(applicationContext,android.R.layout.simple_spinner_dropdown_item,placeType)
        binding.placeType.adapter = placeAdapter
        binding.placeType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLabel(myCalendar)
        }
        binding.chooseDate.setOnClickListener {
            DatePickerDialog(this,datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(
                Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.adressBtn.setOnClickListener {
            startActivityForResult(Intent(this,MapsActivity::class.java),1)
        }
        binding.addBtn.setOnClickListener {
            nr=0
            if(binding.namePlaces.text.isEmpty()){
                binding.namePlaces.error = "Is empty"
                ++nr
            }
            if(binding.note.text!!.isEmpty()){
                binding.note.error = "Is empty"
                ++nr
            }
            if(binding.activityType.selectedItem.equals("Select Activity Type")){
                ++nr
            }
            if(binding.placeType.selectedItem.equals("Select Place Type")){
                ++nr
            }
            if(binding.date.text.equals("Date")){
                binding.date.error = "Select a date"
                ++nr
            }
            if(binding.adress.text.equals("Adress")){
                binding.adress.error = "Select a adress"
                ++nr
            }
            if(nr == 0){
                if(img == null) {
                    img = Uri.parse(
                        ContentResolver.SCHEME_ANDROID_RESOURCE +
                            "://" + resources.getResourcePackageName(R.drawable.ic_photo)
                            + '/' + resources.getResourceTypeName(R.drawable.ic_photo) + '/' + resources.getResourceEntryName(R.drawable.ic_photo) )
                }
                val bitmap: Bitmap =
                    MediaStore.Images.Media.getBitmap(this.contentResolver, img)
                val place = Place(binding.namePlaces.text.toString(),bitmap,binding.date.text.toString(),binding.activityType.selectedItem.toString(),
                    binding.placeType.selectedItem.toString(),list[0]+","+list[1],latitude,longitude,binding.note.text.toString(),
                    fav = false)
                homeViewModel.updatePlace(place)
            }
        }
    }
    @SuppressLint("SimpleDateFormat")
    private fun updateLabel(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat)
        binding.date.text = sdf.format(myCalendar.time)
    }

    private fun pickImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)

    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100){
            binding.filename.text = "Uploaded"
            if (data != null) {
                img = data.data!!
            }
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 1){
            latitude = data!!.getDoubleExtra("lat",0.0)
            longitude = data.getDoubleExtra("long",0.0)
            val adress = geocoder.getFromLocation( latitude,longitude,1)
            list = adress[0].getAddressLine(0).split(',')
            binding.adress.text = "Location received"
        }

    }
}