package com.mustafayosri.gadsleaderboard.ui.submit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mustafayosri.gadsleaderboard.R

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.mustafayosri.gadsleaderboard.databinding.ActivitySubmitBinding
import com.mustafayosri.gadsleaderboard.pojo.ResultWrapperResponse
import com.noha.gadsleaderboard.utils.hideKeyboard

class SubmitActivity : AppCompatActivity() {

    private val viewModel: SubmitViewModel by viewModels()
    private var confirmationAlertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySubmitBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_submit)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.showConfirmationDialog.observe(this) {
            if (it) showConfirmationAlertDialog()
            else confirmationAlertDialog?.dismiss()
        }

        viewModel.showSuccessDialog.observe(this) {
            if (it) showSuccessAlertDialog()
        }

        viewModel.showFailDialog.observe(this) {
            if (it) showErrorAlertDialog()
        }

        viewModel.errorMsg.observe(this) {
            showErrorSnackBar(binding.root, it)
        }

        viewModel.submitStatus.observe(this) {
            viewModel.showConfirmationDialog(false)
            if (it is ResultWrapperResponse.Success) {
                viewModel.showSuccessDialog(true)
            } else {
                viewModel.showFailDialog(true)
            }
        }

        binding.backImageButton.setOnClickListener {
            finish()
        }

        binding.button.setOnClickListener {
            hideKeyboard(binding.root)
            viewModel.submit()
        }
    }

    private fun showConfirmationAlertDialog() {

        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        val dialogView: View = layoutInflater.inflate(R.layout.confirmation_dialog, null)
        dialogBuilder.setView(dialogView)

        val closeButton: ImageButton = dialogView.findViewById(R.id.closeImageButton) as ImageButton

        val yesButton: Button = dialogView.findViewById(R.id.yesButton) as Button

        confirmationAlertDialog = dialogBuilder.create()
        confirmationAlertDialog?.setCanceledOnTouchOutside(false)
        confirmationAlertDialog?.show()

        closeButton.setOnClickListener {
            confirmationAlertDialog?.dismiss()
            viewModel.showConfirmationDialog(false)
        }

        yesButton.setOnClickListener {
            viewModel.submit()
        }
    }

    private fun showErrorAlertDialog() {
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        val dialogView: View = layoutInflater.inflate(R.layout.error_dialog, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.showFailDialog(false)
            alertDialog.dismiss()
        }, 2000)
    }

    private fun showSuccessAlertDialog() {
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        val dialogView: View = layoutInflater.inflate(R.layout.success_dialog, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            alertDialog.dismiss()
            viewModel.showSuccessDialog(false)
            finish()
        }, 2000)
    }

    private fun showErrorSnackBar(view: View, msg: String) {
        Snackbar.make(
            view,
            msg,
            Snackbar.LENGTH_LONG
        ).setBackgroundTint(Color.RED).show()
    }

}
