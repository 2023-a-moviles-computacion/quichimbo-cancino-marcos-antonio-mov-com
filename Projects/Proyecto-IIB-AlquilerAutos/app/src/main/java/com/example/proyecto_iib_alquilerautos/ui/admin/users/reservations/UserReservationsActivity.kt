package com.example.proyecto_iib_alquilerautos.ui.admin.users.reservations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.proyecto_iib_alquilerautos.APICallback
import com.example.proyecto_iib_alquilerautos.api.service.APIService
import com.example.proyecto_iib_alquilerautos.api.types.APIResponse
import com.example.proyecto_iib_alquilerautos.api.types.Reservation
import com.example.proyecto_iib_alquilerautos.api.types.Step
import com.example.proyecto_iib_alquilerautos.databinding.ActivityUserReservationsBinding
import com.example.proyecto_iib_alquilerautos.ui.auth.LoginActivity

import com.example.proyecto_iib_alquilerautos.ui.formreservation.data.FormReservationDataActivity
import com.example.proyecto_iib_alquilerautos.ui.formreservation.payment.FormReservationPaymentActivity
import com.example.proyecto_iib_alquilerautos.ui.formreservation.vehicle.FormReservationVehicleActivity
import com.example.proyecto_iib_alquilerautos.ui.reservations.ReservationsDetailsActivity
import com.example.proyecto_iib_alquilerautos.ui.reservations.adapter.ReservationsAdapter
import com.example.proyecto_iib_alquilerautos.util.UserPreferencesManager
import java.io.IOException

class UserReservationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserReservationsBinding
    private lateinit var preferencesManager: UserPreferencesManager
    private lateinit var adapter: ReservationsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserReservationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        preferencesManager = UserPreferencesManager(this)

        adapter = ReservationsAdapter { reservation ->
            preferencesManager.saveSelectedReservation(reservation)
            startActivity(Intent(this, ReservationsDetailsActivity::class.java))
        }

        // layout
        binding.recyclerReservations.layoutManager = LinearLayoutManager(this)

        //adapter
        binding.recyclerReservations.adapter = adapter

        binding.returnButton.visibility = View.VISIBLE
        binding.returnButton.setOnClickListener {
            finish()
        }

        if (verifyUserRole(preferencesManager)) {
            getLast(preferencesManager, adapter)
        } else {
            loadedWithZero()
        }

    }

    private fun verifyUserRole(preferencesManager: UserPreferencesManager): Boolean {
        if (!preferencesManager.isLoggedIn()) {
            val intent = Intent(this@UserReservationsActivity, LoginActivity::class.java)
            startActivity(intent)
            return false
        }

        return true
    }

    private fun getLast(
        preferencesManager: UserPreferencesManager,
        adapter: ReservationsAdapter
    ) {
        loading()
        val apiService = APIService(preferencesManager.getToken())
        val id = preferencesManager.getTempId()
        val url = "/reservation/last?id=$id"

        apiService.getData(url, object : APICallback {
            override fun onSuccess(response: APIResponse) {
                if (!response.error) {
                    val reservation: Reservation = response.reservation
                    preferencesManager.saveVehicleId(reservation.vehicle_id.toString())
                    preferencesManager.saveReservationId(reservation.id.toString())

                    when (reservation.step) {
                        Step.PERSONAL ->
                            startActivity(
                                Intent(
                                    this@UserReservationsActivity,
                                    FormReservationDataActivity::class.java
                                )
                            )
                        Step.VEHICLE ->
                            startActivity(
                                Intent(
                                    this@UserReservationsActivity,
                                    FormReservationVehicleActivity::class.java
                                )
                            )
                        else ->
                            startActivity(
                                Intent(
                                    this@UserReservationsActivity,
                                    FormReservationPaymentActivity::class.java
                                )
                            )
                    }
                } else {
                    runOnUiThread {
                        getReservations(preferencesManager, adapter)
                    }
                }
            }

            override fun onError(error: IOException) {
                runOnUiThread {
                    getReservations(preferencesManager, adapter)
                }
            }
        })
    }

    private fun getReservations(
        preferencesManager: UserPreferencesManager,
        adapter: ReservationsAdapter
    ) {
        loading()
        val apiService = APIService(preferencesManager.getToken())
        val id = preferencesManager.getTempId()
        val url = "/reservation/user?id=$id"

        apiService.getData(url, object : APICallback {
            override fun onSuccess(response: APIResponse) {
                if (!response.error) {
                    val reservationsListApi: List<Reservation>? = response.reservations
                    if (reservationsListApi != null) {
                        runOnUiThread {
                            adapter.updatedReservations(reservationsListApi)
                        }
                    }

                    runOnUiThread {
                        loaded()
                    }

                } else {
                    val errorCode = response.message

                    runOnUiThread {
                        loadedWithZero()
                        Toast.makeText(
                            this@UserReservationsActivity,
                            errorCode,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onError(error: IOException) {
                runOnUiThread {
                    loadedWithZero()
                    Toast.makeText(
                        this@UserReservationsActivity,
                        error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun loading() {
        binding.recyclerReservations.visibility = View.GONE

        binding.progressBar.visibility = View.VISIBLE
    }

    private fun loaded() {
        binding.recyclerReservations.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    private fun loadedWithZero() {
        binding.recyclerReservations.visibility = View.GONE
        binding.progressBar.visibility = View.GONE

        binding.notFound.visibility = View.VISIBLE
    }
}