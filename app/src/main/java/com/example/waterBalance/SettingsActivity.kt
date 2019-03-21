package com.example.waterBalance

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_day.*
import kotlinx.android.synthetic.main.activity_settings.*


class SettingsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val intent = Intent(this, DayActivity::class.java)

        mConfirmParams.setOnClickListener{
            if (!(mFemaleCheck.isChecked && mMaleCheck.isChecked))
                if (mFemaleCheck.isChecked)
                    intent.putExtra("gender", "female")
                else if (mMaleCheck.isChecked)
                    intent.putExtra("gender", "male")

            val temp = mAddWeight!!.text.toString()
            if (!TextUtils.isEmpty(temp)) {
                intent.putExtra("weight", Integer.parseInt(temp))
            }
            // надо ли как-то выводить старые выборы в полях впоследствии? не критично, но надо решить
            setResult(Activity.RESULT_OK, intent)
            finish() // это временно, вообще настройки не должны так закрываться
        }
    }
}
