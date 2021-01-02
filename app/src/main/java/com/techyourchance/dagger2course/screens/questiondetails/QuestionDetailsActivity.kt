package com.techyourchance.dagger2course.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.questions.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import kotlinx.coroutines.*

class QuestionDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_frame)

        if (savedInstanceState == null) {
            // retrieve question ID passed from outside
            val questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!
            supportFragmentManager.beginTransaction()
                    .add(R.id.frame_content, QuestionDetailsFragment.newInstance(questionId))
                    .commit()
        }
    }

    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }
}