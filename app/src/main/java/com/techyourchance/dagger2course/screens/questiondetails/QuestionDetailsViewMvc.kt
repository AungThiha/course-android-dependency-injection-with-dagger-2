package com.techyourchance.dagger2course.screens.questiondetails

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.questions.QuestionWithBody
import com.techyourchance.dagger2course.screens.common.imageloader.ImageLoader
import com.techyourchance.dagger2course.screens.common.toolbar.MyToolbar
import com.techyourchance.dagger2course.screens.common.viewsmvc.BaseViewMvc

class QuestionDetailsViewMvc(
        private val imageLoader: ImageLoader,
        layoutInflater: LayoutInflater,
        parent: ViewGroup?
): BaseViewMvc<QuestionDetailsViewMvc.Listener>(layoutInflater, parent, R.layout.layout_question_details) {

    interface Listener {
        fun onBackClicked()
    }

    private val toolbar: MyToolbar = findViewById(R.id.toolbar)
    private val swipeRefresh: SwipeRefreshLayout = findViewById(R.id.swipeRefresh)
    private val imgUser: ImageView = findViewById(R.id.img_user)
    private val txtUser: TextView = findViewById(R.id.txt_user_name)
    private val txtQuestionBody: TextView = findViewById(R.id.txt_question_body)

    init {

        // init toolbar
        toolbar.setNavigateUpListener {
            listeners.forEach { it.onBackClicked() }
        }

        // init pull-down-to-refresh (used as a progress indicator)
        swipeRefresh.isEnabled = false
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        swipeRefresh.isRefreshing = false
    }

    fun bindQuestionWithBody(question: QuestionWithBody) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtQuestionBody.text = Html.fromHtml(question.body, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            txtQuestionBody.text = Html.fromHtml(question.body)
        }
        imageLoader.loadImage(question.owner.imageUrl, imgUser)
        txtUser.text = question.owner.name
    }

}