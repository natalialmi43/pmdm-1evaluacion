package edu.iesam.teslaapp.feature.carslist

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import edu.iesam.teslaapp.R
import edu.iesam.teslaapp.feature.carslist.data.CarDataRepository
import edu.iesam.teslaapp.feature.carslist.data.remote.CarApiRemoteDataSource
import edu.iesam.teslaapp.feature.carslist.domain.Car
import edu.iesam.teslaapp.feature.carslist.domain.GetCarByIdUseCase
import edu.iesam.teslaapp.feature.carslist.presentation.CarDetailsViewModel

class CarsListActivity : AppCompatActivity() {

    private val viewModel : CarDetailsViewModel by viewModels {
        CarDetailsViewModel.factory(
            GetCarByIdUseCase(
                CarDataRepository(
                    CarApiRemoteDataSource()
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initObserver()

        initListeners()

        viewModel.loadCarDetails("1")

    }

    private fun initObserver(){
        viewModel.uiState.observe(this){ uiState ->
            bind(uiState)
        }
    }

    private fun bind(uiState: CarDetailsViewModel.UiState){

        if (uiState.isLoading){
            findViewById<ConstraintLayout>(R.id.loading_view).visibility = View.VISIBLE
        } else{
            findViewById<ConstraintLayout>(R.id.loading_view).visibility = View.GONE
        }

        if (uiState.hasError){
            findViewById<ConstraintLayout>(R.id.error_view).visibility = View.VISIBLE
        } else{
            findViewById<ConstraintLayout>(R.id.error_view).visibility = View.GONE
        }


        uiState.car?.let { car ->
            renderUi (car)
        }
    }

    private fun renderUi(car: Car){

        val carView = findViewById<ConstraintLayout>(R.id.car_view)

        val labelBrand = carView.findViewById<TextView>(R.id.tv_brand)
        val labelToFillBrand = carView.findViewById<TextView>(R.id.tv_fill_brand)

        labelBrand.visibility = View.VISIBLE
        labelToFillBrand.text = car.brand


        val labelModel = carView.findViewById<TextView>(R.id.tv_model)
        val labelToFillModel = carView.findViewById<TextView>(R.id.tv_fill_model)

        labelModel.visibility = View.VISIBLE
        labelToFillModel.text = car.model


        val labelMotor = carView.findViewById<TextView>(R.id.tv_motor)
        val labelToFillMotor = carView.findViewById<TextView>(R.id.tv_fill_motor)

        labelMotor.visibility = View.VISIBLE
        labelToFillMotor.text = car.motor


        val labelPrice = carView.findViewById<TextView>(R.id.tv_price)
        val labelToFillPrice = carView.findViewById<TextView>(R.id.tv_fill_price)

        labelPrice.visibility = View.VISIBLE
        labelToFillPrice.text = car.price

    }

    private fun initListeners() {
        val labelCarId = findViewById<EditText>(R.id.input_car_id)
        val actionFind = findViewById<Button>(R.id.action_find)
        actionFind.setOnClickListener {
            buttonFindClicked(labelCarId.text.toString())
        }
    }

    private fun buttonFindClicked(carId: String) {
        //el usuario ha pulsado el botón.
        Log.d(CarsListActivity::class.java.simpleName, carId)
        viewModel.loadCarDetails(carId)

    }
}